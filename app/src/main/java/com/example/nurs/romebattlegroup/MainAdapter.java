package com.example.nurs.romebattlegroup;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by nurs on 01.04.18.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainAdapterViewHolder> {

    final private MainAdapterOnClickHandler mClickHandler;
    private String[] mFractionData;

    public interface MainAdapterOnClickHandler {
        void onClickListener(String str);
    }

    public MainAdapter (MainAdapterOnClickHandler handler){
        mClickHandler = handler;
    }

    public class MainAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView mFractionTextView;
        public MainAdapterViewHolder(View view){
            super(view);
            mFractionTextView = (TextView) view.findViewById(R.id.fraction_tv);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            String thisFractionText = mFractionData[adapterPosition];
            mClickHandler.onClickListener(thisFractionText);
        }
    }

    @Override
    public MainAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.fraction_list;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem,viewGroup,false);
        return new MainAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainAdapterViewHolder mainAdapterViewHolder, int position) {
        String thisFractionText = mFractionData[position];
        mainAdapterViewHolder.mFractionTextView.setText(thisFractionText);
    }

    @Override
    public int getItemCount() {
        if(null == mFractionData)return 0;
        return mFractionData.length;
    }
}
