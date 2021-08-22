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
    ChatFragment chatFragment;
    DiscoverFragment discoverFragment;
    EventsFragment eventsFragment;
    ProfileFragment profileFragment;


    public FragmentsSwipeAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        this.chatFragment = new ChatFragment();
        this.discoverFragment = new DiscoverFragment();
        this.eventsFragment = new EventsFragment();
        this.profileFragment = new ProfileFragment();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0:
                return chatFragment;
            case 1:
                return discoverFragment;
            case 2:
                return eventsFragment;
            case 3:
                return profileFragment;
            default: return null;
        }

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public ChatFragment getChatFragment() {
        return chatFragment;
    }

    public DiscoverFragment getDiscoverFragment() {
        return discoverFragment;
    }

    public EventsFragment getEventsFragment() {
        return eventsFragment;
    }

    public ProfileFragment getProfileFragment() {
        return profileFragment;
    }
}
