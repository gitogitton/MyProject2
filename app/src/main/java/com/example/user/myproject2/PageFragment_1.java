package com.example.user.myproject2;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static android.content.Context.ACTIVITY_SERVICE;

//
// Created by User on 2017/12/08.
//

public class PageFragment_1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private final String CLASS_NAME = getClass().getSimpleName();
//    protected static final String ARG_PARAM1 = "param1";
    private ArrayList<TextView> mArrayList = new ArrayList<>();

    public PageFragment_1() {
        Log.d(CLASS_NAME, "constructor start (empty)");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(CLASS_NAME, "onCreateView() start. savedInstanceState->"+savedInstanceState);
        // Inflate the layout for this fragment
//        if (null!=savedInstanceState) {
//            int page = getArguments().getInt(ARG_PARAM1, 0);
//        }
        return inflater.inflate(R.layout.fragment_page, container, false);
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

        setRunningProcess();

        Context context = this.getContext();
        Context context1 = getActivity();
//        Context context = getActivity().getApplicationContext();

        ListViewAdapter listViewAdapter = new ListViewAdapter( context, R.layout.fragment_page, R.id.list_row_text, mArrayList );
        ListView listView = view.findViewById( R.id.process_list );
        listView.setAdapter( listViewAdapter );

        // セルを選択されたら詳細画面フラグメント呼び出す
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//                // 詳細画面へ値を渡す
//                DetailFragment fragment = new DetailFragment();
//                Bundle bundle = new Bundle();
//                bundle.putInt("selected",position);
//                fragment.setArguments(bundle);
//                // 詳細画面を呼び出す
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
//                transaction.replace(R.id.textView, fragment);
//                // 戻るボタンで戻ってこれるように
//                transaction.addToBackStack(null);
//                transaction.commit();
            }
        });
    }

    private void setRunningProcess() {
        Log.d(CLASS_NAME, "setRunningProcess() start");

        Context context = this.getContext();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);

        if (null != activityManager) {

            List<ActivityManager.RunningAppProcessInfo> runningApp = activityManager.getRunningAppProcesses();
            PackageManager packageManager = context.getPackageManager();
            Log.d(CLASS_NAME, "running appl count : " + runningApp.size());

            if (!runningApp.isEmpty()) {
                int i = 0;
                mArrayList.clear();
                for (ActivityManager.RunningAppProcessInfo app : runningApp) {
                    i++;
                    try {
                        ApplicationInfo applicationInfo = packageManager.getApplicationInfo(app.processName, 0);
                        Drawable applicationIcon = packageManager.getApplicationIcon(applicationInfo);
                        //set application name.
                        TextView textView = new TextView(context);
                        String packageName = i + ") " + (String) packageManager.getApplicationLabel(applicationInfo);
                        textView.setText(packageName);
                        //Log.d(CLASS_NAME, "package name -> "+packageName);
                        //set icon.
                        AtomicReference<Drawable> icon = new AtomicReference<>();
                        icon.set(applicationIcon);
                        //ICONの表示位置を設定 (引数：座標 x, 座標 y, 幅, 高さ)
//                                Log.d(CLASS_NAME, "size of icon (w/h) : "+icon.get().getIntrinsicWidth()+" / "+icon.get().getIntrinsicHeight());
//iconサイズそのままだから・・・                                icon.get().setBounds(0, 0, icon.get().getIntrinsicWidth(), icon.get().getIntrinsicHeight());
                        icon.get().setBounds(0, 0, 72, 72);
                        //TextViewにアイコンセット（四辺(left, top, right, bottom)に対して別個にアイコンを描画できる）
                        textView.setCompoundDrawables(icon.get(), null, null, null);
                        //add new data to array.
                        mArrayList.add(textView);
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                        Log.d(CLASS_NAME, "exception of getapplicationinfo() : i=" + i + "processname=" + app.processName + " / " + "importance=" + app.importance);
                    }
                }//for(app)

            }//if(!runningApp)
        }
    }
}
