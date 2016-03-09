package com.ucsd.meetup;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Lawrence on 3/6/2016.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class CreateAccountLoginTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule(LoginActivity.class);

    @Test
    public void createLogin() {

        /* Create Account */
        onView(withId(R.id.registerIn)).perform(click());

        onView(withId(R.id.fullNameIn)).perform(replaceText("test123"));
        onView(withId(R.id.emailIn)).perform(replaceText("test123@ucsd.edu"));
        onView(withId(R.id.cityIn)).perform(replaceText("San Diego"));
        onView(withId(R.id.stateIn)).perform(replaceText("CA"));
        onView(withId(R.id.passwordIn)).perform(replaceText("123123"));
        onView(withId(R.id.zipIn)).perform(replaceText("92092"));

        /* Creates the Account for the first time */
        onView(withId(R.id.createBtn)).perform(click());

        /* Cancels the create account if already created */
        onView(withId(R.id.cancelBtn)).perform(click());

        /* Logout to Homescreen */
//        onView(withId(R.id.logoutButton)).perform(click());
//        onView(withId(R.id.email_sign_in_button)).check(matches(withText("Log In")));
    }
}
