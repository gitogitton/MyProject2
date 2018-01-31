package com.example.user.myproject2;


import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//import com.example.user.myproject2.main.deb.R;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailInfoFragment extends Fragment {

    private final String CLASS_NAME = getClass().getSimpleName();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    public DetailInfoFragment() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @return A new instance of fragment DetailInfoFragment.
//     */
    public static DetailInfoFragment newInstance( String param1 ) {
        DetailInfoFragment fragment = new DetailInfoFragment();
        Bundle args = new Bundle();
        args.putString( ARG_PARAM1, param1 );
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d( CLASS_NAME, "onCreate() starts." );
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            Log.d( CLASS_NAME, "onCreate() starts. [mParam1 : "+ mParam1 + "]" );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d( CLASS_NAME, "onCreateView() starts." );
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d( CLASS_NAME, "onViewCreated() starts." );
        TextView textView = view.findViewById( R.id.app_name );
        textView.setText( mParam1 );
        Context context = this.getContext();
        PackageManager packageManager = context.getPackageManager();
        ActivityManager activityManager = (ActivityManager) context.getSystemService( Context.ACTIVITY_SERVICE );

//        ActivityManager.RunningAppProcessInfo


        try {
            Log.d( CLASS_NAME, "表示するアプリ名称：" + mParam1 );
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo( mParam1, 0 );
        }
        catch ( PackageManager.NameNotFoundException e ) {
            e.printStackTrace();
//            Log.d(CLASS_NAME, "exception of getapplicationinfo() : ", "processname=" + app.processName + " / " + "importance=" + app.importance);
        }
        super.onViewCreated(view, savedInstanceState);
    }
}
