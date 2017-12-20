package com.quickblox.sample.chat.ui.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.quickblox.chat.QBChatService;
import com.quickblox.chat.QBGroupChat;
import com.quickblox.chat.QBGroupChatManager;
import com.quickblox.chat.exception.QBChatException;
import com.quickblox.chat.listeners.QBMessageListener;
import com.quickblox.chat.model.QBChatDialog;
import com.quickblox.chat.model.QBChatMessage;
import com.quickblox.chat.model.QBDialogType;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.QBEntityCallbackImpl;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.core.request.QBRequestGetBuilder;
import com.quickblox.core.request.QBRequestUpdateBuilder;
import com.quickblox.sample.chat.App;
import com.quickblox.sample.chat.R;
import com.quickblox.sample.chat.utils.Logs;
import com.quickblox.sample.chat.utils.chat.ChatHelper;
import com.quickblox.sample.chat.utils.qb.QbDialogHolder;
import com.quickblox.sample.chat.utils.qb.callback.QbEntityCallbackWrapper;
import com.quickblox.users.model.QBUser;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.muc.DiscussionHistory;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 12/8/2016.
 */

public class ActGroupDemo extends BaseActivity {

    @BindView(R.id.btnCreateGroup)
    Button btnCreateGroup;

    @BindView(R.id.btnAddGroup)
    Button btnAddGroup;

    @BindView(R.id.btnJoinGroup)
    Button btnJoinGroup;

    @BindView(R.id.btnListGroup)
    Button btnListGroup;

