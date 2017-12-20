package com.quickblox.sample.chat.gcm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

import com.quickblox.sample.chat.R;
import com.quickblox.sample.chat.ui.activity.SplashActivity;
import com.quickblox.sample.chat.utils.Logs;
import com.quickblox.sample.core.gcm.CoreGcmPushListenerService;
import com.quickblox.sample.core.utils.NotificationUtils;
import com.quickblox.sample.core.utils.ResourceUtils;
import com.quickblox.sample.core.utils.constant.GcmConsts;

public class GcmPushListenerService extends CoreGcmPushListenerService {
    private static final int NOTIFICATION_ID = 1;

    @Override
    protected void showNotification(String message,Bundle data) {
        /*NotificationUtils.showNotification(this, SplashActivity.class,
                ResourceUtils.getString(R.string.notification_title), message,
                R.mipmap.ic_launcher, NOTIFICATION_ID);*/

        int NOTIFICATION_ID_RANDOM = (int) (System.currentTimeMillis() & 0x00000000FFFFFFFFL);
        Logs.showLog("===NOTIFICATION_ID_RANDOM==" + NOTIFICATION_ID_RANDOM);
        Logs.showLog("===message==" + message);
      /*  NotificationUtils.showMyNotification(this, SplashActivity.class,
                ResourceUtils.getString(R.string.notification_title), message,
                R.mipmap.ic_launcher, NOTIFICATION_ID);*/

        try {

            MessageNotificationHandler.getInstance(this).displayDirectReplayDemo(message,data);
        } catch (Exception e) {
            Logs.showLog("==GcmPushListenerService.java==Exception==" + e);
            e.printStackTrace();
        }

    }

    @Override
    protected void sendPushMessageBroadcast(String message,Bundle data) {
        Intent gcmBroadcastIntent = new Intent(GcmConsts.ACTION_NEW_GCM_EVENT);
        gcmBroadcastIntent.putExtra(GcmConsts.EXTRA_GCM_MESSAGE, message);

        LocalBroadcastManager.getInstance(this).sendBroadcast(gcmBroadcastIntent);
    }
}