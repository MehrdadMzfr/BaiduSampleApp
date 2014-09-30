package com.example.baidupush;

import java.util.List;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.baidu.frontia.api.FrontiaPushMessageReceiver;
import com.microsoft.windowsazure.messaging.NotificationHub;
import com.microsoft.windowsazure.messaging.Registration;

public class MyPushMessageReceiver extends FrontiaPushMessageReceiver {
	/** TAG to Log */
	public static final String TAG = MyPushMessageReceiver.class
			.getSimpleName();

	private String mUserId;
	private String mChannelId;

	/** Register Push */
	private void registerWithNotificationHubs() {
		new AsyncTask<Void, Void, Void>() {
			@Override
			protected Void doInBackground(Void... arg0) {
				try {
					NotificationHub hub = MainActivity.getNotificationHub();
					hub.registerBaidu(mUserId, mChannelId);
					//hub.unregister();

					String[] tags = new String[] {};

					String templateName = "baidu";
					String template = "{\"title\":\"$(title)\",\"description\":\"$(description)\"}";
					//hub.registerBaiduTemplate(mUserId, mChannelId, templateName,template, tags);
					//hub.unregisterTemplate(templateName);
				} catch (Exception e) {
					Log.e("BAIDU", "exception: " + e.getMessage());
					Log.e("BAIDU", "exception: " + e.toString());
				}
				return null;
			}
		}.execute(null, null, null);
	}

	@Override
	public void onBind(Context context, int errorCode, String appid,
			String userId, String channelId, String requestId) {
		String responseString = "onBind errorCode=" + errorCode + " appid="
				+ appid + " userId=" + userId + " channelId=" + channelId
				+ " requestId=" + requestId;
		Log.d(TAG, responseString);
		mUserId = userId;
		mChannelId = channelId;

		// Register with notification hub.
		registerWithNotificationHubs();
	}

	@Override
	public void onSetTags(Context context, int errorCode,
			List<String> sucessTags, List<String> failTags, String requestId) {
		String responseString = "onSetTags errorCode=" + errorCode
				+ " sucessTags=" + sucessTags + " failTags=" + failTags
				+ " requestId=" + requestId;
		Log.d(TAG, responseString);
	}

	@Override
	public void onDelTags(Context context, int errorCode,
			List<String> sucessTags, List<String> failTags, String requestId) {
		String responseString = "onDelTags errorCode=" + errorCode
				+ " sucessTags=" + sucessTags + " failTags=" + failTags
				+ " requestId=" + requestId;
		Log.d(TAG, responseString);
	}

	@Override
	public void onListTags(Context context, int errorCode, List<String> tags,
			String requestId) {
		String responseString = "onListTags errorCode=" + errorCode + " tags="
				+ tags;
		Log.d(TAG, responseString);
	}

	@Override
	public void onUnbind(Context context, int errorCode, String requestId) {
		String responseString = "onUnbind errorCode=" + errorCode
				+ " requestId = " + requestId;
		Log.d(TAG, responseString);
	}

	@Override
	public void onNotificationClicked(Context context, String title,
			String description, String customContentString) {
		String notifyString = "title=\"" + title + "\" description=\""
				+ description + "\" customContent=" + customContentString;
		Log.d(TAG, notifyString);
	}

	@Override
	public void onMessage(Context context, String message,
			String customContentString) {
		String messageString = "message=\"" + message
				+ "\" customContentString=" + customContentString;
		Log.d(TAG, messageString);
		// Azure Notification Hubs does not currently support sending messages
	}
}
