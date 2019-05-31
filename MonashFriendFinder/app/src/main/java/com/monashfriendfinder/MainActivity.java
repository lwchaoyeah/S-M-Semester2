package com.monashfriendfinder;

import android.content.Intent;
import android.support.annotation.NonNull;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;


import com.monashfriendfinder.discover.MatchInputFragment;
import com.monashfriendfinder.friends.FriendListFragment;
import com.monashfriendfinder.home.HomeFragment;
import com.monashfriendfinder.me.MeFragment;


import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView bt;

    @BindView(R.id.viewpager)
    ViewPager t;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        BottomNavigationViewHelper.disableShiftMode(bt);
        bt.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        t.setCurrentItem(0);
                        return true;
                    case R.id.friendlist:
                        t.setCurrentItem(1);
                        return true;
                    case R.id.discover:
                        t.setCurrentItem(2);
                        return true;
                    case R.id.me:
                        t.setCurrentItem(3);
                    return true;
                }
                return false;
            }
        });

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment());
        adapter.addFragment(new FriendListFragment());
        adapter.addFragment(new MatchInputFragment());
        adapter.addFragment(new MeFragment());
        t.setAdapter(adapter);

    }


}