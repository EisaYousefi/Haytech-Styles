package com.haytech.haytechstyles;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;

public class PhoneFieldEditText extends TextInputEditText {
    public PhoneFieldEditText(@NonNull Context context) {
        super(context);
    }

    public PhoneFieldEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PhoneFieldEditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
