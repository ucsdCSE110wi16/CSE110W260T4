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
public class LoginEditProfileLogoutTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule(LoginActivity.class);

    @Test
    public void checkLogin() {
        onView(withId(R.id.email)).perform(replaceText("test@ucsd.edu"));
        onView(withId(R.id.password)).perform(replaceText("123123"));
        onView(withId(R.id.email_sign_in_button)).perform(click());
    }




}
