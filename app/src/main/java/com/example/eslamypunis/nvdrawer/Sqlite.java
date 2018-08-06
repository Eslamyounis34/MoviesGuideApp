package com.example.eslamypunis.nvdrawer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by EslamYounis on 7/8/2018.
 */

public class Sqlite extends SQLiteOpenHelper {
    public static final String DB_name="MyDataBase";
    public static final String Table_name="Movies";
    public static final String movie_poster="postter";
    public static final String movie_title="tittle";
    public static final String movie_date="date";
    public static final String movie_overview="overview";
    public static final String movie_avg="avg";
    public static final String movie_id="idd";
    static final int version1=1;

    public Sqlite(Context context) {
        super(context, DB_name, null, version1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE = "CREATE TABLE " +  Table_name  + " (" +
                movie_id  + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                movie_poster  + " TEXT UNIQUE NOT NULL," +
                movie_title+ " TEXT UNIQUE NOT NULL, " +
                movie_date + " TEXT NOT NULL, " +
                movie_overview+ " TEXT NOT NULL, " +
                movie_avg + " TEXT NOT NULL "+");";
        sqLiteDatabase.execSQL(SQL_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
