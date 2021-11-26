package com.example.lab112;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button mDialButton = (Button) findViewById(R.id.call);
        final TextInputEditText mPhoneNoEt = findViewById(R.id.textTel);
        final TextInputEditText smsEdit = findViewById(R.id.textSMS);
        mDialButton.setOnClickListener(new View.OnClickListener()
        {
            private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 0;

            @Override
            public void onClick(View view)
            {
                String phoneNo = mPhoneNoEt.getText().toString();
                if(!TextUtils.isEmpty(phoneNo))
                {
                    if (ContextCompat.checkSelfPermission(MainActivity.this,
                            Manifest.permission.CALL_PHONE)
                            != PackageManager.PERMISSION_GRANTED) {

                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[]{Manifest.permission.CALL_PHONE},
                                MY_PERMISSIONS_REQUEST_CALL_PHONE);
                    }

                    String dial = "tel:" + phoneNo;
                    startActivity(new Intent(Intent.ACTION_CALL,
                            Uri.parse(dial)));
                }
                else {
                    Toast.makeText(MainActivity.this, "Введите номер телефона", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void SMS(View view){
        TextInputEditText tel = findViewById(R.id.textTel);
        String phoneNo = tel.getText().toString();

        TextInputEditText tosms = findViewById(R.id.textSMS);
        String toSms = "smsto: " + phoneNo;

        String messageText = tosms.getText().toString();
        Intent sms = new Intent(Intent.ACTION_SENDTO, Uri.parse(toSms));
        sms.putExtra("sms_body", messageText);
        startActivity(sms);
        /*SmsManager.getDefault().sendTextMessage(phoneNo, null,
                messageText.toString(), null, null);*/


    }
}