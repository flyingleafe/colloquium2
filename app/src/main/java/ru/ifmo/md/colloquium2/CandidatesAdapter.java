package ru.ifmo.md.colloquium2;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import ru.ifmo.md.colloquium2.db.CandidatesTable;

public class CandidatesAdapter extends CursorAdapter {

    private CandidatesActivity activity;

    public CandidatesAdapter(CandidatesActivity activity, Cursor c, int flags) {
        super(activity, c, flags);
        this.activity = activity;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.candidate_item, viewGroup, false);
        bindView(v, context, cursor);
        return v;
    }

    @Override
    public void bindView(View v, Context context, Cursor cursor) {
        int nameCol = cursor.getColumnIndex(CandidatesTable.COLUMN_NAME_NAME);
        int idCol = cursor.getColumnIndex(CandidatesTable._ID);

        String title = cursor.getString(nameCol);
        final long id = cursor.getLong(idCol);

        final TextView nameText = (TextView) v.findViewById(android.R.id.text1);
        ImageButton deleteButton = (ImageButton) v.findViewById(android.R.id.button1);
        ImageButton editButton = (ImageButton) v.findViewById(android.R.id.button2);
        nameText.setText(title);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.deleteCandidate(id);
            }
        });
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.startEditingCandidate(id);
            }
        });
    }
}
