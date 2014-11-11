package ru.ifmo.md.colloquium2.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class VoteContentProvider extends ContentProvider {
    private static String AUTHORITY = "ru.ifmo.md.colloquium2.db.VoteContentProvider";

    public static final Uri CANDIDATES_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/candidates");
    public static final Uri NEWS_CONTENT_URL = Uri.parse("content://" + AUTHORITY + "/votes");

    private VoteDBHelper mDbHelper;

    private String getTableName(Uri uri) {
        return getTableName(uri.getLastPathSegment());
    }

    private String getTableName(String type) {
        String tableName;
        if(type.equals("candidates")) {
            tableName = CandidatesTable.TABLE_NAME;
        } else if(type.equals("votes")) {
            tableName = VotesTable.TABLE_NAME;
        } else {
            throw new UnsupportedOperationException("Invalid data type");
        }
        return tableName;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        return db.delete(getTableName(uri), selection, selectionArgs);
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        long id = db.insert(tableName, null, values);
        return Uri.parse("content://" + AUTHORITY + "/" + tableName + "/" + Long.toString(id));
    }

    @Override
    public boolean onCreate() {
        mDbHelper = new VoteDBHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        return db.query(getTableName(uri), projection, selection, selectionArgs, null, null, sortOrder);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        return db.update(getTableName(uri), values, selection, selectionArgs);
    }
}
