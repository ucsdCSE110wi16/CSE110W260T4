package com.ucsd.meetup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Details extends AppCompatActivity {
    private List<ParseObject> parseList = new ArrayList<>(1000);
    private List<ParseObject> parseList2 = new ArrayList<>(1000);
    private CreateTempEvent temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myevent_detail);

        TextView when = (TextView) findViewById(R.id.date);
        TextView name = (TextView) findViewById(R.id.actName);
        TextView where = (TextView) findViewById(R.id.where);
        TextView desc = (TextView) findViewById(R.id.descrip);
        TextView type = (TextView) findViewById(R.id.type);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("TempEvents");
        try {
             parseList = query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        query.whereEqualTo("Date", parseList.get(0).getString("Date"));
        try {
            query.getFirst().delete();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Events");
        query2.whereContains("Name", parseList.get(0).getString("Name"));
        try {
            parseList2 = query2.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String mDate = parseList2.get(0).getString("Date");
        String mName = parseList2.get(0).getString("Name");
        String mWhere = parseList2.get(0).getString("Location");
        String mDesc = parseList2.get(0).getString("Description");
        String mType = parseList2.get(0).getString("Type");

        when.setText("Date: " + mDate);
        name.setText("Event Name: " + mName);
        where.setText("Where: " + mWhere);
        desc.setText("Description: " + mDesc);
        type.setText("Type: " + mType);

        temp = new CreateTempEvent(mName, mDate, mType);
        Button joinBtn = (Button) findViewById(R.id.quitBtn);
        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToUserEvents(temp);
            }
        });
    }

    public void addToUserEvents(CreateTempEvent event){
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
                    startActivity(new Intent(Details.this, MyEvents.class));
                }
                else{
                    Log.d("events", e.getMessage());
                }
            }
        });
    }

    public class CreateTempEvent{

        private final String mActName, mDate, mType;

        CreateTempEvent(String name, String date, String type) {
            mActName = name;
            mDate = date;
            mType = type;
        }

        public String getString() {
            return this.mDate + "|" + this.mActName + "|" + this.mType;
        }
    }
}
