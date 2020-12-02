package com.haytech.haytechstyles.customLoginKebord;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
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
import com.haytech.haytechstyles.utils.ThreadUtils;
import com.haytech.haytechstyles.utils.UIUtilsHytechStyle;

public class CustomKeyboard extends ConstraintLayout {

    private static final int CREATE_PASS_STATE = 1;
    private static final int LOGIN_TO_APP_STATE = 2;
    private static final int CHANGE_PASS_STATE = 3;
    private int stateSelected = 1;
    private CircleCheckers circleCheckers[];
    private int countValuePass1 = 0, countValuePass2 = 7, countValuePass3 = 7;
    private int backSpace1 = 0, backSpace2 = 0;
    private String valuePass1 = "", valuePass2 = "", valuePass3 = "";
    private int backPressedKey = 0;
    public TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9, tv0, tvError;
    private CircleCheckers one, tow, three, four, five, sex;
    private TextView tvLable;
    private Animation animation;
    private String validPass = "";
    private TextView tvFingerPrint;
    private ImageView imgFingerPrint;
    private int counterErrorPass = 0;

    private OnKeyboardCustomListener.CreateNewPass createNewPassListener;
    private OnKeyboardCustomListener.LoginApp loginAppListener;
    private OnKeyboardCustomListener.ChangePass changePassListener;
    private OnKeyboardCustomListener.OnClickKey onClickKeyListener;

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

    public TextView getTvLable() {
        return tvLable;
    }

    public void setTvLable(String tvLable) {
        this.tvLable.setText(tvLable);
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

        one = findViewById(R.id.one);
        tow = findViewById(R.id.tow);
        three = findViewById(R.id.three);
        four = findViewById(R.id.foure);
        five = findViewById(R.id.five);
        sex = findViewById(R.id.sex);
        circleCheckers = new CircleCheckers[]{one, tow, three, four, five, sex};
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
        tvLable = findViewById(R.id.tv_lable);
        ImageView btnClear = findViewById(R.id.img_clear);
        ConstraintLayout layoutBackSpace = findViewById(R.id.constrant_clear);
        tvFingerPrint = findViewById(R.id.tv_finger_print);
        imgFingerPrint = findViewById(R.id.img_finger_print);


        btnClear.setOnClickListener(v -> {
            clear();
        });
        layoutBackSpace.setOnClickListener(v -> {
            clear();
        });

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


    }

