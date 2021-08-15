package com.yanus.carevent.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yanus.carevent.R;

import java.util.Calendar;
import java.util.Date;


public class DiscoverFragment extends Fragment {
    public DiscoverFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discover, container, false);


    }

}