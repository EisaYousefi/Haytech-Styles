package com.haytech.haytechstyles.editTextVerify;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.CountDownTimer;
import android.util.AttributeSet;
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
import com.haytech.haytechstyles.button.ButtonFieldCircle;
import com.haytech.haytechstyles.utils.ThreadUtils;


public class CustomKeyboardEditTextVerify extends ConstraintLayout {


    private EditTextVerify[] editTextVerifies;
    private int counterNumbers = 0;
    private int backSpace1 = 0, backSpace2 = 0;
    private String verifyNumber = "";
    private int backPressedKey = 0;
    public TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9, tv0;
    private TextView tvEditPhoneNumber , tvReSendCode  , tvPhoneNumber;
    private ButtonFieldCircle buttonFieldCircle;
    private Animation animation;
    private CountDownTimer countDownTimer;
    private VerifyCodeClickListener verifyCodeClickListener ;

    public CustomKeyboardEditTextVerify(Context context) {
        super(context);
        initView(context, null);
    }

    public CustomKeyboardEditTextVerify(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public CustomKeyboardEditTextVerify(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(@androidx.annotation.NonNull final Context context, AttributeSet attrs) {

//        layoutInflater = LayoutInflater.from(context);
//        binding = DataBindingUtil.inflate(layoutInflater, R.layout.keybord_custom, this, false);
        LayoutInflater.from(context).inflate(R.layout.keboard_custom_verify, this);
        ViewCompat.setLayoutDirection(this, ViewCompat.LAYOUT_DIRECTION_LTR);
        setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        setLayerType(LAYER_TYPE_HARDWARE, null);
        //setBackground(getResources().getDrawable(R.drawable.expandable_layout_bkg));
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) getLayoutParams();
        params.gravity = Gravity.TOP;
        setLayoutParams(params);

        EditTextVerify one = findViewById(R.id.one);
        EditTextVerify tow = findViewById(R.id.tow);
        EditTextVerify three = findViewById(R.id.three);
        EditTextVerify four = findViewById(R.id.four);
        EditTextVerify five = findViewById(R.id.five);
        editTextVerifies = new EditTextVerify[]{one, tow, three, four, five};
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

        tvEditPhoneNumber = findViewById(R.id.tv_edit_phone_number);
        tvReSendCode = findViewById(R.id.tv_re_send);
        tvPhoneNumber = findViewById(R.id.tv_phone_number);
        buttonFieldCircle =findViewById(R.id.btn_verify);

        ImageView btnClear = findViewById(R.id.img_clear);


        for (int i = 0; i < editTextVerifies.length; i++) {
            editTextVerifies[i].setEnabled(false);
        }

        btnClear.setOnClickListener(v -> {
            clear();
        });

        tvEditPhoneNumber.setOnClickListener(v->{
            if (verifyCodeClickListener!=null)
                verifyCodeClickListener.onEditPhoneNumberClicked(v);
        });

        tvReSendCode.setOnClickListener(v->{
            if (verifyCodeClickListener!=null)
                verifyCodeClickListener.onResendCodeClicked(v);
        });

        buttonFieldCircle.setOnClickListener(v->{
            if (verifyCodeClickListener!=null)
                verifyCodeClickListener.onBtnOkClicked(v);
        });

    }

    //متدی برای پاک کردن اعداد
    public void clear() {
        if (counterNumbers > 0 && counterNumbers < 6) {
            editTextVerifies[counterNumbers - 1].setEmptyTextColor(getResources().getColor(R.color.text_empty_color));
            editTextVerifies[counterNumbers - 1].setFullTextColor(getResources().getColor(R.color.text_empty_color));
            editTextVerifies[counterNumbers - 1].onClick(new EditTextVerify(getContext()));
            if (counterNumbers != 0) {
                verifyNumber = verifyNumber.substring(0, counterNumbers - 1);
                counterNumbers--;
                backSpace1--;
            }
        }
    }


    //متدی جهت تاچ کردن بر روی رکمه های کیبورد
    @SuppressLint("ClickableViewAccessibility")
    public void onTouchMethod() {
        tv1.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switchDownAndUp(event, tv1, getResources().getString(R.string.one));
                return true;
            }
        });

        tv2.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switchDownAndUp(event, tv2, getResources().getString(R.string.tow));
                return true;
            }
        });
        tv3.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switchDownAndUp(event, tv3, getResources().getString(R.string.three));
                return true;
            }
        });
        tv0.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switchDownAndUp(event, tv0, getResources().getString(R.string.zero));
                return true;
            }
        });
        tv4.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switchDownAndUp(event, tv4, getResources().getString(R.string.four));
                return true;
            }
        });
        tv5.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switchDownAndUp(event, tv5, getResources().getString(R.string.five));
                return true;
            }
        });
        tv6.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switchDownAndUp(event, tv6, getResources().getString(R.string.sxs));
                return true;
            }
        });
        tv7.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switchDownAndUp(event, tv7, getResources().getString(R.string.seven));
                return true;
            }
        });
        tv8.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switchDownAndUp(event, tv8, getResources().getString(R.string.eahgt));
                return true;
            }
        });
        tv9.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switchDownAndUp(event, tv9, getResources().getString(R.string.nine));
                return true;
            }
        });

    }


    int a1;
    GradientDrawable shape1 = new GradientDrawable();

    @SuppressLint("ResourceType")
    private void switchDownAndUp(MotionEvent event, TextView tv, String textNumber) {
        a1 = 100;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                tv.setBackgroundResource(R.drawable.bck_btn_key);
                tv.setTextColor(getResources().getColor(R.color.text_key_down_color));
                if (counterNumbers < 5) {
                    editTextVerifies[counterNumbers].setValueNumber(textNumber);
                    editTextVerifies[counterNumbers].setEmptyTextColor(getResources().getColor(R.color.text_full_color));
                    editTextVerifies[counterNumbers].setFullTextColor(getResources().getColor(R.color.text_full_color));
                    editTextVerifies[counterNumbers].onClick(new EditTextVerify(getContext()));
                    counterNumbers++;
                    backSpace1++;
                    backPressedKey = 0;
                    verifyNumber += tv.getText().toString().toLowerCase();
                }

                break;
            case MotionEvent.ACTION_UP:

                ThreadUtils.onUI(() -> {
                    tv.setBackgroundResource(Color.TRANSPARENT);
                    tv.setTextColor(getResources().getColor(R.color.text_key_up_color));
                }, 300);

                break;
        }
    }

    public void onBackPrees() {
        if (backPressedKey == 1) {
            counterNumbers = 0;
            backSpace1 = 0;
            backPressedKey = 0;
            verifyNumber = "";
            for (int i = 0; i < 6; i++) {
                editTextVerifies[i].setEmptyTextColor(getResources().getColor(R.color.text_empty_color));
                editTextVerifies[i].setFullTextColor(getResources().getColor(R.color.text_empty_color));
                editTextVerifies[i].onClick(new EditTextVerify(getContext()));
            }
        } else if (backPressedKey == 2) {
            backSpace2 = 0;
            setBackPressedKey(1);
            for (int i = 0; i < 6; i++) {
                editTextVerifies[i].setEmptyTextColor(getResources().getColor(R.color.text_empty_color));
                editTextVerifies[i].setFullTextColor(getResources().getColor(R.color.text_empty_color));
                editTextVerifies[i].onClick(new EditTextVerify(getContext()));

                ThreadUtils.onUI(() -> {
                }, 100);
            }
        }
    }

    public TextView getTvEditPhoneNumber() {
        return tvEditPhoneNumber;
    }

    public void setTvEditPhoneNumber(String tvEditPhoneNumber) {
        this.tvEditPhoneNumber.setText(tvEditPhoneNumber);
    }

    public TextView getTvReSendCode() {
        return tvReSendCode;
    }

    public void setTvReSendCode(String tvReSendCode) {
        this.tvReSendCode.setText(tvReSendCode);
    }

    public TextView getTvPhoneNumber() {
        return tvPhoneNumber;
    }

    public void setTvPhoneNumber(String tvPhoneNumber) {
        this.tvPhoneNumber.setText(tvPhoneNumber);
    }

    public int getBackPressedKey() {
        return backPressedKey;
    }

    public void setBackPressedKey(int backPressedKey) {
        this.backPressedKey = backPressedKey;
    }

    public String getVerifyNumber() {
        return verifyNumber;
    }

    public void setVerifyNumber(String verifyNumber) {
        this.verifyNumber = verifyNumber;
    }

    public VerifyCodeClickListener getVerifyCodeClickListener() {
        return verifyCodeClickListener;
    }

    public void setVerifyCodeClickListener(VerifyCodeClickListener verifyCodeClickListener) {
        this.verifyCodeClickListener = verifyCodeClickListener;
    }

    public void startAnimation(View view, int id) {
        animation = AnimationUtils.loadAnimation(getContext(), id);
        view.startAnimation(animation);
    }


}
