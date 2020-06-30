package com.haytech.haytechstyle;


import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ExpandableLayoutTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest2() {
        ViewInteraction myTextChecker = onView(
                allOf(withId(R.id.myTextChecker), isDisplayed()));
        myTextChecker.perform(click());

        ViewInteraction materialTextView = onView(
                allOf(withId(R.id.tv_read_more), withText("مشخصات کارشناس"), isDisplayed()));
        materialTextView.perform(click());

        ViewInteraction myTextChecker2 = onView(
                allOf(withId(R.id.myTextChecker), isDisplayed()));
        myTextChecker2.perform(click());

        ViewInteraction myTextChecker3 = onView(
                allOf(withId(R.id.myTextChecker), isDisplayed()));
        myTextChecker3.perform(click());
    }
}
