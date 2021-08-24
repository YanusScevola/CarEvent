package com.yanus.carevent.service;

import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Repository {
    private static Repository instance;
    private StorageReference currentUserProfileImageRef;
    private  FirebaseStorage storageInstance;
    private  FirebaseAuth firebaseAuth;
    private MutableLiveData<Uri> uriMutableLiveData;




    private Repository() {
        firebaseAuth = FirebaseAuth.getInstance();
        storageInstance = FirebaseStorage.getInstance();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

//        DatabaseReference currentUserNicknameRef = firebaseDatabase.getReference()
//                .child("users")
//                .child(firebaseAuth.getCurrentUser().getUid())
//                .child("nickname");

    }

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }


    public void getCurrentUserImageProfile(String uid) {
        currentUserProfileImageRef = storageInstance.getReference().child("user/" + uid + "/profile.jpg");
        currentUserProfileImageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
               uriMutableLiveData.setValue(uri);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });


    }

    public void uploadProfileImageToFirebase(Fragment fragment, Uri image, String uid) {
        currentUserProfileImageRef = storageInstance.getReference().child("user/" + uid + "/profile.jpg");
        currentUserProfileImageRef.putFile(image).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(fragment.getContext(), "Фото добавленно", Toast.LENGTH_SHORT).show();
                uriMutableLiveData.setValue(image);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(fragment.getContext(), "Фото НЕ добавленно на сервер", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public LiveData<Uri> getUriImageLiveData() {
        if (uriMutableLiveData == null) {
            uriMutableLiveData = new MutableLiveData<>();
        }

        return uriMutableLiveData;
    }


}
