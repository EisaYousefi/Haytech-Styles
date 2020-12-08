package com.haytech.haytechstyles.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.google.android.material.textview.MaterialTextView;
import com.haytech.haytechstyles.R;

public class ErrorPage extends LinearLayout {
    private static final float TEXT_SIZE = 17;
    public static final int NO_SERVER_CONNECTION_TYPE = 0;
    public static final int PHONE_NOT_SUPPORTED_TYPE = 1;
    public static final int NOT_INTERNET_CONNECTION_TYPE = 2;
    public static final int IMPORTANT_UPDATE_TYPE = 3;
    private final Context context;
    private AppCompatImageView errorIconImageView;
    private AppCompatImageView errorPictureImageView;
    private MaterialTextView errorTitleTextView,
            trackingCodeLableTextView,
            trackingCodeValueTextView,
            errorDescriptionTextView,
            secondButtonTextView,
            firstButtonTextView,
            errorTitleDescriptionTextView;

    public ErrorPage(Context context) {
        super(context);
        this.context = context;
    }

    public ErrorPage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        inflateView();
        initStyle(context, attrs);
        initClicklistener();
    }

    private void inflateView() {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout rootView = (LinearLayout) layoutInflater.inflate(R.layout.layout_error_page, this, true);

        errorIconImageView = (AppCompatImageView) rootView.findViewById(R.id.img_errorIcon);
        errorPictureImageView = (AppCompatImageView) rootView.findViewById(R.id.img_errorPicture);
        errorTitleTextView = (MaterialTextView) rootView.findViewById(R.id.mtv_titleError);
        trackingCodeLableTextView = (MaterialTextView) rootView.findViewById(R.id.mtv_trackingCodeLable);
        trackingCodeValueTextView = (MaterialTextView) rootView.findViewById(R.id.mtv_trackingCodeValue);
        errorTitleDescriptionTextView = (MaterialTextView) rootView.findViewById(R.id.mtv_titleDescriptionError);
        errorDescriptionTextView = (MaterialTextView) rootView.findViewById(R.id.mtv_descriptionError);
        secondButtonTextView = (MaterialTextView) rootView.findViewById(R.id.mtv_secondButton);
        firstButtonTextView = (MaterialTextView) rootView.findViewById(R.id.mbtn_firstButton);
    }

    private void initStyle(Context context, AttributeSet attrs) {

        //init typedArray
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ErrorPage);

        //todo:check styleable property exist before get and use
        if (typedArray.hasValue(R.styleable.ErrorPage_cv_ep_errorIconSource)) {
        }

        //get styleable property
        int errorType = (int) typedArray.getInt(R.styleable.ErrorPage_cv_ep_errorType, NO_SERVER_CONNECTION_TYPE);


        int errorIconVisibility = typedArray.getInt(R.styleable.ErrorPage_cv_ep_errorIconVisibility, View.VISIBLE);
        int errorIconSource = typedArray.getResourceId(R.styleable.ErrorPage_cv_ep_errorIconSource, -1);
        int errorIconWidth = (int) typedArray.getDimension(R.styleable.ErrorPage_cv_ep_errorIconWidth, dpToPx(48));
        int errorIconHeight = (int) typedArray.getDimension(R.styleable.ErrorPage_cv_ep_errorIconHeight, dpToPx(48));////

        int errorImageVisibility = typedArray.getInt(R.styleable.ErrorPage_cv_ep_errorPictureVisibility, View.VISIBLE);
        int errorImageSource = typedArray.getResourceId(R.styleable.ErrorPage_cv_ep_errorPictureSource, -1);
        int errorImageWidth = (int) typedArray.getDimension(R.styleable.ErrorPage_cv_ep_errorPictureWidth, dpToPx(100));
        int errorImageHeight = (int) typedArray.getDimension(R.styleable.ErrorPage_cv_ep_errorPictureHeight, dpToPx(167));////


        Drawable errorTitleBackground = typedArray.getDrawable(R.styleable.ErrorPage_cv_ep_errorTitleBackground);
        String errorTitleText = typedArray.getString(R.styleable.ErrorPage_cv_ep_errorTitleText);
        int errorTitleTextSize = (int) typedArray.getDimension(R.styleable.ErrorPage_cv_ep_errorTitleTextSize, TEXT_SIZE);
        int errorTitleTextColor = typedArray.getColor(R.styleable.ErrorPage_cv_ep_errorTitleTextColor, Color.BLACK);
        int errorTitleTextPaddingTop = (int) typedArray.getDimension(R.styleable.ErrorPage_cv_ep_errorTitleTextPaddingTop, dpToPx(0));////

        Drawable trackingCodeLableBackground = typedArray.getDrawable(R.styleable.ErrorPage_cv_ep_trackingCodeLableBackground);
        String trackingCodeLableText = typedArray.getString(R.styleable.ErrorPage_cv_ep_trackingCodeLableText);
        int trackingCodeLableTextSize = (int) typedArray.getDimension(R.styleable.ErrorPage_cv_ep_trackingCodeLableTextSize, 13);
        int trackingCodeLableTextColor = typedArray.getColor(R.styleable.ErrorPage_cv_ep_trackingCodeLableTextColor, Color.BLACK);
        int trackingCodeLableTextPaddingTop = (int) typedArray.getDimension(R.styleable.ErrorPage_cv_ep_trackingCodeLableTextPaddingTop, dpToPx(0));////

        Drawable trackingCodeValueBackground = typedArray.getDrawable(R.styleable.ErrorPage_cv_ep_trackingCodeValueBackground);
        String trackingCodeValueText = typedArray.getString(R.styleable.ErrorPage_cv_ep_trackingCodeValueText);
        int trackingCodeValueTextSize = (int) typedArray.getDimension(R.styleable.ErrorPage_cv_ep_trackingCodeValueTextSize, TEXT_SIZE);
        int trackingCodeValueTextColor = typedArray.getColor(R.styleable.ErrorPage_cv_ep_trackingCodeValueTextColor, Color.BLACK);
        int trackingCodeValueTextPaddingTop = (int) typedArray.getDimension(R.styleable.ErrorPage_cv_ep_trackingCodeValueTextPaddingTop, dpToPx(0));////

        Drawable errorTitleDescriptionBackground = typedArray.getDrawable(R.styleable.ErrorPage_cv_ep_errorTitleDescriptionBackground);
        String errorTitleDescriptionText = typedArray.getString(R.styleable.ErrorPage_cv_ep_errorTitleDescriptionText);
        int errorTitleDescriptionTextSize = (int) typedArray.getDimension(R.styleable.ErrorPage_cv_ep_errorTitleDescriptionTextSize, 15);
        int errorTitleDescriptionTextColor = typedArray.getColor(R.styleable.ErrorPage_cv_ep_errorTitleDescriptionTextColor, Color.BLACK);
        int errorTitleDescriptionTextPaddingTop = (int) typedArray.getDimension(R.styleable.ErrorPage_cv_ep_errorTitleDescriptionTextPaddingTop, dpToPx(0));////


        Drawable errorDescriptionBackground = typedArray.getDrawable(R.styleable.ErrorPage_cv_ep_errorDescriptionBackground);
        String errorDescriptionText = typedArray.getString(R.styleable.ErrorPage_cv_ep_errorDescriptionText);
        int errorDescriptionTextSize = (int) typedArray.getDimension(R.styleable.ErrorPage_cv_ep_errorDescriptionTextSize, 13);
        int errorDescriptionTextColor = typedArray.getColor(R.styleable.ErrorPage_cv_ep_errorDescriptionTextColor, Color.BLACK);
        int errorDescriptionTextPaddingTop = (int) typedArray.getDimension(R.styleable.ErrorPage_cv_ep_errorDescriptionTextPaddingTop, dpToPx(0));////

        int firstButtonVisibility = typedArray.getInt(R.styleable.ErrorPage_cv_ep_firstButtonVisibility, View.VISIBLE);
        Drawable firstButtonBackground = typedArray.getDrawable(R.styleable.ErrorPage_cv_ep_firstButtonBackground);
        String firstButtonText = typedArray.getString(R.styleable.ErrorPage_cv_ep_firstButtonText);
        int firstButtonTextSize = (int) typedArray.getDimension(R.styleable.ErrorPage_cv_ep_firstButtonTextSize, TEXT_SIZE);
        int firstButtonTextColor = typedArray.getColor(R.styleable.ErrorPage_cv_ep_firstButtonTextColor, Color.WHITE);////

        int secondButtonVisibility = typedArray.getInt(R.styleable.ErrorPage_cv_ep_secondButtonVisibility, View.GONE);
        Drawable secondButtonBackground = typedArray.getDrawable(R.styleable.ErrorPage_cv_ep_secondButtonBackground);
        String secondButtonText = typedArray.getString(R.styleable.ErrorPage_cv_ep_secondButtonText);
        int secondButtonTextSize = (int) typedArray.getDimension(R.styleable.ErrorPage_cv_ep_secondButtonTextSize, TEXT_SIZE);
        int secondButtonTextColor = typedArray.getColor(R.styleable.ErrorPage_cv_ep_secondButtonTextColor, Color.WHITE);


        //set property into view
        errorIconImageView.setVisibility(errorIconVisibility);
        if (errorIconSource != -1)
            errorIconImageView.setImageResource(errorIconSource);
        errorIconImageView.getLayoutParams().height = errorIconHeight;
        errorIconImageView.getLayoutParams().width = errorIconWidth;

        errorPictureImageView.setVisibility(errorImageVisibility);
        if (errorImageSource != -1)
            errorPictureImageView.setImageResource(errorImageSource);
        errorPictureImageView.getLayoutParams().height = errorImageHeight;
        errorPictureImageView.getLayoutParams().width = errorImageWidth;

        if (errorTitleBackground != null)
            errorTitleTextView.setBackground(errorTitleBackground);
        if (errorTitleText != null)
            errorTitleTextView.setText(errorTitleText);
        errorTitleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, errorTitleTextSize);
        errorTitleTextView.setTextColor(errorTitleTextColor);
        errorTitleTextView.setPadding(dpToPx(0), errorTitleTextPaddingTop, dpToPx(0), dpToPx(0));
        if (trackingCodeLableBackground != null)
            trackingCodeLableTextView.setBackground(trackingCodeLableBackground);
        if (trackingCodeLableText != null)
            trackingCodeLableTextView.setText(trackingCodeLableText);
        trackingCodeLableTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, trackingCodeLableTextSize);
        trackingCodeLableTextView.setTextColor(trackingCodeLableTextColor);
        trackingCodeLableTextView.setPadding(dpToPx(0), trackingCodeLableTextPaddingTop, dpToPx(0), dpToPx(0));
        if (trackingCodeValueBackground != null)
            trackingCodeValueTextView.setBackground(trackingCodeValueBackground);
        if (trackingCodeValueText != null)
            trackingCodeValueTextView.setText(trackingCodeValueText);
        trackingCodeValueTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, trackingCodeValueTextSize);
        trackingCodeValueTextView.setTextColor(trackingCodeValueTextColor);
        trackingCodeValueTextView.setPadding(dpToPx(0), trackingCodeValueTextPaddingTop, dpToPx(0), dpToPx(0));
        if (errorDescriptionBackground != null)
            errorDescriptionTextView.setBackground(errorDescriptionBackground);
        if (errorDescriptionText != null)
            errorDescriptionTextView.setText(errorDescriptionText);
        errorDescriptionTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, errorDescriptionTextSize);
        errorDescriptionTextView.setTextColor(errorDescriptionTextColor);
        errorDescriptionTextView.setPadding(dpToPx(0), errorDescriptionTextPaddingTop, dpToPx(0), dpToPx(0));

        firstButtonTextView.setVisibility(firstButtonVisibility);
        if (firstButtonBackground != null)
            firstButtonTextView.setBackground(firstButtonBackground);
        if (firstButtonText != null)
            firstButtonTextView.setText(firstButtonText);
        firstButtonTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, firstButtonTextSize);
        firstButtonTextView.setTextColor(firstButtonTextColor);

        secondButtonTextView.setVisibility(secondButtonVisibility);
        if (secondButtonBackground != null)
            secondButtonTextView.setBackground(secondButtonBackground);
        if (secondButtonText != null)
            secondButtonTextView.setText(secondButtonText);
        secondButtonTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, secondButtonTextSize);
        secondButtonTextView.setTextColor(secondButtonTextColor);


        typedArray.recycle();
        setErrorType(errorType);
    }

    public void setErrorType(int errorType) {
        switch (errorType) {
            case NO_SERVER_CONNECTION_TYPE:
                errorIconImageView.setImageResource(R.drawable.ic_error_no_server_connection);
                errorPictureImageView.setImageResource(R.drawable.error_no_server_connection);
                errorTitleTextView.setText(context.getResources().getString(R.string.not_connected_to_the_server));
                errorDescriptionTextView.setText(context.getResources().getString(R.string.no_server_error_description));
                firstButtonTextView.setText(context.getResources().getString(R.string.review_again));
                secondButtonTextView.setText(context.getResources().getString(R.string.support_call));

                errorDescriptionTextView.setGravity(Gravity.CENTER);
                errorDescriptionTextView.setPadding(0, dpToPx(5), 0, 0);

                trackingCodeLableTextView.setVisibility(GONE);
                trackingCodeValueTextView.setVisibility(GONE);
                errorTitleDescriptionTextView.setVisibility(GONE);
                secondButtonTextView.setVisibility(VISIBLE);
                secondButtonTextView.setTextColor(Color.BLACK);
                break;
            case PHONE_NOT_SUPPORTED_TYPE:
                //todo:vm-delete:set all property
                errorIconImageView.setImageResource(R.drawable.ic_error_phone_not_support);
                errorPictureImageView.setImageResource(R.drawable.error_phone_not_support);
                errorTitleTextView.setText(context.getResources().getString(R.string.your_phone_not_supported));
                trackingCodeLableTextView.setText(context.getResources().getString(R.string.tracking_code_lable));
                trackingCodeValueTextView.setText(context.getResources().getString(R.string.tracking_code_value));
                errorDescriptionTextView.setText(context.getResources().getString(R.string.not_support_error_description));
                firstButtonTextView.setText(context.getResources().getString(R.string.support_call));

                errorDescriptionTextView.setGravity(Gravity.CENTER);
                errorDescriptionTextView.setPadding(0, dpToPx(50), 0, 0);
                trackingCodeValueTextView.setVisibility(VISIBLE);
                trackingCodeLableTextView.setVisibility(VISIBLE);
                errorTitleDescriptionTextView.setVisibility(GONE);
                secondButtonTextView.setVisibility(GONE);
                break;
            case NOT_INTERNET_CONNECTION_TYPE:
                errorIconImageView.setImageResource(R.drawable.ic_error_no_internet_connection);
                errorPictureImageView.setImageResource(R.drawable.error_no_internet_connection);
                errorTitleTextView.setText(context.getResources().getString(R.string.not_connected_to_the_internet));
                errorTitleDescriptionTextView.setText(context.getResources().getString(R.string.suggest));
                errorDescriptionTextView.setText(context.getResources().getString(R.string.no_internet_error_description));
                firstButtonTextView.setText(context.getResources().getString(R.string.review_internet));

                errorDescriptionTextView.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
                errorDescriptionTextView.setPadding(0, dpToPx(25), dpToPx(35), 0);

                trackingCodeValueTextView.setVisibility(GONE);
                trackingCodeLableTextView.setVisibility(GONE);
                errorTitleDescriptionTextView.setVisibility(VISIBLE);
                secondButtonTextView.setVisibility(GONE);
                break;
            case IMPORTANT_UPDATE_TYPE:
                errorIconImageView.setImageResource(R.drawable.ic_error_important_update);
                errorPictureImageView.setImageResource(R.drawable.error_important_update);
                errorTitleTextView.setText(context.getResources().getString(R.string.up_to_the_new_version));
                errorTitleDescriptionTextView.setText(context.getResources().getString(R.string.new_changes));
                errorDescriptionTextView.setText(context.getResources().getString(R.string.update_error_description));
                firstButtonTextView.setText(context.getResources().getString(R.string.receive_and_install_update));

                errorDescriptionTextView.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
                errorDescriptionTextView.setPadding(0, dpToPx(25), dpToPx(35), 0);

                trackingCodeValueTextView.setVisibility(GONE);
                trackingCodeLableTextView.setVisibility(GONE);
                errorTitleDescriptionTextView.setVisibility(VISIBLE);
                secondButtonTextView.setVisibility(GONE);
                break;
        }
    }

    private void initClicklistener() {

    }

    private int dpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }

    public AppCompatImageView getErrorIconImageView() {
        return errorIconImageView;
    }

    public void setErrorIconImageView(AppCompatImageView errorIconImageView) {
        this.errorIconImageView = errorIconImageView;
    }

    public AppCompatImageView getErrorPictureImageView() {
        return errorPictureImageView;
    }

    public void setErrorPictureImageView(AppCompatImageView errorPictureImageView) {
        this.errorPictureImageView = errorPictureImageView;
    }

    public MaterialTextView getErrorTitleTextView() {
        return errorTitleTextView;
    }

    public void setErrorTitleTextView(MaterialTextView errorTitleTextView) {
        this.errorTitleTextView = errorTitleTextView;
    }

    public MaterialTextView getTrackingCodeLableTextView() {
        return trackingCodeLableTextView;
    }

    public void setTrackingCodeLableTextView(MaterialTextView trackingCodeLableTextView) {
        this.trackingCodeLableTextView = trackingCodeLableTextView;
    }

    public MaterialTextView getTrackingCodeValueTextView() {
        return trackingCodeValueTextView;
    }

    public void setTrackingCodeValueTextView(MaterialTextView trackingCodeValueTextView) {
        this.trackingCodeValueTextView = trackingCodeValueTextView;
    }

    public MaterialTextView getErrorDescriptionTextView() {
        return errorDescriptionTextView;
    }

    public void setErrorDescriptionTextView(MaterialTextView errorDescriptionTextView) {
        this.errorDescriptionTextView = errorDescriptionTextView;
    }

    public MaterialTextView getSecondButtonTextView() {
        return secondButtonTextView;
    }

    public void setSecondButtonTextView(MaterialTextView secondButtonTextView) {
        this.secondButtonTextView = secondButtonTextView;
    }

    public MaterialTextView getFirstButtonTextView() {
        return firstButtonTextView;
    }

    public void setFirstButtonTextView(MaterialTextView firstButtonTextView) {
        this.firstButtonTextView = firstButtonTextView;
    }

    public MaterialTextView getErrorTitleDescriptionTextView() {
        return errorTitleDescriptionTextView;
    }

    public void setErrorTitleDescriptionTextView(MaterialTextView errorTitleDescriptionTextView) {
        this.errorTitleDescriptionTextView = errorTitleDescriptionTextView;
    }
}
