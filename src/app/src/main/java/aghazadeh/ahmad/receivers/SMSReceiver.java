package aghazadeh.ahmad.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class SMSReceiver extends BroadcastReceiver {

    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    private static final String TAG = "SMSBroadcastReceiver";

    private static OnSMSRecevice listener;

    public void setOnSMSRecevice(OnSMSRecevice listener) {
        this.listener = listener;
    }





    @Override
    public void onReceive(Context context, Intent intent) {


        Log.i(TAG, "Intent recieved: " + intent.getAction());

        if (intent.getAction() == SMS_RECEIVED) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[])bundle.get("pdus");
                final SmsMessage[] messages = new SmsMessage[pdus.length];
                for (int i = 0; i < pdus.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                }
                if (messages.length > -1) {
                    Log.i(TAG, "Message recieved: " + messages[0].getMessageBody());
                    if(listener!=null)
                    {
                        listener.onReceive(messages);
                    }

                }

            }
        }
    }


    public interface OnSMSRecevice {
        // This can be any number of events to be sent to the activity
        void onReceive(SmsMessage[] messages);

    }
}
