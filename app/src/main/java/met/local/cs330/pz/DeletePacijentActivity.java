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

public class DeletePacijentActivity extends AppCompatActivity {

    TextInputEditText deleteEditId;
    MaterialButton btnDeletePacijent;
    DatabaseReference pacijentiDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_pacijent);

        deleteEditId = findViewById(R.id.deleteId);
        btnDeletePacijent = findViewById(R.id.deletePacijent);
        pacijentiDatabase = FirebaseDatabase.getInstance().getReference().child("pacijenti");

        btnDeletePacijent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String jmbg =  deleteEditId.getText().toString();

                if (!jmbg.equals("")){
                    deletePacijent(jmbg);
                    Toast.makeText(DeletePacijentActivity.this,
                            "Pacijent deleted!", Toast.LENGTH_SHORT).show();
                }else{
                    deleteEditId.setError("Error ocurred!");
                    deleteEditId.requestFocus();
                }
            }
        });

    }

    private void deletePacijent(String strTitle){
        Query deleteQuery = pacijentiDatabase.orderByChild("jmbg").equalTo(strTitle);
        deleteQuery.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot delData: dataSnapshot.getChildren()){
                    delData.getRef().removeValue();
                }
                Toast.makeText(DeletePacijentActivity.this,"Pacijent Deleted",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(DeletePacijentActivity.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}