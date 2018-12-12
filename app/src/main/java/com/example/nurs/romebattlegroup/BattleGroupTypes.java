package com.example.nurs.romebattlegroup;

import android.content.Intent;
import android.support.v4.app.LoaderManager;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.example.nurs.romebattlegroup.data.DataAccess;
import com.example.nurs.romebattlegroup.data.FractionsDbHelper;

public class BattleGroupTypes extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>,
BattleTypesAdapter.BattleTypesAdapterClickListener{
    RecyclerView mRecyclerView;
    private BattleTypesAdapter mBattleTypesAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FractionsDbHelper mDb;
    private Cursor c_types;
    private DataAccess databaseAccess;
    static String frac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.battle_group_types);
        mRecyclerView = this.findViewById(R.id.types_list_view);
        mRecyclerView.setHasFixedSize(false);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        getSupportLoaderManager().initLoader(0,null,this);

        Intent startedIntent = this.getIntent();

        if (startedIntent.hasExtra(Intent.EXTRA_TEXT)){
            frac= startedIntent.getStringExtra(Intent.EXTRA_TEXT);
        }
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
//                TextView text=(TextView) mListView.getChildAt(pos).findViewById(R.id.tv_group_type);
//                String textString = text.getText().toString();
//                Toast.makeText(BattleGroupTypes.this, textString, Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(view.getContext(),BattleGroups.class);
////                intent.putExtra("Type_of_otryad",value);
//                intent.putExtra("Type_of_otryad",textString);
//                intent.putExtra(Intent.EXTRA_TEXT, frac);
//                view.getContext().startActivity(intent);
//
//
//            }
//        });
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this){
            @Override
            public Cursor loadInBackground() {
                databaseAccess = DataAccess.getInstance(BattleGroupTypes.this);
                c_types = databaseAccess.getFracOtryadi(frac);
                databaseAccess.close();
                return c_types;
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        data.moveToFirst();
        mBattleTypesAdapter = new BattleTypesAdapter(this,this,data);
        mRecyclerView.setAdapter(mBattleTypesAdapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }


    @Override
    public void onClickListener(String groupType) {
        Intent intent;
       intent = new Intent(this, BattleGroups.class);
       intent.putExtra("Type_of_otryad", groupType);
       intent.putExtra(Intent.EXTRA_TEXT, frac);
       startActivity(intent);
    }
}
