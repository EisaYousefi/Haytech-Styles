package com.haytech.haytechstyles.username;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputLayout;
import com.haytech.haytechstyles.R;
import com.haytech.haytechstyles.utils.Utils;

import java.util.Objects;

public class UsernameFieldLayout extends TextInputLayout {
    public UsernameFieldLayout(@NonNull Context context) {
        super(context);
    }

    public UsernameFieldLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public UsernameFieldLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
                emptyEditText(s.toString());
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
            setEndIconDrawable(getResources().getDrawable(R.drawable.edit_text_ok));
        } else
            setEndIconDrawable(null);
    }

    private void emptyEditText(String text) {
        setEndIconVisible(false);
        setEndIconDrawable(null);
        setBoxStrokeErrorColor(ColorStateList.valueOf(getResources().getColor(R.color.colorRed)));
        setErrorTextColor(ColorStateList.valueOf(getResources().getColor(R.color.colorRed)));
        setError("نام کاربری نباید خالی باشد");
    }

    public void setNotValidationError(int error) {
        if (getEditText().getText().toString().isEmpty()) {
            setError(getResources().getString(R.string.usernameEmpty));
            setBoxStrokeErrorColor(ColorStateList.valueOf(getResources().getColor(R.color.colorRed)));
            setErrorTextColor(ColorStateList.valueOf(getResources().getColor(R.color.colorRed)));
            getEditText().requestFocus();
            showKeyboard();
            return;
        }
        setError(getResources().getString(error));
        setBoxStrokeErrorColor(ColorStateList.valueOf(getResources().getColor(R.color.colorRed)));
        setErrorTextColor(ColorStateList.valueOf(getResources().getColor(R.color.colorRed)));
        setErrorTextColor(ColorStateList.valueOf(getResources().getColor(R.color.colorRed)));
        getEditText().requestFocus();
        showKeyboard();
    }

    private void showKeyboard(){
        Utils.showKeyboard((Activity) getContext() ,getEditText());
    }

}