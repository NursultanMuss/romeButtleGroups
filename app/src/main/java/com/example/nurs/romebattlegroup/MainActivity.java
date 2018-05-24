package com.example.nurs.romebattlegroup;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Adapter;

import com.example.nurs.romebattlegroup.data.FractionsDbHelper;
import com.example.nurs.romebattlegroup.data.MainFractionContract;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements MainAdapter.MainAdapterOnClickHandler,
        LoaderManager.LoaderCallbacks<Cursor>{
     RecyclerView mRecyclerView;
    private MainAdapter mMainAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private SQLiteDatabase mDb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) this.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        FractionsDbHelper dbHelper = new FractionsDbHelper(this);
        mDb = dbHelper.getReadableDatabase();
        Cursor cursor = getSupportLoaderManager().initLoader(0,null,this);

        mMainAdapter = new MainAdapter(this, cursor);
        mRecyclerView.setAdapter(mMainAdapter);


    }

    @Override
    public void onClickListener(String str) {

    }

//    private Cursor readFromDb(){
//        return mDb.query(
//                MainFractionContract.FractionsEntry.TABLE_NAME,
//                null,
//                null,
//                null,
//                null,
//                null,
//                null
//        );
//    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {

        return new CursorLoader(this){
            @Override
            public Cursor loadInBackground() {
                Cursor cursor = mDb.query(
                        MainFractionContract.FractionsEntry.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                );
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return cursor;
            }
        };

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor s) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
