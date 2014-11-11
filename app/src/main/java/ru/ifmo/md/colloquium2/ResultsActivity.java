package ru.ifmo.md.colloquium2;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import ru.ifmo.md.colloquium2.db.VoteContentProvider;


public class ResultsActivity extends ListActivity {

    ResultsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        adapter = new ResultsAdapter(this, Candidate.getSortedCursor(getContentResolver()), 0);
        setListAdapter(adapter);
    }

    public void startNewVote(View view) {
        getContentResolver().delete(VoteContentProvider.CANDIDATES_CONTENT_URI, null, null);
        Intent intent = new Intent(this, CandidatesActivity.class);
        startActivity(intent);
    }

}
