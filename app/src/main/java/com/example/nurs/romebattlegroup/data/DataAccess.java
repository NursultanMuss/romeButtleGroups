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

    String [] Vybor(String type_of_otryad){
        String [] vybor =null;
            if(type_of_otryad.equals("Пехота ближнего боя") || type_of_otryad.equals("Командование") || type_of_otryad.equals("Полководец") || type_of_otryad.equals("Пехота с копьями") ||
                    type_of_otryad.equals("Конница ближнего боя") || type_of_otryad.equals("Ударная конница") || type_of_otryad.equals("Слон") ||
                    type_of_otryad.equals("Особая боевая еденица")){
                 vybor = new String[] { "_id", "battle_group_name", "img","bliz_boi", "uron_oruzhiem", "natisk",
                        "zashita_bliz_boi", "bronia", "HP", "Moral", "Fraction",
                        "kolvo", "tsena_naima", "tsena_soderzhaniya", "type_of_group"};}
                else if( type_of_otryad.equals( "Стрелки-пехотинцы")|| type_of_otryad.equals( "Стрелки-всадники")|| type_of_otryad.equals("Дальнобойная машина") ||
                    type_of_otryad.equals("Стационарная дальнобойная машина")) {
                vybor = new String[]{"_id", "battle_group_name", "img","bliz_boi", "uron_oruzhiem", "natisk",
                        "zashita_bliz_boi", "bronia", "HP", "Moral", "Fraction",
                        "kolvo", "tsena_naima", "tsena_soderzhaniya", "type_of_group", "uron_snaryada", "dalnost", "vistrel_v_min", "boepripasy"};}
                else if (type_of_otryad.equals("Флотоводец")|| type_of_otryad.equals( "Корабль ближнего боя")){
                vybor = new String[]{"_id", "battle_group_name", "img","bliz_boi", "uron_oruzhiem", "natisk",
                        "zashita_bliz_boi", "bronia", "HP", "Moral", "Fraction",
                        "kolvo", "tsena_naima", "tsena_soderzhaniya", "type_of_group", "ship_hp", "ship_speed"};}
                else if (type_of_otryad.equals("Стрелковый корабль") || type_of_otryad.equals("Корабль с дальнобойными машинами")){
                vybor = new String[] {"_id", "battle_group_name", "img","bliz_boi", "uron_oruzhiem", "natisk",
                        "zashita_bliz_boi", "bronia", "HP", "Moral", "Fraction",
                        "kolvo", "tsena_naima", "tsena_soderzhaniya", "type_of_group", "ship_hp", "ship_speed",  "uron_snaryada", "dalnost", "vistrel_v_min", "boepripasy"};}
                return vybor;
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

    public Cursor getInfanty(String fraction, String type_of_otryad){
        open();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String [] sqlSelect =new String[] {"_id", "battle_group_name", "img","bliz_boi", "uron_oruzhiem", "natisk",
                "zashita_bliz_boi", "bronia", "HP", "Moral", "Fraction",
                "kolvo", "tsena_naima", "tsena_soderzhaniya", "type_of_group", "ship_hp", "ship_speed",  "uron_snaryada", "dalnost", "vistrel_v_min", "boepripasy"};
        String sqlTables = "infanty";
        String selection = "Fraction=? AND type_of_group=?";
        String[] selectionArgs = new String[]{ fraction, type_of_otryad};

        qb.setTables(sqlTables);

        Cursor c = qb.query(this.database, sqlSelect, selection, selectionArgs,
                null, null,null);

        c.moveToFirst();
        return c;
    }
    public Cursor getVozrastaniu(String fraction, String type_of_otryad){
        open();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String [] sqlSelect =new String[] {"_id", "battle_group_name", "img","bliz_boi", "uron_oruzhiem", "natisk",
                "zashita_bliz_boi", "bronia", "HP", "Moral", "Fraction",
                "kolvo", "tsena_naima", "tsena_soderzhaniya", "type_of_group", "ship_hp", "ship_speed",  "uron_snaryada", "dalnost", "vistrel_v_min", "boepripasy"};
        String sqlTables = "infanty";
        String orderBy= "tsena_naima";
        String selection = "Fraction=? AND type_of_group=? ";
        String[] selectionArgs = new String[]{fraction, type_of_otryad};

        qb.setTables(sqlTables);

        Cursor c = qb.query(this.database, sqlSelect, selection,selectionArgs,null,null,orderBy);
        c.moveToFirst();
        return c;
    }

    public Cursor getKrutosti(String fraction, String type_of_otryad){
        open();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String [] sqlSelect = new String[] {"_id", "battle_group_name", "img","bliz_boi", "uron_oruzhiem", "natisk",
                "zashita_bliz_boi", "bronia", "HP", "Moral", "Fraction",
                "kolvo", "tsena_naima", "tsena_soderzhaniya", "type_of_group", "ship_hp", "ship_speed",  "uron_snaryada", "dalnost", "vistrel_v_min", "boepripasy"};
        String sqlTables = "infanty";
        String orderBy= "bliz_boi DESC";
        String selection = "Fraction=? AND type_of_group=?";
        String[] selectionArgs = new String[]{fraction, type_of_otryad};

        qb.setTables(sqlTables);

        Cursor c = qb.query(this.database, sqlSelect, selection,selectionArgs,null,null,orderBy);
        c.moveToFirst();
        return c;
    }
    public Cursor getUbivaniu(String fraction, String type_of_otryad){
        open();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String [] sqlSelect =new String[] {"_id", "battle_group_name", "img","bliz_boi", "uron_oruzhiem", "natisk",
                "zashita_bliz_boi", "bronia", "HP", "Moral", "Fraction",
                "kolvo", "tsena_naima", "tsena_soderzhaniya", "type_of_group", "ship_hp", "ship_speed",  "uron_snaryada", "dalnost", "vistrel_v_min", "boepripasy"};
        String sqlTables = "infanty";
        String orderBy= "tsena_naima DESC";
        String selection = "Fraction=? AND type_of_group=?";
        String[] selectionArgs = new String[]{fraction,type_of_otryad};

        qb.setTables(sqlTables);

        Cursor c = qb.query(this.database, sqlSelect, selection,selectionArgs,null,null,orderBy);
        c.moveToFirst();
        return c;
    }

}
