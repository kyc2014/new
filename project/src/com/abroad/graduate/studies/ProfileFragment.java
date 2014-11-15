package com.abroad.graduate.studies;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager.OnActivityResultListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

public class ProfileFragment extends Fragment {
	
	Button button;
	public final int YES_NO_CALL = 1;
	public ProfileFragment(){}
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		String[] countries = getResources().getStringArray(R.array.profile_institutions_array);
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        Context c = getActivity().getApplicationContext();
        AutoCompleteTextView textView = (AutoCompleteTextView) rootView.findViewById(R.id.profile_autocomplete_institution);
        ArrayAdapter<String> adapter = new ArrayAdapter<String> (c, R.layout.my_list_item, countries);
        textView.setAdapter(adapter);
        button = (Button) rootView.findViewById(R.id.profile_submit);
        //Have the following code for now
        
//        DialogFragment dialog = new YesNoDialog();
//	    Bundle args = new Bundle();
//	    args.putString("title", R.id.profile_confirm_string);
//	    args.putString("message", R.id.profile_submit_confirmation_string);
//	    dialog.setArguments(args);
//	    dialog.setTargetFragment(this, YES_NO_CALL);
//	    dialog.show(getFragmentManager(), "tag");
        
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			   String institution = ((EditText)v.findViewById(R.id.profile_autocomplete_institution)).getText().toString();
			   String name = ((EditText)v.findViewById(R.id.profile_text_name)).getText().toString();
			   String gre = ((EditText)v.findViewById(R.id.profile_text_gre)).getText().toString();
			   String toefl = ((EditText)v.findViewById(R.id.profile_text_toefl)).getText().toString();
			   String ugScore = ((EditText)v.findViewById(R.id.profile_text_ug_score)).getText().toString();
			   String workExperience = ((EditText)v.findViewById(R.id.profile_text_work_experience)).getText().toString();
			   HttpClient httpclient = new DefaultHttpClient();
			    HttpPost httppost = new HttpPost("http://www.yoursite.com/script.php");

			    try {
			        // Add your data
			        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			        nameValuePairs.add(new BasicNameValuePair("institution", institution));
			        nameValuePairs.add(new BasicNameValuePair("name", name));
			        nameValuePairs.add(new BasicNameValuePair("gre", gre));
			        nameValuePairs.add(new BasicNameValuePair("toefl", toefl));
			        nameValuePairs.add(new BasicNameValuePair("ugScore", ugScore));
			        nameValuePairs.add(new BasicNameValuePair("workExperience", workExperience));
			        
			        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			        // Execute HTTP Post Request
			        HttpResponse response = httpclient.execute(httppost);
			        
			    } catch (ClientProtocolException e) {
			        // TODO Auto-generated catch block
			    } catch (IOException e) {
			        // TODO Auto-generated catch block
			    }

			}
		});
        return rootView;
    }
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		
	}

}
