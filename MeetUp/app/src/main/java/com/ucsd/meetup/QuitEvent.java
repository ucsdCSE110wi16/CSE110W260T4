package com.ucsd.meetup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class QuitEvent extends AppCompatActivity {
    private List<ParseObject> parseList = new ArrayList<>(1000);
    private List<ParseObject> parseList2 = new ArrayList<>(1000);
    private String eventToDeleteByDate, eventToDeleteByType;
    private List<String> eventsList, eventsListByType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quit_event);

        TextView when = (TextView) findViewById(R.id.date);
        TextView name = (TextView) findViewById(R.id.actName);
        TextView where = (TextView) findViewById(R.id.where);
        TextView desc = (TextView) findViewById(R.id.descrip);
        TextView type = (TextView) findViewById(R.id.type);

        /* pull the selected event from Parse */
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

        /* display the event details */
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

        ParseUser currUser = ParseUser.getCurrentUser();
        eventsList = currUser.getList("events");
        eventsListByType = currUser.getList("eventsByType");
        eventToDeleteByDate = mDate + "|" + mName + "|" + mType;
        eventToDeleteByType = mType + "|" + mName + "|" + mDate;

        /* quit button if user wants to leave event */
        Button quitBtn = (Button) findViewById(R.id.quitBtn);
        quitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventsList.remove(eventToDeleteByDate);
                eventsListByType.remove(eventToDeleteByType);
                startActivity(new Intent(QuitEvent.this, MyEvents.class));
            }
        });
    }

}
