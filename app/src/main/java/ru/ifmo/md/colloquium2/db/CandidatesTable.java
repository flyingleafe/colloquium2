package ru.ifmo.md.colloquium2.db;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by flyingleafe on 10.11.14.
 */
public class CandidatesTable implements BaseColumns {
    public static final String TABLE_NAME = "candidates";
    public static final String COLUMN_NAME_NAME = "name";
    public static final String COLUMN_NAME_VOTES = "votes";

    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_CANDIDATES =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    _ID + INT_TYPE + " PRIMARY KEY," +
                    COLUMN_NAME_NAME + TEXT_TYPE + " UNIQUE ON CONFLICT IGNORE" + COMMA_SEP +
                    COLUMN_NAME_VOTES + INT_TYPE + " NOT NULL DEFAULT 0" +
                    " );";

    private static final String SQL_DELETE_CANDIDATES =
            "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public static void create(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_CANDIDATES);
    }

    public static void delete(SQLiteDatabase db) {
        db.execSQL(SQL_DELETE_CANDIDATES);
    }
}
