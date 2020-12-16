package com.haytech.haytechstyles.customLoginKebord;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.RippleDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import com.haytech.haytechstyles.R;
import com.haytech.haytechstyles.selector.RadioButtonField;
import com.haytech.haytechstyles.utils.ThreadUtils;
import com.haytech.haytechstyles.utils.UIUtilsHytechStyle;

public class CustomKeyboard extends ConstraintLayout {

    private static final int CREATE_PASS_STATE = 1;
    private static final int LOGIN_TO_APP_STATE = 2;
    private static final int CHANGE_PASS_STATE = 3;
    private static final int DEFAULT_COUNT = 5;
    private static final int ZERO_DEFAULT = 0;

    private static final int DEFAULT_DURATION = 300;
    private static final float DEFAULT_INNER_WIDTH = 1.8f;
    private static final float DEFAULT_OUTER_WIDTH = 1.2f;
    private static final float DEFAULT_INNER_FULL_WIDTH = 1;

    private int selectedType = CREATE_PASS_STATE;
    private RadioButtonField[] circleCheckers;
    private int valuePasswordCounter, repeatValuePasswordCounter, rTryValuePasswordCounter;
    private int backSpaceLoginAndCreatePassword = ZERO_DEFAULT, backSpaceLoginAndCreateAndChangePassword = ZERO_DEFAULT;
    private String currentOrCreateValuePassword = "", createOrRepeatValuePassword = "", repeatValuePassword = "";
    private int backPressedKey = ZERO_DEFAULT;

    public TextView digit1, digit2, digit3, digit4, digit5, digit6, digit7, digit8, digit9, digit0, errorText;
    private RadioButtonField passBiteOne, passBiteTow, passBiteThree, passBiteFour, passBiteFive, passBiteSex;
    private TextView title, message;
    private Animation animation;
    private String validPass = "";
    private TextView backFingerPrint;
    private ImageView imgFingerPrint;
    private int counterErrorPass = ZERO_DEFAULT;

    private int backgroundDownKey;
    private int backgroundUpKey;
    private int textColorDwnKey;
    private int textColorUpKey;
    private int countBitePassword;
    private int srcClearNumber ;
    private int srcFingerPrint ;

    private TitleAndHeaderModel titleAndHeaderModel;
    private OnKeyboardCustomListener.CreateNewPass createNewPassListener;
    private OnKeyboardCustomListener.LoginApp loginAppListener;
    private OnKeyboardCustomListener.ChangePass changePassListener;
    private OnKeyboardCustomListener.OnClickKey onClickKeyListener;
    private int innerColor;
    private int outerColor;
    private float innerWidth = DEFAULT_INNER_WIDTH;
    private float innerWidthFull = DEFAULT_INNER_FULL_WIDTH;
    private float outerWidth = DEFAULT_OUTER_WIDTH;
    private int typeBitePassword = 0;
    private ConstraintLayout backClearDigit;
    private ImageView clearDigit;
    private int duration=DEFAULT_DURATION;

    public CustomKeyboard(Context context) {
        super(context);
        initView(context, null);
    }

    public CustomKeyboard(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public CustomKeyboard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    public int getCountBitePassword() {
        return countBitePassword;
    }

    public String getRepeateValuePassword() {
        return repeatValuePassword;
    }

    public void setRepeateValuePassword(String repeateValuePassword) {
        this.repeatValuePassword = repeateValuePassword;
    }

    public int getSelectedType() {
        return selectedType;
    }

    public void setSelectedType(int selectedType) {
        this.selectedType = selectedType;
    }

    public String getValidPass() {
        return validPass;
    }

    public void setValidPass(String validPass) {
        this.validPass = validPass;
    }

    public TextView getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title.setText(title);
        getTitleAndHeaderModel().setTvLabel(title);
    }

