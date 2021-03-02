package met.local.cs330.pz;

import android.Manifest;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputEditText;

public class AboutActivity extends AppCompatActivity {

    Intent intent;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;

    String SENT = "SMS_SENT";
    String DELIVERED = "SMS_DELIVERED";
    PendingIntent sentPI, deliveredPI;
    BroadcastReceiver smsSentReceiver, smsDeliveredReceiver;
    IntentFilter intentFilter;

    private BroadcastReceiver intentReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //-prikazuje primljeni SMS u TextView pogledu -
            TextView SMSes = (TextView) findViewById(R.id.textView1);
            SMSes.setText(intent.getExtras().getString("sms"));
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        sentPI = PendingIntent.getBroadcast(this, 0,
                new Intent(SENT), 0);

        deliveredPI = PendingIntent.getBroadcast(this, 0,
                new Intent(DELIVERED), 0);

        //-filter prijema SMS poruka-
        intentFilter = new IntentFilter();
        intentFilter.addAction("SMS_RECEIVED_ACTION");

        //---registrovanje primaoca---
        registerReceiver(intentReceiver, intentFilter);

    }

    @Override
    public void onResume() {
        super.onResume();

        /*//---registrovanje primaoca---
        registerReceiver(intentReceiver, intentFilter);*/

        //---kreira BroadcastReceiver kada je SMS poslat---
        smsSentReceiver = new BroadcastReceiver(){
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode())
                {
                    case AppCompatActivity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS prosleđen",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(getBaseContext(), "Generička greška",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(getBaseContext(), "Nema usluge",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(getBaseContext(), "Null PDU",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(getBaseContext(), "Radio isključen",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        //---kreira BroadcastReceiver kada SMS dostavljen---
        smsDeliveredReceiver = new BroadcastReceiver(){
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode())
                {
                    case AppCompatActivity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS dostavljen",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case AppCompatActivity.RESULT_CANCELED:
                        Toast.makeText(getBaseContext(), "SMS nije dostavljen",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        //---registruje dva BroadcastReceiver - a---
        registerReceiver(smsDeliveredReceiver, new IntentFilter(DELIVERED));
        registerReceiver(smsSentReceiver, new IntentFilter(SENT));
    }

    @Override
    public void onPause() {
        super.onPause();

        //---odjavljuje primaoca---
        unregisterReceiver(intentReceiver);

        //---odjavljuje dva BroadcastReceiver-a---
        unregisterReceiver(smsSentReceiver);
        unregisterReceiver(smsDeliveredReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //---odjavljivanje primaoca---
        unregisterReceiver(intentReceiver);
    }


    public void onClick(View v) {

        TextInputEditText poruka = findViewById(R.id.poruka);

        sendSMS("0648516470", poruka.getText().toString());
    }

    public void onSMSIntentClick (View v) {
        Intent i = new
                Intent(Intent.ACTION_VIEW);
        TextInputEditText poruka =  findViewById(R.id.poruka);

        i.putExtra("address", "0648516470");

        i.putExtra("sms_body", poruka.getText().toString());
        i.setType("vnd.android-dir/mms-sms");
        startActivity(i);
    }

    private void sendSMS(String phoneNumber, String message){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }else{         //dozvola je već odobrena
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(phoneNumber, null, message,
                    sentPI, deliveredPI);
            Toast.makeText(getApplicationContext(), "SMS sent.",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void onClickApoteka(View view){

        intent = new Intent(Intent.ACTION_VIEW);

        intent.setData(Uri.parse("https://www.apotekaviva.com/"));
        startActivity(Intent.createChooser(intent, "Browse with"));
    }

    public void onClickFakultet(View view){

        intent = new Intent(Intent.ACTION_VIEW);

        intent.setData(Uri.parse("https://www.metropolitan.ac.rs/osnovne-studije/fakultet-informacionih-tehnologija/"));
        startActivity(Intent.createChooser(intent, "Browse with"));
    }
}