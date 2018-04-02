package com.example.nurs.romebattlegroup.data;

import android.provider.BaseColumns;

/**
 * Created by nurs on 02.04.18.
 */

public final class MainFractionContract {
    public static class FractionsEntry implements BaseColumns{
        public static final String TABLE_NAME = "fractions";
        public static final String COLUMN_FRACTION_NAME = "fraction_name";
        public static final String COLUMN_IMG_RES = "img_res";

        private static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + FractionsEntry.TABLE_NAME + " ("
                + FractionsEntry._ID + " INTEGER PRIMARY KEY,"
                + FractionsEntry.COLUMN_FRACTION_NAME + " VARCHAR,"
                +FractionsEntry.COLUMN_IMG_RES + " VARCHAR)";
        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + FractionsEntry.TABLE_NAME;
    }
}
