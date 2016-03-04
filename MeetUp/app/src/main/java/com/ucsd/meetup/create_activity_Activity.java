package com.ucsd.meetup;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class create_activity_Activity extends AppCompatActivity {
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
//                startActivity(new Intent(create_activity_Activity.this, MyEvent.class));
            }
        });
    }

    public void attemptCreate(){
        mActName = ((EditText) findViewById(R.id.actName)).getText().toString();
        mLoc = ((EditText) findViewById(R.id.loc)).getText().toString();
        mDate = ((EditText) findViewById(R.id.date)).getText().toString();
        mType = ((EditText) findViewById(R.id.type)).getText().toString();
        mDes = ((EditText) findViewById(R.id.description)).getText().toString();
        CreateEventTask createEvent = new CreateEventTask(mActName, mLoc, mDate, mType, mDes);
        createEvent.doInBackground();
    }
    public class CreateEventTask extends AsyncTask<Void, Void, Boolean> {

        private final String mActName, mLoc, mDate, mType, mDesc;

        CreateEventTask(String name, String loc, String date, String type, String desc){
            mActName = name; mLoc = loc; mDate = date; mType = type; mDesc = desc;
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
