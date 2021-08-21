package com.yanus.carevent;

import android.graphics.Color;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.yanus.carevent.activity.UserMainActivity;
import com.yanus.carevent.fragments.ChatFragment;
import com.yanus.carevent.fragments.DiscoverFragment;
import com.yanus.carevent.fragments.EventsFragment;
import com.yanus.carevent.fragments.ProfileFragment;

import java.util.List;

public class FragmentsSwipeAdapter extends FragmentStateAdapter  {
    FragmentActivity fragmentActivity;


    public FragmentsSwipeAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        this.fragmentActivity = fragmentActivity;

    }



    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0:
                return new ChatFragment();
            case 1:
                return new DiscoverFragment();
            case 2:
                return new EventsFragment();
            case 3:
                return new ProfileFragment();
            default: return null;
        }

    }

    @Override
    public int getItemCount() {
        return 4;
    }





}
