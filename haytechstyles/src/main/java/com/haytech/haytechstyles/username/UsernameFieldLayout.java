package com.haytech.haytechstyles.username;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.TextViewCompat;

import com.google.android.material.textfield.TextInputLayout;
import com.haytech.haytechstyles.R;
import com.haytech.haytechstyles.Validation;
import com.haytech.haytechstyles.utils.Utils;

import java.util.Objects;

public class UsernameFieldLayout extends TextInputLayout {

    private static final int DEFAULT_ICON = R.drawable.edit_text_ok;
    private int iconOk = DEFAULT_ICON ;
    private Validation.UsernameValidator usernameValidator ;


    public UsernameFieldLayout(@NonNull Context context) {
        super(context);
        init(context , null);
    }

    public UsernameFieldLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context , attrs);
    }

    public UsernameFieldLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context , attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs , R.styleable.UsernameFieldLayout);
        iconOk = typedArray.getResourceId(R.styleable.UsernameFieldLayout_ufl_icon,DEFAULT_ICON);
        typedArray.recycle();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Objects.requireNonNull(this.getEditText()).addTextChangedListener(userNameChecker);
    }

    private TextWatcher userNameChecker = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @SuppressLint("WrongConstant")
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() == 0) {
                emptyEditText();
            } else {
                fullEditText(s.toString());
            }

        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    private void fullEditText(String text) {
        setError("");
        setEndIconVisible(true);
        if (text.length() > 0) {
            setEndIconDrawable(getResources().getDrawable(iconOk));
        } else
            setEndIconDrawable(null);
    }

    private void emptyEditText() {
        if (usernameValidator!=null)
            usernameValidator.userNameListener();
        setEndIconVisible(false);
        setEndIconDrawable(null);
        setBoxStrokeErrorColor(ColorStateList.valueOf(getResources().getColor(R.color.colorRed)));
        setErrorTextColor(ColorStateList.valueOf(getResources().getColor(R.color.colorRed)));
        setError(getResources().getString(R.string.usernameEmpty));
    }

    public void setNotValidationError(int error) {
        if (usernameValidator!=null)
            usernameValidator.userNameListener();
        isValidState(Objects.requireNonNull(getEditText()).getText().toString() ,error);
    }

    private void isValidState(String editTextValue ,int error) {
        if (editTextValue.isEmpty()) {
            setError(getResources().getString(R.string.usernameEmpty));
            setBoxStrokeErrorColor(ColorStateList.valueOf(getResources().getColor(R.color.colorRed)));
            setErrorTextColor(ColorStateList.valueOf(getResources().getColor(R.color.colorRed)));
            Objects.requireNonNull(getEditText()).requestFocus();
            showKeyboard();
        }else {
            setError(getResources().getString(error));
            setBoxStrokeErrorColor(ColorStateList.valueOf(getResources().getColor(R.color.colorRed)));
            setErrorTextColor(ColorStateList.valueOf(getResources().getColor(R.color.colorRed)));
            setErrorTextColor(ColorStateList.valueOf(getResources().getColor(R.color.colorRed)));
            Objects.requireNonNull(getEditText()).requestFocus();
            showKeyboard();
        }
        if (usernameValidator!=null)
            usernameValidator.userNameListener();
    }

    public void setUsernameValidator(Validation.UsernameValidator usernameValidator) {
        this.usernameValidator = usernameValidator;
    }

    private void showKeyboard(){
        Utils.showKeyboard( getContext() ,getEditText());
    }

}