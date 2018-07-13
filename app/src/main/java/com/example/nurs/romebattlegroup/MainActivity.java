package com.example.nurs.romebattlegroup;

import android.app.ActionBar;
import android.support.v4.app.LoaderManager;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.nurs.romebattlegroup.data.DataAccess;
import com.example.nurs.romebattlegroup.data.FractionsDbHelper;


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
        mRecyclerView = this.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        getSupportLoaderManager().initLoader(0,null,this);


    }

//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        if(hasFocus) hideSystemUI();
//    }
//
//    private void hideSystemUI(){
//        View decorView = getWindow().getDecorView();
//        // Hide the status bar.
//
//        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
//        // Remember that you should never show the action bar if the
//        // status bar is hidden, so hide that too if necessary.
////        ActionBar actionBar = getActionBar();
////        actionBar.hide();
//    }

    @Override
    public void onClickListener(String str) {}

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.fractions_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if(id == R.id.web_page_link){
//            Uri uri= Uri.parse("https://www.honga.net/totalwar/rome2/faction.php?l=ru");
//            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//            if(intent.resolveActivity(getPackageManager()) != null){
//                startActivity(intent);
//            }
//        }
//        return true;
//    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
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
    public void onLoadFinished(Loader loader, Cursor data) {
        data.moveToFirst();
        mMainAdapter = new MainAdapter(this,this, data);
        mRecyclerView.setAdapter(mMainAdapter);
    }


    @Override
    public void onLoaderReset(Loader loader) {

    }
}
