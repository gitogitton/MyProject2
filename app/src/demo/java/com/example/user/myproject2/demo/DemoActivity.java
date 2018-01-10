package com.example.user.myproject2.demo;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.user.myproject2.R;

public class DemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); //ToolbarはAPI21からのサポート。API19だからActionBarをサポートする。

        FragmentManager manager = getSupportFragmentManager();

        final ViewPager viewPager = findViewById(R.id.pager);

        final TestFragmentPagerAdapter adapter =
                new TestFragmentPagerAdapter(manager);
        viewPager.setAdapter(adapter);
    }
}