    public void clear() {
        if (getStateSelected() == CREATE_PASS_STATE) {
            if (countValuePass1 > 0 && countValuePass2 == 7) {
                circleCheckers[countValuePass1 - 1].setInnerColor(getResources().getColor(R.color.back_my_text_checker));
                circleCheckers[countValuePass1 - 1].setOuterColor(getResources().getColor(R.color.back_my_text_checker));
                circleCheckers[countValuePass1 - 1].onClick(new CustomKeyboard(getContext()));
                if (countValuePass1 != 0) {
                    valuePass1 = valuePass1.substring(0, countValuePass1 - 1);
                    countValuePass1--;
                    backSpace1--;
                }
            } else if (backSpace1 == 0 && countValuePass2 > 0 && countValuePass2 != 7) {
                circleCheckers[countValuePass2 - 1].setInnerColor(getResources().getColor(R.color.back_my_text_checker));
                circleCheckers[countValuePass2 - 1].setOuterColor(getResources().getColor(R.color.back_my_text_checker));
                circleCheckers[countValuePass2 - 1].onClick(new CircleCheckers(getContext()));
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
                circleCheckers[countValuePass1 - 1].onClick(new CircleCheckers(getContext()));
                if (countValuePass1 != 0) {
                    valuePass1 = valuePass1.substring(0, countValuePass1 - 1);
                    countValuePass1--;
                    backSpace1--;
                }
                tvError.setVisibility(INVISIBLE);
            }
        } else if (getStateSelected() == CHANGE_PASS_STATE) {
            if (countValuePass1 > 0 && countValuePass2 == 7) {
                circleCheckers[countValuePass1 - 1].setInnerColor(getResources().getColor(R.color.back_my_text_checker));
                circleCheckers[countValuePass1 - 1].setOuterColor(getResources().getColor(R.color.back_my_text_checker));
                circleCheckers[countValuePass1 - 1].onClick(new CircleCheckers(getContext()));
                if (countValuePass1 != 0) {
                    valuePass1 = valuePass1.substring(0, countValuePass1 - 1);
                    countValuePass1--;
                    backSpace1--;
                }
            } else if (backSpace1 == 0 && countValuePass2 > 0 && countValuePass2 != 7 && countValuePass3 == 7) {
                circleCheckers[countValuePass2 - 1].setInnerColor(getResources().getColor(R.color.back_my_text_checker));
                circleCheckers[countValuePass2 - 1].setOuterColor(getResources().getColor(R.color.back_my_text_checker));
                circleCheckers[countValuePass2 - 1].onClick(new CircleCheckers(getContext()));
                if (countValuePass2 != 0) {
                    valuePass2 = valuePass2.substring(0, countValuePass2 - 1);
                    countValuePass2--;
                    backSpace2--;
                }
                tvError.setVisibility(View.INVISIBLE);
            } else if (backSpace2 == 0 && countValuePass3 > 0 && countValuePass3 != 7) {
                circleCheckers[countValuePass3 - 1].setInnerColor(getResources().getColor(R.color.back_my_text_checker));
                circleCheckers[countValuePass3 - 1].setOuterColor(getResources().getColor(R.color.back_my_text_checker));
                circleCheckers[countValuePass3 - 1].onClick(new CircleCheckers(getContext()));
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

    }

    private void switchDownAndUp(MotionEvent event, TextView tv) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ColorStateList csl = ColorStateList.valueOf(getResources().getColor(R.color.ripple_color));
                    RippleDrawable rippleDrawable = new RippleDrawable(csl, null, null);
                }
                tv.setBackgroundResource(R.drawable.back_text_pass_doun);
                tv.setTextColor(getResources().getColor(R.color.white));

                //create a new pass   //تعریف پسورد اولیه برای ورود به نرم افزار
                if (getStateSelected() == CREATE_PASS_STATE) {
                    if (countValuePass1 < 6) {
                        circleCheckers[countValuePass1].setInnerColor(getResources().getColor(R.color.white));
                        circleCheckers[countValuePass1].setOuterColor(getResources().getColor(R.color.radioButtonColor));
                        circleCheckers[countValuePass1].onClick(new CircleCheckers(getContext()));
                        countValuePass1++;
                        backSpace1++;
                        backPressedKey = 0;
                        valuePass1 += tv.getText().toString().trim();
                        tvError.setVisibility(View.INVISIBLE);
                    }

                    //repeat pass
                    if (countValuePass2 < 6) {
                        circleCheckers[countValuePass2].setInnerColor(getResources().getColor(R.color.white));
                        circleCheckers[countValuePass2].setOuterColor(getResources().getColor(R.color.radioButtonColor));
                        circleCheckers[countValuePass2].onClick(new CircleCheckers(getContext()));
                        countValuePass2++;
                        valuePass2 += tv.getText().toString().trim();
                        backPressedKey = 1;
                    }
                    //insert to App After create pass
                } else if (getStateSelected() == LOGIN_TO_APP_STATE) {
                    if (countValuePass1 < 6) {
                        circleCheckers[countValuePass1].setInnerColor(getResources().getColor(R.color.white));
                        circleCheckers[countValuePass1].setOuterColor(getResources().getColor(R.color.radioButtonColor));
                        circleCheckers[countValuePass1].onClick(new CircleCheckers(getContext()));
                        countValuePass1++;
                        backSpace1++;
                        backPressedKey = 0;
                        valuePass1 += tv.getText().toString().trim();
                        tvError.setVisibility(View.INVISIBLE);
                    }
                    //change pass
                } else if (getStateSelected() == CHANGE_PASS_STATE) {
                    //checked Old Pass is valid or not valid
                    if (countValuePass1 < 6) {
                        circleCheckers[countValuePass1].setInnerColor(getResources().getColor(R.color.white));
                        circleCheckers[countValuePass1].setOuterColor(getResources().getColor(R.color.radioButtonColor));
                        circleCheckers[countValuePass1].onClick(new CircleCheckers(getContext()));
                        countValuePass1++;
                        backSpace1++;
                        backPressedKey = 0;
                        valuePass1 += tv.getText().toString().toLowerCase();
                        tvError.setVisibility(View.INVISIBLE);
                    }

                    //new pass
                    if (countValuePass2 < 6) {
                        circleCheckers[countValuePass2].setInnerColor(getResources().getColor(R.color.white));
                        circleCheckers[countValuePass2].setOuterColor(getResources().getColor(R.color.radioButtonColor));
                        circleCheckers[countValuePass2].onClick(new CircleCheckers(getContext()));
                        countValuePass2++;
                        backSpace2++;
                        valuePass2 += tv.getText().toString().trim();
                        backPressedKey = 1;
                    }
                    //repeat new pass
                    if (countValuePass3 < 6) {
                        circleCheckers[countValuePass3].setInnerColor(getResources().getColor(R.color.white));
                        circleCheckers[countValuePass3].setOuterColor(getResources().getColor(R.color.radioButtonColor));
                        circleCheckers[countValuePass3].onClick(new CircleCheckers(getContext()));
                        countValuePass3++;
                        valuePass3 += tv.getText().toString().trim();
                        backPressedKey = 2;
                    }

                }

                break;
            case MotionEvent.ACTION_UP:
                tv.setBackgroundResource(R.drawable.back_text_pass_up);
                tv.setTextColor(getResources().getColor(R.color.un_select_keybord));

                if (getStateSelected() == CREATE_PASS_STATE) {
                    if (backSpace1 == 6) {
                        for (int i = 0; i < 6; i++) {
                            circleCheckers[i].setInnerColor(getResources().getColor(R.color.back_my_text_checker));
                            circleCheckers[i].setOuterColor(getResources().getColor(R.color.back_my_text_checker));
                            circleCheckers[i].onClick(new CircleCheckers(getContext()));

                            backPressedKey = 1;
                            ThreadUtils.onUI(() -> startAnimation(tvLable, R.anim.slide_out_right), 2);

                            ThreadUtils.onUI(() -> {
                                tvLable.setVisibility(View.VISIBLE);
                                startAnimation(tvLable, R.anim.slid_in_left_interpolator);
                                tvLable.setText(getResources().getString(R.string.repeat_pass));
                            }, 300);
                        }

                        tvError.setVisibility(View.INVISIBLE);
                        backSpace1 = 0;
                        countValuePass2 = 0;
                    }

                    //checked valid pass . if valid insert to app .if not valid error
                    if (countValuePass2 == 6) {
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
                    if (!valuePass1.equals(getValidPass()) && backSpace1 == 6) {
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
                    if (backSpace1 == 6) {
                        if (!valuePass1.equals(getValidPass())) {
                            ThreadUtils.onUI(() -> {
                                tvError.setVisibility(View.VISIBLE);
                                UIUtilsHytechStyle.getInstance().animateTransferTypeButton(tvError);
                            }, 300);
                        } else {
                            for (int i = 0; i < 6; i++) {
                                circleCheckers[i].setInnerColor(getResources().getColor(R.color.back_my_text_checker));
                                circleCheckers[i].setOuterColor(getResources().getColor(R.color.back_my_text_checker));
                                circleCheckers[i].onClick(new CircleCheckers(getContext()));
                                tvError.setVisibility(INVISIBLE);
                                backPressedKey = 1;
                                ThreadUtils.onUI(() -> {
                                    startAnimation(tvLable, R.anim.slide_out_right);
                                }, 2);

                                ThreadUtils.onUI(() -> {
                                    tvLable.setVisibility(View.VISIBLE);
                                    startAnimation(tvLable, R.anim.slid_in_left_interpolator);
                                    tvLable.setText(getResources().getString(R.string.new_pass));
                                }, 300);
                            }

                            tvError.setVisibility(View.INVISIBLE);
                            backSpace1 = 0;
                            countValuePass2 = 0;
                        }
                    }
                    if (backSpace2 == 6) {
                        for (int i = 0; i < 6; i++) {
                            circleCheckers[i].setInnerColor(getResources().getColor(R.color.back_my_text_checker));
                            circleCheckers[i].setOuterColor(getResources().getColor(R.color.back_my_text_checker));
                            circleCheckers[i].onClick(new CircleCheckers(getContext()));
                            tvError.setVisibility(INVISIBLE);
                            backPressedKey = 2;
                            ThreadUtils.onUI(() -> {
                                startAnimation(tvLable, R.anim.slide_out_right);
                            }, 2);

                            ThreadUtils.onUI(() -> {
                                tvLable.setVisibility(View.VISIBLE);
                                startAnimation(tvLable, R.anim.slid_in_left_interpolator);
                                tvLable.setText(getResources().getString(R.string.repeat_pass));
                            }, 300);

                        }
                        backSpace2 = 0;
                        countValuePass3 = 0;
                    }
                    if (countValuePass3 == 6) {
                        if (!valuePass2.equals(valuePass3)) {
                            ThreadUtils.onUI(() -> {
                                tvError.setVisibility(View.VISIBLE);
                                UIUtilsHytechStyle.getInstance().animateTransferTypeButton(tvError);
                            }, 300);

                        } else {
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
            countValuePass2 = 7;
            backSpace1 = 0;
            backPressedKey = 0;
            valuePass1 = "";
            valuePass2 = "";
            for (int i = 0; i < 6; i++) {
                circleCheckers[i].setInnerColor(getResources().getColor(R.color.back_my_text_checker));
                circleCheckers[i].setOuterColor(getResources().getColor(R.color.back_my_text_checker));
                circleCheckers[i].onClick(new CircleCheckers(getContext()));
                tvLable.setText(getResources().getString(R.string.define_app_password));
                tvError.setVisibility(View.INVISIBLE);

                ThreadUtils.onUI(() -> {
                    startAnimation(tvLable, R.anim.slide_in_right);
                    tvLable.setVisibility(View.VISIBLE);
                }, 100);
            }
        } else if (backPressedKey == 2) {
            countValuePass2 = 0;
            countValuePass3 = 7;
            backSpace2 = 0;
            setBackPressedKey(1);
            setValuePass3("");
            setValuePass2("");
            for (int i = 0; i < 6; i++) {
                circleCheckers[i].setInnerColor(getResources().getColor(R.color.back_my_text_checker));
                circleCheckers[i].setOuterColor(getResources().getColor(R.color.back_my_text_checker));
                circleCheckers[i].onClick(new CircleCheckers(getContext()));
                tvLable.setText(getResources().getString(R.string.new_pass));
                tvError.setVisibility(View.INVISIBLE);

                ThreadUtils.onUI(() -> {
                    startAnimation(tvLable, R.anim.slide_in_right);
                    tvLable.setVisibility(View.VISIBLE);
                }, 100);
            }
        }
    }

    public void startAnimation(View view, int id) {
        animation = AnimationUtils.loadAnimation(getContext(), id);
        view.startAnimation(animation);
    }


}
