package com.example.nurs.romebattlegroup;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class BattleGroupsAdapter extends RecyclerView.Adapter<BattleGroupsAdapter.BattleGroupsAdapterViewHolder> {
    private Cursor mCursor;
    private Context mContext;
    final private BattleGroupsAdapterOnClickHandler mClickHandler;

    public interface BattleGroupsAdapterOnClickHandler{
        void onClickListener (String str);
    }

    public BattleGroupsAdapter( BattleGroupsAdapterOnClickHandler handler, Context context, Cursor cursor){
        mClickHandler = handler;
        mContext = context;
        mCursor = cursor;
    }

    public class BattleGroupsAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    }
}
