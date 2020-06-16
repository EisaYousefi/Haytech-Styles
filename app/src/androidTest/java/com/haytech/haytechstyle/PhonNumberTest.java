package com.haytech.haytechstyle;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class PhonNumberTest {

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void validPhone() {
        onView(withId(R.id.phoneFieldLayout)).perform(click());
        onView(withId(R.id.phoneFieldEditText)).perform(typeText("09192562910"));
    }

    @Test
    public void notValidPhone() {
        onView(withId(R.id.phoneFieldLayout)).perform(click());
        onView(withId(R.id.phoneFieldEditText)).perform(typeText("09002562910"));
    }

    @Test
    public void notValidStartPhone() {
        onView(withId(R.id.phoneFieldLayout)).perform(click());
        onView(withId(R.id.phoneFieldEditText)).perform(typeText("022222222222dfgdfgdfg"));
    }

    @Test
    public void emptyPhone() {
        onView(withId(R.id.phoneFieldLayout)).perform(click());
        onView(withId(R.id.phoneFieldEditText)).perform(clearText());
    }

    @Test
    public void notEnough() {
        onView(withId(R.id.phoneFieldLayout)).perform(click());
        onView(withId(R.id.phoneFieldEditText)).perform(typeText("0919256"));
    }


}
