package com.yanus.carevent.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.yanus.carevent.databinding.ActivitySignUpBinding;
import com.yanus.carevent.model.UserModel;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {
    public ActivitySignUpBinding binding;
    public FirebaseAuth firebaseAuth;
    public FirebaseDatabase firebaseDatabase;
    public DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = firebaseDatabase.getReference();


        binding.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nickname = Objects.requireNonNull(binding.nickname.getText()).toString().trim();
                final String email = Objects.requireNonNull(binding.email.getText()).toString().trim();
                final String password = Objects.requireNonNull(binding.password.getText()).toString().trim();


                if (TextUtils.isEmpty(nickname)) {
                    binding.nickname.setError("Введите никнейм");
                }

                if (TextUtils.isEmpty(email)) {
                    binding.email.setError("Введите emile");
                    return;
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Log.i("key", "Проверка 1");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    binding.password.setError("Введите password");
                    return;
                }


                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                    startActivity(intent);

                                    DatabaseReference userReference = databaseReference.child("Users");
                                    //userReference.setValue();

                                } else {
                                    Toast.makeText(getApplicationContext(), "НЕ зарегистрировался", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}