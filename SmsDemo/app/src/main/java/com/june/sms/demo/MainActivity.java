package com.june.sms.demo;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smsdemo.R;

public class MainActivity extends Activity {
	public static final String	SMS_ACTION_SEND			= "send";
	public static final String	SMS_ACTION_DELIVERED	= "delivered";
	SmsBroadcastReceiver		receiver;
	private EditText			phoneNum;
	private EditText			smsContent;
	private Button				send;
	private TextView			status;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		phoneNum = (EditText) findViewById(R.id.phonenumber);
		smsContent = (EditText) findViewById(R.id.smscontent);
		send = (Button) findViewById(R.id.send);
		status = (TextView) findViewById(R.id.status);

		send.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				send();
			}
		});
		
		if (receiver == null) {
			receiver = new SmsBroadcastReceiver();
			IntentFilter filter = new IntentFilter();
			filter.addAction(SMS_ACTION_SEND);
			filter.addAction(SMS_ACTION_DELIVERED);
			registerReceiver(receiver, filter);
		}
		
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(receiver);
	}

	private void send() {
		String phoneNumS = phoneNum.getText().toString();
		String smsContentS = smsContent.getText().toString();
		try {
			SmsManager smsManager = SmsManager.getDefault();
			Intent sendIntent = new Intent();
			sendIntent.setPackage(getApplicationContext().getPackageName());
			sendIntent.setAction(SMS_ACTION_SEND);
			PendingIntent sendPI = PendingIntent.getBroadcast(getApplicationContext(), 0, sendIntent, 0);

			Intent deliveryIntent = new Intent();
			deliveryIntent.setPackage(getApplicationContext().getPackageName());
			deliveryIntent.setAction(SMS_ACTION_DELIVERED);
			PendingIntent deliveryPI = PendingIntent.getBroadcast(getApplicationContext(), 0, deliveryIntent, 0);

			smsManager.sendTextMessage(phoneNumS, null, smsContentS, sendPI, deliveryPI);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	class SmsBroadcastReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (SMS_ACTION_SEND.equals(action)) {
				switch (getResultCode()) {
				case Activity.RESULT_OK:
					Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
					status.append("\n");
					break;
				default:
					Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
					status.append("\n");
					break;
				}
			}
			else if (SMS_ACTION_DELIVERED.equals(action)) {
				switch (getResultCode()) {
				case Activity.RESULT_OK:
					Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
					status.append("\n");
					break;
				default:
					Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
					status.append("\n");
					break;
				}
			}
		}
	}
}
