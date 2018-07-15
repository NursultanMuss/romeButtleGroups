package com.example.nurs.romebattlegroup;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nurs.romebattlegroup.data.MainFractionContract;

import org.w3c.dom.Text;

import me.grantland.widget.AutofitHelper;

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
        TextView tv_battle_group_name;
        ImageView iv_image;
        TextView tv_bliz_boi_1;
        TextView tv_uronoruzhiem_1;
        TextView tv_natisk_1;
        TextView tv_zhashita_1;
        TextView tv_bronia_1;
        TextView tv_HP_1;
        TextView tv_Moral_1;
        TextView tv_kolvo_1;
        TextView tv_tsena_1;
        TextView tv_tsena_soderzhaniya_1;



        public BattleGroupsAdapterViewHolder(View view){
            super(view);
            tv_battle_group_name = view.findViewById(R.id.battle_group_name);
            iv_image = view.findViewById(R.id.imageView);
            tv_bliz_boi_1 = view.findViewById(R.id.bliz_boi_1);
            tv_uronoruzhiem_1 = view.findViewById(R.id.uronoruzhiem_2);
            tv_natisk_1 = view.findViewById(R.id.natisk_3);
            tv_zhashita_1 = view.findViewById(R.id.zhashita_4);
            tv_bronia_1 = view.findViewById(R.id.bronia_1);
            tv_HP_1 = view.findViewById(R.id.HP_2);
            tv_Moral_1 = view.findViewById(R.id.Moral_3);
            tv_kolvo_1= view.findViewById(R.id.kolvo_4);
            tv_tsena_1 = view.findViewById(R.id.tsena_1);
            tv_tsena_soderzhaniya_1 = view.findViewById(R.id.tsena_soderzhaniya_1);
//            AutofitHelper.create(tv_bliz_boi);
//            AutofitHelper.create(tv_uronoruzhiem);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }

    @Override
    public BattleGroupsAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutIdForListItem = R.layout.battle_group_list;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(layoutIdForListItem, parent, false);
        return new BattleGroupsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BattleGroupsAdapterViewHolder holder, int position) {
        if(!mCursor.moveToPosition(position))
            return;

        String zn_battle_group_name = mCursor.getString(mCursor.getColumnIndex(MainFractionContract.InfantyEntry.COLUMN_BATTLE_GROUP_NAME));
        String zn_fraction = mCursor.getString(mCursor.getColumnIndex(MainFractionContract.InfantyEntry.COLUMN_FRACTION));
        String zn_image = mCursor.getString(mCursor.getColumnIndex(MainFractionContract.InfantyEntry.COLUMN_IMG));
        String zn_bliz_boi = mCursor.getString(mCursor.getColumnIndex(MainFractionContract.InfantyEntry.COLUMN_BLIZ_BOI));
        String zn_uronoruzhiem_1 = mCursor.getString(mCursor.getColumnIndex(MainFractionContract.InfantyEntry.COLUMN_URON_ORUZHIEM));
        String zn_natisk_1 = mCursor.getString(mCursor.getColumnIndex(MainFractionContract.InfantyEntry.COLUMN_NATISK));
        String zn_zhashita_1 = mCursor.getString(mCursor.getColumnIndex(MainFractionContract.InfantyEntry.COLUMN_ZASHITA_BLIZ_BOI));
        String zn_bronia_1 = mCursor.getString(mCursor.getColumnIndex(MainFractionContract.InfantyEntry.COLUMN_BRONIA));
        String zn_HP_1 = mCursor.getString(mCursor.getColumnIndex(MainFractionContract.InfantyEntry.COLUMN_HP));
        String zn_Moral_1 = mCursor.getString(mCursor.getColumnIndex(MainFractionContract.InfantyEntry.COLUMN_MORAL));
        String zn_kolvo_1 = mCursor.getString(mCursor.getColumnIndex(MainFractionContract.InfantyEntry.COLUMN_KOLVO));
        String zn_tsena_1 = mCursor.getString(mCursor.getColumnIndex(MainFractionContract.InfantyEntry.COLUMN_TSENA_NAIMA));
        String zn_tsena_soderzhaniya_1 = mCursor.getString(mCursor.getColumnIndex(MainFractionContract.InfantyEntry.COLUMN_TSENA_SODERZHANIYA));

        int imgId = holder.iv_image.getResources().getIdentifier(zn_image,"drawable", "com.example.nurs.romebattlegroup");

        holder.tv_battle_group_name.setText(zn_battle_group_name);
        holder.iv_image.setImageResource(imgId);
        holder.tv_bliz_boi_1.setText(zn_bliz_boi);
        holder.tv_uronoruzhiem_1.setText(zn_uronoruzhiem_1);
        holder.tv_natisk_1.setText(zn_natisk_1);
        holder.tv_zhashita_1.setText(zn_zhashita_1);
        holder.tv_bronia_1.setText(zn_bronia_1);
        holder.tv_HP_1.setText(zn_HP_1);
        holder.tv_Moral_1.setText(zn_Moral_1);
        holder.tv_kolvo_1.setText(zn_kolvo_1);
        holder.tv_tsena_1.setText(zn_tsena_1);
        holder.tv_tsena_soderzhaniya_1.setText(zn_tsena_soderzhaniya_1);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }
}
