package com.june.sms.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsReceiver extends BroadcastReceiver {
    public static final String TAG                 = "SmsReceiver";
    public static final String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";
    public static final String SENDER_ADDRESS      = "10011";                                  //sp

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(SMS_RECEIVED_ACTION)) {
            SmsMessage[] messages = getMessagesFromIntent(intent, "");
            for (SmsMessage message : messages) {
                if (message.getOriginatingAddress().indexOf(SENDER_ADDRESS) != -1) {
                    Log.d(TAG, message.getMessageBody());
                    Intent it = new Intent();
                    // it.setAction(Constants.CAN_BIND_PHONE_ACTION);
                    String key = message.getMessageBody();
                    it.putExtra("key", key);
                    context.sendBroadcast(it);
//                    abortBroadcast();
                }
            }
        }
    }

    public final SmsMessage[] getMessagesFromIntent(Intent intent, String queryString) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            SmsMessage[] messages = new SmsMessage[pdus.length];
            for (int i = 0; i < pdus.length; i++)
                messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
            return messages;
            
//            for (SmsMessage message : messages) {
//                String msg = message.getMessageBody();
//                String to = message.getOriginatingAddress();
//                if (msg.toLowerCase().startsWith(queryString)) {
//                    String out = msg.substring(queryString.length());
//                                        sms.sendTextMessage(to, null, out, null, null);
//                }
//            }
        }
        return null;
    }
}