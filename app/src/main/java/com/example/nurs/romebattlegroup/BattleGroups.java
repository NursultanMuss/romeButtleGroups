package com.example.nurs.romebattlegroup;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class BattleGroups extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    RecyclerView mRecyclerView;
    private BattleGroupsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Cursor fractions;
    private DataAccess dbAccess;
    private TextView mtv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_groups);
        mRecyclerView = this.findViewById(R.id.rv_battle_group);
        mRecyclerView.setHasa
        mtv = (TextView) findViewById(R.id.battle_group);
        Intent startedIntent = this.getIntent();

        //ToolBar
        Toolbar topToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(topToolBar);

//        topToolBar.setLogoDescription(getResources().getString(R.string.logo_description));


        if (startedIntent.hasExtra(Intent.EXTRA_TEXT)){
            mtv.setText(startedIntent.getStringExtra(Intent.EXTRA_TEXT));
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

}
