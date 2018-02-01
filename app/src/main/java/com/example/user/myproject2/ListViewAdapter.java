package com.example.user.myproject2;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

//
// Created by User on 2017/12/08.
//

public class ListViewAdapter extends ArrayAdapter<DetailInfo> {

    private final String CLASS_NAME = "ListViewAdapter";
    private LayoutInflater mInflater = null;

    ListViewAdapter(@NonNull Context context, int resource, int textViewResourceId, List<DetailInfo> objects) {
        super(context, resource, textViewResourceId, objects);
        mInflater = LayoutInflater.from(context);
        Log.d( CLASS_NAME,"ListViewAdapter start" );
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View mView;

        if (null!=convertView) {
            mView = convertView;
        } else {
            mView = mInflater.inflate(R.layout.page_row, parent, false);
        }
        //ListViewの行（TextView）Viewを取得
        TextView textView = mView.findViewById( R.id.list_row_text );
        //set item data(text, icon) on TextView control
        if ( null!=textView ) {
            DetailInfo detailInfo = getItem( position );
            //icon
            Drawable[] applicationIcon = detailInfo.getPackageName().getCompoundDrawables();
            AtomicReference<Drawable> icon = new AtomicReference<>();
            icon.set( applicationIcon[0] );
            icon.get().setBounds(0, 0, applicationIcon[0].getBounds().width(), applicationIcon[0].getBounds().height()); //ICONの表示位置を設定 (引数：座標 x, 座標 y, 幅, 高さ)
            textView.setCompoundDrawables(icon.get(), null, null, null); //TextViewにアイコンセット（四辺(left, top, right, bottom)に対して別個にアイコンを描画できる）
            //text
            textView.setText( detailInfo.getPackageName().getText() );
            Log.d( CLASS_NAME, "packageName Text : " + detailInfo.getPackageName().getText() );
        } else {
            Log.d(CLASS_NAME, "textView is null at getView().");
        }//if(textView)

        return mView;
    }
}
