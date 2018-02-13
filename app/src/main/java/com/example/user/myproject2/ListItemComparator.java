package com.example.user.myproject2;

import android.util.Log;
import android.widget.TextView;

import java.util.Comparator;

//
// * Created by user on 2018/02/13.
//

public class ListItemComparator implements Comparator<DetailInfo> {

    private final String CLASS_NAME = getClass().getSimpleName();

    @Override
    public int compare(DetailInfo o1, DetailInfo o2) {
        if ( o1==null || o2==null ) {
            return 0;
        }
        String str1 = o1.getPackageName().getText().toString();
        String str2 = o2.getPackageName().getText().toString();
        Log.d( CLASS_NAME, "str1 / str2 : " + str1 + " / " + str2 );
        return( str1.compareTo( str2 ) );
    }
}
