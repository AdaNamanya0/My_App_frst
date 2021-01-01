package com.example.myapp;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class CountriesDb {
    public static final String KEY_ROWID ="id";
    public static final String KEY_CODE ="code";
    private static final String KEY_NAME ="name";
    public static final String KEY_CONTINENT ="continent";
    public static final String LOG_TAG ="countries";
    public static final String SQLITE_TABLE ="country";

    public static final String TABLE_CREATE =
            "CREATE TABLE if not EXISTS " + SQLITE_TABLE + "(" +
                    KEY_ROWID + "integer PRIMARY KEY autoincrement,"+
                    KEY_CODE + ","+
                    KEY_NAME +","+
                    KEY_CONTINENT + ","+
                    "UNIQUE ("+KEY_CODE+"));";
    public static void onCreate(SQLiteDatabase db) {
        Log.w(TAG, "onCreate: TABLE_CREATE");
        db.execSQL(TABLE_CREATE);
    }
    public static void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion) {
        Log.w(TAG,"uploading database from version "+ oldVersion + "to"
                +newVersion+ ", which will destroy all old data");
    db.execSQL("DROP TABLE IF EXISTS "+SQLITE_TABLE);
    onCreate(db);
    }
}
