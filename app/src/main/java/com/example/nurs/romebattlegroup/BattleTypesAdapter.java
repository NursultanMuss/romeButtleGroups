package com.example.nurs.romebattlegroup;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.nurs.romebattlegroup.data.MainFractionContract;

public class BattleTypesAdapter extends RecyclerView.Adapter<BattleTypesAdapter.BattleTypesAdapterViewHolder> {
    private Cursor mCursor;
    private Context mContext;

    final private BattleTypesAdapterOnClickHandler mClickHandler;

    public interface BattleTypesAdapterOnClickHandler{
        void onClickListener (String str);
    }

    public BattleTypesAdapter(BattleTypesAdapterOnClickHandler handler, Context context, Cursor c) {
        mClickHandler = handler;
        mContext = context;
        mCursor = c;
    }


//    @Override
//    public View newView(Context context, Cursor cursor, ViewGroup parent) {
//        int layout_for_list_item = R.layout.group_types_list;
//        LayoutInflater inflater =LayoutInflater.from(mContext);
//        View view = inflater.inflate(layout_for_list_item, parent,false);
//        return view;
//    }
//
//    @Override
//    public void bindView(View view, Context context, Cursor cursor) {
//        tv_group_type = (TextView) view.findViewById(R.id.tv_group_type);

//        String type= cursor.getString(cursor.getColumnIndex(MainFractionContract.InfantyEntry.COLUMN_TYPE));
//        cursor.moveToNext();
//        String type_con = cursor.getString(cursor.getColumnIndex(MainFractionContract.InfantyEntry.COLUMN_TYPE));
//        if(type == type_con){
//        }
//        String type= cursor.getString(cursor.getColumnIndex(MainFractionContract.FracOtryadEntry.COLUMN_OTRYADI));
//        int kol_vo_types=1;
//        do {
//
//            cursor.moveToNext();
//            String type_con = cursor.getString(cursor.getColumnIndex(MainFractionContract.InfantyEntry.COLUMN_TYPE));
//            if(type == type_con) return;
//            else kol_vo_types++;
//        }while(cursor.moveToNext());

//        tv_group_type.setText(type);
//        String number = cursor.getString(cursor.getColumnIndex(MainFractionContract.InfantyEntry.))
//    }

    public class BattleTypesAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tv_group_type;

        public BattleTypesAdapterViewHolder(View view){
            super(view);
            tv_group_type = view.findViewById(R.id.tv_group_type);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            Intent intent;
            String group_type = tv_group_type.getText().toString();
            intent = new Intent(view.getContext(),BattleGroups.class);
            intent.putExtra(Intent.EXTRA_TEXT,group_type);
            view.getContext().startActivity(intent);
        }
    }

    @Override
    public BattleTypesAdapter.BattleTypesAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      int layoutIdForListItem = R.layout.group_types_list;
      LayoutInflater inflater = LayoutInflater.from(mContext);
      View view = inflater.inflate(layoutIdForListItem,parent,false);
      return new BattleTypesAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BattleTypesAdapter.BattleTypesAdapterViewHolder holder, int position) {
        if(!mCursor.moveToPosition(position))return;

        String zn_group_type = mCursor.getString(mCursor.getColumnIndex(MainFractionContract.FracOtryadEntry.COLUMN_OTRYADI));

        holder.tv_group_type.setText(zn_group_type);
    }

    @Override
    public int getItemCount() {
          return mCursor.getCount();
    }

//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
//        String text= adapterView.getChildAt(pos).findViewById(R.id.tv_group_type).toString();
//        Intent intent = new Intent(view.getContext(),BattleGroups.class);
//        intent.putExtra("Type_of_otryad",text);
////        intent.putExtra(Intent.EXTRA_TEXT, frac);
//        view.getContext().startActivity(intent);
//    }
}
