package com.abhishek.newsify.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.abhishek.newsify.fragments.BusinessFragment;
import com.abhishek.newsify.fragments.EntertainmentFragment;
import com.abhishek.newsify.fragments.HealthFragment;
import com.abhishek.newsify.fragments.HomeFragment;
import com.abhishek.newsify.fragments.ScienceFragment;
import com.abhishek.newsify.fragments.SportsFragment;
import com.abhishek.newsify.fragments.TechnologyFragment;

public class PagerAdapter extends FragmentPagerAdapter {

    int tabCount;

    public PagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabCount = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new SportsFragment();
            case 2:
                return new HealthFragment();
            case 3:
                return new ScienceFragment();
            case 4:
                return new BusinessFragment();
            case 5:
                return new EntertainmentFragment();
            case 6:
                return new TechnologyFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
