package com.quickblox.sample.chat.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.quickblox.sample.chat.R;
import com.quickblox.sample.chat.gcm.MessageNotificationHandler;
import com.quickblox.sample.chat.utils.Logs;
import com.quickblox.sample.core.CoreApp;
import com.quickblox.sample.core.utils.constant.GcmConsts;

/**
 * Created by multidots on 6/23/2016.
 */
public class DirectReplayActivity extends ActionBarActivity {

    private static final int REQUEST_DIALOG_ID_FOR_UPDATE = 165;

private String strMessageText = "";

    EditText edit_chat_message;
    Button btn_chat_send;
    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replay_dailog);


        try {
            edit_chat_message = (EditText) findViewById(R.id.edit_chat_message);
            btn_chat_send = (Button) findViewById(R.id.btn_chat_send);


            CharSequence replayMessage = getMessageText(getIntent());


            if (replayMessage != null) {

                Log.d("your message", replayMessage + "");

                strMessageText = replayMessage.toString();
                Log.d("=======notification id or other user id===", getNotificationId(getIntent()) + "");
                MessageNotificationHandler.getInstance(this).dismissDirectReplayNotification(getNotificationId(getIntent()));

                //111 -- Add notifify   MessageNotificationHandler.getInstance(this).addNewReplay(replayMessage.toString());
            } else {
                // MessageNotificationHandler.getInstance(this).dismissDirectReplayNotification(getNotificationId(getIntent()));
                MessageNotificationHandler.getInstance(this).dismissDirectReplayNotification();
            }

            CoreApp.showLog("=======notification id or other user id===" + getNotificationId(getIntent()) + "");
            MessageNotificationHandler.getInstance(this).dismissDirectReplayNotification(getNotificationId(getIntent()));



          /* if (android.os.Build.VERSION.SDK_INT >= 24) {
                finish();
            } else {
                CoreApp.showLog("=======notification id or other getNotificationDialogId ===" + getNotificationDialogId(getIntent()) + "");
                ChatActivity.startForResult(DirectReplayActivity.this, REQUEST_DIALOG_ID_FOR_UPDATE, getNotificationDialogId(getIntent()));
            }*/


            if (android.os.Build.VERSION.SDK_INT >= 24)
            {
                sendMessageDirect(true);
            }
            else
            {
                sendMessageDirect(false);
               /* btn_chat_send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        strMessageText = edit_chat_message.getText().toString().trim();
                        if(strMessageText !=null && strMessageText.length() > 0)
                        {
                            sendMessageDirect(false);
                        }
                        else
                        {
                            Snackbar.make(v,"Please type your message",Snackbar.LENGTH_SHORT).show();
                        }
                    }
                });*/
            }




        } catch (Exception e) {
            e.printStackTrace();
            Logs.showLog("========Exception on replay click====");
        }

    }

    private void sendMessageDirect(final boolean isAndroidN)
    {
        try {
            Handler mainThreadHandler = new Handler(Looper.getMainLooper());
            DialogsActivity.start(this);
            mainThreadHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    CoreApp.showLog("=======notification id or other getNotificationDialogId ===" + getNotificationDialogId(getIntent()) + "");
                    //ChatActivity.startForResult(DirectReplayActivity.this, REQUEST_DIALOG_ID_FOR_UPDATE, getNotificationDialogId(getIntent()));

                    if (strMessageText != null && strMessageText.length() > 0) {

                    } else {
                        strMessageText = "hi...";
                    }
                    if(isAndroidN == true)
                    {
                        ChatActivity.startForResult(DirectReplayActivity.this, REQUEST_DIALOG_ID_FOR_UPDATE, getNotificationDialogId(getIntent()), strMessageText);
                    }
                    else
                    {
                      //  ChatActivity.startForResult(DirectReplayActivity.this, REQUEST_DIALOG_ID_FOR_UPDATE, "581d9d71a0eb47b7de000039");
                        Logs.showLog("==getNotificationDialogId======="+getNotificationDialogId(getIntent()));
                        ChatActivity.startForResult(DirectReplayActivity.this, REQUEST_DIALOG_ID_FOR_UPDATE, getNotificationDialogId(getIntent()));
                    }
                }
            }, 10000);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    private CharSequence getMessageText(Intent intent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput != null) {
            return remoteInput.getCharSequence(MessageNotificationHandler.KEY_TEXT_REPLY);
        }
        return null;
    }

    private int getNotificationId(Intent intent) {
        Bundle remoteInput = intent.getExtras();
        if (remoteInput != null) {
            return remoteInput.getInt(GcmConsts.EXTRA_GCM_USER_ID);
        }
        return 0;
    }

    private String getNotificationDialogId(Intent intent) {
        Bundle remoteInput = intent.getExtras();
        if (remoteInput != null) {
            return remoteInput.getString(GcmConsts.EXTRA_GCM_DIALOG_ID);
        }
        return "123";
    }



}
