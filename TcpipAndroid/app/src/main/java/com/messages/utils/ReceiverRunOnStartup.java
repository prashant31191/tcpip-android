package com.messages.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Prashant
 */
public class ReceiverRunOnStartup extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            /*Intent i = new Intent(context, ActSplashScreen.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);*/
            App.showLog("===ReceiverRunOnStartup====onReceive===");
           // App.startAlarmServices(context);
        }
    }

}
