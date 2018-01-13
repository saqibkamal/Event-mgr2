package kamal.saqib.eventmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MyEventlist extends AppCompatActivity {
    ArrayList<String> smsMessagesList = new ArrayList<>();
    ArrayList<Event> events;
    ArrayAdapter arrayAdapter;
    ListView messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_eventlist);

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        events = (ArrayList<Event>) args.getSerializable("myeventlist");

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, smsMessagesList);
        messages.setAdapter(arrayAdapter);
        arrayAdapter.addAll(events);
    }
}
