package com.example.nurs.romebattlegroup;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import com.example.nurs.romebattlegroup.data.DataAccess;
import com.example.nurs.romebattlegroup.data.MainFractionContract;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PropertiesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PropertiesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PropertiesFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "squadName";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String squadName;
    private String mParam2;
    private ListView lv_prop;
    private Cursor c_prop;
    private DataAccess db;

//    private OnFragmentInteractionListener mListener;

    public PropertiesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param name Parameter 1.
//     * @param param2 Parameter 2.
     * @return A new instance of fragment PropertiesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PropertiesFragment newInstance(String name) {
        PropertiesFragment fragment = new PropertiesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, name);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            squadName = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_properties, container, false);
        lv_prop = v.findViewById(R.id.lv_prop);
        getLoaderManager().initLoader(1,null,this);
        return inflater.inflate(R.layout.fragment_properties, container, false);
    }

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext()){
            @Override
            public Cursor loadInBackground() {
                db= DataAccess.getInstance(getActivity());
                db.open();
                c_prop = db.getProperties(squadName);
                db.close();
                return c_prop;
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        data.moveToFirst();
        PropertiesAdapter adapter = new PropertiesAdapter(getActivity(), R.layout.prop_list_view,data,0);
        lv_prop.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private static class PropertiesAdapter extends ResourceCursorAdapter{

        public PropertiesAdapter(Context context, int layout, Cursor cursor, int flags) {
            super(context, layout, cursor,flags);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView propName = (TextView) view.findViewById(R.id.properties);
            TextView propText = (TextView) view.findViewById(R.id.prop_text);

            String s_propName  = cursor.getString(cursor.getColumnIndex(MainFractionContract.SquadPropertiesEntry.COLUMN_PROPERTIES_NAME));
            String s_propText = cursor.getString(cursor.getColumnIndex(MainFractionContract.SquadPropertiesEntry.COLUMN_PROPERTIES_DESC));

            propName.setText(s_propName);
            propText.setText(s_propText);
        }
    }
}
