package com.yanus.carevent;

import android.net.Uri;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Repository {
    private StorageReference currentUserProfileImageRef;
    private static Repository instance;
    FirebaseStorage storageInstance;
    FirebaseAuth firebaseAuth;
    public Uri image;

    private Repository() {
        firebaseAuth = FirebaseAuth.getInstance();
        storageInstance = FirebaseStorage.getInstance();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

//        DatabaseReference currentUserNicknameRef = firebaseDatabase.getReference()
//                .child("users")
//                .child(firebaseAuth.getCurrentUser().getUid())
//                .child("nickname");

    }

    public static synchronized Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }


    public Uri getCurrentUserImageProfile(String uid) {
        currentUserProfileImageRef = storageInstance.getReference().child("user/" + uid + "/profile.jpg");

        currentUserProfileImageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                image = uri;
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
        return image;
    }

    public void uploadImageToFirebase(Fragment fragment, Uri image, ImageView imageView) {
        currentUserProfileImageRef.putFile(image).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                if (fragment.getActivity() != null) {
                    Glide.with(fragment).load(image).centerCrop().into(imageView);
                }
                Toast.makeText(fragment.getContext(), "Фото добавленно", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(fragment.getContext(), "Фото НЕ добавленно на сервер", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void w(Fragment fragment, Uri image, ImageView imageView){


    }
}
