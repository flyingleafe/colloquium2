package ru.ifmo.md.colloquium2;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import ru.ifmo.md.colloquium2.db.CandidatesTable;
import ru.ifmo.md.colloquium2.db.VoteContentProvider;


public class CandidatesActivity extends ListActivity {

    private CandidatesAdapter adapter;
    private TextView newCandidateInput;

    private Cursor getCandidatesCursor() {
        return Candidate.getAllCursor(getContentResolver());
    }

    private void refreshList() {
        adapter.changeCursor(getCandidatesCursor());
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newCandidateInput = (TextView) findViewById(R.id.new_candidate_input);
        adapter = new CandidatesAdapter(this, getCandidatesCursor(), 0);
        setListAdapter(adapter);
    }

    public void deleteCandidate(long id) {
        String[] args = {Long.toString(id)};
        getContentResolver().delete(VoteContentProvider.CANDIDATES_CONTENT_URI,
                CandidatesTable._ID + "=?", args);
        refreshList();
    }

    public void startEditingCandidate(final long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.candidate_edit_desc);
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newName = input.getText().toString();
                editCandidate(id, newName);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    public void editCandidate(long id, String newName) {
        ContentValues row = new ContentValues();
        row.put(CandidatesTable.COLUMN_NAME_NAME, newName);
        getContentResolver().update(VoteContentProvider.CANDIDATES_CONTENT_URI,
                row,
                CandidatesTable._ID + "=?",
                new String[] {Long.toString(id)});
        refreshList();
    }

    public void addNewCandidate(View view) {
        String name = newCandidateInput.getText().toString();
        newCandidateInput.setText("");
        ContentValues row = new ContentValues();
        row.put(CandidatesTable.COLUMN_NAME_NAME, name);
        getContentResolver().insert(VoteContentProvider.CANDIDATES_CONTENT_URI, row);
        refreshList();
    }

    public void startVote(View view) {
        Intent intent = new Intent(this, VoteActivity.class);
        startActivity(intent);
    }
}
