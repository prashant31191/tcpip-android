package com.tcpipandroid.client;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.tcpipandroid.R;

import static android.widget.EditText.*;

public class ClientMainActivity extends Activity {
    private TextView tvServerMessage;
    Button send,btnClear;
    final private String SERVER_PORT = "8080";
    private String strClientName = "Prince";
    EditText etMessage;
    TextView tvMessages;
    ScrollView scrollview;
    String strMessage = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_main);
        @SuppressLint("WifiManagerLeak")
        final WifiManager myWifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        Random random = new Random();
        strClientName = strClientName + "_"+String.format("%04d", random.nextInt(10000));;
        tvServerMessage = (TextView) findViewById(R.id.textViewServerMessage);
        scrollview = (ScrollView) findViewById(R.id.scrollview);
        etMessage = (EditText) findViewById(R.id.etMessage);
        tvMessages = (TextView) findViewById(R.id.tvMessages);
        send = (Button) findViewById(R.id.btnSend);
        btnClear = (Button)findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                tvMessages.setText("");

            }
        });


        etMessage.setOnEditorActionListener(new OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    //do here your stuff f
                    send.performClick();
                    return true;
                }
                return false;
            }
        });
        
        send.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                strMessage = etMessage.getText().toString().trim();
                if(strMessage !=null && strMessage.length() > 0) {
                    ClientAsyncTask clientAST = new ClientAsyncTask();

                clientAST.execute(new String[]{
                        intToIP(myWifiManager.getDhcpInfo().gateway), SERVER_PORT,
                        strClientName + " : " +strMessage});

                    if(strMessage !=null && strMessage.length() > 0)
                    {
                        tvMessages.append(strMessage+"\n \n");
                    }

                    scrollview.post(new Runnable() {
                        @Override
                        public void run() {
                            scrollview.fullScroll(ScrollView.FOCUS_DOWN);
                        }
                    });

                    strMessage = "";
                    etMessage.setText("");
                }
                else
                {
                    Snackbar.make(etMessage,"Please enter message.",Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

    public String intToIP(int i) {
        return ((i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF)
                + "." + ((i >> 24) & 0xFF));
    }


    /**
     * AsyncTask which handles the communication with the server
     */
    class ClientAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String result = null;
            try {

                Socket socket = new Socket(params[0],
                        Integer.parseInt(params[1]));

                InputStream is = socket.getInputStream();

                PrintWriter out = new PrintWriter(socket.getOutputStream(),
                        true);

                out.println(params[2]);

                BufferedReader br = new BufferedReader(
                        new InputStreamReader(is));

                result = br.readLine();

                socket.close();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {

            tvServerMessage.setText(s);


        }
    }
}