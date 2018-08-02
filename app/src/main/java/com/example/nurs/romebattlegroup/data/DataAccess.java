package com.example.nurs.romebattlegroup.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

/**
 * Created by nurs on 11.04.18.
 */

public class DataAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DataAccess instance;


    private DataAccess (Context context){this.openHelper = new FractionsDbHelper(context);}
    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DataAccess getInstance(Context context){
        if(instance == null){
            instance = new DataAccess(context);
        }
        return instance;
    }
    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    public Cursor getFractions() {

//        SQLiteDatabase db = getReadableDatabase();
        open();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String [] sqlSelect = {"_id", "fraction_name", "img_res"};
        String sqlTables = "fractions";

        qb.setTables(sqlTables);
        Cursor c = qb.query(this.database, sqlSelect, null, null,
                null, null, null);
        c.moveToFirst();
        return c;
    }
    public  Cursor getFracOtryadi(String fraction){
        open();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] sqlSelect = {"_id", "frac_name", "otryadi"};
        String sqlTables = "fraction_otryad";
        String selection = "frac_name = ?";
        String[] selectionArgs = new String[]{fraction};
        qb.setTables(sqlTables);

        Cursor c = qb.query(this.database, sqlSelect, selection,selectionArgs, null, null, null);
        c.moveToFirst();
        return c;
    }

//    public Cursor getInfanty(String fraction, String type_of_otryad){
//        open();
//        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
//        String[] sqlSelect = { "_id", "battle_group_name", "img","bliz_boi", "uron_oruzhiem", "natisk",
//                "zashita_bliz_boi", "bronia", "HP", "Moral", "Fraction",
//                "kolvo", "tsena_naima", "tsena_soderzhaniya", "type_of_group"};
//        String sqlTables = "infanty";
//        String selection = "type_of_group=?";
//        String[] selectionArgs = new String[]{ type_of_otryad};
//
//        qb.setTables(sqlTables);
//
//        Cursor c = qb.query(this.database, sqlSelect, selection, selectionArgs,
//                null, null,null);
//
//        c.moveToFirst();
//        return c;
//    }
    public Cursor getVozrastaniu(String fraction){
        open();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String []  sqlSelect = { "_id", "battle_group_name", "img","bliz_boi", "uron_oruzhiem", "natisk",
                "zashita_bliz_boi", "bronia", "HP", "Moral", "Fraction",
                "kolvo", "tsena_naima", "tsena_soderzhaniya"};
        String sqlTables = "infanty";
        String orderBy= "tsena_naima";
        String selection = "Fraction = ?";
        String[] selectionArgs = new String[]{fraction};

        qb.setTables(sqlTables);

        Cursor c = qb.query(this.database, sqlSelect, selection,selectionArgs,null,null,orderBy);
        c.moveToFirst();
        return c;
    }

    public Cursor getKrutosti(String fraction, String type_of_otryad){
        open();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String []  sqlSelect = { "_id", "battle_group_name", "img","bliz_boi", "uron_oruzhiem", "natisk",
                "zashita_bliz_boi", "bronia", "HP", "Moral", "Fraction",
                "kolvo", "tsena_naima", "tsena_soderzhaniya", "type_of_group"};
        String sqlTables = "infanty";
        String orderBy= "bliz_boi DESC";
        String selection = "Fraction=? AND type_of_group=?";
        String[] selectionArgs = new String[]{fraction, type_of_otryad};

        qb.setTables(sqlTables);

        Cursor c = qb.query(this.database, sqlSelect, selection,selectionArgs,null,null,orderBy);
        c.moveToFirst();
        return c;
    }
    public Cursor getUbivaniu(String fraction){
        open();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String []  sqlSelect = { "_id", "battle_group_name", "img","bliz_boi", "uron_oruzhiem", "natisk",
                "zashita_bliz_boi", "bronia", "HP", "Moral", "Fraction",
                "kolvo", "tsena_naima", "tsena_soderzhaniya"};
        String sqlTables = "infanty";
        String orderBy= "tsena_naima DESC";
        String selection = "Fraction = ?";
        String[] selectionArgs = new String[]{fraction};

        qb.setTables(sqlTables);

        Cursor c = qb.query(this.database, sqlSelect, selection,selectionArgs,null,null,orderBy);
        c.moveToFirst();
        return c;
    }
//    public Cursor getGroupsType(String fraction){
//        open();
//        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
//
//    }

}
