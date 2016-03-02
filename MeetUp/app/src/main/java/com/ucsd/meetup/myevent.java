package com.ucsd.meetup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Lawrence on 2/21/2016.
 */
public class MyEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myevent);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        Button mCreateEventButton = (Button) findViewById(R.id.createEvent_MyEvent);
        mCreateEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyEvent.this, CreateEvent.class));
            }
        });


    }
//    public void goToCreateEvent(View view) {
    public void goToCreateEvent() {

        Intent intent = new Intent(this, CreateEvent.class);
        startActivity(intent);
    }
//    public void createActivity(View view) {
//        // do something in response to button
//        Intent intent = new Intent(this, CreateEvent.class );
//        startActivity(intent);
//    }



//    goToCreateEvent

}
