package com.ucsd.meetup;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsAnything.anything;


/**
 * Created by AnnLonea on 3/7/16.
 */

// Note: Test is working. Mark 3-11-16
/*
android.support.test.espresso.NoMatchingViewException: No views in hierarchy found matching: with id: com.ucsd.meetup:id/eventsList

View Hierarchy:
+>DecorView{id=-1, visibility=VISIBLE, width=1080, height=1920, has-focus=true, has-focusable=true, has-window-focus=true, is-clickable=false, is-enabled=true, is-focused=false, is-focusable=false, is-layout-requested=false, is-selected=false, root-is-layout-requested=false, has-input-connection=false, x=0.0, y=0.0, child-count=1}


 */
public class LoginCreateEventLogoutTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule(LoginActivity.class);

    @Test
    public void LoginCreateEvnetLogout() {

        /* Login */
        onView(withId(R.id.email)).perform(replaceText("a@a.com"));
        onView(withId(R.id.password)).perform(replaceText("aaaaaa"));
        onView(withId(R.id.email_sign_in_button)).perform(click());

        /* CreateEvent */
        onView(withId(R.id.createEvent_MyEvent)).perform(click());
        onView(withId(R.id.actName)).perform(replaceText("EventNameTest"));
        onView(withId(R.id.date)).perform(replaceText("00/00/00"));
        onView(withId(R.id.type)).perform(replaceText("EventTypeTest"));
        onView(withId(R.id.createBtn)).perform(click());

        /* Check that event details have been confirmed */
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onData(anything()).inAdapterView(withId(R.id.eventsList)).atPosition(0).check(matches(withText("00/00/00|EventNameTest|EventTypeTest")));


        /* Logout to homescreen */
        onView(withId(R.id.logoutButton)).perform(click());
        onView(withId(R.id.email_sign_in_button)).check(matches(withText("Log In")));
    }



}


