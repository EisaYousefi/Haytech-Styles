package com.haytech.haytechstyle;

import android.content.Context;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class UsernameTest {

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void notValidUsername() {
        onView(withId(R.id.usernameFieldLayout)).perform(click());
        onView(withId(R.id.usernameFieldEditText)).perform(typeText("eisa1"));
        onView(withId(R.id.button)).perform(click());
    }

    @Test
    public void emptyUsername() {
        onView(withId(R.id.usernameFieldLayout)).perform(click());
        onView(withId(R.id.usernameFieldEditText)).perform(clearText());
    }

    @Test
    public void validUsernameAndEmptyPass() {
        onView(withId(R.id.usernameFieldLayout)).perform(click());
        onView(withId(R.id.usernameFieldEditText)).perform(typeText("eisa"));
        onView(withId(R.id.button)).perform(click());
    }



    @Test
    public void valid() {
        onView(withId(R.id.usernameFieldLayout)).perform(click());
        onView(withId(R.id.usernameFieldEditText)).perform(typeText("eisa"));
        onView(withId(R.id.passwordFieldLayout)).perform(click());
        onView(withId(R.id.passwordFieldEditText)).perform(typeText("eisa@2910"));
        onView(withId(R.id.button)).perform(click());
    }


}
