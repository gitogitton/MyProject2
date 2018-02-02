package com.example.user.myproject2;

import android.widget.TextView;

import java.io.Serializable;

/**
 * Created by user on 2018/02/01.
 */

public class DetailInfo implements Serializable {

    DetailInfo() {
    }

    private TextView mPackageName;
    private int mPid;
    private int mPss;

    public int getPss() {
        return mPss;
    }

    public void setPss( int pss ) {
        mPss = pss;
    }

    public TextView getPackageName() {
        return mPackageName;
    }

    public void setPackageName( TextView packageName) {
        this.mPackageName = packageName;
    }

    public int getPid() {
        return mPid;
    }

    public void setPid(int pid) {
        this.mPid = pid;
    }
}
