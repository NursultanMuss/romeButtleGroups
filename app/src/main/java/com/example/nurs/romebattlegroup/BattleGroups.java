package com.example.nurs.romebattlegroup;

import android.content.res.Configuration;
import android.os.BaseBundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.nurs.romebattlegroup.data.DataAccess;
import com.example.nurs.romebattlegroup.data.FractionsDbHelper;
import com.example.nurs.romebattlegroup.data.MainFractionContract;

public class BattleGroups extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>,
        BattleGroupsAdapter.BattleGroupsAdapterOnClickHandler
{


    RecyclerView mRecyclerView;
    GridLayout mGridLayout;
    private BattleGroupsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Cursor c_battleGroups;
    private DataAccess dbAccess;
    String frac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_groups);
        mRecyclerView = this.findViewById(R.id.rv_battle_group);
        int orientation = getResources().getConfiguration().orientation;
        mRecyclerView.setHasFixedSize(false);
        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);
        }else {
            mLayoutManager = new GridLayoutManager(this, 2);
            mRecyclerView.setLayoutManager(mLayoutManager);
        }
        getSupportLoaderManager().initLoader(0,null,this);
        Intent startedIntent = this.getIntent();

        //ToolBar
        Toolbar topToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(topToolBar);

        if (startedIntent.hasExtra(Intent.EXTRA_TEXT)){
            frac= startedIntent.getStringExtra(Intent.EXTRA_TEXT);
        }



        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.populus,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                int item = 5;
                switch(pos){
                    case 0 :
                        item= 0;
                        break;
                    case 1 : item=1;
                        break;
                    case 2 : item=2;
                        break;
                }
                String itemStr = item + "";
//                Toast.makeText(BattleGroups.this, itemStr, Toast.LENGTH_SHORT).show();
                Bundle args = new Bundle();
                args.putInt("selected_item_id", item);
                getSupportLoaderManager().restartLoader(0,args,BattleGroups.this);
//                if(item == 0){
//                    getSupportLoaderManager().initLoader(0,args,BattleGroups.this);
//                }else{
//
//                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(BattleGroups.this,"yoyo",Toast.LENGTH_SHORT).show();
            }
        });
//        int selectedItemf = spinner.getSelectedItemPosition();

    }
//    public void poKrutosti(){
//        getSupportLoaderManager().
//    }


//    @Override
//    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
//
//    }

    @Override
    public android.support.v4.content.Loader<Cursor> onCreateLoader(int id, final Bundle args) {
        return new CursorLoader(this){
            @Override
            public Cursor loadInBackground() {
                dbAccess = DataAccess.getInstance(BattleGroups.this);
                if(args == null){
                    c_battleGroups = dbAccess.getInfanti(BattleGroups.this.frac);
                }else if(args.getInt("selected_item_id")==1) {
                    c_battleGroups = dbAccess.getVozrastaniu(BattleGroups.this.frac);
                }else if (args.getInt("selected_item_id") == 0){
                    c_battleGroups = dbAccess.getKrutosti(BattleGroups.this.frac);
                }else if(args.getInt("selected_item_id") == 2){
                    c_battleGroups = dbAccess.getUbivaniu(BattleGroups.this.frac);
                }
                dbAccess.close();
                return c_battleGroups;
            }
        };
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<Cursor> loader, Cursor data) {
        data.moveToFirst();
        mAdapter = new BattleGroupsAdapter(this,this,data);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<Cursor> loader) {
        mAdapter.swapCursor(c_battleGroups);
    }


    @Override
    public void onClickListener(String str) {

    }
}
