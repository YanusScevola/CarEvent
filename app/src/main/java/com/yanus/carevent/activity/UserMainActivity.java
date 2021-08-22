package com.yanus.carevent.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.navigation.NavigationBarView;
import com.yanus.carevent.FragmentsSwipeAdapter;
import com.yanus.carevent.R;
import com.yanus.carevent.databinding.ActivityUserMainBinding;

import java.util.Arrays;
import java.util.List;

public class UserMainActivity extends AppCompatActivity {
    private ActivityUserMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.navigation.getMenu().getItem(2).setChecked(true);
        List<Integer> viewList = Arrays.asList(R.id.chat, R.id.discover, R.id.myevents, R.id.profile);

        ViewPager2 viewPager2 = findViewById(R.id.pager);
        viewPager2.setAdapter(new FragmentsSwipeAdapter(UserMainActivity.this));
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        setColorsCombination(viewList, binding.navigation.getMenu().getItem(0));
                        break;
                    case 1:
                        setColorsCombination(viewList, binding.navigation.getMenu().getItem(1));
                        break;
                    case 2:
                        setColorsCombination(viewList, binding.navigation.getMenu().getItem(2));
                        break;
                    case 3:
                        setColorsCombination(viewList, binding.navigation.getMenu().getItem(3));


                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });


        binding.navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.chat:
                        setColorsCombination(viewList, binding.navigation.getMenu().getItem(0));
                        viewPager2.setCurrentItem(0);
                        break;
                    case R.id.discover:
                        setColorsCombination(viewList, binding.navigation.getMenu().getItem(1));
                        viewPager2.setCurrentItem(1);
                        break;
                    case R.id.myevents:
                        setColorsCombination(viewList, binding.navigation.getMenu().getItem(2));
                        viewPager2.setCurrentItem(2);
                        break;
                    case R.id.profile:
                        setColorsCombination(viewList, binding.navigation.getMenu().getItem(3));
                        viewPager2.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });

    }



    public void setColorsCombination(List<Integer> viewsId, MenuItem menuItem){
        int colorPanel = Color.parseColor("#" + Integer.toHexString(ContextCompat.getColor(UserMainActivity.this, R.color.colorPanel)));
        int colorButton = Color.parseColor("#" + Integer.toHexString(ContextCompat.getColor(UserMainActivity.this, R.color.colorButton)));

        for(int viewId : viewsId){
            if (viewId == menuItem.getItemId()) {
                findViewById(viewId).setBackgroundColor(colorButton);
                menuItem.setChecked(true);

            }else {
                findViewById(viewId).setBackgroundColor(colorPanel);
            }
        }
    }






}