package com.clientserverchat;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.tcpipandroid.R;
import com.tcpipandroid.client.ClientMainActivity;
import com.tcpipandroid.server.ServerMainActivity;

public class ActDashboatdClientServerChat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_multiclient);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Button btnClient1 = (Button) findViewById(R.id.btnClient1);
        btnClient1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "ActClient App", Snackbar.LENGTH_LONG).show();
                startActivity(new Intent(ActDashboatdClientServerChat.this, ClientActivity.class));
            }
        });


        Button btnServer1 = (Button) findViewById(R.id.btnServer1);
        btnServer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "ActServer App", Snackbar.LENGTH_LONG).show();
                startActivity(new Intent(ActDashboatdClientServerChat.this, ServerActivity.class));
            }
        });





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_client, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
