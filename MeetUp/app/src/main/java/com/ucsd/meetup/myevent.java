package com.ucsd.meetup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.parse.ParseUser;

import java.util.ArrayList;


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

        Button createActivityBtn = (Button) findViewById(R.id.createEvent_MyEvent);
        createActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyEvent.this, CreateEvents.class));
            }
        });

        Button logoutBtn = (Button) findViewById(R.id.logoutButton);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.getCurrentUser().logOut();
                startActivity(new Intent(MyEvent.this, LoginActivity.class));
            }
        });

        Button profileBtn = (Button) findViewById(R.id.profileButton);
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("EDIT PROFILE CLICKED!");
                startActivity(new Intent(MyEvent.this, EditProfile.class));
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
