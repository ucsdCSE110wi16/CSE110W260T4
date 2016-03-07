package com.ucsd.meetup;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.Arrays;
import java.util.List;

public class CreateEvents extends AppCompatActivity {
    private String mActName, mLoc, mDate, mType, mDes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button createBtn = (Button)findViewById(R.id.createBtn);
        createBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                attemptCreate();
//                startActivity(new Intent(CreateEvents.this, myevent.class));
            }
        });
    }

    public void attemptCreate(){
        /* grab user input */
        mActName = ((EditText) findViewById(R.id.actName)).getText().toString();
        mLoc = ((EditText) findViewById(R.id.loc)).getText().toString();
        mDate = ((EditText) findViewById(R.id.date)).getText().toString();
        mType = ((EditText) findViewById(R.id.type)).getText().toString();
        mDes = ((EditText) findViewById(R.id.description)).getText().toString();

        /* create the event and store into Parse*/
        CreateEventTask event = new CreateEventTask(mActName, mLoc, mDate, mType, mDes);
        event.doInBackground();

        /* store into current user's events */
        ParseUser user = ParseUser.getCurrentUser();
        List<String> list = user.getList("events");
        String[] myEvents = list.toArray(new String[list.size()+1]);
        myEvents[list.size()] = event.getString();
        user.put("events", Arrays.asList(myEvents));
        user.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    /* go back to main page */
                    startActivity(new Intent(CreateEvents.this, myevent.class));
                }
                else{
                    Log.d("events", e.getMessage());
                }
            }
        });
    }
    public class CreateEventTask extends AsyncTask<Void, Void, Boolean> {

        private final String mActName, mLoc, mDate, mType, mDesc;

        CreateEventTask(String name, String loc, String date, String type, String desc){
            mActName = name; mLoc = loc; mDate = date; mType = type; mDesc = desc;
        }

        public String getString(){
            return this.mDate + " | " + this.mActName + " | " + this.mType;
        }

        protected Boolean doInBackground(Void... params) {
            ParseObject events = new ParseObject("Events");
            events.put("Name", mActName);
            events.put("Location", mLoc);
            events.put("Date", mDate);
            events.put("Type", mType);
            events.put("Description", mDesc);
            events.saveInBackground();

            return true;
        }
    }
}
