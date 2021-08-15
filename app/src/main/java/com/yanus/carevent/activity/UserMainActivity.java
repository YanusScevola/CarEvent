package com.yanus.carevent.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationBarView;
import com.yanus.carevent.R;
import com.yanus.carevent.databinding.ActivityUserMainBinding;
import com.yanus.carevent.fragments.DiscoverFragment;
import com.yanus.carevent.fragments.EventsFragment;
import com.yanus.carevent.fragments.ProfileFragment;

public class UserMainActivity extends AppCompatActivity {
    private ActivityUserMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.discover:
                        fragment = new DiscoverFragment();
                        setColorNavigationItem(R.id.discover, R.color.colorButton);
                        setColorNavigationItem(R.id.myevents, R.color.colorPanel);
                        setColorNavigationItem(R.id.profile, R.color.colorPanel);
                        break;

                    case R.id.myevents:
                        fragment = new EventsFragment();
                        setColorNavigationItem(R.id.myevents, R.color.colorButton);
                        setColorNavigationItem(R.id.discover, R.color.colorPanel);
                        setColorNavigationItem(R.id.profile, R.color.colorPanel);
                        break;

                    case R.id.profile:
                        fragment = new ProfileFragment();
                        setColorNavigationItem(R.id.profile, R.color.colorButton);
                        setColorNavigationItem(R.id.discover, R.color.colorPanel);
                        setColorNavigationItem(R.id.myevents, R.color.colorPanel);
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        setColorNavigationItem(R.id.myevents, R.color.colorButton);
    }

    public void setColorNavigationItem(int viewId, int colorId) {
        View navigationItemDiscover = findViewById(viewId);
        int color = Color.parseColor("#" + Integer.toHexString(ContextCompat.getColor(UserMainActivity.this, colorId)));
        navigationItemDiscover.setBackgroundColor(color);
    }


}