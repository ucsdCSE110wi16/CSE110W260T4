package com.ucsd.meetup;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.ArrayList;

public class CreateProfile extends AppCompatActivity {

    private String mName, mCity, mState, mZip;
    private EditText mEmail, mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        // Puts a "Create Profile" Header on our Sign Up Page
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button mCancelButton = (Button) findViewById(R.id.cancelBtn);
        mCancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(CreateProfile.this, LoginActivity.class));
            }
        });

        Button mCreateButton = (Button) findViewById(R.id.createBtn);
        mCreateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
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

        // Reset errors
        mEmail.setError(null);
        mPassword.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password
        if (TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPassword.setError(getString(R.string.error_invalid_password));
            focusView = mPassword;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmail.setError(getString(R.string.error_field_required));
            focusView = mEmail;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmail.setError(getString(R.string.error_invalid_email));
            focusView = mEmail;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            mName = ((EditText) findViewById(R.id.fullNameIn)).getText().toString();
            mCity = ((EditText) findViewById(R.id.cityIn)).getText().toString();
            mState = ((EditText) findViewById(R.id.stateIn)).getText().toString();
            mZip = ((EditText) findViewById(R.id.zipIn)).getText().toString();
            mEmail = (EditText) findViewById(R.id.emailIn);
            mPassword = (EditText) findViewById(R.id.passwordIn);

            CreateUserTask createUser = new CreateUserTask(email, password, mName, mCity, mState, mZip);
            createUser.doInBackground();
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        if((email.contains(".com") || email.contains(".cn")
                || (email.contains(".edu")) && email.contains("@"))) {
            return email.length() > 3;
        }
        return false;
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    public class CreateUserTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail, mPassword, mName, mCity, mState, mZip;
        //private ArrayList<create_activity_Activity.CreateEventTask> myEvents[];

        CreateUserTask(String email, String password, String name, String city, String state, String zip){
            mEmail = email; mPassword = password; mName = name; mCity = city; mState = state; mZip = zip;
        }

        protected Boolean doInBackground(Void... params) {
            ParseUser user = new ParseUser();
            user.setUsername(mEmail);
            user.setPassword(mPassword);
            user.setEmail(mEmail);

            user.put("fullName", mName);
            user.put("city", mCity);
            user.put("state", mState);
            user.put("zip", mZip);
            //user.put("events", myEvents);

            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        Log.d("username", "Sign up successful");
                        startActivity(new Intent(CreateProfile.this, create_activity_Activity.class));
                    } else {
                        Log.d("username", "Sign up failed\n");
                        e.printStackTrace();
                    }
                }
            });

            return true;
        }
    }
}
