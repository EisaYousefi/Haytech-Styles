package com.haytech.haytechstyle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.Toast;

import com.haytech.haytechstyle.databinding.ActivityMainBinding;
import com.haytech.haytechstyles.Validation;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements Validation.phoneValidator {

    private ActivityMainBinding binding ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this , R.layout.activity_main);

         // binding.passwordFieldLayout.setTextGoodPass(R.string.app_name);
       // binding.passwordFieldLayout.setFlagValidationPass(true);

        binding.phoneFieldLayout.setValidatorListener(this);

        binding.button.setOnClickListener(v -> {
            if (!Objects.requireNonNull(binding.usernameFieldEditText.getText()).toString().trim().equals("eisa")) {
                binding.usernameFieldLayout.setNotValidationError(R.string.notValidUser);
            } else if (!Objects.requireNonNull(binding.passwordFieldEditText.getText()).toString().trim().equals("eisa@2910")) {
                binding.passwordFieldLayout.setNotValidationError(R.string.notValidPass);
            } else {
                Toast.makeText(this, "user and pass valid", Toast.LENGTH_SHORT).show();
            }
        });

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
        Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
        binding.phoneFieldLayout.setError("");
         }
}
