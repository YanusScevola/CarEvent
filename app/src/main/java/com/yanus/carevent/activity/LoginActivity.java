package com.yanus.carevent.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.yanus.carevent.databinding.ActivityForgotPasswordBinding;
import com.yanus.carevent.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}