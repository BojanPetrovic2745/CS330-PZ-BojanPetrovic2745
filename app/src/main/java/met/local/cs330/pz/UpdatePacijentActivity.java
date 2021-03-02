package met.local.cs330.pz;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class UpdatePacijentActivity extends AppCompatActivity {

    TextInputEditText editTextJmbg, editTextSimptomi, editTextTerapija, editTextIzlecen;
    MaterialButton btnUpdatePacijent;
    DatabaseReference pacijentDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pacijent);

        editTextJmbg = findViewById(R.id.updateJmbg);
        editTextSimptomi = findViewById(R.id.updateSimptomi);
        editTextTerapija = findViewById(R.id.updateTerapija);
        editTextIzlecen = findViewById(R.id.updateIzlecen);
        btnUpdatePacijent = findViewById(R.id.updatePacijent);
        pacijentDatabase = FirebaseDatabase.getInstance().getReference("pacijenti");

        btnUpdatePacijent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String jmbg = editTextJmbg.getText().toString();
                String simptomi = editTextSimptomi.getText().toString();
                String terapija = editTextTerapija.getText().toString();
                boolean izlecen = Boolean.parseBoolean(editTextIzlecen.getText().toString());

                updatePacijent(jmbg, simptomi, terapija, izlecen);
            }
        });
    }

    private void updatePacijent(String jmbg, final String simptomi, final String terapija,  final boolean izlecen){

        Query editQuery = pacijentDatabase.orderByChild("jmbg").equalTo(jmbg);
        editQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot edtData: dataSnapshot.getChildren()){
                    edtData.getRef().child("simptomi").setValue(simptomi);
                    edtData.getRef().child("terapija").setValue(terapija);
                    edtData.getRef().child("izlecen").setValue(izlecen);
                }
                Toast.makeText(UpdatePacijentActivity.this,"Pacijent Updated",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(UpdatePacijentActivity.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}