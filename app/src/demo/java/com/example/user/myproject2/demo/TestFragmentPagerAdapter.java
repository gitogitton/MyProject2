package com.example.user.myproject2.demo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

//
// Created by User on 2017/12/12.
//

public class TestFragmentPagerAdapter extends FragmentPagerAdapter {

    TestFragmentPagerAdapter(android.support.v4.app.FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new MainFragment();
            case 1:
                return new DetailFragment();
            default:
                return new MainFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + (position+1);
    }
}

