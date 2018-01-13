package kamal.saqib.eventmanager;

import android.content.Context;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;

public class addnewevent extends AppCompatActivity {
    ArrayList<Employee> employees;
    Button submit;
    EditText eventname,date,time,address;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnewevent);

        employees=new ArrayList<>();
        eventname=(EditText) findViewById(R.id.eventname);
        date=(EditText) findViewById(R.id.date);
        submit=(Button) findViewById(R.id.submit);
        time=(EditText) findViewById(R.id.time);
        address=(EditText) findViewById(R.id.address);

        databaseReference = FirebaseDatabase.getInstance().getReference("Events");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String event_name=eventname.getText().toString();
                String dat=date.getText().toString();
                String tme=time.getText().toString();
                String add=address.getText().toString();
                if(event_name.length()==0){
                    Toast.makeText(getApplicationContext(),"Insert Event name",Toast.LENGTH_SHORT).show();
                }
                else if(dat.length()==0){
                    Toast.makeText(getApplicationContext(),"Insert Event date",Toast.LENGTH_SHORT).show();
                }
                else if(employees.size()==0){
                    Toast.makeText(getApplicationContext(),"Select presenters first",Toast.LENGTH_SHORT).show();
                }
                else if(tme.length()==0){
                    Toast.makeText(getApplicationContext(),"Insert Event time",Toast.LENGTH_SHORT).show();
                }
                else if(add.length()==0){
                    Toast.makeText(getApplicationContext(),"Insert Event Address",Toast.LENGTH_SHORT).show();
                }
                else{
                    Event event=new Event();
                    event.setDate(dat);
                    //event.setOrganizer();
                    event.setPresenter(employees);
                    event.setEventname(event_name);
                    databaseReference.setValue(event);

                    //Intent in=new Intent(getApplicationContext(), .class);
                    finish();

                }

            }
        });


    }

    public class CustomAdapter extends BaseAdapter {
        ArrayList<String> result;
        Context context;
        int [] imageId;
        private LayoutInflater inflater=null;
        public CustomAdapter(MainActivity mainActivity) {
            // TODO Auto-generated constructor stub
            //result=msgsndrs;
            context=mainActivity;


            inflater = ( LayoutInflater )context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return result.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        public class Holder
        {
            TextView tv;
            ImageView img;
            CheckBox chk;

        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            final kamal.saqib.eventmanager.addnewevent.CustomAdapter.Holder holder= new kamal.saqib.eventmanager.addnewevent.CustomAdapter.Holder();
            View rowView;
            rowView = inflater.inflate(R.layout.additemlistview, null);
            holder.tv=(TextView) rowView.findViewById(R.id.presentername);
            //holder.fmsg=(TextView) rowView.findViewById(R.id.fmsgs);
            holder.img=(ImageView) rowView.findViewById(R.id.imageview);
            holder.chk=(CheckBox) rowView.findViewById(R.id.checkbox);
            //holder.tv.setText(result.get(position));
            //holder.fmsg.setText(fmsgs.get(position));

            //holder.img.setImageResource(imageId[position]);
            //if(holder.chk.isChecked())

            holder.chk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(holder.chk.isChecked()){
                        //employees.add();
                    }
                    else{
                        //employees.remove();
                    }
                }
            });


            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Intent in = new Intent(getBaseContext(), single_user_msg.class);
                   // Bundle args = new Bundle();
                   // args.putSerializable("ARRAYLIST",(Serializable)map_for_db.get(p));
                    //in.putExtra("BUNDLE",args);


                   // startActivity(in);
                }
            });
            return rowView;
        }

    }
}
