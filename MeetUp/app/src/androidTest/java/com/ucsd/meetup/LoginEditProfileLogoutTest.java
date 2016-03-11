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

// Note: Test is working
public class LoginEditProfileLogoutTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule(LoginActivity.class);

    @Test
    public void loginAndEditProfile() {
        /* Login */
        onView(withId(R.id.email)).perform(replaceText("a@a.com"));
        onView(withId(R.id.password)).perform(replaceText("aaaaaa"));
        onView(withId(R.id.email_sign_in_button)).perform(click());

        /* Edit Profile */
        onView(withId(R.id.profileButton)).perform(click());
        onView(withId(R.id.fullNameIn)).perform(replaceText("FullNameTest"));
        onView(withId(R.id.cityIn)).perform(replaceText("CityTest"));
        onView(withId(R.id.stateIn)).perform(replaceText("StateTest"));
        onView(withId(R.id.zipIn)).perform(replaceText("ZipTest"));
        onView(withId(R.id.editBtn)).perform(click());

        /* Check that Edits have been confirmed */
        onView(withId(R.id.profileButton)).perform(click());
        onView(withId(R.id.fullNameIn)).check(matches(withText("FullNameTest")));
        onView(withId(R.id.cityIn)).check(matches(withText("CityTest")));
        onView(withId(R.id.stateIn)).check(matches(withText("StateTest")));
        onView(withId(R.id.zipIn)).check(matches(withText("ZipTest")));
        onView(withId(R.id.editBtn)).perform(click());

        /* Logout to homescreen */
        onView(withId(R.id.logoutButton)).perform(click());
        onView(withId(R.id.email_sign_in_button)).check(matches(withText("Log In")));
    }
}
