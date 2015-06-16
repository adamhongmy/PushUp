package com.example.williams.asd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by USER on 21/3/2015.
 */
public class RecordsAdapter extends ArrayAdapter<UserRecord> {

    private Context context;
    private List<UserRecord> values;
    public RecordsAdapter(Context context, List<UserRecord> values) {
        super(context,R.layout.records_view, values);
        this.context=context;
        this.values=values;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.records_view, parent, false);
        TextView textView1 = (TextView) rowView.findViewById(R.id.txtDateR);
        TextView textView2 = (TextView) rowView.findViewById(R.id.txtfreq);
        TextView textView3 = (TextView) rowView.findViewById(R.id.txtprog);
        UserRecord re = values.get(position);
        textView1.setText(re.getDate());
        textView2.setText(String.format("%d",re.getFrequency()));
        textView3.setText(String.format("%d",re.getProgress()));


        return rowView;
    }
}
