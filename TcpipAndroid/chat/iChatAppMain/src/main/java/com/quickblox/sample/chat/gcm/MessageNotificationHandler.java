package com.quickblox.sample.chat.gcm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.NotificationCompat;

import com.quickblox.sample.chat.R;
import com.quickblox.sample.chat.ui.activity.DirectReplayActivity;
import com.quickblox.sample.chat.ui.activity.SplashActivity;
import com.quickblox.sample.chat.utils.Logs;
import com.quickblox.sample.core.utils.constant.GcmConsts;

import java.util.ArrayList;

/**
 * Created by multidots on 6/23/2016.
 */
public class MessageNotificationHandler {
    // Where should direct replies be put in the intent bundle (can be any string)
    public static final String KEY_TEXT_REPLY = "key_text_reply";
    public static final int BIG_TEXT_NOTIFICATION_KEY = 0;
    private static NotificationManager notificationManager;
    public static ArrayList<android.support.v4.app.NotificationCompat.MessagingStyle.Message> mMessages;
    private static volatile MessageNotificationHandler sInstance;
    private Context context;

    public MessageNotificationHandler(Context context){
        this.context = context;
        mMessages = new ArrayList<>();
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        //generate fake data
        //111 generateData();
    }

    public static MessageNotificationHandler getInstance(Context context){
        if (sInstance == null)sInstance = new MessageNotificationHandler(context);
        return sInstance;
    }

    private void generateData() {


        //android.support.v4.app.NotificationCompat.MessagingStyle.Message  message = new android.support.v4.app.NotificationCompat.MessagingStyle.Message("iChat", System.currentTimeMillis() - 30000, "UserName");
        android.support.v4.app.NotificationCompat.MessagingStyle.Message  message = new android.support.v4.app.NotificationCompat.MessagingStyle.Message("iChat", System.currentTimeMillis() - 30000, " ");
        mMessages.add(message);


/*
        message = new android.support.v4.app.NotificationCompat.MessagingStyle.Message("iChat", System.currentTimeMillis() - 30000, "UserName");
        mMessages.add(message);

       message = new android.support.v4.app.NotificationCompat.MessagingStyle.Message("Dhosa vada", System.currentTimeMillis() - 30000, "Mirant");
        mMessages.add(message);

        message = new android.support.v4.app.NotificationCompat.MessagingStyle.Message("Marigold 10 vaalu", System.currentTimeMillis() - 30000, "Hiral");
        mMessages.add(message);

        message = new android.support.v4.app.NotificationCompat.MessagingStyle.Message("Sev mamra (Balaji na j)", System.currentTimeMillis() - 30000, "Parasbhai");
        mMessages.add(message);*/
    }

    public void displayDirectReplayDemo(String strMessage,Bundle data) {
        PendingIntent pIntent = PendingIntent.getActivity(context,(int) System.currentTimeMillis(),new Intent(context, SplashActivity.class), 0);

        // Create the RemoteInput specifying this key
        RemoteInput remoteInput = new RemoteInput.Builder(KEY_TEXT_REPLY)
                .setLabel("Type your reply")
                .build();

        String strUserID ="0010";
        String strDailogID ="0010";


        if(data!=null && data.getString(GcmConsts.EXTRA_GCM_USER_ID) !=null && data.getString(GcmConsts.EXTRA_GCM_USER_ID).length() > 0)
        {
            strUserID = data.getString(GcmConsts.EXTRA_GCM_USER_ID);
        }
        if(data!=null && data.getString(GcmConsts.EXTRA_GCM_DIALOG_ID) !=null && data.getString(GcmConsts.EXTRA_GCM_DIALOG_ID).length() > 0)
        {
            strDailogID = data.getString(GcmConsts.EXTRA_GCM_DIALOG_ID);
        }

        //android.support.v4.app.NotificationCompat.MessagingStyle.Message  message = new android.support.v4.app.NotificationCompat.MessagingStyle.Message(""+strMessage, System.currentTimeMillis() - 30000, "Roy will");
        String strFullMessage [] = null;
        if(strMessage !=null && strMessage.contains(":"))
        {
            strFullMessage = strMessage.split(":");
        }
        android.support.v4.app.NotificationCompat.MessagingStyle.Message  message = new android.support.v4.app.NotificationCompat.MessagingStyle.Message(""+strMessage, System.currentTimeMillis() - 30000, "");

        if(strFullMessage !=null && strFullMessage.length > 0 )
        {
            message = new android.support.v4.app.NotificationCompat.MessagingStyle.Message(""+strFullMessage[1], System.currentTimeMillis() - 30000, strFullMessage[0]);
        }


        mMessages.add(message);

        int intUserID = Integer.parseInt(strUserID);

        Logs.showLog("===intUserID==="+intUserID);


        Bundle bundleData = new Bundle();
        bundleData.putInt(GcmConsts.EXTRA_GCM_USER_ID,intUserID);
        bundleData.putString(GcmConsts.EXTRA_GCM_DIALOG_ID,strDailogID);

        Intent intDirectMessage = new Intent(context, DirectReplayActivity.class);
        intDirectMessage.putExtra(GcmConsts.EXTRA_GCM_USER_ID,intUserID);
        intDirectMessage.putExtra(GcmConsts.EXTRA_GCM_DIALOG_ID,strDailogID);


        PendingIntent directReplayIntent = PendingIntent.getActivity(context,(int) System.currentTimeMillis(),intDirectMessage, 0);

        // Add to your action, enabling Direct Reply for it
        NotificationCompat.Action directReplayAction =
                new NotificationCompat.Action.Builder(R.drawable.ic_replay, "Replay", directReplayIntent)
                        .addRemoteInput(remoteInput)
                        .addExtras(bundleData)
                        .setAllowGeneratedReplies(true)
                        .build();

        //prepare message style notification
        android.support.v4.app.NotificationCompat.MessagingStyle style = new NotificationCompat.MessagingStyle("Me").setConversationTitle("iChat Message");


        for (int i = 0; i < mMessages.size(); i++) style.addMessage(mMessages.get(i));

        Notification noti = new NotificationCompat.Builder(context)
                .setContentText("iChat - "+strMessage)
                .setAutoCancel(false)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
                        R.mipmap.ic_launcher))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setStyle(style)
                .setContentIntent(pIntent)
                .addAction(directReplayAction)
                .build();

        // hide the notification after its selected
        noti.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(intUserID, noti);
        //notificationManager.notify(BIG_TEXT_NOTIFICATION_KEY, noti);
    }

    public void addNewReplay(String replay) {
        mMessages.add(new android.support.v4.app.NotificationCompat.MessagingStyle.Message(replay, System.currentTimeMillis(), null));

        //update notification
        displayDirectReplayDemo(replay,null);
    }

    public void dismissDirectReplayNotification() {
        notificationManager.cancel(BIG_TEXT_NOTIFICATION_KEY);
    }

    public void dismissDirectReplayNotification(int notificationId) {
        notificationManager.cancel(notificationId);
    }
}