    public TextView getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message.setText(message);
        getTitleAndHeaderModel().setTvHeaderTitle(message);
    }

    public OnKeyboardCustomListener.CreateNewPass getCreateNewPassListener() {
        return createNewPassListener;
    }

    public void setCreateNewPassListener(OnKeyboardCustomListener.CreateNewPass createNewPassListener) {
        this.createNewPassListener = createNewPassListener;
    }

    public OnKeyboardCustomListener.LoginApp getLoginAppListener() {
        return loginAppListener;
    }

    public void setLoginAppListener(OnKeyboardCustomListener.LoginApp loginAppListener) {
        this.loginAppListener = loginAppListener;
    }

    public OnKeyboardCustomListener.ChangePass getChangePassListener() {
        return changePassListener;
    }

    public void setChangePassListener(OnKeyboardCustomListener.ChangePass changePassListener) {
        this.changePassListener = changePassListener;
    }

    public OnKeyboardCustomListener.OnClickKey getOnClickKeyListener() {
        return onClickKeyListener;
    }

    public void setOnClickKeyListener(OnKeyboardCustomListener.OnClickKey onClickKeyListener) {
        this.onClickKeyListener = onClickKeyListener;
    }

    public TitleAndHeaderModel getTitleAndHeaderModel() {
        return titleAndHeaderModel;
    }

    public void setTitleAndHeaderModel(TitleAndHeaderModel titleAndHeaderModel) {
        this.titleAndHeaderModel = titleAndHeaderModel;
    }

    public int getBackPressedKey() {
        return backPressedKey;
    }

    public void setBackPressedKey(int backPressedKey) {
        this.backPressedKey = backPressedKey;
    }

    public String getCurrentOrCreateValuePassword() {
        return currentOrCreateValuePassword;
    }

    public void setCurrentOrCreateValuePassword(String currentOrCreateValuePassword) {
        this.currentOrCreateValuePassword = currentOrCreateValuePassword;
    }

    public String getCreateOrRepeatValuePassword() {
        return createOrRepeatValuePassword;
    }

    public void setCreateOrRepeatValuePassword(String createOrRepeatValuePassword) {
        this.createOrRepeatValuePassword = createOrRepeatValuePassword;
    }

    public void showTvFingerPrint() {
        backFingerPrint.setVisibility(VISIBLE);
    }

    public void hideTvFingerPrint() {
        backFingerPrint.setVisibility(INVISIBLE);
    }

    public void showImgFingerPrint() {
        imgFingerPrint.setVisibility(VISIBLE);
    }

    public void hideImgFingerPrint() {
        imgFingerPrint.setVisibility(INVISIBLE);
    }

    public int getCounterErrorPass() {
        return counterErrorPass;
    }

    public void setCounterErrorPass(int counterErrorPass) {
        this.counterErrorPass = counterErrorPass;
    }

    private void initView(@androidx.annotation.NonNull final Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.keboard_custom, this);
        ViewCompat.setLayoutDirection(this, ViewCompat.LAYOUT_DIRECTION_LTR);
        setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        initId();

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustomKeyboard);
        backgroundDownKey = typedArray.getResourceId(R.styleable.CustomKeyboard_CK_background_down, R.drawable.back_text_pass_doun);
        textColorDwnKey = typedArray.getInt(R.styleable.CustomKeyboard_CK_textColor_down, getResources().getColor(R.color.text_number_down));
        backgroundUpKey = typedArray.getResourceId(R.styleable.CustomKeyboard_CK_background_up, R.drawable.back_text_pass_up);
        textColorUpKey = typedArray.getInt(R.styleable.CustomKeyboard_CK_textColor_up, getResources().getColor(R.color.un_select_keybord));
        countBitePassword = typedArray.getInt(R.styleable.CustomKeyboard_CK_count, DEFAULT_COUNT);
        srcClearNumber = typedArray.getResourceId(R.styleable.CustomKeyboard_CK_src_clear_text_image, R.drawable.backspace);
        srcFingerPrint = typedArray.getResourceId(R.styleable.CustomKeyboard_CK_src_finger_print_image, R.drawable.ic_ok_fingerprint);
        //radioButtonField status
        typeBitePassword = typedArray.getInt(R.styleable.CustomKeyboard_CK_type, 2);
        innerWidth = typedArray.getFloat(R.styleable.CustomKeyboard_CK_inner_width, DEFAULT_INNER_WIDTH);
        outerWidth = typedArray.getFloat(R.styleable.CustomKeyboard_CK_outer_width, DEFAULT_OUTER_WIDTH);
        duration = typedArray.getInt(R.styleable.CustomKeyboard_CK_duration, DEFAULT_DURATION);
        innerColor = typedArray.getColor(R.styleable.CustomKeyboard_CK_checked_inner_color, getResources().getColor(R.color.innerColor));
        outerColor = typedArray.getColor(R.styleable.CustomKeyboard_CK_checked_outer_color, getResources().getColor(R.color.outerColor));
        typedArray.recycle();

        setDefaultParam();

        onClick();

    }

    private void onClick() {
        backFingerPrint.setOnClickListener(v1 -> {
            initFingerPrentClick(v1);
        });

        imgFingerPrint.setOnClickListener(v1 -> {
            if (onClickKeyListener != null) {
                onClickKeyListener.imgFingerClick(v1);
            }
        });
    }

    private void setDefaultParam() {
        titleAndHeaderModel = new TitleAndHeaderModel(getContext());
        titleAndHeaderModel.setDataTitleAndHeader();

        backFingerPrint.setBackgroundResource(backgroundUpKey);

        setStyleDigitButton(digit0);
        setStyleDigitButton(digit1);
        setStyleDigitButton(digit2);
        setStyleDigitButton(digit3);
        setStyleDigitButton(digit3);
        setStyleDigitButton(digit4);
        setStyleDigitButton(digit5);
        setStyleDigitButton(digit6);
        setStyleDigitButton(digit7);
        setStyleDigitButton(digit8);
        setStyleDigitButton(digit9);

        backClearDigit.setBackgroundResource(backgroundUpKey);
        clearDigit.setImageResource(srcClearNumber);
        imgFingerPrint.setImageResource(srcFingerPrint);

        valuePasswordCounter = 0;
        repeatValuePasswordCounter = countBitePassword + 1;
        rTryValuePasswordCounter = countBitePassword + 1;

        passBiteOne.setTypeCircle(typeBitePassword);
        passBiteTow.setTypeCircle(typeBitePassword);
        passBiteThree.setTypeCircle(typeBitePassword);
        passBiteFour.setTypeCircle(typeBitePassword);
        passBiteFive.setTypeCircle(typeBitePassword);
        passBiteSex.setTypeCircle(typeBitePassword);

        passBiteOne.setInnerWidth(innerWidth);
        passBiteOne.setOuterWidth(outerWidth);
        passBiteTow.setInnerWidth(innerWidth);
        passBiteTow.setOuterWidth(outerWidth);
        passBiteThree.setInnerWidth(innerWidth);
        passBiteThree.setOuterWidth(outerWidth);
        passBiteFour.setInnerWidth(innerWidth);
        passBiteFour.setOuterWidth(outerWidth);
        passBiteFive.setInnerWidth(innerWidth);
        passBiteFive.setOuterWidth(outerWidth);
        passBiteSex.setInnerWidth(innerWidth);
        passBiteSex.setOuterWidth(outerWidth);

        passBiteOne.  setDuration(duration);
        passBiteTow.  setDuration(duration);
        passBiteThree.setDuration(duration);
        passBiteFour. setDuration(duration);
        passBiteFive. setDuration(duration);
        passBiteSex.  setDuration(duration);


        switch (countBitePassword) {
            case 3:
                passBiteFour.setVisibility(GONE);
                passBiteFive.setVisibility(GONE);
                passBiteSex.setVisibility(GONE);
            case 4:
                passBiteFive.setVisibility(GONE);
                passBiteSex.setVisibility(GONE);
            case 5:
                passBiteSex.setVisibility(GONE);
        }
    }

    private void setStyleDigitButton(TextView button) {
        button.setTextColor(textColorUpKey);
        button.setBackgroundResource(backgroundUpKey);
    }

    private void initId() {
        passBiteOne = findViewById(R.id.one);
        passBiteTow = findViewById(R.id.tow);
        passBiteThree = findViewById(R.id.three);
        passBiteFour = findViewById(R.id.foure);
        passBiteFive = findViewById(R.id.five);
        passBiteSex = findViewById(R.id.sex);
        circleCheckers = new RadioButtonField[]{passBiteOne, passBiteTow, passBiteThree, passBiteFour, passBiteFive, passBiteSex};

        digit1 = findViewById(R.id.tv1);
        digit2 = findViewById(R.id.tv2);
        digit3 = findViewById(R.id.tv3);
        digit4 = findViewById(R.id.tv4);
        digit5 = findViewById(R.id.tv5);
        digit6 = findViewById(R.id.tv6);
        digit7 = findViewById(R.id.tv7);
        digit8 = findViewById(R.id.tv8);
        digit9 = findViewById(R.id.tv9);
        digit0 = findViewById(R.id.tv0);
        errorText = findViewById(R.id.tv_error);
        title = findViewById(R.id.tv_lable);
        message = findViewById(R.id.headerTitle);
        clearDigit = findViewById(R.id.img_clear);
        backClearDigit = findViewById(R.id.constrant_clear);
        backFingerPrint = findViewById(R.id.tv_finger_print);
        imgFingerPrint = findViewById(R.id.img_finger_print);
    }

    public void clear() {
        if (getSelectedType() == CREATE_PASS_STATE) {
            if (valuePasswordCounter > 0 && repeatValuePasswordCounter == countBitePassword + 1) {
                circleCheckers[valuePasswordCounter - 1].setInnerColor(getResources().getColor(R.color.back_my_text_checker));
                circleCheckers[valuePasswordCounter - 1].setOuterColor(getResources().getColor(R.color.back_my_text_checker));
                circleCheckers[valuePasswordCounter - 1].onClick(new CustomKeyboard(getContext()));
                if (valuePasswordCounter != 0) {
                    currentOrCreateValuePassword = currentOrCreateValuePassword.substring(0, valuePasswordCounter - 1);
                    valuePasswordCounter--;
                    backSpaceLoginAndCreatePassword--;
                }
            } else if (backSpaceLoginAndCreatePassword == 0 && repeatValuePasswordCounter > 0 && repeatValuePasswordCounter != countBitePassword + 1) {
                handlerBitePasswordDownAndUp(repeatValuePasswordCounter - 1, R.color.back_my_text_checker, R.color.back_my_text_checker);
                if (repeatValuePasswordCounter != 0) {
                    createOrRepeatValuePassword = createOrRepeatValuePassword.substring(0, repeatValuePasswordCounter - 1);
                    repeatValuePasswordCounter--;
                }
                errorText.setVisibility(View.INVISIBLE);
            }
        } else if (getSelectedType() == LOGIN_TO_APP_STATE) {
            if (valuePasswordCounter > 0) {
                handlerBitePasswordDownAndUp(valuePasswordCounter - 1, R.color.back_my_text_checker, R.color.back_my_text_checker);
                if (valuePasswordCounter != 0) {
                    currentOrCreateValuePassword = currentOrCreateValuePassword.substring(0, valuePasswordCounter - 1);
                    valuePasswordCounter--;
                    backSpaceLoginAndCreatePassword--;
                }
                errorText.setVisibility(INVISIBLE);
            }
        } else if (getSelectedType() == CHANGE_PASS_STATE) {
            if (valuePasswordCounter > 0 && repeatValuePasswordCounter == countBitePassword + 1) {
                handlerBitePasswordDownAndUp(valuePasswordCounter - 1, R.color.back_my_text_checker, R.color.back_my_text_checker);
                if (valuePasswordCounter != 0) {
                    currentOrCreateValuePassword = currentOrCreateValuePassword.substring(0, valuePasswordCounter - 1);
                    valuePasswordCounter--;
                    backSpaceLoginAndCreatePassword--;
                }
            } else if (backSpaceLoginAndCreatePassword == 0 && repeatValuePasswordCounter > 0 && repeatValuePasswordCounter != countBitePassword + 1 && rTryValuePasswordCounter == countBitePassword + 1) {
                handlerBitePasswordDownAndUp(repeatValuePasswordCounter - 1, R.color.back_my_text_checker, R.color.back_my_text_checker);
                if (repeatValuePasswordCounter != 0) {
                    createOrRepeatValuePassword = createOrRepeatValuePassword.substring(0, repeatValuePasswordCounter - 1);
                    repeatValuePasswordCounter--;
                    backSpaceLoginAndCreateAndChangePassword--;
                }
                errorText.setVisibility(View.INVISIBLE);
            } else if (backSpaceLoginAndCreateAndChangePassword == 0 && rTryValuePasswordCounter > 0 && rTryValuePasswordCounter != countBitePassword + 1) {
                handlerBitePasswordDownAndUp(rTryValuePasswordCounter - 1, R.color.back_my_text_checker, R.color.back_my_text_checker);
                if (rTryValuePasswordCounter != 0) {
                    repeatValuePassword = repeatValuePassword.substring(0, rTryValuePasswordCounter - 1);
                    rTryValuePasswordCounter--;
                }
                errorText.setVisibility(View.INVISIBLE);
            }
        }
    }


    @SuppressLint("ClickableViewAccessibility")
    public void onTouchMethod() {
        touchDigit();
        touchFingerPrint();
        touchClearDigit();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void touchClearDigit() {
        clearDigit.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    backClearDigit.setBackgroundResource(backgroundDownKey);
                    break;
                case MotionEvent.ACTION_UP:
                    backClearDigit.setBackgroundResource(backgroundUpKey);
                    startAnimation(backClearDigit, R.anim.fade_in);
                    clear();
                    break;
            }
            return true;
        });

        backClearDigit.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    backClearDigit.setBackgroundResource(backgroundDownKey);
                    break;
                case MotionEvent.ACTION_UP:
                    backClearDigit.setBackgroundResource(backgroundUpKey);
                    clear();
                    startAnimation(backClearDigit, R.anim.fade_in);
                    break;
            }
            return true;
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void touchFingerPrint() {
        imgFingerPrint.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    backFingerPrint.setBackgroundResource(backgroundDownKey);
                    break;
                case MotionEvent.ACTION_UP:
                    backFingerPrint.setBackgroundResource(backgroundUpKey);
                    startAnimation(imgFingerPrint, R.anim.fade_in);
                    clear();
                    initFingerPrentClick(v);
                    break;
            }
            return true;
        });

        backFingerPrint.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    backFingerPrint.setBackgroundResource(backgroundDownKey);
                    break;
                case MotionEvent.ACTION_UP:
                    backFingerPrint.setBackgroundResource(backgroundUpKey);
                    clear();
                    startAnimation(backFingerPrint, R.anim.fade_in);
                    initFingerPrentClick(v);
                    break;
            }
            return true;
        });
    }

    private void initFingerPrentClick(View view) {
        if (onClickKeyListener != null) {
            onClickKeyListener.tvFingerClick(view);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void touchDigit() {
        digit1.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switchDownAndUpDigit(event, digit1);
                return true;
            }
        });
        digit2.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switchDownAndUpDigit(event, digit2);
                return true;
            }
        });
        digit3.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switchDownAndUpDigit(event, digit3);
                return true;
            }
        });
        digit0.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switchDownAndUpDigit(event, digit0);
                return true;
            }
        });
        digit4.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switchDownAndUpDigit(event, digit4);
                return true;
            }
        });
        digit5.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switchDownAndUpDigit(event, digit5);
                return true;
            }
        });
        digit6.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switchDownAndUpDigit(event, digit6);
                return true;
            }
        });
        digit7.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switchDownAndUpDigit(event, digit7);
                return true;
            }
        });
        digit8.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switchDownAndUpDigit(event, digit8);
                return true;
            }
        });
        digit9.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switchDownAndUpDigit(event, digit9);
                return true;
            }
        });
    }

    private void switchDownAndUpDigit(MotionEvent event, TextView tv) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ColorStateList csl = ColorStateList.valueOf(getResources().getColor(R.color.ripple_color));
                    RippleDrawable rippleDrawable = new RippleDrawable(csl, null, null);
                }
                tv.setBackgroundResource(backgroundDownKey);
                tv.setTextColor(textColorDwnKey);
                //create a new pass   //تعریف پسورد اولیه برای ورود به نرم افزار
                if (getSelectedType() == CREATE_PASS_STATE) {
                    if (valuePasswordCounter < countBitePassword) {
                        handlerBitePasswordDownAndUp(valuePasswordCounter, R.color.white, R.color.radioButtonColor);
                        valuePasswordCounter++;
                        backSpaceLoginAndCreatePassword++;
                        backPressedKey = countBitePassword;
                        currentOrCreateValuePassword += tv.getText().toString().trim();
                        errorText.setVisibility(View.INVISIBLE);
                    }

                    //repeat pass
                    if (repeatValuePasswordCounter < countBitePassword) {
                        handlerBitePasswordDownAndUp(repeatValuePasswordCounter, R.color.white, R.color.radioButtonColor);
                        repeatValuePasswordCounter++;
                        createOrRepeatValuePassword += tv.getText().toString().trim();
                        backPressedKey = 1;
                    }
                    //insert to App After create pass
                } else if (getSelectedType() == LOGIN_TO_APP_STATE) {
                    if (valuePasswordCounter < countBitePassword) {
                        handlerBitePasswordDownAndUp(valuePasswordCounter, R.color.white, R.color.radioButtonColor);
                        valuePasswordCounter++;
                        backSpaceLoginAndCreatePassword++;
                        backPressedKey = 0;
                        currentOrCreateValuePassword += tv.getText().toString().trim();
                        errorText.setVisibility(View.INVISIBLE);
                    }
                    //change pass
                } else if (getSelectedType() == CHANGE_PASS_STATE) {
                    //checked Old Pass is valid or not valid
                    if (valuePasswordCounter < countBitePassword) {
                        handlerBitePasswordDownAndUp(valuePasswordCounter, R.color.white, R.color.radioButtonColor);
                        valuePasswordCounter++;
                        backSpaceLoginAndCreatePassword++;
                        backPressedKey = 0;
                        currentOrCreateValuePassword += tv.getText().toString().toLowerCase();
                        errorText.setVisibility(View.INVISIBLE);
                    }

                    //new pass
                    if (repeatValuePasswordCounter < countBitePassword) {
                        handlerBitePasswordDownAndUp(repeatValuePasswordCounter, R.color.white, R.color.radioButtonColor);
                        repeatValuePasswordCounter++;
                        backSpaceLoginAndCreateAndChangePassword++;
                        createOrRepeatValuePassword += tv.getText().toString().trim();
                        backPressedKey = 1;
                    }
                    //repeat new pass
                    if (rTryValuePasswordCounter < countBitePassword) {
                        handlerBitePasswordDownAndUp(rTryValuePasswordCounter, R.color.white, R.color.radioButtonColor);
                        rTryValuePasswordCounter++;
                        repeatValuePassword += tv.getText().toString().trim();
                        backPressedKey = 2;
                    }

                }

                break;
            case MotionEvent.ACTION_UP:
                tv.setBackgroundResource(backgroundUpKey);
                tv.setTextColor(textColorUpKey);
                startAnimation(tv, R.anim.fade_in);
                if (getSelectedType() == CREATE_PASS_STATE) {
                    if (backSpaceLoginAndCreatePassword == countBitePassword) {
                        for (int i = 0; i < countBitePassword; i++) {
                            handlerBitePasswordDownAndUp(i, R.color.back_my_text_checker, R.color.back_my_text_checker);
                            backPressedKey = 1;
                            ThreadUtils.onUI(() -> {
                                startAnimation(title, R.anim.slide_out_right);
                                startAnimation(message, R.anim.slide_out_right);
                            }, 2);

                            ThreadUtils.onUI(() -> {
                                title.setVisibility(View.VISIBLE);
                                startAnimation(title, R.anim.slid_in_left_interpolator);
                                startAnimation(message, R.anim.slid_in_left_interpolator);
                                title.setText(titleAndHeaderModel.getTvLabelRepeatNewPass2());
                                message.setText(titleAndHeaderModel.getTvHeaderRepeatNewPass2());
                            }, 300);
                        }

                        errorText.setVisibility(View.INVISIBLE);
                        backSpaceLoginAndCreatePassword = 0;
                        repeatValuePasswordCounter = 0;
                    }

                    //checked valid pass . if valid insert to app .if not valid error
                    if (repeatValuePasswordCounter == countBitePassword) {
                        if (!currentOrCreateValuePassword.equals(createOrRepeatValuePassword)) {
                            ThreadUtils.onUI(() -> {
                                errorText.setVisibility(View.VISIBLE);
                                UIUtilsHytechStyle.getInstance().animateTransferTypeButton(errorText);
                            }, 300);

                        } else {
                            if (createNewPassListener != null)
                                createNewPassListener.validPass();
                        }
                        Log.i("eisavvvvv", "countValuePass1 2 : " + createOrRepeatValuePassword);
                    }
                } else if (getSelectedType() == LOGIN_TO_APP_STATE) {
                    if (!currentOrCreateValuePassword.equals(getValidPass()) && backSpaceLoginAndCreatePassword == countBitePassword) {
                        ThreadUtils.onUI(() -> {
                            errorText.setVisibility(View.VISIBLE);
                            UIUtilsHytechStyle.getInstance().animateTransferTypeButton(errorText);
                            counterErrorPass++;
                            if (counterErrorPass > 3) {
                                loginAppListener.counterErrorPass();
                            }
                        }, 300);

                    } else {
                        errorText.setVisibility(View.INVISIBLE);
                        loginAppListener.validPass();
                    }
                } else if (getSelectedType() == CHANGE_PASS_STATE) {
                    //valid old pass
                    if (backSpaceLoginAndCreatePassword == countBitePassword) {
                        if (!currentOrCreateValuePassword.equals(getValidPass())) {
                            ThreadUtils.onUI(() -> {
                                errorText.setVisibility(View.VISIBLE);
                                UIUtilsHytechStyle.getInstance().animateTransferTypeButton(errorText);
                            }, 300);
                        } else {
                            for (int i = 0; i < countBitePassword; i++) {
                                handlerBitePasswordDownAndUp(i, R.color.back_my_text_checker, R.color.back_my_text_checker);
                                errorText.setVisibility(INVISIBLE);
                                backPressedKey = 1;
                                ThreadUtils.onUI(() -> {
                                    startAnimation(title, R.anim.slide_out_right);
                                    startAnimation(message, R.anim.slide_out_right);
                                }, 2);

                                ThreadUtils.onUI(() -> {
                                    title.setVisibility(View.VISIBLE);
                                    startAnimation(title, R.anim.slid_in_left_interpolator);
                                    startAnimation(message, R.anim.slid_in_left_interpolator);
                                    title.setText(getTitleAndHeaderModel().getTvLabelCreateNewForPassChange3());
                                    message.setText(getTitleAndHeaderModel().getTvHeaderCreateNewForPassChange3());

                                }, 300);
                            }

                            errorText.setVisibility(View.INVISIBLE);
                            backSpaceLoginAndCreatePassword = 0;
                            repeatValuePasswordCounter = 0;
                        }
                    }
                    if (backSpaceLoginAndCreateAndChangePassword == countBitePassword) {
                        for (int i = 0; i < countBitePassword; i++) {
                            handlerBitePasswordDownAndUp(i, R.color.back_my_text_checker, R.color.back_my_text_checker);
                            errorText.setVisibility(INVISIBLE);
                            backPressedKey = 2;
                            ThreadUtils.onUI(() -> {
                                startAnimation(title, R.anim.slide_out_right);
                                startAnimation(message, R.anim.slide_out_right);
                            }, 2);

                            ThreadUtils.onUI(() -> {
                                title.setVisibility(View.VISIBLE);
                                startAnimation(title, R.anim.slid_in_left_interpolator);
                                title.setText(getTitleAndHeaderModel().getTvLabelRepeatNewForPassChange3());
                                message.setText(getTitleAndHeaderModel().getTvHeaderRepeatNewForPassChange3());
                            }, 300);

                        }
                        backSpaceLoginAndCreateAndChangePassword = 0;
                        rTryValuePasswordCounter = 0;
                    }
                    if (rTryValuePasswordCounter == countBitePassword) {
                        if (!createOrRepeatValuePassword.equals(repeatValuePassword)) {
                            ThreadUtils.onUI(() -> {
                                errorText.setVisibility(View.VISIBLE);
                                UIUtilsHytechStyle.getInstance().animateTransferTypeButton(errorText);
                            }, 300);

                        } else {
                            if (changePassListener != null)
                                changePassListener.validPass();
                        }
                    }
                }
                break;
        }
    }

    private void handlerBitePasswordDownAndUp(int valuePasswordCounter, int innerColor, int outerColor) {
        circleCheckers[valuePasswordCounter].setInnerColor(getResources().getColor(innerColor));
        circleCheckers[valuePasswordCounter].setOuterColor(getResources().getColor(outerColor));
        circleCheckers[valuePasswordCounter].onClick(new RadioButtonField(getContext()));
    }

    //پایان تعریف پسورد اولیه



    public void onBackPress() {
        if (backPressedKey == 1) {
            valuePasswordCounter = 0;
            repeatValuePasswordCounter = countBitePassword + 1;
            backSpaceLoginAndCreatePassword = 0;
            setBackPressedKey(0);
            currentOrCreateValuePassword = "";
            createOrRepeatValuePassword = "";
            for (int i = 0; i < countBitePassword; i++) {
                circleCheckers[i].setChecked(true);
                circleCheckers[i].onClick(new RadioButtonField(getContext()));
                circleCheckers[i].setInnerColor(getResources().getColor(R.color.back_my_text_checker));
                circleCheckers[i].setOuterColor(getResources().getColor(R.color.back_my_text_checker));

                errorText.setVisibility(View.INVISIBLE);

                ThreadUtils.onUI(() -> {
                    startAnimation(title, R.anim.slide_in_right);
                    startAnimation(message, R.anim.slide_in_right);
                    title.setText(getTitleAndHeaderModel().getTvLabel());
                    message.setText(getTitleAndHeaderModel().getTvHeaderTitle());
                    title.setVisibility(View.VISIBLE);
                }, 100);
            }
        } else if (backPressedKey == 2) {
            repeatValuePasswordCounter = 0;
            rTryValuePasswordCounter = countBitePassword + 1;
            backSpaceLoginAndCreateAndChangePassword = 0;
            setBackPressedKey(1);
            createOrRepeatValuePassword = "";
            repeatValuePassword = "";
            for (int i = 0; i < countBitePassword; i++) {
                circleCheckers[i].setChecked(true);
                circleCheckers[i].onClick(new RadioButtonField(getContext()));
                circleCheckers[i].setInnerColor(getResources().getColor(R.color.back_my_text_checker));
                circleCheckers[i].setOuterColor(getResources().getColor(R.color.back_my_text_checker));

                errorText.setVisibility(View.INVISIBLE);

                ThreadUtils.onUI(() -> {
                    startAnimation(title, R.anim.slide_in_right);
                    startAnimation(message, R.anim.slide_in_right);
                    title.setText(getTitleAndHeaderModel().getTvLabelCreateNewForPassChange3());
                    message.setText(getTitleAndHeaderModel().getTvHeaderCreateNewForPassChange3());
                    title.setVisibility(View.VISIBLE);
                }, 100);
            }
        }
    }

    public void startAnimation(View view, int id) {
        animation = AnimationUtils.loadAnimation(getContext(), id);
        view.startAnimation(animation);
    }


}
