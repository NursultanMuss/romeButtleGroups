package com.example.nurs.romebattlegroup;

import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nurs.romebattlegroup.data.MainFractionContract;

import org.w3c.dom.Text;

import java.util.Observer;


public class BattleGroupsAdapter extends RecyclerView.Adapter<BattleGroupsAdapter.BattleGroupsAdapterViewHolder> {
    private Cursor mCursor;
    private ContentObserver mChangeObserver;
    private DataSetObserver mDataSetObserver;
    private int mRowIDColumn;
    private boolean mDataValid;
    private Context mContext;
    private String mType;

    final private BattleGroupsAdapterOnClickHandler mClickHandler;
//Замена и обновление курсора после выбора метода сортировки списка в тулбаре
    public void changeCursor(Cursor cursor) {
        Cursor old = swapCursor(cursor);
        if (old != null) {
            old.close();
        }
    }

    public Cursor swapCursor (Cursor newCursor){
        if (newCursor == mCursor) {
            return null;
        }
        Cursor oldCursor = mCursor;
        if (oldCursor != null) {
            if (mChangeObserver != null) oldCursor.unregisterContentObserver(mChangeObserver);
            if (mDataSetObserver != null) oldCursor.unregisterDataSetObserver(mDataSetObserver);
        }
        mCursor = newCursor;
        if (newCursor != null) {
            if (mChangeObserver != null) newCursor.registerContentObserver(mChangeObserver);
            if (mDataSetObserver != null) newCursor.registerDataSetObserver(mDataSetObserver);
            mRowIDColumn = newCursor.getColumnIndexOrThrow("_id");
            mDataValid = true;
            // notify the observers about the new cursor
            notifyDataSetChanged();
        } else {
            mRowIDColumn = -1;
            mDataValid = false;
            // notify the observers about the lack of a data set
//            notifyDataSetInvalidated();
        }
        return oldCursor;
    }

    public interface BattleGroupsAdapterOnClickHandler{
        void onClickListener (String str,String youTube, String description,String squadImage );
    }

    public BattleGroupsAdapter( BattleGroupsAdapterOnClickHandler handler, Context context, Cursor cursor, String type){
        mClickHandler = handler;
        mContext = context;
        mCursor = cursor;
        mType = type;

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

        TextView tv_ship_hp_1;
        TextView tv_ship_speed_2;
        TextView tv_uron_snaryada_1;
        TextView tv_dalnost_2;
        TextView tv_vystrel_v_minutu_3;

        TextView tv_ship_hp;
        TextView tv_ship_speed;
        TextView tv_uron_snaryada;
        TextView tv_dalnost;
        TextView tv_vys_v_min;




        private BattleGroupsAdapterViewHolder(View view){
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
            tv_ship_hp_1 = view.findViewById(R.id.ship_hp_1);
            tv_ship_speed_2 = view.findViewById(R.id.ship_speed_2);
            tv_uron_snaryada_1 = view.findViewById(R.id.uron_snaryada_1);
            tv_dalnost_2 = view.findViewById(R.id.dalnost_2);
            tv_vystrel_v_minutu_3 = view.findViewById(R.id.vystrel_v_minutu_3);

            tv_ship_hp = view.findViewById(R.id.ship_hp);
            tv_ship_speed = view.findViewById(R.id.ship_speed);
            tv_uron_snaryada = view.findViewById(R.id.uron_snaryada);
            tv_dalnost = view.findViewById(R.id.dalnost);
            tv_vys_v_min = view.findViewById(R.id.vystrel_v_minutu);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String squadName = tv_battle_group_name.getText().toString();
            int position = mCursor.getPosition();
            String youTube=null;
            String description=null;
            String squadImage=null;
            if(mCursor.moveToPosition(position)){
                youTube = mCursor.getString(mCursor.getColumnIndex(MainFractionContract.InfantyEntry.COLUMN_YOUTUBEVIDEO));
                description = mCursor.getString(mCursor.getColumnIndex(MainFractionContract.InfantyEntry.COLUMN_DESCRIPTION));
                squadImage = mCursor.getString(mCursor.getColumnIndex(MainFractionContract.InfantyEntry.COLUMN_SQUAD_IMAGE));
            }
            mClickHandler.onClickListener(squadName, youTube, description,squadImage);
        }
    }

