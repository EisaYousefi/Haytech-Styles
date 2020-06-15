package com.haytech.haytechstyles;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;

public final class Utils {
    public static void hideKeyboard(@NonNull Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        View focusView = activity.getCurrentFocus();
        if (focusView != null) {
            inputMethodManager.hideSoftInputFromWindow(focusView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
        if (activity.getCurrentFocus() != null) {
            activity.getCurrentFocus().clearFocus();
        }
    }

    public static void showKeyboard(@NonNull Context context, View v1) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(v1, InputMethodManager.SHOW_IMPLICIT);
    }

}
