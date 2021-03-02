package met.local.cs330.pz;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import met.local.cs330.pz.model.Pacijent;

public class AddPacijentActivity extends AppCompatActivity {

    TextInputEditText editJmbg, editIme, editPrezime, editSimptomi, editTerapija, editIzlecen;
    MaterialButton btnAddPacijent;
    DatabaseReference databasePacijenti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pacijent);

        databasePacijenti = FirebaseDatabase.getInstance().getReference("pacijenti");

        editJmbg = findViewById(R.id.jmbg);
        editIme =  findViewById(R.id.ime);
        editPrezime = findViewById(R.id.prezime);
        editSimptomi = findViewById(R.id.simptomi);
        editTerapija = findViewById(R.id.terapija);
        editIzlecen = findViewById(R.id.izlecen);
        btnAddPacijent = findViewById(R.id.addPacijent);

        editIzlecen.setText("false");

        btnAddPacijent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPacijent();
            }
        });
    }

    private void addPacijent(){

        String jmbg = editJmbg.getText().toString().trim();
        String ime = editIme.getText().toString().trim();
        String prezime = editPrezime.getText().toString().trim();
        String simptomi = editSimptomi.getText().toString();
        String terapija = editTerapija.getText().toString();

        boolean izlecen = Boolean.parseBoolean(editIzlecen.getText().toString().trim());

        if( !TextUtils.isEmpty(jmbg) && !TextUtils.isEmpty(ime) && !TextUtils.isEmpty(prezime) && !TextUtils.isEmpty(simptomi)
                && !TextUtils.isEmpty(terapija) && izlecen == false){

            Pacijent pacijent = new Pacijent(jmbg, ime, prezime, simptomi, terapija, izlecen);

            databasePacijenti.child(jmbg).setValue(pacijent);

            Toast.makeText(this, "Pacijent Added!", Toast.LENGTH_SHORT).show();

            editJmbg.setText("");
            editIme.setText("");
            editPrezime.setText("");
            editSimptomi.setText("");
            editTerapija.setText("");
        }else{
            Toast.makeText(this, "Please fill form!", Toast.LENGTH_SHORT).show();
        }
    }
}