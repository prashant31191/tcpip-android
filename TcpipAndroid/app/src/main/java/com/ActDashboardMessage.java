package com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.clientserverchat.ActDashboatdClientServerChat;
import com.demosynch.EntryListActivity;
import com.messages.ActAllMessageBox;
import com.messages.ActConvoMessageBox;
import com.messages.ActMessages;
import com.messages.ActMyMessages;
import com.synchdata.ActSyncData;
import com.tcpipandroid.DashboardActivity;
import com.tcpipandroid.R;

/**
 * Created by prashant.patel on 12/20/2017.
 */

public class ActDashboardMessage extends AppCompatActivity
{
    Button btnTcpClientServerChat;
    Button btnTcpClientServerChat2;

    Button btnAllMsgBox;
    Button btnConvoMsgBox;
    Button btnMessages;
    Button btnMyMessages;

    Button btnSyncAdapter;
    Button btnSyncAdapterMy;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_dashboard_message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnTcpClientServerChat = (Button) findViewById(R.id.btnTcpClientServerChat);
        btnTcpClientServerChat2 = (Button) findViewById(R.id.btnTcpClientServerChat2);

        btnAllMsgBox = (Button) findViewById(R.id.btnAllMsgBox);
        btnConvoMsgBox = (Button) findViewById(R.id.btnConvoMsgBox);
        btnMessages = (Button) findViewById(R.id.btnMessages);
        btnMyMessages = (Button) findViewById(R.id.btnMyMessages);

        btnSyncAdapter = (Button) findViewById(R.id.btnSyncAdapter);
        btnSyncAdapterMy = (Button) findViewById(R.id.btnSyncAdapterMy);

        setclickEvent();
    }

    private void setclickEvent() {
        try{
            btnTcpClientServerChat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startAct(ActDashboatdClientServerChat.class);
                }
            });
            btnTcpClientServerChat2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startAct(DashboardActivity.class);
                }
            });


            btnAllMsgBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startAct(ActAllMessageBox.class);
                }
            });
            btnConvoMsgBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startAct(ActConvoMessageBox.class);
                }
            });
            btnMessages.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startAct(ActMessages.class);
                }
            });
            btnMyMessages.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startAct(ActMyMessages.class);
                }
            });


            btnSyncAdapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startAct(EntryListActivity.class);
                }
            });
            btnSyncAdapterMy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startAct(ActSyncData.class);
                }
            });


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    private void startAct(Class classSatrt)
    {
        Intent intent = new Intent(ActDashboardMessage.this,classSatrt);

        startActivity(intent);
    }
}