    private static final int REQUEST_DIALOG_ID_FOR_UPDATE = 165;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_group_demo);
        ButterKnife.bind(this);


    }


    public void onCreateGroupClick(View view) {
        try {

            // run with public group
            QBChatDialog dialog = new QBChatDialog();
            dialog.setName("PG-1212");
            dialog.setType(QBDialogType.PUBLIC_GROUP);

            QBGroupChatManager groupChatManager = QBChatService.getInstance().getGroupChatManager();
            groupChatManager.createDialog(dialog, new QBEntityCallbackImpl<QBChatDialog>() {
                @Override
                public void onSuccess(QBChatDialog dialog, Bundle args) {
                    Log.i("==onSucess=", "==QBChatDialog=dialog: " + dialog);


                }

                @Override
                public void onError(QBResponseException errors) {
                    Log.i("==onError=", "==QBChatDialog=dialog: ");
                }
            });





            // run with simple group

           /* ArrayList<Integer> occupantIdsList = new ArrayList<Integer>();
            occupantIdsList.add(21588251);
            occupantIdsList.add(21588363);

            QBChatDialog dialog = new QBChatDialog();
            dialog.setName("PG-1212");
            dialog.setPhoto("9122016");
            dialog.setType(QBDialogType.GROUP);
            dialog.setOccupantsIds(occupantIdsList);


            App.showLog("=======createDialog========");

            QBGroupChatManager groupChatManager = QBChatService.getInstance().getGroupChatManager();
            groupChatManager.createDialog(dialog, new QBEntityCallback<QBChatDialog>() {
                @Override
                public void onSuccess(QBChatDialog dialog, Bundle args) {
                    App.showLog("=======onSuccess===createDialog group=====");
                    Logs.showLog("=======notification id or other existingPrivateDialog id ===" + dialog.getDialogId() + "");
                    App.showToast(ActGroupDemo.this,"=======onSuccess===createDialog group=====");

                    ChatActivity.startForResult(ActGroupDemo.this, REQUEST_DIALOG_ID_FOR_UPDATE, dialog.getDialogId());
                }

                @Override
                public void onError(QBResponseException errors) {
                    App.showLog("=======onError===createDialog group=====");
                    App.showToast(ActGroupDemo.this,"=======onError===createDialog group=====");
                    errors.printStackTrace();
                }
            });*/




        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onAddGroupClick(View view) {
        try {

            getMessagesList("asd");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onJoinGroupClick(View view) {
        try {

/*
            {
              //  final QBUser user = new QBUser("Javck", "javckpassword"); user.setExternalId("45345");;
                QBChatDialog dialogToUpdate = new QBChatDialog();
                // 584959e2a28f9aa291000106 dialogToUpdate.setDialogId("dialog_id");
                dialogToUpdate.setDialogId("584e553da0eb47ed1500001a");


                QBRequestUpdateBuilder requestBuilder = new QBRequestUpdateBuilder();
                //requestBuilder.push("occupants_ids", 21588145);

                QBChatService.getInstance().getGroupChatManager().updateDialog(dialogToUpdate, requestBuilder, new QBEntityCallbackImpl() {
                    @Override
                    public void onSuccess(Object result, Bundle params) {
                        App.showToast(ActGroupDemo.this,"=======onSuccess===getGroupChatManager().updateDialog===onJoinGroupClick=");
                        ChatActivity.startForResult(ActGroupDemo.this, REQUEST_DIALOG_ID_FOR_UPDATE, "584e553da0eb47ed1500001a");
                        super.onSuccess(result, params);
                    }

                    @Override
                    public void onError(QBResponseException responseException) {
                        App.showToast(ActGroupDemo.this,"=======onError===getGroupChatManager().updateDialog====onJoinGroupClick=");
                        super.onError(responseException);
                    }
                });
            }*/


            {


              /*  DiscussionHistory history = new DiscussionHistory();
                history.setMaxStanzas(0);
                QBChatDialog dialogToUpdate = new QBChatDialog();
                dialogToUpdate.setDialogId("dialog_id");*/


               /* QBChatDialog dialogToUpdate = new QBChatDialog();
                dialogToUpdate.setDialogId("dialog_id");
                dialogToUpdate = dialogToUpdate.createGroupChat(dialogToUpdate.getRoomJid());

                dialogToUpdate.join(history, new QBEntityCallbackImpl() {
                    @Override
                    public void onSuccess() {
                        log("join Room success");

                        // add listeners
                        currentChatRoom.addMessageListener(groupChatQBMessageListener);
                    }

                    @Override
                    public void onError(final List list) {
                        log("join Room error: " + list);
                    }
                });*/






              final  QBMessageListener<QBGroupChat> groupChatQBMessageListener = new QBMessageListener<QBGroupChat>() {
                    @Override
                    public void processMessage(final QBGroupChat groupChat, final QBChatMessage chatMessage) {
                        App.showLog("=======processMessage===groupChatQBMessageListener group=====");
                    }

                    @Override
                    public void processError(final QBGroupChat groupChat, QBChatException error, QBChatMessage originMessage){
                        App.showLog("=======processError===groupChatQBMessageListener group=====");
                    }
                };





                QBChatDialog dialog = new QBChatDialog();
                //dialog.setName("PG-1212");
                dialog.setType(QBDialogType.PUBLIC_GROUP);

                dialog.setDialogId("584e717ba0eb47d6e6000027");


                QBGroupChatManager groupChatManager = QBChatService.getInstance().getGroupChatManager();


                DiscussionHistory history = new DiscussionHistory();
                history.setMaxStanzas(0);

              final  QBGroupChat   currentChatRoom = groupChatManager.createGroupChat(dialog.getRoomJid());

                currentChatRoom.join(history, new QBEntityCallbackImpl() {

                    @Override
                    public void onSuccess(Object result, Bundle params) {
                        App.showLog("=======onSuccess===join group=====");

                        // add listeners
                        currentChatRoom.addMessageListener(groupChatQBMessageListener);

                        try{
                            QBChatMessage chatMessage = new QBChatMessage();
                            chatMessage.setBody("Hi there 44444");
                            chatMessage.setProperty("save_to_history", "1"); // Save to Chat 2.0 history

                            try {
                                currentChatRoom.sendMessage(chatMessage);


                            }  catch (SmackException.NotConnectedException e) {
                            e.printStackTrace();
                        }
                        } catch (Exception e){
                            e.printStackTrace();
                        }


                        super.onSuccess(result, params);
                    }

                    @Override
                    public void onError(QBResponseException responseException) {
                        App.showLog("=======onError===join group=====");
                        super.onError(responseException);
                    }


                   /* @Override
                    public void onSuccess() {
                      //  log("join Room success");

                        // add listeners
                        dialog.setDialogId("584e717ba0eb47d6e6000027");
                        dialog.setRoomJid("47911_584e717ba0eb47d6e6000027@muc.chat.quickblox.com");

                      //  currentChatRoom.addMessageListener(groupChatQBMessageListener);
                    }

                    @Override
                    public void onError(final List list) {
                       // log("join Room error: " + list);
                    }*/
                });


/*
aaaa-------------
                QBChatDialog chatDialog = new QBChatDialog();
                chatDialog.setType(QBDialogType.GROUP);
                chatDialog.setDialogId("584e4fc6a28f9a535b00002f");

                DiscussionHistory discussionHistory = new DiscussionHistory();
                discussionHistory.setMaxStanzas(0);

                chatDialog.join(discussionHistory, new QBEntityCallback() {
                    @Override
                    public void onSuccess(Object o, Bundle bundle) {
                        App.showLog("=join====onSuccess=");
                        //App.showToast(ActGroupDemo.this,"=======onSuccess===getGroupChatManager().updateDialog===onJoinGroupClick=");
                    }

                    @Override
                    public void onError(QBResponseException e) {
                        App.showLog("=join====onError=");
                       // App.showToast(ActGroupDemo.this,"=======onError===getGroupChatManager().updateDialog===onJoinGroupClick=");
                        e.printStackTrace();
                    }
                });*/



            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getMessagesList(String strId) {
    try
    {
        QBChatDialog dialog = new QBChatDialog();
        //dialog.setName("PG-1212");
        dialog.setType(QBDialogType.PUBLIC_GROUP);
        dialog.setRoomJid("47911_584e717ba0eb47d6e6000027@muc.chat.quickblox.com");
        dialog.setDialogId("584e717ba0eb47d6e6000027");
        //QBDialog qbDialog = new QBDialog("584e717ba0eb47d6e6000027");

        QBRequestGetBuilder requestBuilder = new QBRequestGetBuilder();
        requestBuilder.setLimit(100);

        QBRequestGetBuilder customObjectRequestBuilder = new QBRequestGetBuilder();
        requestBuilder.gt("rating", "5.5");
        requestBuilder.setPagesLimit(5);
        requestBuilder.eq("documentary", "false");
        requestBuilder.sortAsc("rating");



        QBChatService.getDialogMessages(dialog, customObjectRequestBuilder, new QBEntityCallback<ArrayList<QBChatMessage>>() {
            @Override
            public void onSuccess(ArrayList<QBChatMessage> messages, Bundle args) {
                App.showLog("=getDialogMessages====onSuccess=");
                App.showLog("=messages=="+messages.size());
            }

            @Override
            public void onError(QBResponseException errors) {
                App.showLog("=getDialogMessages====onError=");

            }
        });
    }
    catch (Exception e)
    {
        e.printStackTrace();
    }
    }


    public void onListGroupClick(View view) {
        try {

          //111  ChatActivity.startForResult(ActGroupDemo.this, REQUEST_DIALOG_ID_FOR_UPDATE, "584a93dba28f9a19c400004c");
            //58496227a0eb4705a1000098 PUB GROUP
            //584959e2a28f9aa291000106 GROUP

            DiscussionHistory history = new DiscussionHistory();
            history.setMaxStanzas(0);

            QBChatDialog qbChatDialog = new QBChatDialog();
            qbChatDialog = QbDialogHolder.getInstance().getChatDialogById("584e553da0eb47ed1500001a");


            qbChatDialog.join(history, new QBEntityCallback<Void>() {
                @Override
                public void onSuccess(Void result, Bundle b) {
                    App.showLog("=====join=====onSuccess====");
                    App.showToast(ActGroupDemo.this,"=======onSuccess===join group=====");
                }

                @Override
                public void onError(QBResponseException e) {
                    App.showLog("=====join=====onError====");
                    App.showToast(ActGroupDemo.this,"=======onError===join group=====");
                    e.printStackTrace();

                }
            });

           /* DiscussionHistory history = new DiscussionHistory();
            history.setMaxStanzas(0);

            chatDialog.join(history, new QbEntityCallbackWrapper<Void>(callback) {
                @Override
                public void onSuccess(final Void result, final Bundle b) {
                    onSuccessInMainThread(result, b);
                }

                @Override
                public void onError(final QBResponseException e) {
                    onErrorInMainThread(e);
                }
            });*/

            /*ChatHelper.getInstance().join(qbChatDialog, new QBEntityCallback<Void>() {
                @Override
                public void onSuccess(Void result, Bundle b) {
                    App.showLog("=====join=====onSuccess====");

                }

                @Override
                public void onError(QBResponseException e) {
                    App.showLog("=====join=====onError====");
                    e.printStackTrace();

                }
            });*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void start(Context context) {
        Intent intent = new Intent(context, ActGroupDemo.class);
        context.startActivity(intent);
    }

    @Override
    protected View getSnackbarAnchorView() {
        return findViewById(R.id.layout_root);
    }

    @Override
    public void onSessionCreated(boolean success) {
        if (success) {
            QBUser currentUser = ChatHelper.getCurrentUser();
            if (currentUser != null) {
                setActionBarTitle(getString(R.string.dialogs_logged_in_as, currentUser.getFullName()));
            }
        }
    }
}
