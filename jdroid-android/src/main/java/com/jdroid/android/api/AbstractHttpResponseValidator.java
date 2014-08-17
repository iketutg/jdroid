package com.jdroid.android.api;

import org.slf4j.Logger;
import com.jdroid.android.exception.CommonErrorCode;
import com.jdroid.android.exception.InvalidApiVersionException;
import com.jdroid.android.exception.InvalidUserTokenException;
import com.jdroid.java.exception.ConnectionException;
import com.jdroid.java.exception.ErrorCode;
import com.jdroid.java.http.HttpResponseWrapper;
import com.jdroid.java.http.HttpWebServiceProcessor;
import com.jdroid.java.http.WebService;
import com.jdroid.java.utils.LoggerUtils;

public abstract class AbstractHttpResponseValidator implements HttpWebServiceProcessor {
	
	private final static Logger LOGGER = LoggerUtils.getLogger(AbstractHttpResponseValidator.class);
	
	private static final String STATUS_CODE_HEADER = "status-code";
	private static final String SUCCESSFULL_STATUS_CODE = "200";
	
	/**
	 * @see com.jdroid.java.http.HttpWebServiceProcessor#beforeExecute(com.jdroid.java.http.WebService)
	 */
	@Override
	public void beforeExecute(WebService webService) {
		// Do Nothing
		
	}
	
	/**
	 * @see com.jdroid.java.http.HttpWebServiceProcessor#afterExecute(com.jdroid.java.http.WebService,
	 *      com.jdroid.java.http.HttpResponseWrapper)
	 */
	@Override
	public void afterExecute(WebService webService, HttpResponseWrapper httpResponse) {
		// validate response.
		this.validateResponse(httpResponse);
	}
	
	/**
	 * Validate the response generated by the server.
	 * 
	 * @param httpResponse
	 */
	protected void validateResponse(HttpResponseWrapper httpResponse) {
		
		String message = httpResponse.logStatusCode();
		if (httpResponse.isSuccess()) {
			ErrorCode errorCode = getErrorCode(httpResponse, CommonErrorCode.SERVER_ERROR);
			if (errorCode != null) {
				throw errorCode.newBusinessException();
			}
			
		} else if (httpResponse.isClientError()) {
			ErrorCode errorCode = getErrorCode(httpResponse, CommonErrorCode.INTERNAL_ERROR);
			if (CommonErrorCode.INVALID_API_VERSION.equals(errorCode)) {
				throw new InvalidApiVersionException();
			} else if (CommonErrorCode.INVALID_USER_TOKEN.equals(errorCode)) {
				throw new InvalidUserTokenException();
			} else if (CommonErrorCode.INVALID_CREDENTIALS.equals(errorCode)) {
				throw CommonErrorCode.INVALID_CREDENTIALS.newBusinessException();
			}
		} else if (httpResponse.isServerError()) {
			// 504 - Gateway Timeout
			if (httpResponse.getStatusCode() == 504) {
				throw new ConnectionException(message);
			} else {
				throw CommonErrorCode.SERVER_ERROR.newApplicationException(message);
			}
		}
	}
	
	private ErrorCode getErrorCode(HttpResponseWrapper httpResponse, ErrorCode defaultErrorCode) {
		ErrorCode errorCode = null;
		String statusCode = httpResponse.getHeader(STATUS_CODE_HEADER);
		if (statusCode != null) {
			LOGGER.debug("Server Status code: " + statusCode);
			if (!statusCode.equals(SUCCESSFULL_STATUS_CODE)) {
				errorCode = findByStatusCode(statusCode);
				if (errorCode == null) {
					errorCode = CommonErrorCode.findByStatusCode(statusCode);
					if (errorCode == null) {
						LOGGER.warn("Unknown Server Status code: " + statusCode);
						throw defaultErrorCode.newApplicationException("Unknown Server Status code: " + statusCode);
					}
				}
			}
		} else {
			throw CommonErrorCode.SERVER_ERROR.newApplicationException("Missing " + STATUS_CODE_HEADER + " header.");
		}
		return errorCode;
	}
	
	protected abstract ErrorCode findByStatusCode(String statusCode);
}