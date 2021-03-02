package met.local.cs330.pz;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity{

    Intent intent;
    ListView listView;
    ArrayList<String> listItems = new ArrayList<String>();
    ArrayList<String> listKeys = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference dbRef = database.getReference("pacijenti");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        listView =  findViewById(R.id.listViewPacjineti);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, listItems);

        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_NONE);

        addChildEventListener();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        CreateMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return MenuChoice(item);
    }

    private void addChildEventListener() {

        ChildEventListener childListener = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                adapter.add("JMBG: " + (String) dataSnapshot.child("jmbg").getValue());
                adapter.add( "IME: " + (String) dataSnapshot.child("ime").getValue());
                adapter.add("PREZIME: " + (String) dataSnapshot.child("prezime").getValue());
                adapter.add("SIMPTOMI: " + (String) dataSnapshot.child("simptomi").getValue());
                adapter.add("TERAPIJA: " + (String) dataSnapshot.child("terapija").getValue());
                adapter.add("IZLECEN: " + dataSnapshot.child("izlecen").getValue());

                listKeys.add(dataSnapshot.getKey());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                int index = listKeys.indexOf(key);

                if (index != -1) {
                    listItems.remove(index);
                    listKeys.remove(index);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        dbRef.addChildEventListener(childListener);
    }

    private void CreateMenu(Menu menu) {

        MenuItem mnu1 = menu.add(0, 0, 0, "ADD Pacijent");
        {
            mnu1.setIcon(R.mipmap.ic_launcher_round);
            mnu1.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM
                    | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        }

        MenuItem mnu2 = menu.add(0, 1, 1, "UPDATE Pacijent");
        {
            mnu2.setIcon(R.mipmap.ic_launcher_round);
            mnu2.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM
                    | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        }

        MenuItem mnu3 = menu.add(0, 2, 2, "DELETE Pacijent");
        {
            mnu3.setIcon(R.mipmap.ic_launcher_round);
            mnu3.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM
                    | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        }

        MenuItem mnu4 = menu.add(0, 3, 3, "SEND Email");
        {
            mnu4.setIcon(R.mipmap.ic_launcher_round);
            mnu4.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM
                    | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        }


        MenuItem mnu5 = menu.add(0, 4, 4, "IZLECENI Pacijenti");
        {
            mnu5.setIcon(R.mipmap.ic_launcher_round);
            mnu5.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM
                    | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        }

        MenuItem mnu6 = menu.add(0, 5, 5, "TRENUTNI Pacijenti");
        {
            mnu6.setIcon(R.mipmap.ic_launcher_round);
            mnu6.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM
                    | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        }

        MenuItem mnu7 = menu.add(0, 6, 6, "ABOUT");
        {
            mnu7.setIcon(R.mipmap.ic_launcher_round);
            mnu7.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM
                    | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        }

        MenuItem mnu8 = menu.add(0, 7, 7, "LOGOUT");
        {
            mnu8.setIcon(R.mipmap.ic_launcher_round);
            mnu8.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM
                    | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        }
    }

    private boolean MenuChoice(final MenuItem item){

        switch (item.getItemId()){
            case 0:
                intent = new Intent(HomeActivity.this, AddPacijentActivity.class);
                startActivity(intent);
                return true;
            case 1:
                intent = new Intent(HomeActivity.this, UpdatePacijentActivity.class);
                startActivity(intent);
                return true;
            case 2:
                intent = new Intent(HomeActivity.this, DeletePacijentActivity.class);
                startActivity(intent);
                return true;
            case 3:
               intent = new Intent(HomeActivity.this, SendEmailActivity.class);
               startActivity(intent);
               return true;
            case 4:
                intent = new Intent(HomeActivity.this, IzleceniActivity.class);
                startActivity(intent);
                return true;
            case 5:
                intent = new Intent(HomeActivity.this, TrenutniPacijenti.class);
                startActivity(intent);
                return true;
            case 6:
                intent = new Intent(HomeActivity.this, AboutActivity.class);
                startActivity(intent);
                return true;
            case 7:
                FirebaseAuth.getInstance().signOut();
                intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
                return true;

        }
        return false;
    }
}