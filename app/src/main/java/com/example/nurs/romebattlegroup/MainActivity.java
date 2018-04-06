package com.example.nurs.romebattlegroup;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Adapter;

import com.example.nurs.romebattlegroup.data.FractionsDbHelper;

public class MainActivity extends AppCompatActivity implements MainAdapter.MainAdapterOnClickHandler {
    private RecyclerView mRecyclerView;
    private MainAdapter mMainAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mMainAdapter = new MainAdapter(this);
        mRecyclerView.setAdapter(mMainAdapter);

        FractionsDbHelper dbHelper = new FractionsDbHelper(this);

        mDb = dbHelper.getWritableDatabase();
    }

    @Override
    public void onClickListener(String str) {

    }
}
