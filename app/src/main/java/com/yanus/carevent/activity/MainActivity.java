package com.yanus.carevent.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.yanus.carevent.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    final FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() == null) {
            Intent intent = new Intent(this, NoAuthActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Пользователь отсутствует", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(MainActivity.this, UserMainActivity.class);
            startActivity(intent);
        }
    }
}