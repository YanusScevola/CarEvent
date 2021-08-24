package com.yanus.carevent.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.yanus.carevent.service.Repository;
import com.yanus.carevent.R;
import com.yanus.carevent.activity.MainActivity;
import com.yanus.carevent.viewmodel.MyViewModel;

public class ProfileFragment extends Fragment {
    public ImageView imageViewSingOut, imageViewProfile;
    public TextView textViewNickname;
    public ActivityResultLauncher<String> resultContent;
    public FirebaseStorage storage;
    public DatabaseReference currentUserNicknameRef;
    public FirebaseAuth firebaseAuth;
    public FirebaseDatabase firebaseDatabase;
    public Uri uriImage;
    public MyViewModel viewModel;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageViewSingOut = view.findViewById(R.id.singOut);
        imageViewProfile = view.findViewById(R.id.profile_image);
        textViewNickname = view.findViewById(R.id.nickname);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        initProfileImageFromLiveData(ProfileFragment.this);

        currentUserNicknameRef = firebaseDatabase.getReference()
                .child("users")
                .child(firebaseAuth.getCurrentUser().getUid())
                .child("nickname");


        //Get and set a nickname into view.
        currentUserNicknameRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String nickname = snapshot.getValue().toString();
                    textViewNickname.setText(nickname);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "Ошибка получения никнейма", Toast.LENGTH_SHORT).show();
            }
        });

        resultContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri uri) {
                        viewModel.uploadProfileImageToFirebase(ProfileFragment.this, uri, firebaseAuth.getCurrentUser().getUid());
                        initProfileImageFromLiveData(ProfileFragment.this);
                    }
                });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }


    @Override
    public void onStart() {
        super.onStart();

        imageViewSingOut.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(ProfileFragment.this.getActivity(), MainActivity.class));
        });

        imageViewProfile.setOnClickListener(view -> {
            resultContent.launch("image/*");
        });
    }


    public void initProfileImageFromLiveData(Fragment fragment) {
        viewModel.getLiveDataProfileImage(firebaseAuth.getCurrentUser().getUid()).observe(this, profileImageUri -> {
            Glide.with(fragment).load(profileImageUri).centerCrop().into(imageViewProfile);
        });
    }


}