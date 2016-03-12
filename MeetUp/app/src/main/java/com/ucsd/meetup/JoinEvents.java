package com.ucsd.meetup;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JoinEvents extends AppCompatActivity {

    private ListView joinListView;
    private List<String> theList = new ArrayList<>(1000);
    private List<ParseObject> parseList = new ArrayList<>(1000);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_events);

        /* populate the events list */
        joinListView = (ListView) findViewById(R.id.joinEventsList);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Events");
        try {
            parseList = query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < parseList.size(); i++) {
            theList.add(parseList.get(i).getString("Date") + "|" + parseList.get(i).getString("Name") + "|" + parseList.get(i).getString("Type"));
        }
        Collections.sort(theList);
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.content_simplerow, theList);
        joinListView.setAdapter(adapter);

        /* populate the filter */
        Spinner spinner = (Spinner) findViewById(R.id.joinEventsFilter);
        ArrayAdapter<CharSequence> filterAdapter = ArrayAdapter.createFromResource(this, R.array.filterArray, android.R.layout.simple_spinner_item);
        filterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(filterAdapter);
        /* decide which filter to use */
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                if (item.toString().equals("Date"))
                    listByDate();
                if (item.toString().equals("Type"))
                    listByType();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /* pull up details about event */
        joinListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = theList.get(position);
                String date = "";
                String name = "";
                String type = "";

                int i = 0;
                while(item.charAt(i) != '|'){
                    date += item.charAt(i);
                    i++;
                }
                i++;
                while(item.charAt(i) != '|'){
                    name += item.charAt(i);
                    i++;
                }
                i++;
                while(i < item.length()){
                    type += item.charAt(i);
                    i++;
                }

                ParseObject object = new ParseObject("TempEvents");
                object.put("Date", date);
                object.put("Name", name);
                object.saveInBackground();
                startActivity(new Intent(JoinEvents.this, Details.class));
            }
        });
    }
    /* populate the list by dates */
    public void listByDate(){
        joinListView = (ListView) findViewById(R.id.joinEventsList);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Events");
        try {
            parseList = query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        theList.clear();
        for (int i = 0; i < parseList.size(); i++) {
            theList.add(parseList.get(i).getString("Date") + "|" + parseList.get(i).getString("Name") + "|" + parseList.get(i).getString("Type"));
        }
        Collections.sort(theList);
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.content_simplerow, theList);
        joinListView.setAdapter(adapter);
    }
    /* populate the list by type */
    public void listByType(){
        joinListView = (ListView) findViewById(R.id.joinEventsList);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Events");
        try {
            parseList = query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        theList.clear();
        for (int i = 0; i < parseList.size(); i++) {
            theList.add(parseList.get(i).getString("Type") + "|" + parseList.get(i).getString("Name") + "|" + parseList.get(i).getString("Date"));
        }
        Collections.sort(theList);
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.content_simplerow, theList);
        joinListView.setAdapter(adapter);
    }
}
