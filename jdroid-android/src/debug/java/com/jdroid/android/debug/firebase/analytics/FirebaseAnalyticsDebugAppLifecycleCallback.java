package com.jdroid.android.debug.firebase.analytics;

import android.content.Context;
import android.support.v4.util.Pair;

import com.jdroid.android.application.lifecycle.ApplicationLifecycleCallback;
import com.jdroid.android.debug.info.DebugInfoAppender;
import com.jdroid.android.debug.info.DebugInfoHelper;
import com.jdroid.android.firebase.analytics.FirebaseAnalyticsAppContext;
import com.jdroid.java.collections.Lists;

import java.util.List;

public class FirebaseAnalyticsDebugAppLifecycleCallback extends ApplicationLifecycleCallback {
	
	@Override
	public void onCreate(Context context) {
		DebugInfoHelper.addDebugInfoAppender(new DebugInfoAppender() {
			@Override
			public List<Pair<String, Object>> getDebugInfoProperties() {
				List<Pair<String, Object>> properties = Lists.newArrayList();
				properties.add(new Pair<String, Object>("Firebase Analytics Enabled", FirebaseAnalyticsAppContext.isFirebaseAnalyticsEnabled()));
				return properties;
			}
		});
	}
}
