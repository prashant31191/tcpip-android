package com.quickblox.sample.user.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.sample.chat.R;
import com.quickblox.sample.chat.ui.activity.DialogsActivity;
import com.quickblox.sample.chat.ui.activity.SplashActivity;
import com.quickblox.sample.chat.utils.SharedPreferencesUtil;
import com.quickblox.sample.chat.utils.chat.ChatHelper;
import com.quickblox.sample.core.ui.dialog.ProgressDialogFragment;
import com.quickblox.sample.core.utils.ErrorUtils;
import com.quickblox.sample.core.utils.Toaster;

import com.quickblox.sample.user.activities.helper.DataHolder;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

public class SignInActivity extends BaseActivity {

    private EditText loginEditText;
    private EditText passwordEditText;

    private Button btnLogin,btnSignup;

    public static void start(Context context) {
        Intent intent = new Intent(context, SignInActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.activity_sign_in);
        initUI();
        setClickEvents();

    }

    private void setClickEvents() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intSignup = new Intent(SignInActivity.this,SignUpUserActivity.class);
                startActivity(intSignup);
            }
        });
    }


    @Override
    protected void initUI() {
        actionBar.setDisplayHomeAsUpEnabled(true);

        loginEditText = _findViewById(R.id.login_in_edittext);
        passwordEditText = _findViewById(R.id.password_in_edittext);
        btnLogin = _findViewById(R.id.btnLogin);
        btnSignup = _findViewById(R.id.btnSignup);
    }

    public void signIn() {

        progressDialog.show();

        QBUser qbUser = new QBUser(loginEditText.getText().toString(), passwordEditText.getText().toString());
        QBUsers.signIn(qbUser).performAsync(new QBEntityCallback<QBUser>() {
            @Override
            public void onSuccess(QBUser qbUser, Bundle bundle) {
                progressDialog.dismiss();


                setResult(RESULT_OK);

                DataHolder.getInstance().setSignInQbUser(qbUser);
                Toaster.longToast(R.string.user_successfully_sign_in);
                qbUser.setPassword(passwordEditText.getText().toString());

                login(qbUser);


                /*setResult(RESULT_OK);

                DataHolder.getInstance().setSignInQbUser(qbUser);
                Toaster.longToast(R.string.user_successfully_sign_in);

                finish();*/
            }

            @Override
            public void onError(QBResponseException errors) {
                progressDialog.dismiss();
                View rootLayout = findViewById(R.id.activity_sign_in);
                showSnackbarError(rootLayout, R.string.errors, errors, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        signIn();
                    }
                });
            }
        });
    }




    private void login(final QBUser user) {
        ProgressDialogFragment.show(getSupportFragmentManager(), R.string.dlg_login);
        ChatHelper.getInstance().login(user, new QBEntityCallback<Void>() {
            @Override
            public void onSuccess(Void result, Bundle bundle) {
                SharedPreferencesUtil.saveQbUser(user);

                DialogsActivity.start(SignInActivity.this);
                // finish();

                ProgressDialogFragment.hide(getSupportFragmentManager());
            }

            @Override
            public void onError(QBResponseException e) {
                ProgressDialogFragment.hide(getSupportFragmentManager());
                ErrorUtils.showSnackbar(loginEditText, R.string.login_chat_login_error, e,
                        R.string.dlg_retry, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                login(user);
                            }
                        });
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_sign_in_up, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_sign_in_up:
                signIn();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}