package com.example.user.myproject2;


import android.graphics.drawable.Drawable;
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

import java.util.concurrent.atomic.AtomicReference;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailInfoFragment extends Fragment {

    private final String CLASS_NAME = getClass().getSimpleName();

    private TextView mPackageName;
    private int mProcessId;
    private int mMemoryPss;
    private String mProcessName;
    private String mClassName;
    private String mPckgDetailName;
    private String mSimpleClassName;
    private String mCanonicalClassName;;

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
    public static DetailInfoFragment newInstance( DetailInfo param1 ) {
        DetailInfoFragment fragment = new DetailInfoFragment();
        Bundle args = new Bundle();
//        args.putSerializable( ARG_PARAM1, param1 );
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d( CLASS_NAME, "onCreate() starts." );
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        DetailInfo detailInfo = (DetailInfo) bundle.getSerializable( "DETAILINFO" );
        if ( null!=detailInfo) {
            mPackageName = detailInfo.getPackageName();
            mProcessId = detailInfo.getPid();
            mMemoryPss = detailInfo.getPss();
            mProcessName = detailInfo.getProcessName();
            mPckgDetailName = detailInfo.getDetailPkgName();
            mClassName = detailInfo.getClassName();
            mSimpleClassName = detailInfo.getClassCanonicalName();
            mCanonicalClassName = detailInfo.getClassCanonicalName();
            Log.d( CLASS_NAME, "onCreate() [ mPackageName/mProcessId/mMemoryPss/mProcessName/mPckgDetailName"+
                    mPackageName.getText().toString() + "/ "+ mProcessId +" / "+mMemoryPss+" / "+mProcessName+" ]" );
            Log.d( CLASS_NAME, "onCreate() [ mClassName/mSimpleClassName/mCanonicalClassName : "+
                    mClassName+" / "+mSimpleClassName+" / "+mCanonicalClassName+" ]" );
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
        //package name with icon
        TextView textViewName = view.findViewById( R.id.app_name );
        textViewName.setText( mPackageName.getText().toString() );
        Drawable[] drawable = mPackageName.getCompoundDrawables();
        AtomicReference<Drawable> icon = new AtomicReference<>();
        icon.set( drawable[0] );
        textViewName.setCompoundDrawables( icon.get(), null, null, null );
        //package name (detail)
        TextView textViewDetailName = view.findViewById( R.id.pkgName );
        textViewDetailName.setText( "("+ mPckgDetailName +")" );
        //process id
        TextView textViewId = view.findViewById( R.id.pid ); //process id
        textViewId.setText( "* Process ID *\n"+Integer.toString( mProcessId ) );
        //process name
        TextView textViewProcName = view.findViewById( R.id.process_name );
        textViewProcName.setText( "* Process Name *\n"+mProcessName );
        //usage memory size (pss)
        TextView textViewPss = view.findViewById( R.id.mem_size );
        textViewPss.setText( "* Usage Memory Size (KB ?) *\n"+String.format( "%d", mMemoryPss ) );
        //class name
        TextView textViewClass = view.findViewById( R.id.class_name );
        textViewClass.setText( "* Class Name *\n"+mClassName );
        //class simple name
        TextView textViewSimpleClass = view.findViewById( R.id.class_simpleName );
        textViewSimpleClass.setText( "* Class S Name *\n" + mSimpleClassName );
        //class canonical name
        TextView textViewCanonicalClass = view.findViewById( R.id.class_canonicalName );
        textViewCanonicalClass.setText( "* Class C Name *\n" + mCanonicalClassName );

        super.onViewCreated(view, savedInstanceState);
    }
}
