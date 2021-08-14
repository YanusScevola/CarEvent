package com.yanus.carevent.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.yanus.carevent.R;


public class UserEventsFragment extends Fragment {
    public UserEventsFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_user_events, container, false);
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();

        return view;
    }
}