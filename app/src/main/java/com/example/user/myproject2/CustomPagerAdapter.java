package com.example.user.myproject2;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//
// Created by User on 2017/12/08.
//

public class CustomPagerAdapter extends FragmentPagerAdapter {

    private final String[] pageTitle = {"Running Process", "Installed Application"};//, "Setting"}; //タブページのタイトル
    private final static int TAB_PAGE_RUNNING_PROCESS = 0;
    private final static int TAB_PAGE_INSTALLED_APPLICATION = 1;

    private final String CLASS_NAME =getClass().getSimpleName();

    CustomPagerAdapter(FragmentManager fm) {
        super(fm);
        Log.d(CLASS_NAME, "constructor start.");
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
//        Log.d(CLASS_NAME, "getCount() start.");
        return pageTitle.length;
    }

    /**
     * This method may be called by the ViewPager to obtain a title string
     * to describe the specified page. This method may return null
     * indicating no title for this page. The default implementation returns
     * null.
     *
     * @param position The position of the title requested
     * @return A title for the requested page
     */
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0 :
                return pageTitle[0];
            case 1 :
                return pageTitle[1];
            default:
                return "----";
        }
    }

    /**
     * Return the Fragment associated with a specified position.
     */
    @Override
    public Fragment getItem(int position) {
        Log.d(CLASS_NAME, "FragmentPagerAdapter.getItem() start. position=" + position);
        switch (position) {
            case TAB_PAGE_RUNNING_PROCESS :
                return new PageFragment_1();
            case TAB_PAGE_INSTALLED_APPLICATION :
                return new PageFragment_2();
            default:
                Log.d(CLASS_NAME, "illegal argument. (position)");
                return new PageFragment_2();
        }
    }

//    /**
//     * Determines whether a page View is associated with a specific key object
//     * as returned by {@link #instantiateItem(ViewGroup, int)}. This method is
//     * required for a PagerAdapter to function properly.
//     *
//     * @param view   Page View to check for association with <code>object</code>
//     * @param object Object to check for association with <code>view</code>
//     * @return true if <code>view</code> is associated with the key object <code>object</code>
//     */
//    @Override
//    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
////        return false;
//        return view == object;
//    }

}
