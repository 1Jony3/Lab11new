# Lab11new
# **Добавление элементов в activity_main.xml**

- [X] Добавить два com.google.android.material.textfield.TextInputLayout для номера телефона и смс
- [X] Добавить кнопки для сохранения отправки сообщения и для совершения звонка
#  **Добавление кода**

- [X] Добавить обработчик кнопоки для отправки смс
```
        public void SMS(View view){
        TextInputEditText tel = findViewById(R.id.textTel);
        String phoneNo = tel.getText().toString();

        TextInputEditText tosms = findViewById(R.id.textSMS);
        String toSms = "smsto: " + phoneNo;

        String messageText = tosms.getText().toString();
        Intent sms = new Intent(Intent.ACTION_SENDTO, Uri.parse(toSms));
        sms.putExtra("sms_body", messageText);
        startActivity(sms);
    }
```
- [X] Добавить обработчик кнопоки для совершения звонка
```
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
```

- [X] Установить разрешение на доступ в файле «AndroidManifest.xml»
```
<uses-permission android:name="android.permission.CALL_PHONE" />
    <uses-permission android:name="android.permission.SEND_SMS" />
``` 
***Все работает***

[Информация по офрмлению README.md](https://github.com/GnuriaN/format-README.git)