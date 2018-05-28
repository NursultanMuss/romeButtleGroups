package com.example.nurs.romebattlegroup;

import android.support.v4.app.LoaderManager;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.nurs.romebattlegroup.data.DataAccess;
import com.example.nurs.romebattlegroup.data.FractionsDbHelper;
import com.example.nurs.romebattlegroup.data.MainFractionContract;

import java.io.IOException;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity implements MainAdapter.MainAdapterOnClickHandler, LoaderManager.LoaderCallbacks<Cursor> {
     RecyclerView mRecyclerView;
    private MainAdapter mMainAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FractionsDbHelper mDb;
    private Cursor fractions;
    private DataAccess databaseAccess;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) this.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //Do it in Backgrond Task

        //end

//        try{
//
//        }catch(IOException e){
//
//        }
//        Cursor cursor = readFromDb();

        mMainAdapter = new MainAdapter(this,this, fractions);
        mRecyclerView.setAdapter(mMainAdapter);


    }

    @Override
    public void onClickListener(String str) {}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.fractions_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.web_page_link){
            Uri uri= Uri.parse("https://www.honga.net/totalwar/rome2/faction.php?l=ru");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if(intent.resolveActivity(getPackageManager()) != null){
                startActivity(intent);
            }
        }
        return true;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this) {
            @Override
            public Cursor loadInBackground() {
                databaseAccess = DataAccess.getInstance(MainActivity.this);
                fractions = databaseAccess.getFractions();
                databaseAccess.close();
                return fractions;
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mMainAdapter.
    }


    @Override
    public void onLoaderReset(Loader loader) {

    }
}
