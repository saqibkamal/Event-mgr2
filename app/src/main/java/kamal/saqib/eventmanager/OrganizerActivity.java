package kamal.saqib.eventmanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.io.Serializable;
import java.util.ArrayList;

public class OrganizerActivity extends AppCompatActivity implements View.OnClickListener{

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    FirebaseStorage firebaseStorage;
    String email;
    Employee employee;
    ArrayList<Event> events;
    Button bt_my,bt_all,add_new,logout;
    TextView tx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizer2);
        bt_my=(Button) findViewById(R.id.bt_myactivity);
        bt_all=(Button) findViewById(R.id.bt_allactivity);
        add_new=(Button) findViewById(R.id.bt_addnew);
        logout=(Button) findViewById(R.id.bt_logout);





        bt_my.setOnClickListener(this);
        bt_all.setOnClickListener(this);
        add_new.setOnClickListener(this);
        logout.setOnClickListener(this);




        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        employee = (Employee) args.getSerializable("employee_detail");


        firebaseAuth = FirebaseAuth.getInstance();
        email = firebaseAuth.getCurrentUser().getEmail().toString();

        events=employee.getEvent();




    }

    @Override
    public void onClick(View view) {
        if(view==bt_my){
            Intent in = new Intent(getBaseContext(), MyEventlist.class);
            Bundle args = new Bundle();
            args.putSerializable("myeventlist",(Serializable)employee.getEvent());
            in.putExtra("BUNDLE",args);
            startActivity(in);
        }
        else if(view==bt_all){
            Intent in = new Intent(getBaseContext(), alleventlist.class);
            startActivity(in);
        }
        else if(view==add_new){
            Intent in = new Intent(getBaseContext(), addnewevent.class);
            startActivity(in);

        }
        else if(view==logout){
            new android.support.v7.app.AlertDialog.Builder(OrganizerActivity.this).setTitle("Log Out").
                    setMessage("Are you sure you want to Log out ?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "Logged Out Succesfully", Toast.LENGTH_LONG).show();
                    firebaseAuth.signOut();
                    finish();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).show();

        }
    }
}
