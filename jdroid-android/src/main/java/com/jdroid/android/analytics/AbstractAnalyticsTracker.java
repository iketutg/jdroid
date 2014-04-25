package com.jdroid.android.analytics;

import java.util.Map;
import android.app.Activity;
import com.jdroid.android.inappbilling.Product;
import com.jdroid.android.social.AccountType;
import com.jdroid.android.social.SocialAction;
import com.jdroid.java.exception.ConnectionException;

/**
 * 
 * @author Maxi Rosson
 */
public abstract class AbstractAnalyticsTracker implements AnalyticsTracker {
	
	/**
	 * @see com.jdroid.android.analytics.AnalyticsTracker#onInitExceptionHandler(java.util.Map)
	 */
	@Override
	public void onInitExceptionHandler(Map<String, String> metadata) {
		// Do Nothing
	}
	
	/**
	 * @see com.jdroid.android.analytics.AnalyticsTracker#onActivityStart(android.app.Activity,
	 *      com.jdroid.android.analytics.AppLoadingSource, java.lang.Object)
	 */
	@Override
	public void onActivityStart(Activity activity, AppLoadingSource appLoadingSource, Object data) {
		// Do Nothing
	}
	
	/**
	 * @see com.jdroid.android.analytics.AnalyticsTracker#onActivityStop(android.app.Activity)
	 */
	@Override
	public void onActivityStop(Activity activity) {
		// Do Nothing
	}
	
	/**
	 * @see com.jdroid.android.analytics.AnalyticsTracker#trackConnectionException(com.jdroid.java.exception.ConnectionException)
	 */
	@Override
	public void trackConnectionException(ConnectionException connectionException) {
		// Do Nothing
	}
	
	/**
	 * @see com.jdroid.android.analytics.AnalyticsTracker#trackHandledException(java.lang.Throwable)
	 */
	@Override
	public void trackHandledException(Throwable throwable) {
		// Do Nothing
	}
	
	/**
	 * @see com.jdroid.android.analytics.AnalyticsTracker#trackUriHandled(java.lang.Boolean, java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public void trackUriHandled(Boolean handled, String validUri, String invalidUri) {
		// Do Nothing
	}
	
	/**
	 * @see com.jdroid.android.analytics.AnalyticsTracker#trackInAppBillingPurchase(com.jdroid.android.inappbilling.Product)
	 */
	@Override
	public void trackInAppBillingPurchase(Product product) {
		// Do Nothing
	}
	
	/**
	 * @see com.jdroid.android.analytics.AnalyticsTracker#trackSocialInteraction(com.jdroid.android.social.AccountType,
	 *      com.jdroid.android.social.SocialAction, java.lang.String)
	 */
	@Override
	public void trackSocialInteraction(AccountType accountType, SocialAction socialAction, String socialTarget) {
		// Do Nothing
	}
	
}