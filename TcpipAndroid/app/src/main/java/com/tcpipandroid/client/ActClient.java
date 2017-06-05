package com.tcpipandroid.client;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tcpipandroid.R;

public class ActClient extends Activity {

	private Socket socket;

	private static  int SERVERPORT = 5000;
	private static  String SERVER_IP = "10.0.2.2";

	EditText etServerIp,etServerPort;
	Button btnCustome,btnDefault;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_client);

		etServerIp = (EditText) findViewById(R.id.etServerIp);
		etServerPort = (EditText) findViewById(R.id.etServerPort);

		btnCustome = (Button) findViewById(R.id.btnCustome);
		btnDefault = (Button) findViewById(R.id.btnDefault);

		btnCustome.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				try {
					String strPort = etServerPort.getText().toString().trim();
					String strIp = etServerIp.getText().toString().trim();

					if (strPort != null && strPort.length() > 0) {
						SERVERPORT = Integer.parseInt(strPort);
					}

					if (strIp != null && strIp.length() > 0) {
						SERVER_IP = strIp;
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}




			}
		});

		btnDefault.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				SERVERPORT = 5000;
				SERVER_IP = "10.0.2.2";

				etServerIp.setText(""+SERVER_IP);
				etServerPort.setText(""+SERVERPORT);

			}
		});



		new Thread(new ClientThread()).start();
	}
	
	public void onClick(View view) {
		try {
			EditText et = (EditText) findViewById(R.id.EditText01);
			String str = et.getText().toString();
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(socket.getOutputStream())),
					true);
			out.println(str);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	class ClientThread implements Runnable {

		@Override
		public void run() {
			
			try {
				InetAddress serverAddr = InetAddress.getByName(SERVER_IP);

				socket = new Socket(serverAddr, SERVERPORT);

			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}

	}
}