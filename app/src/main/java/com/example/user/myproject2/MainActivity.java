package com.example.user.myproject2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import static android.util.TypedValue.COMPLEX_UNIT_SP;

//Created by User on 2017/12/08

public class MainActivity extends AppCompatActivity {

    private final String CLASS_NAME = getClass().getSimpleName();

//    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(CLASS_NAME, "onCreate() start. savedInstanceState->"+savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); //ToolbarはAPI21からのサポート。API19だからActionBarをサポートする。

//        mTabLayout = findViewById(R.id.tab);
//        mTabLayout.setupWithViewPager(mViewPager); //TabLayoutとViewPagerを連動させる

        FragmentManager fragmentManager = getSupportFragmentManager();

        final ViewPager viewPager = findViewById(R.id.view_pager);
        if ( 0 < viewPager.getChildCount() ) {
            PagerTitleStrip pagerTitleStrip = (PagerTitleStrip)viewPager.getChildAt( 0 );
            pagerTitleStrip.setTextSize( COMPLEX_UNIT_SP, (float)20.0 );
            pagerTitleStrip.setTextSpacing( 100 ); //pixel
            pagerTitleStrip.setTextColor( Color.BLUE );
        }
        final CustomPagerAdapter fragmentPagerAdapter = new CustomPagerAdapter(fragmentManager);
        viewPager.setAdapter( fragmentPagerAdapter );

//        mViewPager.addOnPageChangeListener(this); //ページ切り替え、ページスクロール時に呼ばれるリスナー登録

    }
}

//    //現在のページがスクロールされたときに呼び出されます。
//    @Override
//    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//        Log.d(CLASS_NAME, "onPageScrolled() : position = "+position);
//    }
//    //新しいページが選択されると呼び出されます。
//    @Override
//    public void onPageSelected(int position) {
//        Log.d(CLASS_NAME, "onPageSelected() start. position="+position);
//    }
//    //スクロール状態が変更されたときに呼び出されます。
//    @Override
//    public void onPageScrollStateChanged(int state) {
//        Log.d(CLASS_NAME, "onPageScrollStateChanged() start. state="+state);
//    }
//
//    @Override
//    public void onFragmentInteraction(Uri uri) {
//        Log.d(CLASS_NAME, "onFragmentInteraction() start. uri->"+uri);
//    }
