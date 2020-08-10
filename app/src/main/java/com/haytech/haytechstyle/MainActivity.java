package com.haytech.haytechstyle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Toast;

import com.haytech.haytechstyle.databinding.ActivityMainBinding;
import com.haytech.haytechstyles.Validation;
import com.haytech.haytechstyles.editTextVerify.VerifyCodeClickListener;

import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements Validation.phoneValidator {

    private ActivityMainBinding binding;
    private ValueAnimator animator;
    private CountDownTimer counter;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


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


}
