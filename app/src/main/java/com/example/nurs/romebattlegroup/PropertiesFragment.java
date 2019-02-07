package com.example.nurs.romebattlegroup;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import com.example.nurs.romebattlegroup.data.DataAccess;
import com.example.nurs.romebattlegroup.data.MainFractionContract;


/**
 * A simple {@link Fragment} subclass.
 */
public class PropertiesFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    DataAccess db;
    Cursor c_abilities;
    String squadName;
    ListView listView;
    AbilityAdapter adapter;

    public PropertiesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        squadName = getArguments().getString("squadName");
        View v =   inflater.inflate(R.layout.fragment_properties, container, false);
        listView = v.findViewById(R.id.abilities_list_view);
        getLoaderManager().initLoader(0,null, this);
        return v;
    }

    public static PropertiesFragment newInstance(String name) {
        
        Bundle args = new Bundle();
        args.putString("squadName", name);
        PropertiesFragment fragment = new PropertiesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext()){
            @Override
            public Cursor loadInBackground() {
                db = DataAccess.getInstance(getActivity());
                c_abilities = db.getAbilities(squadName);
                return c_abilities;
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        data.moveToFirst();
        adapter = new AbilityAdapter(getActivity(), R.layout.properties_listview,data,0);
        listView.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private static class AbilityAdapter extends ResourceCursorAdapter{

        public AbilityAdapter(Context context, int layout, Cursor cursor, int flags) {
            super(context, layout, cursor,flags);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView type = (TextView) view.findViewById(R.id.type);
            TextView ability = (TextView) view.findViewById(R.id.ability);
            TextView ability_desc = (TextView) view.findViewById(R.id.ability_desc);
            TextView ability_adv = (TextView) view.findViewById(R.id.ability_adv);
            TextView ability_weak = (TextView) view.findViewById(R.id.ability_weak);

            String s_type = cursor.getString(cursor.getColumnIndex(MainFractionContract.SquadAbilitiesEntry.COLUMN_TYPE));
            String s_ability = cursor.getString(cursor.getColumnIndex(MainFractionContract.SquadAbilitiesEntry.COLUMN_ABILITY));
            String s_ability_desc = cursor.getString(cursor.getColumnIndex(MainFractionContract.SquadAbilitiesEntry.COLUMN_DESCRIPTION));
            String s_ability_adv = cursor.getString(cursor.getColumnIndex(MainFractionContract.SquadAbilitiesEntry.COLUMN_PLUS));
            String s_ability_weak = cursor.getString(cursor.getColumnIndex(MainFractionContract.SquadAbilitiesEntry.COLUMN_MINUS));

            type.setText(s_type);
            ability.setText(s_ability);
            ability_desc.setText(s_ability_desc);
            ability_adv.setText(s_ability_adv);
            ability_weak.setText(s_ability_weak);


        }
    }

}
