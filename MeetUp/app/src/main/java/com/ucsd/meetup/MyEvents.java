package com.ucsd.meetup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by Lawrence on 2/21/2016.
 */
public class MyEvents extends AppCompatActivity {

    private ListView mainListView;
    private List<String> eventsList = new ArrayList<>(1000);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myevent);

        /* populate the list with current User's events */
        mainListView = (ListView) findViewById(R.id.eventsList);
        ParseUser currUser = ParseUser.getCurrentUser();
        eventsList = currUser.getList("events");
        if(eventsList != null) {
            Collections.sort(eventsList);
            ArrayAdapter adapter = new ArrayAdapter(this, R.layout.content_simplerow, eventsList);
            mainListView.setAdapter(adapter);
        }

        /* populate filter */
        Spinner spinner = (Spinner) findViewById(R.id.filter);
        ArrayAdapter<CharSequence> filterAdapter = ArrayAdapter.createFromResource(this, R.array.filterArray, android.R.layout.simple_spinner_item);
        filterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(filterAdapter);

        /* decide which filter is being use */
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                if(item.toString().equals("Date"))
                    listByDate();
                if(item.toString().equals("Type"))
                    listByType();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /* Display details about the event when User clicks it */
        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = eventsList.get(position);
                String date = "";
                String name = "";
                String type = "";

                int i = 0;
                while (item.charAt(i) != '|') {
                    date += item.charAt(i);
                    i++;
                }
                i++;
                while (item.charAt(i) != '|') {
                    name += item.charAt(i);
                    i++;
                }
                i++;
                while (i < item.length()) {
                    type += item.charAt(i);
                    i++;
                }

                ParseObject object = new ParseObject("TempEvents");
                object.put("Date", date);
                object.put("Name", name);
                object.saveInBackground();
                startActivity(new Intent(MyEvents.this, QuitEvent.class));
            }
        });

        /* create button will pull up Create Events page */
        Button createActivityBtn = (Button) findViewById(R.id.createEvent_MyEvent);
        createActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyEvents.this, CreateEvents.class));
            }
        });

        /* join button will pull up Join Events page */
        Button joinBtn = (Button)findViewById(R.id.events);
        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyEvents.this, JoinEvents.class));
            }
        });

        /* logout the user */
        Button logoutBtn = (Button) findViewById(R.id.logoutButton);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.getCurrentUser().logOut();
                startActivity(new Intent(MyEvents.this, LoginActivity.class));
            }
        });
    }

    /* populate the list by dates */
    public void listByDate(){
        mainListView = (ListView) findViewById(R.id.eventsList);
        ParseUser currUser = ParseUser.getCurrentUser();
        eventsList = currUser.getList("events");
        if(!eventsList.isEmpty()) {
            Collections.sort(eventsList);
            ArrayAdapter adapter = new ArrayAdapter(this, R.layout.content_simplerow, eventsList);
            mainListView.setAdapter(adapter);
        }
    }
    /* populate the list by type */
    public void listByType(){
        mainListView = (ListView) findViewById(R.id.eventsList);
        ParseUser currUser = ParseUser.getCurrentUser();
        eventsList = currUser.getList("eventsByType");
        if(!eventsList.isEmpty()) {
            Collections.sort(eventsList);
            ArrayAdapter adapter = new ArrayAdapter(this, R.layout.content_simplerow, eventsList);
            mainListView.setAdapter(adapter);
        }
    }
}
