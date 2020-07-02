package com.haytech.haytechstyles.password;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.TextViewCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.haytech.haytechstyles.R;
import com.haytech.haytechstyles.utils.ThreadUtils;
import com.haytech.haytechstyles.utils.Utils;
import com.haytech.haytechstyles.Validation;

import java.util.Objects;

public class PasswordFieldLayout extends TextInputLayout {

    public static final int RANK_WEAK = 4;
    public static final int RANK_STRONG = 5;

    private boolean legend = false;
    private int count = 0;

    private int colorWeakPassBoxStroke = 0;
    private int colorGoodPassBoxStroke = 0;
    private int colorStrongPassBoxStroke = 0;

    private int errorTextColorWeakPass = 0;
    private int errorTextColorGoodPass = 0;
    private int errorTextColorStrokePass = 0;


    private int iconColor = 0;
    private int boxStrokeErrorColor = 0;
    private int textGoodPass;
    private int textWeakPass;
    private int textStrongPass;
    int colorTextWeakPass = 0;
    int colorTextGoodPass = 0;
    boolean flagValidationPass = false;

    private Validation.PassValidator.ValueText valueTextListener;


    public PasswordFieldLayout(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public PasswordFieldLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public PasswordFieldLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Objects.requireNonNull(this.getEditText()).addTextChangedListener(passwordStrengthChecker);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PasswordFieldLayout);
        colorWeakPassBoxStroke = typedArray.getColor(R.styleable.PasswordFieldLayout_FP_box_stroke_color_weak_pass,
                getResources().getColor(R.color.black));
        colorGoodPassBoxStroke = typedArray.getColor(R.styleable.PasswordFieldLayout_FP_box_stroke_color_good_pass,
                getResources().getColor(R.color.black));
        colorStrongPassBoxStroke = typedArray.getColor(R.styleable.PasswordFieldLayout_FP_box_stroke_color_stroke_pass,
                getResources().getColor(R.color.black));


        errorTextColorGoodPass = typedArray.getColor(R.styleable.PasswordFieldLayout_FP_error_text_color_good_pass,
                getResources().getColor(R.color.colorRed));
        errorTextColorWeakPass = typedArray.getColor(R.styleable.PasswordFieldLayout_FP_error_text_color_weak_pass,
                getResources().getColor(R.color.colorRed));
        errorTextColorStrokePass = typedArray.getColor(R.styleable.PasswordFieldLayout_FP_error_text_color_stroke_pass,
                getResources().getColor(R.color.colorRed));

        iconColor = typedArray.getColor(R.styleable.PasswordFieldLayout_ctl_icon_color, getResources().getColor(R.color.icon_color));
        boxStrokeErrorColor = typedArray.getColor(R.styleable.PasswordFieldLayout_FP_boxStrokeErrorColor, Color.RED);

        flagValidationPass = typedArray.getBoolean(R.styleable.PasswordFieldLayout_FP_flag_validation, false);
        typedArray.recycle();

