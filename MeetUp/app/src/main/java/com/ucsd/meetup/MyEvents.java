package com.ucsd.meetup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Lawrence on 2/21/2016.
 */
public class MyEvents extends AppCompatActivity {

    private ListView mainListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myevent);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);



        mainListView = (ListView) findViewById(R.id.eventsList);
        ParseUser currUser = ParseUser.getCurrentUser();
        List<String> eventsList = currUser.getList("events");
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.content_simplerow, eventsList);
        mainListView.setAdapter(adapter);

        Spinner spinner = (Spinner) findViewById(R.id.filter);
        ArrayAdapter<CharSequence> filterAdapter = ArrayAdapter.createFromResource(this, R.array.filterArray, android.R.layout.simple_spinner_item);
        filterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(filterAdapter);

        Button createActivityBtn = (Button) findViewById(R.id.createEvent_MyEvent);
        createActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyEvents.this, CreateEvents.class));
            }
        });

        Button joinBtn = (Button)findViewById(R.id.events);
        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyEvents.this, JoinEvents.class));
            }
        });

        Button logoutBtn = (Button) findViewById(R.id.logoutButton);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.getCurrentUser().logOut();
                startActivity(new Intent(MyEvents.this, LoginActivity.class));
            }
        });
    }
//    public void goToCreateEvent(View view) {
    public void goToCreateEvent() {

        Intent intent = new Intent(this, CreateEvents.class);
        startActivity(intent);
    }
//    public void createActivity(View view) {
//        // do something in response to button
//        Intent intent = new Intent(this, CreateEvent.class );
//        startActivity(intent);
//    }



//    goToCreateEvent

}
