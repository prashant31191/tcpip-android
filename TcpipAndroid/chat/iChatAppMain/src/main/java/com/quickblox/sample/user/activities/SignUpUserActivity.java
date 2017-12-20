package com.quickblox.sample.user.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;

import com.quickblox.core.helper.StringifyArrayList;
import com.quickblox.sample.chat.R;
import com.quickblox.sample.chat.utils.Consts;
import com.quickblox.sample.user.activities.helper.DataHolder;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

import java.util.Random;

public class SignUpUserActivity extends BaseActivity {


    private EditText passwordEditText;
    private EditText confirmPasswordEditText;

    private EditText loginEditText,full_name_textview,email_textview,phone_textview,tag_textview;
    private Toast toast;

    private Button btnSignup,btnLogin;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initUI();
    }

    @SuppressLint("ShowToast")
    @Override
    protected void initUI() {
        actionBar.setDisplayHomeAsUpEnabled(true);

        toast = Toast.makeText(this, R.string.error, Toast.LENGTH_LONG);
        loginEditText = _findViewById(R.id.login_up_edittext);
        passwordEditText = _findViewById(R.id.password_up_edittext);
        confirmPasswordEditText = _findViewById(R.id.password_confirm_edittext);

        btnSignup  = _findViewById(R.id.btnSignup);
        btnLogin = _findViewById(R.id.btnLogin);

        full_name_textview  = _findViewById(R.id.full_name_textview);
        email_textview  = _findViewById(R.id.email_textview);
        phone_textview  = _findViewById(R.id.phone_textview);
        tag_textview  = _findViewById(R.id.tag_textview);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void signUp() {
        String login = loginEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString();
        String confirmPassword = confirmPasswordEditText.getText().toString();

        String fullname = full_name_textview.getText().toString();
        String email = email_textview.getText().toString();
        String phone = phone_textview.getText().toString();
        String tag = tag_textview.getText().toString();

        if (!isValidData(login, password, confirmPassword,fullname ,email ,phone ,tag)) {
            return;
        }
        progressDialog.show();

        QBUser qbUser = new QBUser();
        qbUser.setLogin(login);
        qbUser.setPassword(password);


        StringifyArrayList<String> tagsArray = new StringifyArrayList<>();
        if(tag.contains(" "))
        {
            String [] arrSpash = tag.split(" ");
            if(arrSpash !=null) {
                for (int iSpash = 0; iSpash < arrSpash.length; iSpash++) {
                    tagsArray.add(arrSpash[iSpash]);
                }
            }
        }

        if(tag.contains(","))
        {
            String [] arrSpash = tag.split(",");
            if(arrSpash !=null) {
                for (int iSpash = 0; iSpash < arrSpash.length; iSpash++) {
                    tagsArray.add(arrSpash[iSpash]);
                }
            }
        }
        tagsArray.add(tag);
        tagsArray.add(Consts.QB_USERS_TAG);
        tagsArray.add(Consts.QB_USERS_TAG2);


        qbUser.setFullName(fullname);
        qbUser.setEmail(email);
        qbUser.setPhone(phone);
        qbUser.setTags(tagsArray);




String arrStringImageUrls [] = {
        "http://hollywoodneuz.us/wp-content/uploads/2013/07/GeorgeClooney.jpg",
        "http://3.bp.blogspot.com/-L6L6ui-ipiE/T--pVeIJFzI/AAAAAAAAHj4/OVf54dqj_cI/s1600/Tom+Cruise.jpg",
        "http://2.bp.blogspot.com/-wXRFjdgPGgQ/ToY19qEv_xI/AAAAAAAAAIY/L5soyJrk24g/s1600/bella-swan-01.jpg",
        "http://4.bp.blogspot.com/-Gy6XRP-19X0/T8ZoULxvkvI/AAAAAAAAAJs/uyNVES-x2OM/s400/2010-kristen-stewart-400.jpg",
        "http://4.bp.blogspot.com/-B3GMqQQEgZY/T4MGEdoc9YI/AAAAAAAAAJs/hlnEYdoQkQo/s1600/sandra+bullock+hot+new+pic+2012+02.jpg",
        "http://2.bp.blogspot.com/-hc63ybjgB-Q/T1cQfg_CiZI/AAAAAAAABKw/FpBCjs5R8N8/s1600/eric-dane-06.jpg",
        "http://4.bp.blogspot.com/-impJVDWAN7w/TyX6T5XkEWI/AAAAAAAAJaE/tE3dKO_5m14/s1600/Ellen+Page3.jpg",
        "http://3.bp.blogspot.com/-pP9DA3u9ooU/TzUWx8tOP0I/AAAAAAAAB_8/ODq1oxoXYEs/s1600/johny+deep.jpg",
        "https://surfinghollywood.files.wordpress.com/2015/03/thor.jpg"
} ;


        String imageUrl = arrStringImageUrls[new Random().nextInt(arrStringImageUrls.length)];

        qbUser.setCustomData(imageUrl);

        QBUsers.signUpSignInTask(qbUser).performAsync(new QBEntityCallback<QBUser>() {
            @Override
            public void onSuccess(QBUser qbUser, Bundle bundle) {
                progressDialog.dismiss();

                DataHolder.getInstance().addQbUser(qbUser);
                DataHolder.getInstance().setSignInQbUser(qbUser);

                setResult(RESULT_OK, new Intent());
                finish();
            }

            @Override
            public void onError(QBResponseException error) {
                progressDialog.dismiss();
                View rootLayout = findViewById(R.id.activity_sign_up);
                showSnackbarError(rootLayout, R.string.errors, error, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        signUp();
                    }
                });
            }
        });
    }

    private boolean isValidData(String login, String password, String confirm,String fullname,String email,String phone,String tag) {

        if (TextUtils.isEmpty(login) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirm)|| TextUtils.isEmpty(fullname)|| TextUtils.isEmpty(email)|| TextUtils.isEmpty(phone)|| TextUtils.isEmpty(tag))
        {
            if (TextUtils.isEmpty(login)) {
                loginEditText.setError(getResources().getString(R.string.error_field_is_empty));
            }
            if (TextUtils.isEmpty(password)) {
                passwordEditText.setError(getResources().getString(R.string.error_field_is_empty));
            }
            if (TextUtils.isEmpty(confirm)) {
                confirmPasswordEditText.setError(getResources().getString(R.string.error_field_is_empty));
            }



            if (TextUtils.isEmpty(fullname)) {
                full_name_textview.setError(getResources().getString(R.string.error_field_is_empty));
            }
            if (TextUtils.isEmpty(email)) {
                email_textview.setError(getResources().getString(R.string.error_field_is_empty));
            }
            if (TextUtils.isEmpty(phone)) {
                phone_textview.setError(getResources().getString(R.string.error_field_is_empty));
            }
            if (TextUtils.isEmpty(tag)) {
                tag_textview.setError(getResources().getString(R.string.error_field_is_empty));
            }

            return false;
        }

        if (!TextUtils.equals(password, confirm)) {
            confirmPasswordEditText.setError(getResources().getString(R.string.confirm_error));
            return false;
        }
        return true;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        toast.cancel();
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
                signUp();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}