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


        //TODO: Нужно реализовать ViewPager а не ViewPager2
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
                //Log.i("key", position+"a");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                Log.i("key", savedInstanceState+"a");
            }
        });






        binding.navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;
                switch (item.getItemId()) {

                    case R.id.chat:
                        setColorsCombination(viewList, R.id.chat);
                        //viewPager2.setAdapter(new FragmentsSwipeAdapter(UserMainActivity.this, viewList, R.id.chat));

                        //fragment = new ChatFragment();
                        break;

                    case R.id.discover:
                        setColorsCombination(viewList, R.id.discover);
                        //viewPager2.setAdapter(new FragmentsSwipeAdapter(UserMainActivity.this, viewList, R.id.discover));

                        //fragment = new DiscoverFragment();
                        break;

                    case R.id.myevents:
                        setColorsCombination(viewList, R.id.myevents);
                        //fragment = new EventsFragment();
                       // viewPager2.setAdapter(new FragmentsSwipeAdapter(UserMainActivity.this, viewList, R.id.myevents));
                        break;

                    case R.id.profile:
                        setColorsCombination(viewList, R.id.profile);
                       // viewPager2.setAdapter(new FragmentsSwipeAdapter(UserMainActivity.this, viewList, R.id.profile));
                        //fragment = new ProfileFragment();
                        break;
                }
                //getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
                return true;
            }
        });
    }



    public void setColorsCombination(List<Integer> viewsId, int mainView){

        int colorPanel = Color.parseColor("#" + Integer.toHexString(ContextCompat.getColor(UserMainActivity.this, R.color.colorPanel)));
        int colorButton = Color.parseColor("#" + Integer.toHexString(ContextCompat.getColor(UserMainActivity.this, R.color.colorButton)));

        for(int viewId : viewsId){
            if (viewId == mainView) {
                findViewById(viewId).setBackgroundColor(colorButton);
            }else {
                findViewById(viewId).setBackgroundColor(colorPanel);
            }

        }


    }






}