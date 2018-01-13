package kamal.saqib.eventmanager;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class alleventlist extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    ArrayList<Event> events;
    ListView listView;
    HashMap<String,Event> mapofevent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alleventlist);

        firebaseAuth = FirebaseAuth.getInstance();
        listView=(ListView) findViewById(R.id.listView);

        databaseReference = FirebaseDatabase.getInstance().getReference("Event");

        events=new ArrayList<>();
        mapofevent=new HashMap<>();



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                events.clear();

                for (DataSnapshot user : dataSnapshot.getChildren()) {
                    Event event = user.getValue(Event.class);
                    events.add(event);
                    mapofevent.put(event.getEventname(),event);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listView.setAdapter(new CustomAdapter(this));





    }

    public class CustomAdapter extends BaseAdapter {
        Context context;
        private LayoutInflater inflater=null;
        public CustomAdapter(alleventlist mainActivity) {
            // TODO Auto-generated constructor stub

            context=mainActivity;


            inflater = ( LayoutInflater )context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public class Holder
        {
            TextView eventname,date;
        }

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            kamal.saqib.eventmanager.alleventlist.CustomAdapter.Holder holder= new kamal.saqib.eventmanager.alleventlist.CustomAdapter.Holder();
            View rowView;
            rowView = inflater.inflate(R.layout.alleventlistview, null);
            holder.date=(TextView) rowView.findViewById(R.id.date);
            holder.eventname=(TextView) rowView.findViewById(R.id.eventname);

            holder.date.setText(events.get(position).getDate());
            holder.eventname.setText(events.get(position).getEventname());

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });


            return rowView;
        }

    }

}
