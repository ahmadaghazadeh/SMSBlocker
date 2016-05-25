package aghazadeh.ahmad.smsblocker;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsMessage;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import aghazadeh.ahmad.receivers.SMSReceiver;

public class MainActivity extends AppCompatActivity implements SMSReceiver.OnSMSRecevice {


    TextView textView;
    static final int REQUEST_CODE_SOME_FEATURES_PERMISSIONS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textView = (TextView) findViewById(R.id.txt_message);

        checkPermissions();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:

                Intent intent=new Intent(this,RolesActivity.class);
                startActivity(intent);
                break;

            case R.id.action_roles:

                Intent RolesShowActivity=new Intent(this,RolesShowActivity.class);
                startActivity(RolesShowActivity);
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void checkPermissions() {
        SMSReceiver sms = new SMSReceiver();
        sms.setOnSMSRecevice(this);


        int hasRECEIVE_SMSPermission = checkSelfPermission(Manifest.permission.RECEIVE_SMS);
        int hasSMSPermission = checkSelfPermission(Manifest.permission.SEND_SMS);
        List<String> permissions = new ArrayList<>();
        if (hasRECEIVE_SMSPermission != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.RECEIVE_SMS);
        }

        if (hasSMSPermission != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.SEND_SMS);
        }

        if (!permissions.isEmpty()) {
            requestPermissions(permissions.toArray(new String[permissions.size()]), REQUEST_CODE_SOME_FEATURES_PERMISSIONS);
        }
    }

    @Override
    public void onReceive(SmsMessage[] messages) {
        for (SmsMessage sms : messages) {
            textView.setText(textView.getText() + "\n" + sms.getOriginatingAddress() + " :" + sms.getMessageBody());
        }
    }
}
