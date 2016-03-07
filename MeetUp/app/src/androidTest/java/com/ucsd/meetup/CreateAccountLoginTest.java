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
//EditText fullNamein, emailIn, cityIn, stateIn, passwordIn, zipIn

    public void createLogin() {

        /* Create Account */
        onView(withId(R.id.registerIn)).perform(click());

        onView(withId(R.id.fullNameIn)).perform(replaceText("test123"));
        onView(withId(R.id.emailIn)).perform(replaceText("test123@ucsd.edu"));
        onView(withId(R.id.cityIn)).perform(replaceText("San Diego"));
        onView(withId(R.id.stateIn)).perform(replaceText("CA"));
        onView(withId(R.id.passwordIn)).perform(replaceText("123123"));
        onView(withId(R.id.zipIn)).perform(replaceText("92092"));

    }

// Field Names: actName, loc, date, type, description
// Button Name: createBtn
//    public void checkCreateEvent() {
//        onView(withId(R.id.actName)).perform(replaceText("activityName"));
//        onView(withId(R.id.loc)).perform(replaceText("loc"));
//        onView(withId(R.id.date)).perform(replaceText("date"));
//        onView(withId(R.id.type)).perform(replaceText("type"));
//        onView(withId(R.id.description)).perform(replaceText("description"));
//
//        onView(withId(R.id.createBtn)).perform(click());
//    }


}
