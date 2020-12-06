package com.haytech.haytechstyles.customLoginKebord;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.RippleDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
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
    private int stateSelected = 1;
    private RadioButtonField[] circleCheckers;
    private int countValuePass1, countValuePass2, countValuePass3;
    private int backSpace1 = 0, backSpace2 = 0;
    private String valuePass1 = "", valuePass2 = "", valuePass3 = "";
    private int backPressedKey = 0;
    public TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9, tv0, tvError;
    private RadioButtonField one, tow, three, four, five, sex;
    private TextView tvLabel, tvHeaderTitle;
    private Animation animation;
    private String validPass = "";
    private TextView tvFingerPrint;
    private ImageView imgFingerPrint;
    private int counterErrorPass = 0;

    private int backgroundDownKey;
    private int backgroundUpKey;
    private int textColorDwnKey;
    private int textColorUpKey;
    private int count;
    private TitleAndHeaderModel titleAndHeaderModel;
    private OnKeyboardCustomListener.CreateNewPass createNewPassListener;
    private OnKeyboardCustomListener.LoginApp loginAppListener;
    private OnKeyboardCustomListener.ChangePass changePassListener;
    private OnKeyboardCustomListener.OnClickKey onClickKeyListener;

    private int innerColor;
    private int outerColor;
    private int typeCircle = 0;
    private ConstraintLayout layoutBackSpace;
    private ImageView btnClear;

    public int getCount() {
        return count;
    }

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

    public String getValuePass3() {
        return valuePass3;
    }

    public void setValuePass3(String valuePass3) {
        this.valuePass3 = valuePass3;
    }

    public int getStateSelected() {
        return stateSelected;
    }

    public void setStateSelected(int stateSelected) {
        this.stateSelected = stateSelected;
    }

    public String getValidPass() {
        return validPass;
    }

    public void setValidPass(String validPass) {
        this.validPass = validPass;
    }

    public TextView getTvLabel() {
        return tvLabel;
    }

    public void setTvLabel(String tvLabel) {
        this.tvLabel.setText(tvLabel);
        getTitleAndHeaderModel().setTvLabel(tvLabel);
    }

    public TextView getTvHeaderTitle() {
        return tvHeaderTitle;
    }

    public void setTvHeaderTitle(String tvHeaderTitle) {
        this.tvHeaderTitle.setText(tvHeaderTitle);
        getTitleAndHeaderModel().setTvHeaderTitle(tvHeaderTitle);
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

    public String getValuePass1() {
        return valuePass1;
    }

    public void setValuePass1(String valuePass1) {
        this.valuePass1 = valuePass1;
    }

    public String getValuePass2() {
        return valuePass2;
    }

    public void setValuePass2(String valuePass2) {
        this.valuePass2 = valuePass2;
    }

    public void showTvFingerPrint() {
        tvFingerPrint.setVisibility(VISIBLE);
    }

    public void hideTvFingerPrint() {
        tvFingerPrint.setVisibility(INVISIBLE);
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
//        layoutInflater = LayoutInflater.from(context);
//        binding = DataBindingUtil.inflate(layoutInflater, R.layout.keybord_custom, this, false);
        LayoutInflater.from(context).inflate(R.layout.keboard_custom, this);
        ViewCompat.setLayoutDirection(this, ViewCompat.LAYOUT_DIRECTION_LTR);
        setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        setLayerType(LAYER_TYPE_HARDWARE, null);
        //setBackground(getResources().getDrawable(R.drawable.expandable_layout_bkg));
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) getLayoutParams();
        params.gravity = Gravity.TOP;
        setLayoutParams(params);

        titleAndHeaderModel = new TitleAndHeaderModel(getContext());
        titleAndHeaderModel.setDataTitleAndHeader();

        one = findViewById(R.id.one);
        tow = findViewById(R.id.tow);
        three = findViewById(R.id.three);
        four = findViewById(R.id.foure);
        five = findViewById(R.id.five);
        sex = findViewById(R.id.sex);
        circleCheckers = new RadioButtonField[]{one, tow, three, four, five, sex};

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        tv5 = findViewById(R.id.tv5);
        tv6 = findViewById(R.id.tv6);
        tv7 = findViewById(R.id.tv7);
        tv8 = findViewById(R.id.tv8);
        tv9 = findViewById(R.id.tv9);
        tv0 = findViewById(R.id.tv0);
        tvError = findViewById(R.id.tv_error);
        tvLabel = findViewById(R.id.tv_lable);
        tvHeaderTitle = findViewById(R.id.headerTitle);
        btnClear = findViewById(R.id.img_clear);
        layoutBackSpace = findViewById(R.id.constrant_clear);
        tvFingerPrint = findViewById(R.id.tv_finger_print);
        imgFingerPrint = findViewById(R.id.img_finger_print);

        //onClickkListener
        tvFingerPrint.setOnClickListener(v1 -> {
            if (onClickKeyListener != null) {
                onClickKeyListener.tvFingerClick(v1);
            }
        });

        imgFingerPrint.setOnClickListener(v1 -> {
            if (onClickKeyListener != null) {
                onClickKeyListener.imgFingerClick(v1);
            }
        });


        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustomKeyboard);
        backgroundDownKey = typedArray.getResourceId(R.styleable.CustomKeyboard_CK_background_down, R.drawable.back_text_pass_doun);
        textColorDwnKey = typedArray.getInt(R.styleable.CustomKeyboard_CK_textColor_down, getResources().getColor(R.color.text_number_down));
        backgroundUpKey = typedArray.getResourceId(R.styleable.CustomKeyboard_CK_background_up, R.drawable.back_text_pass_up);
        textColorUpKey = typedArray.getInt(R.styleable.CustomKeyboard_CK_textColor_up, getResources().getColor(R.color.un_select_keybord));
        count = typedArray.getInt(R.styleable.CustomKeyboard_CK_count, DEFAULT_COUNT);
        innerColor = typedArray.getInt(R.styleable.CustomKeyboard_CK_one, R.color.blue);
        typedArray.recycle();

        tvFingerPrint.setBackgroundResource(backgroundUpKey);

        tv0.setTextColor(textColorUpKey);
        tv0.setBackgroundResource(backgroundUpKey);

        tv1.setTextColor(textColorUpKey);
        tv1.setBackgroundResource(backgroundUpKey);

        tv2.setTextColor(textColorUpKey);
        tv2.setBackgroundResource(backgroundUpKey);

        tv3.setTextColor(textColorUpKey);
        tv3.setBackgroundResource(backgroundUpKey);

        tv3.setTextColor(textColorUpKey);
        tv3.setBackgroundResource(backgroundUpKey);

        tv4.setTextColor(textColorUpKey);
        tv4.setBackgroundResource(backgroundUpKey);

        tv5.setTextColor(textColorUpKey);
        tv5.setBackgroundResource(backgroundUpKey);

        tv6.setTextColor(textColorUpKey);
        tv6.setBackgroundResource(backgroundUpKey);

        tv7.setTextColor(textColorUpKey);
        tv7.setBackgroundResource(backgroundUpKey);

        tv8.setTextColor(textColorUpKey);
        tv8.setBackgroundResource(backgroundUpKey);

        tv9.setTextColor(textColorUpKey);
        tv9.setBackgroundResource(backgroundUpKey);

        layoutBackSpace.setBackgroundResource(backgroundUpKey);


        countValuePass1 = 0;
        countValuePass2 = count + 1;
        countValuePass3 = count + 1;

        switch (count) {
            case 3:
                four.setVisibility(GONE);
                five.setVisibility(GONE);
                sex.setVisibility(GONE);
            case 4:
                five.setVisibility(GONE);
                sex.setVisibility(GONE);
            case 5:
                sex.setVisibility(GONE);
        }

    }

    public void clear() {
        if (getStateSelected() == CREATE_PASS_STATE) {
            if (countValuePass1 > 0 && countValuePass2 == count + 1) {
                circleCheckers[countValuePass1 - 1].setInnerColor(getResources().getColor(R.color.back_my_text_checker));
                circleCheckers[countValuePass1 - 1].setOuterColor(getResources().getColor(R.color.back_my_text_checker));
                circleCheckers[countValuePass1 - 1].onClick(new CustomKeyboard(getContext()));
                if (countValuePass1 != 0) {
                    valuePass1 = valuePass1.substring(0, countValuePass1 - 1);
                    countValuePass1--;
                    backSpace1--;
                }
            } else if (backSpace1 == 0 && countValuePass2 > 0 && countValuePass2 != count + 1) {
                circleCheckers[countValuePass2 - 1].setInnerColor(getResources().getColor(R.color.back_my_text_checker));
                circleCheckers[countValuePass2 - 1].setOuterColor(getResources().getColor(R.color.back_my_text_checker));
                circleCheckers[countValuePass2 - 1].onClick(new RadioButtonField(getContext()));
                if (countValuePass2 != 0) {
                    valuePass2 = valuePass2.substring(0, countValuePass2 - 1);
                    countValuePass2--;
                }
                tvError.setVisibility(View.INVISIBLE);
            }
        } else if (getStateSelected() == LOGIN_TO_APP_STATE) {
            if (countValuePass1 > 0) {
                circleCheckers[countValuePass1 - 1].setInnerColor(getResources().getColor(R.color.back_my_text_checker));
                circleCheckers[countValuePass1 - 1].setOuterColor(getResources().getColor(R.color.back_my_text_checker));
                circleCheckers[countValuePass1 - 1].onClick(new RadioButtonField(getContext()));
                if (countValuePass1 != 0) {
                    valuePass1 = valuePass1.substring(0, countValuePass1 - 1);
                    countValuePass1--;
                    backSpace1--;
                }
                tvError.setVisibility(INVISIBLE);
            }
        } else if (getStateSelected() == CHANGE_PASS_STATE) {
            if (countValuePass1 > 0 && countValuePass2 == count + 1) {
                circleCheckers[countValuePass1 - 1].setInnerColor(getResources().getColor(R.color.back_my_text_checker));
                circleCheckers[countValuePass1 - 1].setOuterColor(getResources().getColor(R.color.back_my_text_checker));
                circleCheckers[countValuePass1 - 1].onClick(new RadioButtonField(getContext()));
                if (countValuePass1 != 0) {
                    valuePass1 = valuePass1.substring(0, countValuePass1 - 1);
                    countValuePass1--;
                    backSpace1--;
                }
            } else if (backSpace1 == 0 && countValuePass2 > 0 && countValuePass2 != count + 1 && countValuePass3 == count + 1) {
                circleCheckers[countValuePass2 - 1].setInnerColor(getResources().getColor(R.color.back_my_text_checker));
                circleCheckers[countValuePass2 - 1].setOuterColor(getResources().getColor(R.color.back_my_text_checker));
                circleCheckers[countValuePass2 - 1].onClick(new RadioButtonField(getContext()));
                if (countValuePass2 != 0) {
                    valuePass2 = valuePass2.substring(0, countValuePass2 - 1);
                    countValuePass2--;
                    backSpace2--;
                }
                tvError.setVisibility(View.INVISIBLE);
            } else if (backSpace2 == 0 && countValuePass3 > 0 && countValuePass3 != count + 1) {
                circleCheckers[countValuePass3 - 1].setInnerColor(getResources().getColor(R.color.back_my_text_checker));
                circleCheckers[countValuePass3 - 1].setOuterColor(getResources().getColor(R.color.back_my_text_checker));
                circleCheckers[countValuePass3 - 1].onClick(new RadioButtonField(getContext()));
                if (countValuePass3 != 0) {
                    valuePass3 = valuePass3.substring(0, countValuePass3 - 1);
                    countValuePass3--;
                }
                tvError.setVisibility(View.INVISIBLE);
            }
        }
    }


    @SuppressLint("ClickableViewAccessibility")
    public void onTouchMethod() {
        tv1.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switchDownAndUp(event, tv1);
                return true;
            }
        });

        tv2.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switchDownAndUp(event, tv2);
                return true;
            }
        });
        tv3.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switchDownAndUp(event, tv3);
                return true;
            }
        });
        tv0.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switchDownAndUp(event, tv0);
                return true;
            }
        });
        tv4.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switchDownAndUp(event, tv4);
                return true;
            }
        });
        tv5.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switchDownAndUp(event, tv5);
                return true;
            }
        });
        tv6.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switchDownAndUp(event, tv6);
                return true;
            }
        });
        tv7.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switchDownAndUp(event, tv7);
                return true;
            }
        });
        tv8.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switchDownAndUp(event, tv8);
                return true;
            }
        });
        tv9.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switchDownAndUp(event, tv9);
                return true;
            }
        });

        btnClear.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    layoutBackSpace.setBackgroundResource(backgroundDownKey);
                    break;
                case MotionEvent.ACTION_UP:
                    layoutBackSpace.setBackgroundResource(backgroundUpKey);
                    startAnimation(layoutBackSpace, R.anim.fade_in);
                    clear();
                    break;
            }
            return true;
        });

        layoutBackSpace.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    layoutBackSpace.setBackgroundResource(backgroundDownKey);
                    break;
                case MotionEvent.ACTION_UP:
                    layoutBackSpace.setBackgroundResource(backgroundUpKey);
                    clear();
                    startAnimation(layoutBackSpace, R.anim.fade_in);
                    break;
            }
            return true;
        });

    }

    private void switchDownAndUp(MotionEvent event, TextView tv) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ColorStateList csl = ColorStateList.valueOf(getResources().getColor(R.color.ripple_color));
                    RippleDrawable rippleDrawable = new RippleDrawable(csl, null, null);
                }
                tv.setBackgroundResource(backgroundDownKey);
                tv.setTextColor(textColorDwnKey);
                //create a new pass   //تعریف پسورد اولیه برای ورود به نرم افزار
                if (getStateSelected() == CREATE_PASS_STATE) {
                    if (countValuePass1 < count) {
                        circleCheckers[countValuePass1].setInnerColor(getResources().getColor(R.color.white));
                        circleCheckers[countValuePass1].setOuterColor(getResources().getColor(R.color.radioButtonColor));
                        circleCheckers[countValuePass1].onClick(new RadioButtonField(getContext()));
                        countValuePass1++;
                        backSpace1++;
                        backPressedKey = count;
                        valuePass1 += tv.getText().toString().trim();
                        tvError.setVisibility(View.INVISIBLE);
                    }

                    //repeat pass
                    if (countValuePass2 < count) {
                        circleCheckers[countValuePass2].setInnerColor(getResources().getColor(R.color.white));
                        circleCheckers[countValuePass2].setOuterColor(getResources().getColor(R.color.radioButtonColor));
                        circleCheckers[countValuePass2].onClick(new RadioButtonField(getContext()));
                        countValuePass2++;
                        valuePass2 += tv.getText().toString().trim();
                        backPressedKey = 1;
                    }
                    //insert to App After create pass
                } else if (getStateSelected() == LOGIN_TO_APP_STATE) {
                    if (countValuePass1 < count) {
                        circleCheckers[countValuePass1].setInnerColor(getResources().getColor(R.color.white));
                        circleCheckers[countValuePass1].setOuterColor(getResources().getColor(R.color.radioButtonColor));
                        circleCheckers[countValuePass1].onClick(new RadioButtonField(getContext()));
                        countValuePass1++;
                        backSpace1++;
                        backPressedKey = 0;
                        valuePass1 += tv.getText().toString().trim();
                        tvError.setVisibility(View.INVISIBLE);
                    }
                    //change pass
                } else if (getStateSelected() == CHANGE_PASS_STATE) {
                    //checked Old Pass is valid or not valid
                    if (countValuePass1 < count) {
                        circleCheckers[countValuePass1].setInnerColor(getResources().getColor(R.color.white));
                        circleCheckers[countValuePass1].setOuterColor(getResources().getColor(R.color.radioButtonColor));
                        circleCheckers[countValuePass1].onClick(new RadioButtonField(getContext()));
                        countValuePass1++;
                        backSpace1++;
                        backPressedKey = 0;
                        valuePass1 += tv.getText().toString().toLowerCase();
                        tvError.setVisibility(View.INVISIBLE);
                    }

                    //new pass
                    if (countValuePass2 < count) {
                        circleCheckers[countValuePass2].setInnerColor(getResources().getColor(R.color.white));
                        circleCheckers[countValuePass2].setOuterColor(getResources().getColor(R.color.radioButtonColor));
                        circleCheckers[countValuePass2].onClick(new RadioButtonField(getContext()));
                        countValuePass2++;
                        backSpace2++;
                        valuePass2 += tv.getText().toString().trim();
                        backPressedKey = 1;
                    }
                    //repeat new pass
                    if (countValuePass3 < count) {
                        circleCheckers[countValuePass3].setInnerColor(getResources().getColor(R.color.white));
                        circleCheckers[countValuePass3].setOuterColor(getResources().getColor(R.color.radioButtonColor));
                        circleCheckers[countValuePass3].onClick(new RadioButtonField(getContext()));
                        countValuePass3++;
                        valuePass3 += tv.getText().toString().trim();
                        backPressedKey = 2;
                    }

                }

                break;
            case MotionEvent.ACTION_UP:
                tv.setBackgroundResource(backgroundUpKey);
                tv.setTextColor(textColorUpKey);
                startAnimation(tv, R.anim.fade_in);
                if (getStateSelected() == CREATE_PASS_STATE) {
                    if (backSpace1 == count) {
                        for (int i = 0; i < count; i++) {
                            circleCheckers[i].setInnerColor(getResources().getColor(R.color.back_my_text_checker));
                            circleCheckers[i].setOuterColor(getResources().getColor(R.color.back_my_text_checker));
                            circleCheckers[i].onClick(new RadioButtonField(getContext()));
                            backPressedKey = 1;
                            ThreadUtils.onUI(() -> {
                                startAnimation(tvLabel, R.anim.slide_out_right);
                                startAnimation(tvHeaderTitle, R.anim.slide_out_right);
                            }, 2);

                            ThreadUtils.onUI(() -> {
                                tvLabel.setVisibility(View.VISIBLE);
                                startAnimation(tvLabel, R.anim.slid_in_left_interpolator);
                                startAnimation(tvHeaderTitle, R.anim.slid_in_left_interpolator);
                                tvLabel.setText(titleAndHeaderModel.getTvLabelRepeatNewPass2());
                                tvHeaderTitle.setText(titleAndHeaderModel.getTvHeaderRepeatNewPass2());
                            }, 300);
                        }

                        tvError.setVisibility(View.INVISIBLE);
                        backSpace1 = 0;
                        countValuePass2 = 0;
                    }

                    //checked valid pass . if valid insert to app .if not valid error
                    if (countValuePass2 == count) {
                        if (!valuePass1.equals(valuePass2)) {
                            ThreadUtils.onUI(() -> {
                                tvError.setVisibility(View.VISIBLE);
                                UIUtilsHytechStyle.getInstance().animateTransferTypeButton(tvError);
                            }, 300);

                        } else {
                            if (createNewPassListener != null)
                                createNewPassListener.validPass();
                        }
                        Log.i("eisavvvvv", "countValuePass1 2 : " + valuePass2);
                    }
                } else if (getStateSelected() == LOGIN_TO_APP_STATE) {
                    if (!valuePass1.equals(getValidPass()) && backSpace1 == count) {
                        ThreadUtils.onUI(() -> {
                            tvError.setVisibility(View.VISIBLE);
                            UIUtilsHytechStyle.getInstance().animateTransferTypeButton(tvError);
                            counterErrorPass++;
                            if (counterErrorPass > 3) {
                                loginAppListener.counterErrorPass();
                            }
                        }, 300);

                    } else {
                        tvError.setVisibility(View.INVISIBLE);
                        loginAppListener.validPass();
                    }
                } else if (getStateSelected() == CHANGE_PASS_STATE) {
                    //valid old pass
                    if (backSpace1 == count) {
                        if (!valuePass1.equals(getValidPass())) {
                            ThreadUtils.onUI(() -> {
                                tvError.setVisibility(View.VISIBLE);
                                UIUtilsHytechStyle.getInstance().animateTransferTypeButton(tvError);
                            }, 300);
                        } else {
                            for (int i = 0; i < count; i++) {
                                circleCheckers[i].setInnerColor(getResources().getColor(R.color.back_my_text_checker));
                                circleCheckers[i].setOuterColor(getResources().getColor(R.color.back_my_text_checker));
                                circleCheckers[i].onClick(new RadioButtonField(getContext()));
                                tvError.setVisibility(INVISIBLE);
                                backPressedKey = 1;
                                ThreadUtils.onUI(() -> {
                                    startAnimation(tvLabel, R.anim.slide_out_right);
                                    startAnimation(tvHeaderTitle, R.anim.slide_out_right);
                                }, 2);

                                ThreadUtils.onUI(() -> {
                                    tvLabel.setVisibility(View.VISIBLE);
                                    startAnimation(tvLabel, R.anim.slid_in_left_interpolator);
                                    startAnimation(tvHeaderTitle, R.anim.slid_in_left_interpolator);
                                    tvLabel.setText(getTitleAndHeaderModel().getTvLabelCreateNewForPassChange3());
                                    tvHeaderTitle.setText(getTitleAndHeaderModel().getTvHeaderCreateNewForPassChange3());

                                }, 300);
                            }

                            tvError.setVisibility(View.INVISIBLE);
                            backSpace1 = 0;
                            countValuePass2 = 0;
                        }
                    }
                    if (backSpace2 == count) {
                        for (int i = 0; i < count; i++) {
                            circleCheckers[i].setInnerColor(getResources().getColor(R.color.back_my_text_checker));
                            circleCheckers[i].setOuterColor(getResources().getColor(R.color.back_my_text_checker));
                            circleCheckers[i].onClick(new RadioButtonField(getContext()));
                            tvError.setVisibility(INVISIBLE);
                            backPressedKey = 2;
                            ThreadUtils.onUI(() -> {
                                startAnimation(tvLabel, R.anim.slide_out_right);
                                startAnimation(tvHeaderTitle, R.anim.slide_out_right);
                            }, 2);

                            ThreadUtils.onUI(() -> {
                                tvLabel.setVisibility(View.VISIBLE);
                                startAnimation(tvLabel, R.anim.slid_in_left_interpolator);
                                tvLabel.setText(getTitleAndHeaderModel().getTvLabelRepeatNewForPassChange3());
                                tvHeaderTitle.setText(getTitleAndHeaderModel().getTvHeaderRepeatNewForPassChange3());
                            }, 300);

                        }
                        backSpace2 = 0;
                        countValuePass3 = 0;
                    }
                    if (countValuePass3 == count) {
                        if (!valuePass2.equals(valuePass3)) {
                            ThreadUtils.onUI(() -> {
                                tvError.setVisibility(View.VISIBLE);
                                UIUtilsHytechStyle.getInstance().animateTransferTypeButton(tvError);
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

    //پایان تعریف پسورد اولیه

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

    public void onBackPrees() {
        if (backPressedKey == 1) {
            countValuePass1 = 0;
            countValuePass2 =count+1;
            backSpace1 = 0;
            setBackPressedKey(0);
            valuePass1 = "";
            valuePass2 = "";
            for (int i = 0; i < count; i++) {
                circleCheckers[i].setChecked(true);
                circleCheckers[i].onClick(new RadioButtonField(getContext()));
                circleCheckers[i].setInnerColor(getResources().getColor(R.color.back_my_text_checker));
                circleCheckers[i].setOuterColor(getResources().getColor(R.color.back_my_text_checker));
                tvLabel.setText(getTitleAndHeaderModel().getTvLabel());
                tvHeaderTitle.setText(getTitleAndHeaderModel().getTvHeaderTitle());
                tvError.setVisibility(View.INVISIBLE);

                ThreadUtils.onUI(() -> {
                    startAnimation(tvLabel, R.anim.slide_in_right);
                    startAnimation(tvHeaderTitle, R.anim.slide_in_right);
                    tvLabel.setVisibility(View.VISIBLE);
                }, 100);
            }
        } else if (backPressedKey == 2) {
            countValuePass2 = 0;
            countValuePass3 = count+1;
            backSpace2 = 0;
            setBackPressedKey(1);
            valuePass2 = "";
            valuePass3 = "";
            for (int i = 0; i < count; i++) {
                circleCheckers[i].setChecked(true);
                circleCheckers[i].onClick(new RadioButtonField(getContext()));
                circleCheckers[i].setInnerColor(getResources().getColor(R.color.back_my_text_checker));
                circleCheckers[i].setOuterColor(getResources().getColor(R.color.back_my_text_checker));
                tvLabel.setText(getTitleAndHeaderModel().getTvLabelCreateNewForPassChange3());
                tvHeaderTitle.setText(getTitleAndHeaderModel().getTvHeaderCreateNewForPassChange3());
                tvError.setVisibility(View.INVISIBLE);

                ThreadUtils.onUI(() -> {
                    startAnimation(tvLabel, R.anim.slide_in_right);
                    startAnimation(tvHeaderTitle, R.anim.slide_in_right);
                    tvLabel.setVisibility(View.VISIBLE);
                }, 100);
            }
        }
    }

    public void startAnimation(View view, int id) {
        animation = AnimationUtils.loadAnimation(getContext(), id);
        view.startAnimation(animation);
    }


}
