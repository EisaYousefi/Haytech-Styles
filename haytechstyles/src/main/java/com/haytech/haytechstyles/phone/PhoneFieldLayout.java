package com.haytech.haytechstyles.phone;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputLayout;
import com.haytech.haytechstyles.R;
import com.haytech.haytechstyles.Validation;

import java.util.Objects;

public class PhoneFieldLayout extends TextInputLayout {


    private Validation.phoneValidator validatorListener;

    public PhoneFieldLayout(@NonNull Context context) {
        super(context);
    }

    public PhoneFieldLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PhoneFieldLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Validation.phoneValidator getValidatorListener() {
        return validatorListener;
    }

    public void setValidatorListener(Validation.phoneValidator validatorListener) {
        this.validatorListener = validatorListener;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Objects.requireNonNull(getEditText()).addTextChangedListener(phoneTextWatcher);
        getEditText().setInputType(InputType.TYPE_CLASS_PHONE);
    }

    private TextWatcher phoneTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!s.toString().startsWith("09")) {
                notValidStartPhoneNumber();
            }

            boolean isValid = (s.toString().length() == 11 && !s.toString().substring(2, 4).equals("00") && !s.toString().startsWith("09")) && (!s.toString().isEmpty());
            boolean isNotValid = 0 < s.toString().length() && s.toString().length() <= 11;

            if (validatorListener != null) {
                if (!s.toString().startsWith("09")) {
                    validatorListener.startPhoneNumber(s.toString());
                }

                if (isValid) {
                    validatorListener.validPhoneNumber();
                }

                if (s.toString().length() == 0) {
                    validatorListener.emptyPhoneNumber();
                }

                if (isNotValid) {
                    validatorListener.notValidationPhoneNumber(s.toString());
                }
            } else {
                if (!s.toString().startsWith("09")) {
                    notValidStartPhoneNumber();
                }

                if (isValid) {
                    setError("");
                }

                if (s.toString().length() == 0) {
                    empty();
                }

                if (isNotValid) {
                    notValidPhoneNumber(s.toString());
                }
            }
        }
    };

    public void notValidPhoneNumber(String lengthPhoneNumber) {
        if (lengthPhoneNumber.length() > 3 && lengthPhoneNumber.substring(2, 4).equals("00")) {
            setError(getResources().getString(R.string.not_valid_phone_number));
        } else if (lengthPhoneNumber.length() == 11 && lengthPhoneNumber.substring(2, 4).equals("00")) {
            setError(getResources().getString(R.string.not_valid_phone_number));
        } else if (lengthPhoneNumber.length() < 11) {
            setError(getResources().getString(R.string.length_phone_number));
        } else {
            setError("");
        }
    }

    public void empty() {
        setError(getResources().getString(R.string.empty_phone_number));
    }

    public void notValidStartPhoneNumber() {
        setError(getResources().getString(R.string.start_phone_number));
    }

    public void notValidPhoneNumber( String phoneNumber,int notValidPhoneNumber , int lowerBound) {
        if (phoneNumber.length() > 3 && phoneNumber.substring(2, 4).equals("00")) {
            setError(getResources().getString(notValidPhoneNumber));
        } else if (phoneNumber.length() == 11 && phoneNumber.substring(2, 4).equals("00")) {
            setError(getResources().getString(notValidPhoneNumber));
        } else if (phoneNumber.length() < 11) {
            setError(getResources().getString(lowerBound));
        } else {
            setError("");
        }
    }

    public void empty(int emptyPhoneNumber) {
        setError(getResources().getString(emptyPhoneNumber));
    }

    public void notValidStartPhoneNumber(int startPhoneNumber ) {
        setError(getResources().getString(startPhoneNumber));
    }
}

