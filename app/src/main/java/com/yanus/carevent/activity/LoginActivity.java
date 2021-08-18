package com.yanus.carevent.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.yanus.carevent.databinding.ActivityForgotPasswordBinding;
import com.yanus.carevent.databinding.ActivityLoginBinding;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();

        binding.login.setOnClickListener(view -> {
            final String email = Objects.requireNonNull(binding.email.getText()).toString().trim();
            final String password = Objects.requireNonNull(binding.password.getText()).toString().trim();

            if (TextUtils.isEmpty(email)) {
                binding.email.setError("Введите emile");

                if (TextUtils.isEmpty(email) & TextUtils.isEmpty(password)) {
                    binding.email.setError("Введите emile");
                    binding.password.setError("Введите password");
                }
                return;
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(getApplicationContext(), "Не верно введён emile", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                binding.password.setError("Введите password");
                return;
            }


            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        AuthResult authResult = task.getResult();
                        FirebaseUser firebaseUser = authResult.getUser();
                        String mEmile = firebaseUser.getEmail();
                        Log.i("key", mEmile);

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Не верный emile или password", Toast.LENGTH_LONG).show();
                    }

                }
            });

        });


    }
}