package ru.ifmo.md.colloquium2;

import android.content.ContentResolver;
import android.database.Cursor;

import ru.ifmo.md.colloquium2.db.VoteContentProvider;

/**
 * Created by flyingleafe on 11.11.14.
 */
public class Candidate {
    public static Cursor getAllCursor(ContentResolver resolver) {
        String[] proj = {"*"};
        return resolver.query(
                    VoteContentProvider.CANDIDATES_CONTENT_URI,
                    proj,
                    null,
                    null,
                    null);
    }

    public static Cursor getSortedCursor(ContentResolver resolver) {
        String[] proj = {"*"};
        return resolver.query(
                VoteContentProvider.CANDIDATES_CONTENT_URI,
                proj,
                null,
                null,
                "votes DESC");
    }
}
