package com.example.nurs.romebattlegroup;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

 * to handle interaction events.
 * Use the {@link SilaSlabostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SilaSlabostFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String squadName;
    private ListView ls;
    private DataAccess db;
    private Cursor cursor;
    private SilaSlabostiAdapter adapter;

//    private OnFragmentInteractionListener mListener;

    public SilaSlabostFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param name Parameter 1.
//     * @param param2 Parameter 2.
     * @return A new instance of fragment SilaSlabostFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SilaSlabostFragment newInstance(String name) {
        SilaSlabostFragment fragment = new SilaSlabostFragment();
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
        View v = inflater.inflate(R.layout.fragment_sila_slabost, container, false);
        ls = (ListView) v.findViewById(R.id.sila_slab_lv);
        return v;
    }

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(getContext()){
            @Override
            public Cursor loadInBackground() {
                db = DataAccess.getInstance(getActivity());
                db.open();
                cursor = db.getSilaSlabosti(squadName);
//                db.close();
                return cursor;
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        cursor.moveToFirst();
        adapter = new SilaSlabostiAdapter(getActivity(),R.layout.sila_slab_listview,cursor,0);
        ls.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }

    private static class SilaSlabostiAdapter extends ResourceCursorAdapter {

        public SilaSlabostiAdapter(Context context, int layout, Cursor cursor, int flags) {
            super(context, layout, cursor,flags);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView silaSlabTv = (TextView) view.findViewById(R.id.sila_slab_tv);
            TextView propText = (TextView) view.findViewById(R.id.properties);

            String s_silaSlabTv  = cursor.getString(cursor.getColumnIndex(MainFractionContract.SilaSlabostEntry.COLUMN_TYPE));
            String s_propText = cursor.getString(cursor.getColumnIndex(MainFractionContract.SilaSlabostEntry.COLUMN_PROPERTIES));

            silaSlabTv.setText(s_silaSlabTv);
            propText.setText(s_propText);
        }
    }
}
