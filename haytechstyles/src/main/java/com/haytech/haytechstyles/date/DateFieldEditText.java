package com.haytech.haytechstyles.date;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;

public class DateFieldEditText extends TextInputEditText {
    public DateFieldEditText(@NonNull Context context) {
        super(context);
        init(context,null,-1);
    }

    public DateFieldEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs , -1);
    }

    public DateFieldEditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs ,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {

    }



}
