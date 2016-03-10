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
        onView(withId(R.id.loc)).perform(replaceText("EventCityTest"));
        onView(withId(R.id.date)).perform(replaceText("mm/dd/yy"));
        onView(withId(R.id.type)).perform(replaceText("EventTypeTest"));
        onView(withId(R.id.description)).perform(replaceText("DesTest"));
        onView(withId(R.id.createBtn)).perform(click());

        /* Check that event details have been confirmed */
        onData(anything()).inAdapterView(withId(R.id.eventsList)).atPosition(0).perform(click());
        onView(withId(R.id.actName)).check(matches(withText("EventNameTest")));
        onView(withId(R.id.loc)).check(matches(withText("EventCityTest")));
        onView(withId(R.id.date)).check(matches(withText("mm/dd/yy")));
        onView(withId(R.id.type)).check(matches(withText("EventTypeTest")));
        onView(withId(R.id.description)).check(matches(withText("DesTest")));


        /* Logout to homescreen */
        onView(withId(R.id.logoutButton)).perform(click());
        onView(withId(R.id.email_sign_in_button)).check(matches(withText("Log In")));
    }



}


