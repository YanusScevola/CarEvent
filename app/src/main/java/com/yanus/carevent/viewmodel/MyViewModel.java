package com.yanus.carevent.viewmodel;

import android.net.Uri;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bumptech.glide.Glide;
import com.yanus.carevent.fragments.ProfileFragment;
import com.yanus.carevent.service.Repository;

public class MyViewModel extends ViewModel {
    private final Repository repository;

    public MyViewModel() {
        this.repository = Repository.getInstance();
    }

    public LiveData<Uri> getLiveDataProfileImage(String uid) {
        repository.getCurrentUserImageProfile(uid);
        return repository.getUriImageLiveData();
    }

    public void uploadProfileImageToFirebase(Fragment fragment, Uri image, String uid) {
        repository.uploadProfileImageToFirebase(fragment, image, uid);
    }


}
