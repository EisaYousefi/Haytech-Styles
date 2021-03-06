package com.haytech.haytechstyles.date;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputLayout;
import com.haytech.haytechstyles.R;
import com.haytech.haytechstyles.Validation;
import com.haytech.haytechstyles.utils.Utils;

import java.util.Objects;

public class DateFieldLayout extends TextInputLayout {

   // private static final int DEFAULT_ICON = R.drawable.edit_text_ok;
    //private int iconOk = DEFAULT_ICON ;
    private Validation.UsernameValidator usernameValidatorListener;


    public DateFieldLayout(@NonNull Context context) {
        super(context);
        init(context , null);
    }

    public DateFieldLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context , attrs);
    }

    public DateFieldLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context , attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs , R.styleable.UsernameFieldLayout);
        //iconOk = typedArray.getResourceId(R.styleable.UsernameFieldLayout_ufl_icon,DEFAULT_ICON);
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
            if (usernameValidatorListener!= null)
                usernameValidatorListener.userNameListener(s.toString().length());
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
           // setEndIconDrawable(getResources().getDrawable(iconOk));
        } else
            setEndIconDrawable(null);
    }

    private void emptyEditText() {
        setEndIconVisible(false);
        setEndIconDrawable(null);
        setBoxStrokeErrorColor(ColorStateList.valueOf(getResources().getColor(R.color.colorRed)));
        setErrorTextColor(ColorStateList.valueOf(getResources().getColor(R.color.colorRed)));
        setError(getResources().getString(R.string.usernameEmpty));
    }

    public void setNotValidationError(int error) {
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
    }

    public void setUsernameValidatorListener(Validation.UsernameValidator usernameValidatorListener) {
        this.usernameValidatorListener = usernameValidatorListener;
    }

    private void showKeyboard(){
        Utils.showKeyboard( getContext() ,getEditText());
    }

}