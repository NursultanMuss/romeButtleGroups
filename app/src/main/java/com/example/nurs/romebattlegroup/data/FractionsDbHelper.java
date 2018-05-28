package com.example.nurs.romebattlegroup.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

/**
 * Created by nurs on 03.04.18.
 */

public class FractionsDbHelper extends SQLiteAssetHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "fractions.sqlite";
    private static FractionsDbHelper instance;

    private final String TAG = "Debug";
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public FractionsDbHelper (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG,"constructor called");
        this.mContext = context;
    }





}
