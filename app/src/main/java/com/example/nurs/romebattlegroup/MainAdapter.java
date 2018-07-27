package com.example.nurs.romebattlegroup;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nurs.romebattlegroup.data.MainFractionContract;

/**
 * Created by nurs on 01.04.18.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainAdapterViewHolder> {


    private Cursor mCursor;
    private Context mContext;
    final private MainAdapterOnClickHandler mClickHandler;

    public interface MainAdapterOnClickHandler {
        void onClickListener(String str);
    }

    public MainAdapter (MainAdapterOnClickHandler handler ,Context context, Cursor cursor){
        mClickHandler = handler;
        mContext = context;
        mCursor = cursor;

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
            Intent intent;
            String fractionsName = mFractionTextView.getText().toString();
            switch (adapterPosition){
                case  0:
                    intent = new Intent(view.getContext(),BattleGroupTypes.class);
                    intent.putExtra(Intent.EXTRA_TEXT,fractionsName);
                    view.getContext().startActivity(intent);
                    break;
                case 1:
                    intent = new Intent(view.getContext(),BattleGroupTypes.class);
                    intent.putExtra(Intent.EXTRA_TEXT,fractionsName);
                    view.getContext().startActivity(intent);
                    break;
            }

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
//        String img = String.valueOf(imgId);
        int imgId = mainAdapterViewHolder.mImageView.getResources().getIdentifier(imageViewText,"drawable","com.example.nurs.romebattlegroup");

        String img = String.valueOf(imgId);
        mainAdapterViewHolder.mFractionTextView.setText(thisFractionText);
        mainAdapterViewHolder.mImageView.setImageResource(imgId);

    }

    @Override
    public int getItemCount() {

        return mCursor.getCount();
    }
}