        textWeakPass = R.string.password_weak;
        textGoodPass = R.string.password_good;
        textStrongPass = R.string.password_strong;
    }

    public void setLegend(boolean legend) {
        this.legend = legend;
    }

    private TextWatcher passwordStrengthChecker = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            if (charSequence.toString().length() == 0)
                emptyEditText(charSequence);
            else
                fullEditText(charSequence);
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };

    private void emptyEditText(CharSequence charSequence) {
        setBoxStrokeErrorColor(ColorStateList.valueOf(getResources().getColor(R.color.colorRed)));
        setErrorTextColor(ColorStateList.valueOf(getResources().getColor(R.color.colorRed)));
        setError("رمز عبور نباید خالی باشد");
        if (isFlagValidationPass()) {
            ThreadUtils.onUI(() -> {
                        count = 0;
                        isCounterEnabled();
                        setError("");
                        setPasswordVisibilityToggleEnabled(false);
                    }
                    , 1500);
        } else {

        }

        setPasswordVisibilityToggleTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorRed)));

    }

    private void fullEditText(CharSequence charSequence) {
        setPasswordVisibilityToggleEnabled(true);
        setPasswordVisibilityToggleDrawable(R.drawable.icon_pass_selector);
        setPasswordVisibilityToggleTintList(ColorStateList.valueOf(getResources().getColor(R.color.icon_color)));
        if (isFlagValidationPass()) {
            count++;
            ThreadUtils.onUI(() -> {
                        count = 0;
                        isCounterEnabled();
                        setError("");
                    }
                    , 1500);
        } else {
            setErrorIconDrawable(0);
            isCounterEnabled();
            setError("");
        }
        passwordStrengthChecker(charSequence.toString(), PasswordFieldLayout.this);
    }

    public int passwordStrengthChecker(Object sourceObject, View indicatorView) {
        StringBuilder sourceText = new StringBuilder();

        if (sourceObject != null && sourceObject instanceof TextView) {
            sourceText.append(((TextView) sourceObject).getText().toString().trim());
        }
        if (sourceObject != null && sourceObject instanceof EditText) {
            sourceText.append(((EditText) sourceObject).getText().toString().trim());
        }

        if (sourceObject != null && sourceObject instanceof TextInputEditText) {
            sourceText.append(((TextInputEditText) sourceObject).getText().toString().trim());
        }

        if (sourceObject != null && sourceObject instanceof PasswordFieldLayout) {
            sourceText.append(((PasswordFieldLayout) sourceObject).getEditText().getText().toString().trim());
        }

        if (sourceObject != null && sourceObject instanceof String) {
            sourceText.append(sourceObject.toString().trim());
        }

        if (!sourceText.toString().isEmpty()) {
            int rank = passwordRanking(sourceText), errorTextApp = -1, errorTextId = -1, color = -1,
                    boxStrokeErrorColor = -1, icon = -1;

            if (rank < RANK_WEAK) {
                errorTextApp = R.style.AppTheme_TextErrorAppearance;
                errorTextId = textWeakPass;
                boxStrokeErrorColor = colorWeakPassBoxStroke;
                color = errorTextColorWeakPass;
                icon = 0;

            } else if (rank >= RANK_STRONG) {
                errorTextApp = R.style.AppTheme_PasswordStrongAppearance;
                errorTextId = textStrongPass;
                color = errorTextColorStrokePass;
                boxStrokeErrorColor = colorStrongPassBoxStroke;
                icon = 0;
            } else {
                errorTextApp = R.style.AppTheme_PasswordGoodAppearance;
                errorTextId = textGoodPass;
                color = errorTextColorGoodPass;
                boxStrokeErrorColor = colorGoodPassBoxStroke;
                icon = 0;
            }

            if (errorTextApp != -1 && errorTextId != -1 && isFlagValidationPass()) {
                if (indicatorView != null && indicatorView instanceof TextView) {
                    ((TextView) indicatorView).setText(errorTextId);
                    TextViewCompat.setTextAppearance((TextView) indicatorView, errorTextApp);
                }

                if (indicatorView != null && indicatorView instanceof PasswordFieldLayout) {
                    Context context = indicatorView.getContext();
                    ((PasswordFieldLayout) indicatorView).setError(context.getString(errorTextId));
                    ((PasswordFieldLayout) indicatorView).setErrorTextAppearance(errorTextApp);
                    ((PasswordFieldLayout) indicatorView).setErrorIconDrawable(icon);
                    ((PasswordFieldLayout) indicatorView).setBoxStrokeErrorColor(ColorStateList.valueOf(boxStrokeErrorColor));
                    ((PasswordFieldLayout) indicatorView).setErrorTextColor(ColorStateList.valueOf(color));

                }

            }
            sourceText.replace(0, sourceText.length(), ""); // Clear the string builder
            sourceText.setLength(0); // Clear the string builder
            return rank;
        } else {
            sourceText.setLength(0); // Clear the string builder
            return -1;
        }
    }


    public static int passwordRanking(StringBuilder textToVerify) throws IllegalArgumentException {

        if (textToVerify == null) {
            throw new IllegalArgumentException();
        }
        int strength = -1;

        boolean containsUppercase = !textToVerify.equals(textToVerify.toString().toLowerCase());
        boolean containsLowercase = !textToVerify.equals(textToVerify.toString().toUpperCase());

        if (containsUppercase && containsLowercase) {
            strength += 2; // Contains both lower or upper case
        } else {
            strength--;
        }

        int numDigits = getNumberDigits(textToVerify);
        if (numDigits > 0 && numDigits != textToVerify.length()) {
            strength++;
        } // contains digits and non-digits

        int numSymbols = getSymbols(textToVerify);
        if (numSymbols > 0 && numSymbols != textToVerify.length()) {
            strength++;
        } // Symbols

        if (textToVerify.length() >= 8) { // Password require minimum 8 characters
            strength += 2;
        }
        return strength;
    }

    public static int getNumberDigits(StringBuilder inString) {
        if (isEmpty(inString)) {
            return 0;
        }
        int numDigits = 0;
        int length = inString.length();
        for (int i = 0; i < length; i++) {
            if (Character.isDigit(inString.charAt(i))) {
                numDigits++;
            }
        }
        return numDigits;
    }

    public static int getSymbols(StringBuilder inString) {
        if (isEmpty(inString)) {
            return 0;
        }
        int numDigits = 0;
        int length = inString.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isLetterOrDigit(inString.charAt(i))) {
                numDigits++;
            }
        }
        return numDigits;
    }

    public static boolean isEmpty(StringBuilder inString) {
        return inString == null || inString.length() == 0;
    }

    private int convertToDP(int px) {
        return (int) (px * getContext().getResources().getDisplayMetrics().density);
    }

    public void setNotValidationError(int error) {
        if (getEditText().getText().toString().isEmpty()) {
            setError(getResources().getString(R.string.passEmpty));
            setBoxStrokeErrorColor(ColorStateList.valueOf(errorTextColorWeakPass));
            setErrorTextColor(ColorStateList.valueOf(errorTextColorWeakPass));
            setPasswordVisibilityToggleEnabled(false);
            setPasswordVisibilityToggleTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorRed)));
            getEditText().requestFocus();
            showKeyboard();
            return;
        }
        setError(getResources().getString(error));
        setBoxStrokeErrorColor(ColorStateList.valueOf(errorTextColorWeakPass));
        setErrorTextColor(ColorStateList.valueOf(errorTextColorWeakPass));
        setPasswordVisibilityToggleEnabled(true);
        setPasswordVisibilityToggleTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorRed)));
        getEditText().requestFocus();
        showKeyboard();

    }

    private void showKeyboard(){
        Utils.showKeyboard( getContext() ,getEditText());
    }
    public int getTextGoodPass() {
        return textGoodPass;
    }

    public void setTextGoodPass(int textGoodPass) {
        this.textGoodPass = textGoodPass;
    }

    public int getTextWeakPass() {
        return textWeakPass;
    }

    public void setTextWeakPass(int textWeakPass) {
        this.textWeakPass = textWeakPass;
    }

    public int getTextStrongPass() {
        return textStrongPass;
    }

    public void setTextStrongPass(int textStrongPass) {
        this.textStrongPass = textStrongPass;
    }

    public void setValueTextListener(Validation.PassValidator.ValueText valueTextListener) {
        this.valueTextListener = valueTextListener;
    }

    public boolean isFlagValidationPass() {
        return flagValidationPass;
    }

    public void setFlagValidationPass(boolean flagValidationPass) {
        this.flagValidationPass = flagValidationPass;
    }
}
