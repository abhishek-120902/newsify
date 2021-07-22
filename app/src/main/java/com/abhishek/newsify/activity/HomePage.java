package com.abhishek.newsify.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.abhishek.newsify.R;
import com.abhishek.newsify.adapters.PagerAdapter;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class HomePage extends AppCompatActivity {

    TabLayout tabLayout;
    TabItem mHome, mSports, mHealth, mScience, mBusiness, mEntertainment, mTechnology;
    PagerAdapter pagerAdapter;
    Toolbar toolbar;
    int tabPosition;
    String api = "47de04280d214dca971c2e75fef05451";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tabLayout);
        mHome = findViewById(R.id.itemHome);
        mSports = findViewById(R.id.itemSports);
        mHealth = findViewById(R.id.itemHealth);
        mScience = findViewById(R.id.itemScience);
        mBusiness = findViewById(R.id.itemBusiness);
        mEntertainment = findViewById(R.id.itemEntertainment);
        mTechnology = findViewById(R.id.itemTechnology);

        setSupportActionBar(toolbar);

        ViewPager viewPager = findViewById(R.id.fragment_container);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), 7);
        viewPager.setAdapter(pagerAdapter);

        //Moving the user to different tabs
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabPosition = tab.getPosition();
                viewPager.setCurrentItem(tabPosition);
                if (tabPosition == 0 || tabPosition == 1 || tabPosition == 2 || tabPosition == 3 || tabPosition == 4 || tabPosition == 5) {
                    pagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }
}