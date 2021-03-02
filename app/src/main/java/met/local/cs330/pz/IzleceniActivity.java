package met.local.cs330.pz;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import met.local.cs330.pz.interaces.RecyclerViewInterface;
import met.local.cs330.pz.model.Pacijent;

public class IzleceniActivity extends AppCompatActivity implements RecyclerViewInterface {

    private List<Pacijent>listData;
    private RecyclerView rv;
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_izleceni);

        rv = findViewById(R.id.recyclerview);

        getData(true, rv);
    }

    @Override
    public  void getData(final boolean izlecen, RecyclerView view){

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        listData = new ArrayList<>();

        final DatabaseReference nm= FirebaseDatabase.getInstance().getReference().child("pacijenti");

        nm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){

                        Pacijent l=npsnapshot.getValue(Pacijent.class);

                        if(l.isIzlecen() == izlecen){
                            listData.add(l);
                        }
                    }
                    adapter=new RecyclerViewAdapter(listData);
                    rv.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}