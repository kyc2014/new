package com.abroad.graduate.studies;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import com.abroad.graduate.studies.adapter.UniversityListAdapter;
import com.abroad.graduate.studies.model.University;

import java.util.List;
import java.util.Locale;

public class HomeFragment extends Fragment {
	
	public HomeFragment(){
        universityList=new ArrayList<University>();
    }

    private ListView listView;
    private UniversityListAdapter adapter;
    private List<University> universityList;
    private EditText nameFilter;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        populateUniversityList(rootView);

        nameFilter=(EditText)rootView.findViewById(R.id.university_name_filter);

        nameFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = nameFilter.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
            }
        });

        return rootView;
    }

    public void populateUniversityList(View rootView)
    {

        universityList.add(new University("University of California Berkeley", "", "2014/12/30", "", "", "", 320, 110, 7.5f));
        universityList.add(new University("Stony Brook University", "", "2014/12/30", "", "", "", 320, 110, 7.5f));
        universityList.add(new University("University of Southern California", "", "2014/12/30", "", "", "", 320, 110, 7.5f));
        universityList.add(new University("University of California Davis", "", "2014/12/30", "", "", "", 320, 110, 7.5f));
        universityList.add(new University("University of California Los Angeles", "", "2014/12/30", "", "", "", 320, 110, 7.5f));
        universityList.add(new University("Stanford University", "", "2014/12/30", "", "", "", 320, 110, 7.5f));
        universityList.add(new University("Harvard University", "", "2014/12/30", "", "", "", 320, 110, 7.5f));
        universityList.add(new University("Carnegie Melon University", "", "2014/12/30", "", "", "", 320, 110, 7.5f));

        listView = (ListView) rootView.findViewById(R.id.list);
        adapter = new UniversityListAdapter(this.getActivity(),universityList);
        listView.setAdapter(adapter);

    }

}
