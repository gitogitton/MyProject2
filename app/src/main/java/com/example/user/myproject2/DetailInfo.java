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
    private String mProcessName;
    private String mClassName;
    private String mDetailPkgName;
    private String mClassSimpleName;
    private String mClassCanonicalName;

    public String getClassSimpleName() {
        return mClassSimpleName;
    }

    public void setClassSimpleName(String classSimpleName) {
        this.mClassSimpleName = classSimpleName;
    }

    public String getClassCanonicalName() {
        return mClassCanonicalName;
    }

    public void setClassCanonicalName(String classCanonicalName) {
        this.mClassCanonicalName = classCanonicalName;
    }

    public String getDetailPkgName() {
        return mDetailPkgName;
    }

    public void setDetailPkgName( String detailPkgName ) {
        this.mDetailPkgName = detailPkgName;
    }

    public String getClassName() {
        return mClassName;
    }

    public void setClassName( String className ) {
        this.mClassName = className;
    }

    public String getProcessName() {
        return mProcessName;
    }
    public void setProcessName( String processName ) {
        mProcessName = processName;
    }

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
