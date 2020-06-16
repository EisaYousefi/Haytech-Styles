package com.haytech.haytechstyle;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class PasswordTest {

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void validUsernameAndEmptyPass() {
        onView(withId(R.id.usernameFieldLayout)).perform(click());
        onView(withId(R.id.usernameFieldEditText)).perform(typeText("eisa"));
        onView(withId(R.id.button)).perform(click());
    }

    @Test
    public void emptyPass() {
        onView(withId(R.id.usernameFieldLayout)).perform(click());
        onView(withId(R.id.usernameFieldEditText)).perform(typeText("eisa"));
        onView(withId(R.id.passwordFieldLayout)).perform(click());
        onView(withId(R.id.passwordFieldEditText)).perform(typeText(""));
        onView(withId(R.id.button)).perform(click());
    }


    @Test
    public void clearText() {
        onView(withId(R.id.passwordFieldLayout)).perform(click());
        onView(withId(R.id.passwordFieldEditText)).perform(typeText("eisa"));
        onView(withId(R.id.passwordFieldEditText)).perform(ViewActions.clearText());
    }


    @Test
    public void valid() {
        onView(withId(R.id.usernameFieldLayout)).perform(click());
        onView(withId(R.id.usernameFieldEditText)).perform(typeText("eisa"));
        onView(withId(R.id.passwordFieldLayout)).perform(click());
        onView(withId(R.id.passwordFieldEditText)).perform(typeText("eisa@2910"));
        onView(withId(R.id.button)).perform(click());
    }

    @Test
    public void weakPass() {
        onView(withId(R.id.passwordFieldLayout)).perform(click());
        onView(withId(R.id.passwordFieldEditText)).perform(typeText("eisa"));
    }

    @Test
    public void goodPass() {
        onView(withId(R.id.passwordFieldLayout)).perform(click());
        onView(withId(R.id.passwordFieldEditText)).perform(typeText("eisa@yousefi"));
    }

    @Test
    public void strokePass() {
        onView(withId(R.id.passwordFieldLayout)).perform(click());
        onView(withId(R.id.passwordFieldEditText)).perform(typeText("eisa@1234"));
    }





}
