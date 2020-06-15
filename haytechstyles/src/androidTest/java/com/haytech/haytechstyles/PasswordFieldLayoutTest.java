package com.haytech.haytechstyles;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.haytech.haytechstyles.password.PasswordFieldLayout;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class PasswordFieldLayoutTest {

    private  Context appContext;
    private PasswordFieldLayout passwordFieldLayout ;

    @Before
    public void before(){
        appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
       // passwordFieldLayout = new PasswordFieldLayout(App.getInstance());
    }
    @Test
    public void useAppContext() {
        // Context of the app under test.
        assertEquals("com.haytech.haytechstyles.test", appContext.getPackageName());
    }

    @Test
    public void test(){
       // passwordFieldLayout.setNotValidationError(R.string.empty_phone_number);
    }
}
