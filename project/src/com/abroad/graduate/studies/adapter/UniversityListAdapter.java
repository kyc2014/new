package com.abroad.graduate.studies.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.abroad.graduate.studies.R;
import com.abroad.graduate.studies.model.University;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by gp on 11/11/14.
 */
public class UniversityListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<University> universityItems; // filtered
    private List<University> completeUniversityItems; // not filtered

    public UniversityListAdapter(Activity activity, List<University> universityItems) {
        this.activity = activity;
        this.universityItems = universityItems;
        completeUniversityItems=new ArrayList<University>();
        completeUniversityItems.addAll(universityItems);
    }

    @Override
    public int getCount() {
        return universityItems.size();
    }

    @Override
    public Object getItem(int location) {
        return universityItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        TextView name = (TextView) convertView.findViewById(R.id.university_name);

        University u = universityItems.get(position);

        name.setText(u.getName());

        return convertView;
    }

    public void filter(String filterText)
    {
        filterText = filterText.toLowerCase(Locale.getDefault());
        universityItems.clear();
        if (filterText.length() == 0) {
            universityItems.addAll(completeUniversityItems);
        }
        else
        {
            for (University wp : completeUniversityItems)
            {
                if (wp.getName().toLowerCase(Locale.getDefault()).contains(filterText))
                {
                    universityItems.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}
