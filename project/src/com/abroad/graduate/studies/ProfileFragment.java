package com.abroad.graduate.studies;


import com.abroad.graduate.studies.https.caller.HttpsCaller;
import com.abroad.graduate.studies.util.ValidatorUtil;


import android.app.DialogFragment;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
	public static final int YES_NO_CALL = 1;
	public final HttpsCaller httpsCaller;
	public ProgressDialog progressProfile;
	public final DialogFragment dialog;
	public final Bundle args;

	public ProfileFragment() {
		this.httpsCaller = new HttpsCaller();
		this.dialog = new YesNoDialog();
		this.args = new Bundle();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.progressProfile = new ProgressDialog(getActivity());
		populateWithExistingData();
		String[] countries = getResources().getStringArray(
				R.array.profile_institutions_array);
		final View rootView = inflater.inflate(R.layout.fragment_profile,
				container, false);
		final Context c = getActivity().getApplicationContext();
		AutoCompleteTextView textView = (AutoCompleteTextView) rootView
				.findViewById(R.id.profile_autocomplete_institution);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(c,
				R.layout.my_list_item, countries);
		textView.setAdapter(adapter);
		button = (Button) rootView.findViewById(R.id.profile_submit);
		// Have the following code for now 14/Nov/2014
		final ProfileFragment profileFragment = this;
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				final String institution = ((EditText) rootView
						.findViewById(R.id.profile_autocomplete_institution))
						.getText().toString();
				final String name = ((EditText) rootView
						.findViewById(R.id.profile_text_name)).getText()
						.toString();
				final String greQuants = ((EditText) rootView
						.findViewById(R.id.profile_text_gre_quants)).getText()
						.toString();
				final String greVerbal = ((EditText) rootView
						.findViewById(R.id.profile_text_gre_verbal)).getText()
						.toString();
				final String greAWA = ((EditText) rootView
						.findViewById(R.id.profile_text_gre_awa)).getText()
						.toString();
				final String toeflListening = ((EditText) rootView
						.findViewById(R.id.profile_text_toefl_listening))
						.getText().toString();
				final String toeflSpeaking = ((EditText) rootView
						.findViewById(R.id.profile_text_toefl_speaking))
						.getText().toString();
				final String toeflWriting = ((EditText) rootView
						.findViewById(R.id.profile_text_toefl_writing))
						.getText().toString();
				final String ugScore = ((EditText) rootView
						.findViewById(R.id.profile_text_ug_score)).getText()
						.toString();
				final String email = ((EditText) rootView
						.findViewById(R.id.profile_text_email)).getText()
						.toString();
				final String confirmEmail = ((EditText) rootView
						.findViewById(R.id.profile_text_confirm_email))
						.getText().toString();
				final String workExperience = ((EditText) rootView
						.findViewById(R.id.profile_text_work_experience))
						.getText().toString();
				final String backlogs = ((EditText) rootView
						.findViewById(R.id.profile_text_backlogs)).getText()
						.toString();
				final String description = ((EditText) rootView
						.findViewById(R.id.profile_description)).getText()
						.toString();
				final String country = ((EditText) rootView
						.findViewById(R.id.profile_country)).getText()
						.toString();
				// Validate the input
				if (!ValidatorUtil.isEmptyString(name)) {
					if (!ValidatorUtil.isEmptyString(email)
							|| !ValidatorUtil.isEmptyString(confirmEmail)) {
						if (!ValidatorUtil.isValidEmail(email)) {
							dialog.setTargetFragment(profileFragment,
									YES_NO_CALL);
							args.putString("title", "Email");
							args.putString("message", "Invalid email");
							dialog.setArguments(args);
							dialog.show(getFragmentManager(), "tag");
						} else {
							if (email.equals(confirmEmail)) {
								progressProfile.setTitle("Saving Data");
								progressProfile.setMessage("Plese wait ...");
								progressProfile.show();
								StringBuilder sb = new StringBuilder();
								sb.append("institution=").append(institution);
								sb.append("&name=").append(name);
								sb.append("&greQuants=").append(greQuants);
								sb.append("&greVerbal=").append(greVerbal);
								sb.append("&greAWA=").append(greAWA);
								sb.append("&toeflListening=").append(
										toeflListening);
								sb.append("&toeflWriting=")
										.append(toeflWriting);
								sb.append("&toeflSpeaking=").append(
										toeflSpeaking);
								sb.append("&ugscore=").append(ugScore);
								sb.append("&email=").append(email);
								sb.append("&workExperience=").append(
										workExperience);
								sb.append("&description=").append(description);
								sb.append("&backlogs=").append(backlogs);
								sb.append("&country=").append(country);
								httpsCaller.caller(sb.toString(), c,
										profileFragment);
							} else {
								dialog.setTargetFragment(profileFragment, YES_NO_CALL);
								args.putString("title", "Email");
								args.putString("message", "Email Mismatch");
								dialog.setArguments(args);
								dialog.show(getFragmentManager(), "tag");
							}
						}
					} else {
						dialog.setTargetFragment(profileFragment, YES_NO_CALL);
						args.putString("title", "Email");
						args.putString("message", "Email field is mandatory");
						dialog.setArguments(args);
						dialog.show(getFragmentManager(), "tag");
					}
				} else {
					dialog.setTargetFragment(profileFragment, YES_NO_CALL);
					args.putString("title", "Name");
					args.putString("message", "Name field is empty");
					dialog.setArguments(args);
					dialog.show(getFragmentManager(), "tag");
				}

				// Add your data

			}
		});
		return rootView;
	}

	private void populateWithExistingData() {
		
		
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

	}

}