    @Override
    public BattleGroupsAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutIdForListItem = R.layout.ship_archer_list;

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




//        if(mCursor.getString(mCursor.getColumnIndex(MainFractionContract.InfantyEntry.COLUMN_SHIP_HP)) != null){
//            zn_ship_hp_1= mCursor.getString(mCursor.getColumnIndex(MainFractionContract.InfantyEntry.COLUMN_SHIP_HP));
//            zn_ship_speed_2= mCursor.getString(mCursor.getColumnIndex(MainFractionContract.InfantyEntry.COLUMN_SHIP_SPEED));
//            holder.tv_ship_hp_1.setText(zn_ship_hp_1);
//            holder.tv_ship_speed_2.setText(zn_ship_speed_2);
//        }
//        if(mCursor.getString(mCursor.getColumnIndex(MainFractionContract.InfantyEntry.COLUMN_URON_SNARYADA )) != null){
//            zn_uron_snaryada_1= mCursor.getString(mCursor.getColumnIndex(MainFractionContract.InfantyEntry.COLUMN_URON_SNARYADA));
//            zn_dalnost_2= mCursor.getString(mCursor.getColumnIndex(MainFractionContract.InfantyEntry.COLUMN_DALNOST));
//            zn_vystrel_v_minutu_3= mCursor.getString(mCursor.getColumnIndex(MainFractionContract.InfantyEntry.COLUMN_VISTREL_V_MIN));
//            holder.tv_uron_snaryada_1.setText(zn_uron_snaryada_1);
//            holder.tv_dalnost_2.setText(zn_dalnost_2);
//            holder.tv_vystrel_v_minutu_3.setText(zn_vystrel_v_minutu_3);
//        }
//        if(mCursor.getString(mCursor.getColumnIndex(MainFractionContract.InfantyEntry.COLUMN_SHIP_HP)) != null ||
//                mCursor.getString(mCursor.getColumnIndex(MainFractionContract.InfantyEntry.COLUMN_URON_SNARYADA )) != null){
//            zn_ship_hp_1= mCursor.getString(mCursor.getColumnIndex(MainFractionContract.InfantyEntry.COLUMN_SHIP_HP));
//            zn_ship_speed_2= mCursor.getString(mCursor.getColumnIndex(MainFractionContract.InfantyEntry.COLUMN_SHIP_SPEED));
//            zn_uron_snaryada_1= mCursor.getString(mCursor.getColumnIndex(MainFractionContract.InfantyEntry.COLUMN_URON_SNARYADA));
//            zn_dalnost_2= mCursor.getString(mCursor.getColumnIndex(MainFractionContract.InfantyEntry.COLUMN_DALNOST));
//            zn_vystrel_v_minutu_3= mCursor.getString(mCursor.getColumnIndex(MainFractionContract.InfantyEntry.COLUMN_VISTREL_V_MIN));
//
//            holder.tv_ship_hp_1.setText(zn_ship_hp_1);
//            holder.tv_ship_speed_2.setText(zn_ship_speed_2);
//            holder.tv_uron_snaryada_1.setText(zn_uron_snaryada_1);
//            holder.tv_dalnost_2.setText(zn_dalnost_2);
//            holder.tv_vystrel_v_minutu_3.setText(zn_vystrel_v_minutu_3);
//        }




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
        if(mCursor.getString(mCursor.getColumnIndex(MainFractionContract.InfantyEntry.COLUMN_SHIP_HP)) != null){
            holder.tv_ship_hp.setVisibility(View.VISIBLE);
            String zn_ship_hp_1= mCursor.getString(mCursor.getColumnIndex(MainFractionContract.InfantyEntry.COLUMN_SHIP_HP));
            holder.tv_ship_hp_1.setText(zn_ship_hp_1);
        }else{
            holder.tv_ship_hp_1.setVisibility(View.GONE);
            holder.tv_ship_hp.setVisibility(View.GONE);}

        if(mCursor.getString(mCursor.getColumnIndex(MainFractionContract.InfantyEntry.COLUMN_SHIP_SPEED)) != null){
            holder.tv_ship_speed.setVisibility(View.VISIBLE);
            String zn_ship_speed_2 = mCursor.getString(mCursor.getColumnIndex(MainFractionContract.InfantyEntry.COLUMN_SHIP_SPEED));
            holder.tv_ship_speed_2.setText(zn_ship_speed_2);
        }else{
            holder.tv_ship_speed_2.setVisibility(View.GONE);
            holder.tv_ship_speed.setVisibility(View.GONE);}

        if(mCursor.getString(mCursor.getColumnIndex(MainFractionContract.InfantyEntry.COLUMN_URON_SNARYADA)) != null){
            holder.tv_uron_snaryada.setVisibility(View.VISIBLE);
            String zn_uron_snaryada_1= mCursor.getString(mCursor.getColumnIndex(MainFractionContract.InfantyEntry.COLUMN_URON_SNARYADA));
            holder.tv_uron_snaryada_1.setText(zn_uron_snaryada_1);
        }else{
            holder.tv_uron_snaryada_1.setVisibility(View.GONE);
            holder.tv_uron_snaryada.setVisibility(View.GONE);}
        if(mCursor.getString(mCursor.getColumnIndex(MainFractionContract.InfantyEntry.COLUMN_DALNOST)) != null){
            holder.tv_dalnost.setVisibility(View.VISIBLE);
            String zn_dalnost_2= mCursor.getString(mCursor.getColumnIndex(MainFractionContract.InfantyEntry.COLUMN_DALNOST));
            holder.tv_dalnost_2.setText(zn_dalnost_2);
        }else{
            holder.tv_dalnost_2.setVisibility(View.GONE);
            holder.tv_dalnost.setVisibility(View.GONE);}

        if(mCursor.getString(mCursor.getColumnIndex(MainFractionContract.InfantyEntry.COLUMN_VISTREL_V_MIN)) != null){

            String zn_vystrel_v_minutu_3= mCursor.getString(mCursor.getColumnIndex(MainFractionContract.InfantyEntry.COLUMN_VISTREL_V_MIN));
            holder.tv_vystrel_v_minutu_3.setText(zn_vystrel_v_minutu_3);
            holder.tv_vys_v_min.setVisibility(View.VISIBLE);
        }else{
            holder.tv_vystrel_v_minutu_3.setVisibility(View.GONE);
            holder.tv_vys_v_min.setVisibility(View.GONE);}


    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }
}
