package com.example.myapp;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyContentProvider extends ContentProvider {
private MyDatabaseHelper dbHelper;

private static final int ALL_COUNTRIES =1;
    private static final int SINGLE_COUNTRY =2;

//authority is the symbolic name of the provider
    //To avoid conflicts with other provider u shd use
    //Internet ownership (in reverse)as the basis of your provider authority
private static final String AUTHORITY ="com.example.myapplication.contentproviderusingsqlitedatabase.contentprovider";

//create content URIs from the authority by appendind path to database table
public static final Uri CONTENT_URI =
        Uri.parse("content://" + AUTHORITY + "/countries");
    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "countries", ALL_COUNTRIES);
        uriMatcher.addURI(AUTHORITY, "countries/#", SINGLE_COUNTRY);
    }

    @Override
    public boolean onCreate() {
        //get access to the database helper
        dbHelper = new MyDatabaseHelper (getContext());
        return false;
    }


    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
       SQLiteDatabase db = dbHelper.getWritableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(CountriesDb.SQLITE_TABLE);

        switch (uriMatcher.match(uri)) {
            case ALL_COUNTRIES:
                break;
            case SINGLE_COUNTRY:
                String id = uri.getPathSegments().get(1);
                queryBuilder.appendWhere(CountriesDb.KEY_ROWID +"=" + id);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI: "+uri);
        }
        return queryBuilder.query(db,projection, selection, selectionArgs, null,null, sortOrder);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)){
            case ALL_COUNTRIES:
                return "vnd.android.cursor.dir/vnd.com.example.myapplication.contentproviderusingsqlitedatabase.contentprovider";
            case SINGLE_COUNTRY:
                return "vnd.android.cursor.dir/vnd.com.example.myapplication.contentproviderusingsqlitedatabase.contentprovider";
            default:
                throw new IllegalArgumentException("Unsupported URI: "+uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        switch (uriMatcher.match(uri)) {
            case ALL_COUNTRIES:
                //do nothing
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI: "+uri);
        }
        long id =db.insert(CountriesDb.SQLITE_TABLE, null,values);
        getContext().getContentResolver().notifyChange(uri,null);
        return Uri.parse(CONTENT_URI + "/" +id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        switch (uriMatcher.match(uri)){
            case ALL_COUNTRIES:
                break;
            case SINGLE_COUNTRY:
                String id = uri.getPathSegments().get(1);
                selection = CountriesDb.KEY_ROWID + "=" + id
                        + (!TextUtils.isEmpty(selection) ?
                        "AND (" + selection + ')' : "");
                break;
            default:
                throw new IllegalArgumentException("Un supported URI: "+uri);
        }
        int deletecount = db.delete(CountriesDb.SQLITE_TABLE,selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri,null);
        return deletecount;
    }


    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        switch (uriMatcher.match(uri)) {
            case ALL_COUNTRIES:
                break;
            case SINGLE_COUNTRY:
                String id = uri.getPathSegments().get(1);
                selection = CountriesDb.KEY_ROWID + "=" + id
                        + (!TextUtils.isEmpty(selection) ?
                        "AND (" + selection + ')' : "");
                break;
            default:
                throw new IllegalArgumentException("Un supported URI: " + uri);
        }

        int updatecount = db.update(CountriesDb.SQLITE_TABLE,values,selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri,null);
        return updatecount;
    }
}
