package com.example.user.myproject2;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static android.content.Context.ACTIVITY_SERVICE;

//
// Created by User on 2017/12/08.
//

public class PageFragment_1 extends Fragment {

    private final String CLASS_NAME = getClass().getSimpleName();
    private ArrayList<DetailInfo> mArrayList = new ArrayList<>();
    private View mView;
    private ListView mListView;
    private ProgressBar mProgressBar;

    public PageFragment_1() {
        Log.d(CLASS_NAME, "constructor start (empty)");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(CLASS_NAME, "onCreateView() start. savedInstanceState->"+savedInstanceState);
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fragment_page, container, false );
    }

    /**
     * Called immediately after {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}
     * has returned, but before any saved state has been restored in to the view.
     * This gives subclasses a chance to initialize themselves once
     * they know their view hierarchy has been completely created.  The fragment's
     * view hierarchy is not however attached to its parent at this point.
     *
     * @param view               The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(CLASS_NAME, "onViewCreated() start.");
        mArrayList.clear();
        mView = view;
        //プログレスバー表示
        mProgressBar = mView.findViewById( R.id.progressBar );
        mProgressBar.setVisibility( View.VISIBLE );
        //リストビュー非表示
        mListView = mView.findViewById( R.id.process_list );
        mListView.setVisibility( View.GONE );
        setRunningProcess(); //実行中プロセスリスト取得
        //ListView データ表示
        Context context = getActivity();
        ListViewAdapter listViewAdapter = new ListViewAdapter( context, R.layout.fragment_page, R.id.list_row_text, mArrayList );
        mListView.setAdapter( listViewAdapter );
        //プログレスバー非表示
        mProgressBar.setVisibility( View.GONE );
        //リストビュー表示
        mListView.setVisibility( View.VISIBLE );

        // セルを選択されたら詳細画面フラグメント呼び出す
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Log.d( CLASS_NAME, "onItemClick() starts." );
                ListView listView1 = (ListView)parent;
                DetailInfo detailInfo = (DetailInfo) listView1.getAdapter().getItem( position );
                Bundle bundle = new Bundle();
                bundle.putSerializable("DETAILINFO", detailInfo );
                DetailInfoFragment detailInfoFragment = new DetailInfoFragment();
                detailInfoFragment.setArguments( bundle );
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add( R.id.topViewGroup, detailInfoFragment );
                fragmentTransaction.addToBackStack( null );
                fragmentTransaction.commit();
                fragmentManager.executePendingTransactions(); // FragmentのTransaction処理の完了同期待ち（必須ではない）
            }
        });
    }

    private void setRunningProcess() {
        Log.d(CLASS_NAME, "setRunningProcess() start");
        Context context = this.getContext();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
        if ( null != activityManager ) {
            List<ActivityManager.RunningAppProcessInfo> runningApp = activityManager.getRunningAppProcesses();
            PackageManager packageManager = context.getPackageManager();
            Log.d(CLASS_NAME, "running appl count : " + runningApp.size());
            if ( !runningApp.isEmpty() ) {
                int i = 0;
                mArrayList.clear();
                int pIds[] = new int[ 1 ];
                for (ActivityManager.RunningAppProcessInfo app : runningApp) {
                    i++;
                    try {
                        ApplicationInfo applicationInfo = packageManager.getApplicationInfo(app.processName, 0);
                        //set application name.
                        String packageName = (String)packageManager.getApplicationLabel( applicationInfo );
                        TextView textView = new TextView( context );
                        textView.setText( packageName );
                        //set icon.
                        AtomicReference<Drawable> icon = new AtomicReference<>();
                        Drawable applicationIcon = packageManager.getApplicationIcon( applicationInfo );
                        icon.set(applicationIcon);
                        //ICONの表示位置を設定 (引数：座標 x, 座標 y, 幅, 高さ)
//                                Log.d(CLASS_NAME, "size of icon (w/h) : "+icon.get().getIntrinsicWidth()+" / "+icon.get().getIntrinsicHeight());
//iconサイズそのままだから・・・                                icon.get().setBounds(0, 0, icon.get().getIntrinsicWidth(), icon.get().getIntrinsicHeight());
                        icon.get().setBounds(0, 0, 72, 72);
                        //TextViewにアイコンセット（四辺(left, top, right, bottom)に対して別個にアイコンを描画できる）
                        textView.setCompoundDrawables(icon.get(), null, null, null);
                        //add new data to listview array.
                        DetailInfo detailInfo = new DetailInfo();
                        detailInfo.setPackageName( textView );
                        detailInfo.setPid( app.pid );
                        detailInfo.setProcessName( app.processName );

                        detailInfo.setClassName( applicationInfo.className );
                        detailInfo.setClassSimpleName( "" ); //x
                        detailInfo.setClassCanonicalName( "" ); //X

                        detailInfo.setDetailPkgName( applicationInfo.packageName );
                        Log.d( CLASS_NAME, "packageName : " + textView.getText().toString() );
                        //get memory info
                        pIds[ 0 ] = app.pid;
                        android.os.Debug.MemoryInfo[] memoryInfos = activityManager.getProcessMemoryInfo( pIds );
                        for ( android.os.Debug.MemoryInfo info : memoryInfos ) {
                            detailInfo.setPss( info.getTotalPss() );
                            Log.d( CLASS_NAME, "pss : " + info.getTotalPss() );
                        }
                        mArrayList.add( detailInfo );
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                        Log.d(CLASS_NAME, "exception of getapplicationinfo() : i=" + i + "processname=" + app.processName + " / " + "importance=" + app.importance);
                    }
                }//for(app)
                Collections.sort( mArrayList, new ListItemComparator() ); //mArrayListをアプリ名でソート
            }//if(!runningApp)
        }
    }
}
