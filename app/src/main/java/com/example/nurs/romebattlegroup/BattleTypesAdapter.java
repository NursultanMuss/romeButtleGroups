package com.example.nurs.romebattlegroup;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.nurs.romebattlegroup.data.MainFractionContract;

public class BattleTypesAdapter extends CursorAdapter {
    private Cursor mCursor;
    private Context mContext;
    TextView tv_group_type;
    TextView tv_group_number;


    public BattleTypesAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        mContext = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        int layout_for_list_item = R.layout.group_types_list;
        LayoutInflater inflater =LayoutInflater.from(mContext);
        View view = inflater.inflate(layout_for_list_item, parent,false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        tv_group_type = (TextView) view.findViewById(R.id.tv_group_type);
        tv_group_number = (TextView) view.findViewById(R.id.tv_group_number);

        String type= cursor.getString(cursor.getColumnIndex(MainFractionContract.InfantyEntry.COLUMN_TYPE));
        String number = cursor.getString(cursor.getColumnIndex(MainFractionContract.InfantyEntry.))


    }
}
