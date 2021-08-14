package com.yanus.carevent.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yanus.carevent.databinding.ActivityNoAuthBinding;

public class NoAuthActivity extends AppCompatActivity {
    ActivityNoAuthBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNoAuthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NoAuthActivity.this, SignUpActivity.class));
            }
        });

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NoAuthActivity.this, LoginActivity.class));
            }
        });
    }
}