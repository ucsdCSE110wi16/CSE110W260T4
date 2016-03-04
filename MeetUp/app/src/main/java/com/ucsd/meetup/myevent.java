package com.ucsd.meetup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.json.JSONArray;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Lawrence on 2/21/2016.
 */
public class MyEvent extends AppCompatActivity {
    private ListView mainListView;
    //private ArrayAdapter<String> listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myevent);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        mainListView = (ListView) findViewById(R.id.eventsList);
        ArrayList<String> eventsList = new ArrayList<String>();
        //eventsList.addAll(Arrays.asList(events));
        //ArrayAdapter adapter = new ArrayAdapter(this, R.layout.content_simplerow, eventsList);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, eventsList);
        mainListView.setAdapter(adapter);

        Spinner spinner = (Spinner) findViewById(R.id.filter);
        ArrayAdapter<CharSequence> filterAdapter = ArrayAdapter.createFromResource(this, R.array.filterArray, android.R.layout.simple_spinner_item);
        filterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(filterAdapter);

        Button createActivityBtn = (Button) findViewById(R.id.newEvent);
        createActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyEvent.this, create_activity_Activity.class));
            }
        });
    }

    public void createActivity(View view) {
        // do something in response to button
        Intent intent = new Intent(this, create_activity_Activity.class );
        startActivity(intent);
    }
}
