package com.yanus.carevent.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.yanus.carevent.databinding.ActivityViewEventBinding;

public class ViewEventActivity extends AppCompatActivity {
    ActivityViewEventBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewEventBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}