package com.haytech.haytechstyles;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;

public class PasswordFieldEditText extends TextInputEditText {
    public PasswordFieldEditText(@NonNull Context context) {
        super(context);
    }

    public PasswordFieldEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PasswordFieldEditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
