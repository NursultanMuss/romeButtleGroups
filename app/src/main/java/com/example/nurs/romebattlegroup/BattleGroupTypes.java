package com.example.nurs.romebattlegroup;

import android.content.Intent;
import android.support.v4.app.LoaderManager;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.nurs.romebattlegroup.data.DataAccess;
import com.example.nurs.romebattlegroup.data.FractionsDbHelper;

public class BattleGroupTypes extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    ListView mListView;
    private BattleTypesAdapter mBattleTypesAdapter;
    private FractionsDbHelper mDb;
    private Cursor c_types;
    private DataAccess databaseAccess;
    String frac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.battle_group_types);
        mListView = this.findViewById(R.id.types_list_view);
        getSupportLoaderManager().initLoader(0,null,this);

        final Intent startedIntent = this.getIntent();
        if (startedIntent.hasExtra(Intent.EXTRA_TEXT)){
            frac= startedIntent.getStringExtra(Intent.EXTRA_TEXT);
        }
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                String value = mListView.getAdapter().getItem(pos).toString();
                Intent intent = new Intent(view.getContext(),BattleGroups.class);
                intent.putExtra("Type_of_otryad",value);
                intent.putExtra(Intent.EXTRA_TEXT, frac);
                view.getContext().startActivity(intent);


            }
        });
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
        mBattleTypesAdapter = new BattleTypesAdapter(this, data,0);
        mListView.setAdapter(mBattleTypesAdapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
