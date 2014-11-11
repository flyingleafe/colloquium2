package ru.ifmo.md.colloquium2;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

import ru.ifmo.md.colloquium2.db.CandidatesTable;

/**
 * Created by flyingleafe on 11.11.14.
 */
public class ResultsAdapter extends CursorAdapter {
    private ResultsActivity activity;

    public ResultsAdapter(ResultsActivity activity, Cursor c, int flags) {
        super(activity, c, flags);
        this.activity = activity;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.result_item, viewGroup, false);
        bindView(v, context, cursor);
        return v;
    }

    @Override
    public void bindView(View v, Context context, Cursor cursor) {
        int nameCol = cursor.getColumnIndex(CandidatesTable.COLUMN_NAME_NAME);
        int votesCol = cursor.getColumnIndex(CandidatesTable.COLUMN_NAME_VOTES);

        String title = cursor.getString(nameCol);
        int votes = cursor.getInt(votesCol);

        TextView nameText = (TextView) v.findViewById(android.R.id.text1);
        TextView votesText = (TextView) v.findViewById(android.R.id.text2);
        nameText.setText(title);
        votesText.setText(Integer.toString(votes));
    }
}
