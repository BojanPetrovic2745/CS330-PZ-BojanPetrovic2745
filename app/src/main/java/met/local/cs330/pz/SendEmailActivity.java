package met.local.cs330.pz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class SendEmailActivity extends AppCompatActivity {

    TextInputEditText textViewEmailAddr, textViewCC, textViewSubject, textViewEmailText;
    MaterialButton btnSendMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email);

        textViewEmailAddr =  findViewById(R.id.emailAddr);
        textViewCC = findViewById(R.id.carbonCopies);
        textViewSubject =  findViewById(R.id.subject);
        textViewEmailText =  findViewById(R.id.emailText);

        btnSendMail =  findViewById(R.id.sendEmail);

        btnSendMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailAddr[] = {textViewEmailAddr.getText().toString()};
                String cc[] = {textViewCC.getText().toString()};
                sendEmail(emailAddr, cc, textViewSubject.getText().toString(), textViewEmailText.getText().toString());
            }
        });
    }

    private void sendEmail(String[] emailAddresses, String[] carbonCopies, String subject, String message){

        Intent email = new Intent(Intent.ACTION_SEND);

        email.setData(Uri.parse("mailto: "));

        email.putExtra(Intent.EXTRA_EMAIL, emailAddresses);
        email.putExtra(Intent.EXTRA_CC, carbonCopies);
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.putExtra(Intent.EXTRA_TEXT, message);

        email.setType("message/rfc822");
        startActivity(Intent.createChooser(email, "Email"));
    }
}