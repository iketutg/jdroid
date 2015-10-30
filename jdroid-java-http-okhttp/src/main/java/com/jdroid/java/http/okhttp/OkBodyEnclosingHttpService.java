package com.jdroid.java.http.okhttp;

import com.jdroid.java.http.HttpServiceProcessor;
import com.jdroid.java.http.MimeType;
import com.jdroid.java.http.Server;
import com.jdroid.java.http.post.BodyEnclosingHttpService;
import com.jdroid.java.utils.LoggerUtils;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import org.slf4j.Logger;

import java.util.List;

public abstract class OkBodyEnclosingHttpService extends OkHttpService implements BodyEnclosingHttpService {

	protected static final Logger LOGGER = LoggerUtils.getLogger(OkBodyEnclosingHttpService.class);

	private String body;

	public OkBodyEnclosingHttpService(Server server, List<Object> urlSegments, List<HttpServiceProcessor> httpServiceProcessors) {
		super(server, urlSegments, httpServiceProcessors);
	}

	@Override
	protected void onConfigureRequestBuilder(Request.Builder builder) {

		RequestBody requestBody = null;
		if (body != null) {
			requestBody = RequestBody.create(MediaType.parse(MimeType.JSON), body);
		}
		onConfigureRequestBuilder(builder, requestBody);
	}

	protected abstract void onConfigureRequestBuilder(Request.Builder builder, RequestBody requestBody);

	@Override
	public void setBody(String body) {
		LOGGER.debug("Body: " + body);
		this.body = body;
	}
}
