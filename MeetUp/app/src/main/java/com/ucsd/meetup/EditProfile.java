package com.ucsd.meetup;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.widget.TextView;


import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.List;

public class EditProfile extends AppCompatActivity {

    private String mName, mCity, mState, mZip;
    private EditText mEmail, mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        // Puts a "Create Profile" Header on our Sign Up Page
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /* Changable Fields */
        /* password,email,fullName,city,state,zip */
        ParseUser currentUser = ParseUser.getCurrentUser();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");
//        query.whereEqualTo("objectId", (String)(currentUser.getObjectId()));
//        query.getInBackground((String)(currentUser.getObjectId()), new GetCallback<ParseObject>() {
        query.getInBackground((String)(currentUser.getObjectId()), new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    System.out.println("Retrieved " + object.getString("fullName") + " scores");
                    ((EditText) findViewById(R.id.fullNameIn)).setText(object.getString("fullName"), TextView.BufferType.EDITABLE);
                    ((EditText) findViewById(R.id.cityIn)).setText(object.getString("city"), TextView.BufferType.EDITABLE);
                    ((EditText) findViewById(R.id.stateIn)).setText(object.getString("state"), TextView.BufferType.EDITABLE);
                    ((EditText) findViewById(R.id.zipIn)).setText(object.getString("zip"), TextView.BufferType.EDITABLE);
                    System.out.println("Retrieved " + object.getString("fullName") + " scores");

                } else {
                    System.out.println("Error: " + e.getMessage());
//                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });

        if (currentUser != null) {
            System.out.println("ASDFJKL;!");

        } else {
            // show the signup or login screen
            // this should never happen
        }

//        mName = ((EditText) findViewById(R.id.fullNameIn)).getText().toString();
//        mCity = ((EditText) findViewById(R.id.cityIn)).getText().toString();
//        mState = ((EditText) findViewById(R.id.stateIn)).getText().toString();
//        mZip = ((EditText) findViewById(R.id.zipIn)).getText().toString();
//        mEmail = (EditText) findViewById(R.id.emailIn);
//        mPassword = (EditText) findViewById(R.id.passwordIn);

        Button mCancelButton = (Button) findViewById(R.id.cancelBtn);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditProfile.this, MyEvents.class));
            }
        });

        Button mEditButton = (Button) findViewById(R.id.editBtn);
        mEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptSignUp();

            }
        });
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptSignUp() {
        System.out.println("ATTEMPTING TO SIGN UP!");
        mName = ((EditText) findViewById(R.id.fullNameIn)).getText().toString();
        mCity = ((EditText) findViewById(R.id.cityIn)).getText().toString();
        mState = ((EditText) findViewById(R.id.stateIn)).getText().toString();
        mZip = ((EditText) findViewById(R.id.zipIn)).getText().toString();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");
        // Retrieve the object by id
        ParseUser currentUser = ParseUser.getCurrentUser();
        query.getInBackground((String) (currentUser.getObjectId()), new GetCallback<ParseObject>() {
            public void done(ParseObject user, ParseException e) {
                if (e == null) {
                    // Now let's update it with some new data. In this case, only cheatMode and score
                    // will get sent to the Parse Cloud. playerName hasn't changed.
                    user.put("fullName", mName);
                    user.put("city", mCity);
                    user.put("state", mState);
                    user.put("zip", mZip);
                    user.saveInBackground();
                    System.out.println("ATTEMPTING TO Edit!");

                    startActivity(new Intent(EditProfile.this, MyEvents.class));
                }
            }
        });
    }
}
//            user.signUpInBackground(new SignUpCallback() {
//                @Override
//                public void done(ParseException e) {
//                    if (e == null) {
//                        Log.d("username", "Edit successful");
////                        startActivity(new Intent(CreateProfile.this, CreateEvents.class));
//                        startActivity(new Intent(EditProfile.this, MyEvent.class));
//                    } else {
//                        Log.d("username", "Edit failed\n");
//                        e.printStackTrace();
//                    }
//                }
//            });
