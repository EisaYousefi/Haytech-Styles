package com.haytech.haytechstyle;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.haytech.haytechstyle.databinding.ActivityMainBinding;
import com.haytech.haytechstyles.Validation;
import com.haytech.haytechstyles.customLoginKebord.OnKeyboardCustomListener;
import com.haytech.haytechstyles.editTextVerify.VerifyCodeClickListener;
import com.haytech.haytechstyles.layout.ErrorPage;
import com.haytech.haytechstyles.multibutton.BaseListAdapter;
import com.haytech.haytechstyles.multibutton.ItemStyleModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements Validation.phoneValidator, Validation.UsernameValidator {

    String state;
    private ActivityMainBinding binding;
    private ValueAnimator animator;
    private CountDownTimer counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        ArrayList<String> Items = new ArrayList<>();
        Items.add("سالم");
        Items.add("آسیب دیده");
        binding.multiButtonView.setItems(Items);
        binding.multiButtonView.setItemClickListener(new BaseListAdapter.OnItemClickListener<ItemStyleModel>() {
            @Override
            public void onItemClick(ItemStyleModel item, int position) {
                Log.i("vouria", item.getLabelText().toString() + "  " + position);
            }
        });

        List<String> listDate = new ArrayList<>();
        listDate.add("یک روز");
        listDate.add("یک هفته");
        listDate.add("یک ماه");
        listDate.add("یک سال");
        listDate.add("محدودیت");
        binding.seekbar2.setTitle(listDate).build();
        // binding.seekBarTimeSaveFilm.setTitle(listDate).build();


        binding.seekbar2.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });


        showErrorView();


        binding.key.onTouchMethod();

        binding.key.setVerifyCodeClickListener(new VerifyCodeClickListener() {
            @Override
            public void onResendCodeClicked(View view) {
                setCountDown();
            }

            @Override
            public void onBtnOkClicked(View view) {
                Toast.makeText(MainActivity.this, "" + binding.key.getVerifyNumber(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onEditPhoneNumberClicked(View view) {
                Toast.makeText(MainActivity.this, "3 " + binding.key.getTvEditPhoneNumber().toString(), Toast.LENGTH_SHORT).show();

            }


        });

        // binding.passwordFieldLayout.setTextGoodPass(R.string.app_name);
        // binding.passwordFieldLayout.setFlagValidationPass(true);

        binding.phoneFieldLayout.setValidatorListener(this);
        binding.usernameFieldLayout.setUsernameValidatorListener(this);

        binding.button.setOnClickListener(v -> {
            if (!Objects.requireNonNull(binding.usernameFieldEditText.getText()).toString().trim().equals("eisa")) {
                binding.usernameFieldLayout.setNotValidationError(R.string.notValidUser);
            } else if (!Objects.requireNonNull(binding.passwordFieldEditText.getText()).toString().trim().equals("123")) {
                binding.passwordFieldLayout.setNotValidationError(R.string.notValidPass);
            } else {
                Toast.makeText(this, "user and pass valid", Toast.LENGTH_SHORT).show();
            }
        });


        setCountDown();
        login();


        binding.verifyCodeView.getVerifyCode()
                .subscribe(verifyCode -> {
                    if (verifyCode.equals("11111")) {
                        binding.verifyCodeView.setTextPaint(getResources().getColor(R.color.colorRed));
                    } else
                        binding.verifyCodeView.setTextPaint(getResources().
                                getColor(R.color.blue));
                });

    }

    private void login() {

        state = "2";
        switch (state) {
            case "1":
                //create a new pass
                binding.customKeborad.hideImgFingerPrint();
               /* binding.customKeborad.setMessage("dsf");
                binding.customKeborad.setTitle("dsfdsfsd");
                binding.customKeborad.getTitleAndHeaderModel().setTvLabelRepeatNewPass2("ddsdfsdfsdf");
                binding.customKeborad.getTitleAndHeaderModel().setTvHeaderRepeatNewPass2("dd");*/
                binding.customKeborad.hideTvFingerPrint();
                binding.customKeborad.onTouchMethod();
                binding.customKeborad.setSelectedType(1);
                break;
            case "2":
                // login pass
                binding.customKeborad.setHeaderText(getResources().getString(R.string.enter_password));
                binding.customKeborad.setMessageText(getResources().getString(R.string.login));
                binding.customKeborad.onTouchMethod();
                binding.customKeborad.setSelectedType(2);
                binding.customKeborad.setValidPass("1111");

                break;
            case "3":
                //change pass
                binding.customKeborad.onTouchMethod();
                binding.customKeborad.setSelectedType(3);
                binding.customKeborad.setMessageText(getResources().getString(R.string.old_pass));
                binding.customKeborad.setHeaderText(getResources().getString(R.string.old_pass));
                binding.customKeborad.setValidPass("1111");
                binding.customKeborad.hideTvFingerPrint();
                binding.customKeborad.hideImgFingerPrint();
                break;

        }


        binding.customKeborad.setLoginAppListener(new OnKeyboardCustomListener.LoginApp() {
            @Override
            public void validPass() {
                if (binding.customKeborad.getCurrentOrCreateValuePassword().equals("1111")) {
                    Toast.makeText(MainActivity.this, "validPass", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void counterErrorPass() {
                Toast.makeText(MainActivity.this, "counterErrorPass", Toast.LENGTH_SHORT).show();
            }
        });

        binding.customKeborad.setChangePassListener(() -> {
            Toast.makeText(MainActivity.this, "validPass change", Toast.LENGTH_SHORT).show();
        });

        binding.customKeborad.setOnClickKeyListener(new OnKeyboardCustomListener.OnClickKey() {

            @Override
            public void tvFingerClick(View view) {
                Toast.makeText(MainActivity.this, "tvFingerClick", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void imgFingerClick(View view) {
                Toast.makeText(MainActivity.this, "imgFingerClick", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showErrorView() {
        binding.cvErrorPage.getFirstButtonTextView().setOnClickListener(v -> {
            binding.mainLayout.setVisibility(View.VISIBLE);
            binding.cvErrorPage.setVisibility(View.GONE);
        });
        binding.cvFilterView.getSearchButton().setOnClickListener(v -> {
            String searchText = binding.cvFilterView.getFilterBox().getText().toString().trim();
            switch (searchText) {
                case "0":
                    binding.cvErrorPage.setErrorType(ErrorPage.NO_SERVER_CONNECTION_TYPE);
                    binding.cvErrorPage.getFirstButtonTextView().setText(binding.cvErrorPage.getFirstButtonTextView().getText().toString() + "(برگشت)");
                    binding.mainLayout.setVisibility(View.GONE);
                    binding.cvErrorPage.setVisibility(View.VISIBLE);
                    break;
                case "1":
                    binding.cvErrorPage.setErrorType(ErrorPage.NOT_INTERNET_CONNECTION_TYPE);
                    binding.cvErrorPage.getFirstButtonTextView().setText(binding.cvErrorPage.getFirstButtonTextView().getText().toString() + "(برگشت)");
                    binding.mainLayout.setVisibility(View.GONE);
                    binding.cvErrorPage.setVisibility(View.VISIBLE);
                    break;
                case "2":
                    binding.cvErrorPage.setErrorType(ErrorPage.PHONE_NOT_SUPPORTED_TYPE);
                    binding.cvErrorPage.getFirstButtonTextView().setText(binding.cvErrorPage.getFirstButtonTextView().getText().toString() + "(برگشت)");
                    binding.mainLayout.setVisibility(View.GONE);
                    binding.cvErrorPage.setVisibility(View.VISIBLE);
                    break;
                case "3":
                    binding.cvErrorPage.setErrorType(ErrorPage.IMPORTANT_UPDATE_TYPE);
                    binding.cvErrorPage.getFirstButtonTextView().setText(binding.cvErrorPage.getFirstButtonTextView().getText().toString() + "(برگشت)");
                    binding.mainLayout.setVisibility(View.GONE);
                    binding.cvErrorPage.setVisibility(View.VISIBLE);
                    break;

                default:
                    Toast.makeText(MainActivity.this, "لطفا برای مشاهده صفحات خطا اعداد 0 تا 3 را وارد کنید" + binding.key.getVerifyNumber(), Toast.LENGTH_SHORT).show();
                    break;

            }

        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        simulateProgress();
    }

    private void setCountDown() {
        counter = new CountDownTimer(3000, 1000) {

            public void onTick(long millisUntilFinished) {
                //  title.setText(String.format("اطلاعات مربوط به %s در هفته %s ام", temp, weekNumber));
                binding.key.getTvReSendCode().setEnabled(false);
                binding.key.setTvReSendCode(getResources().getString(R.string.reSendCodeVerify));
                binding.key.getTvReSendCode().append(" ");
                binding.key.getTvReSendCode().setTextColor(getResources().getColor(R.color.textColor));

                binding.key.getTvReSendCode().append(String.format(new Locale("en"), "%02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))
                ));
                binding.key.getTvReSendCode().append(" دیگر");
            }

            public void onFinish() {
                binding.key.getTvReSendCode().setEnabled(true);
                binding.key.getTvReSendCode().setTextColor(getResources().getColor(R.color.colorBackground));
                binding.key.setTvReSendCode(getString(R.string.reSendCode));
                //  binding.timer.setVisibility(View.GONE);
                //  binding.resendCode.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    private void simulateProgress() {
        animator = ValueAnimator.ofInt(0, 101);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int progress = (int) animation.getAnimatedValue();
                binding.circleProgressBar.setProgress(progress);
            }
        });
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setDuration(10000);
        animator.start();
    }

    @Override
    public void startPhoneNumber(String startPhoneNumber) {
        binding.phoneFieldLayout.notValidStartPhoneNumber();
    }

    @Override
    public void notValidationPhoneNumber(String lengthPhoneNumber) {
        binding.phoneFieldLayout.notValidPhoneNumber(lengthPhoneNumber);
    }

    @Override
    public void emptyPhoneNumber() {
        binding.phoneFieldLayout.empty();
    }

    @Override
    public void validPhoneNumber() {
        binding.phoneFieldLayout.valid();
    }


    @Override
    public void userNameListener(int length) {
        Log.i("TAGeeee", "userNameListener: " + length);
    }

    @Override
    public void onBackPressed() {
        if (binding.customKeborad.getBackPressedKey() == 1) {
            binding.customKeborad.onBackPress();
        } else if (binding.customKeborad.getBackPressedKey() == 2) {
            binding.customKeborad.onBackPress();
        } else {
            super.onBackPressed();
        }
    }
}
