package ru.ifmo.md.colloquium2;

import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import ru.ifmo.md.colloquium2.db.CandidatesTable;
import ru.ifmo.md.colloquium2.db.VoteContentProvider;
import ru.ifmo.md.colloquium2.db.VoteDBHelper;


public class VoteActivity extends ListActivity {

    private VoteAdapter adapter;
    private VoteDBHelper dbHelper;

    private void refreshList() {
        adapter.changeCursor(Candidate.getAllCursor(getContentResolver()));
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);
        adapter = new VoteAdapter(this, Candidate.getAllCursor(getContentResolver()), 0);
        dbHelper = new VoteDBHelper(this);
        setListAdapter(adapter);
    }

    public void voteForCandidate(long id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(
                "UPDATE " + CandidatesTable.TABLE_NAME + " SET " +
                CandidatesTable.COLUMN_NAME_VOTES + "=" + CandidatesTable.COLUMN_NAME_VOTES + "+1 WHERE " +
                CandidatesTable._ID + "=?", new String[] { Long.toString(id) }
        );
    }

    public void finishVote(View view) {
        Intent intent = new Intent(this, ResultsActivity.class);
        startActivity(intent);
    }

}
