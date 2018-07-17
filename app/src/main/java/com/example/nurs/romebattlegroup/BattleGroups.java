package com.example.nurs.romebattlegroup;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.nurs.romebattlegroup.data.DataAccess;
import com.example.nurs.romebattlegroup.data.FractionsDbHelper;
import com.example.nurs.romebattlegroup.data.MainFractionContract;

public class BattleGroups extends AppCompatActivity implements AdapterView.OnItemSelectedListener, LoaderManager.LoaderCallbacks<Cursor>,
        BattleGroupsAdapter.BattleGroupsAdapterOnClickHandler
{


    RecyclerView mRecyclerView;
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
        mRecyclerView.setHasFixedSize(false);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        getSupportLoaderManager().initLoader(0,null,this);
        Intent startedIntent = this.getIntent();

        //ToolBar
        Toolbar topToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(topToolBar);

//        topToolBar.setLogoDescription(getResources().getString(R.string.logo_description));


        if (startedIntent.hasExtra(Intent.EXTRA_TEXT)){
            frac= startedIntent.getStringExtra(Intent.EXTRA_TEXT);
        }



        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.populus,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        String txt="1";
        switch(pos){
            case 0 : txt="populus";
            break;
            case 1 : txt="vozrastaniu";
            break;
            case 2 : txt="ubyvaniu";
            break;
        }
        Toast.makeText(this, txt, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

//    @Override
//    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
//
//    }

    @Override
    public android.support.v4.content.Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this){
            @Override
            public Cursor loadInBackground() {
                dbAccess = DataAccess.getInstance(BattleGroups.this);
                c_battleGroups = dbAccess.getInfanti(BattleGroups.this.frac);
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

    }


    @Override
    public void onClickListener(String str) {

    }
}
