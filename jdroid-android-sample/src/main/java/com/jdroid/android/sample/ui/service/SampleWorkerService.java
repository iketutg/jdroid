package com.jdroid.android.sample.ui.service;

import android.content.Intent;

import com.jdroid.android.application.AbstractApplication;
import com.jdroid.android.notification.NotificationBuilder;
import com.jdroid.android.notification.NotificationUtils;
import com.jdroid.android.service.WorkerService;
import com.jdroid.java.exception.UnexpectedException;
import com.jdroid.java.utils.IdGenerator;

public class SampleWorkerService extends WorkerService {

	@Override
	protected void doExecute(Intent intent) {

		Boolean fail = intent.getExtras().getBoolean("fail");

		if (fail) {
			throw new UnexpectedException("Failing service");
		} else {
			NotificationBuilder builder = new NotificationBuilder("myNotification");
			builder.setSmallIcon(AbstractApplication.get().getNotificationIconResId());
			builder.setTicker("Sample Ticker");
			builder.setContentTitle(getClass().getSimpleName());
			builder.setContentText(intent.getExtras().get("a").toString());

			NotificationUtils.sendNotification(IdGenerator.getIntId(), builder);
		}
	}

	public static void runIntentInService(Intent intent) {
		WorkerService.runIntentInService(AbstractApplication.get(), intent, SampleWorkerService.class);
	}
}
