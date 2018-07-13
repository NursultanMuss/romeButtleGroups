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

    public Cursor getInfanti(){
        open();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] sqlSelect = { "_id", "battle_group_name", "bliz_boi", "uron_oruzhiem", "natisk",
                "zashita_bliz_boi", "bronia", "HP", "Moral", "Fraction",
                "kolvo", "tsena_naima", "tsena_soderzhaniya"};
        String sqlTable = "infanty";

        qb.setTables(sqlTable);

        Cursor c = qb.query(this.database, sqlSelect, null, null,
                null, null,null);

        c.moveToFirst();
        return c;
    }
}
