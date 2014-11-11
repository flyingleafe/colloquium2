package ru.ifmo.md.colloquium2.db;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by flyingleafe on 11.11.14.
 */
public class VotesTable implements BaseColumns {
    public static final String TABLE_NAME = "votes";
    public static final String COLUMN_NAME_CANDIDATE = "candidate";

    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_VOTES =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME_CANDIDATE + " INTEGER UNIQUE ON CONFLICT IGNORE" + COMMA_SEP +
                    "FOREIGN KEY(" + COLUMN_NAME_CANDIDATE + ") REFERENCES " + CandidatesTable.TABLE_NAME +
                        "(" + CandidatesTable._ID + ") ON DELETE CASCADE" +
                    " );";

    private static final String SQL_DELETE_VOTES =
            "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public static void create(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_VOTES);
    }

    public static void delete(SQLiteDatabase db) {
        db.execSQL(SQL_DELETE_VOTES);
    }
}
