package com.example.user.myproject2.demo;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.myproject2.ListViewAdapter;
import com.example.user.myproject2.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static android.content.Context.ACTIVITY_SERVICE;

/**
 * Created by User on 2017/12/12.
 */

public class MainFragment extends Fragment {
    private final String CLASS_NAME = getClass().getSimpleName();
    ArrayList<TextView> mArrayList = new ArrayList<>();
    View mView;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_demo, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
//        // ListViewに表示するデータ
//        final ArrayList<String> items = new ArrayList<>();
//        items.add("データ1");
//        items.add("データ2");
//        items.add("データ3");

        mView = view;
        setRunningProcess();

        // ListViewをセット
        Context context = this.getContext();
//        Context context = getActivity().getApplicationContext();
        final DemoListViewAdapter adapter = new DemoListViewAdapter(context, R.layout.list_item_1, R.id.row_text, mArrayList);
        ListView listView = (ListView) view.findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        // セルを選択されたら詳細画面フラグメント呼び出す
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // 詳細画面へ値を渡す
                DetailFragment fragment = new DetailFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("selected", position);
                fragment.setArguments(bundle);
                // 詳細画面を呼び出す
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.textView, fragment);
                // 戻るボタンで戻ってこれるように
                transaction.addToBackStack(null);
                transaction.commit();
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
