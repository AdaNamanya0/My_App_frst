package com.example.myapp;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="Theworld";
    private static final int DATABASE_VERSION= 1;
    private static final char factory = 2;
    private static final String name ="";
    private static final SQLiteDatabase.OpenParams version = ;

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, name, factory, version);
    }

    public MyDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public MyDatabaseHelper(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
CountriesDb.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
CountriesDb.onUpgrade(db,oldVersion,newVersion);
    }
}
