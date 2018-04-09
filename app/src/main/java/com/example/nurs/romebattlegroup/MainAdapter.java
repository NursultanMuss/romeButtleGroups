package com.example.nurs.romebattlegroup;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nurs.romebattlegroup.data.MainFractionContract;

/**
 * Created by nurs on 01.04.18.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainAdapterViewHolder> {


    private Cursor mCursor;
    private Context mContext;


    public interface MainAdapterOnClickHandler {
        void onClickListener(String str);
    }

    public MainAdapter (Context context, Cursor cursor){

        this.mContext = context;
        this.mCursor = cursor;

    }

    public class MainAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mFractionTextView;
        ImageView mImageView;

        public MainAdapterViewHolder(View view){
            super(view);
            mFractionTextView =  view.findViewById(R.id.fraction_tv);
            mImageView =  view.findViewById(R.id.fractionButton);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();

        }
    }

    @Override
    public MainAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        int layoutIdForListItem = R.layout.fraction_list;
        LayoutInflater inflater = LayoutInflater.from(mContext);

        View view = inflater.inflate(layoutIdForListItem,viewGroup,false);
        return new MainAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainAdapterViewHolder mainAdapterViewHolder, int position) {
        if(!mCursor.moveToPosition(position))
            return;


        String thisFractionText = mCursor.getString(mCursor.getColumnIndex(MainFractionContract.FractionsEntry.COLUMN_FRACTION_NAME));
        String imageViewText = mCursor.getString(mCursor.getColumnIndex(MainFractionContract.FractionsEntry.COLUMN_IMG_RES));
        int imgId = Integer.parseInt("R.id."+imageViewText+".png");
        mainAdapterViewHolder.mFractionTextView.setText(thisFractionText);
        mainAdapterViewHolder.mImageView.setImageResource(imgId);

    }

    @Override
    public int getItemCount() {

        return mCursor.getCount();
    }
}
