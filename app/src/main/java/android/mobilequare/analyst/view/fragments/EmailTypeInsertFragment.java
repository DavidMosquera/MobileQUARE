package android.mobilequare.analyst.view.fragments;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.mobilequare.analyst.model.po.ClassConcept;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.mobilequare.analyst.R;
import android.widget.TextView;
import java.util.List;
public class EmailTypeInsertFragment extends Fragment implements InsertFragmentInterface<EmailTypeInsertFragment> {
	private String name;
	private View rootView;
	private TextView insertEmailView, labelTextView, infoTextView;
	public EmailTypeInsertFragment() {
		// Required empty public constructor
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_email_type_insert, container, false);
		insertEmailView = rootView.findViewById(R.id.input_email_type_fragment);
		insertEmailView.setHint("Insert \"" + name + "\" here (email)");
		labelTextView = rootView.findViewById(R.id.label_email_type_name_fragment);
		labelTextView.setText(name);
		infoTextView = rootView.findViewById(R.id.info_email_type_fragment);
		return rootView;
	}
	@Override
	public void newInfo(String info) {
		infoTextView.setText(info);
		infoTextView.setVisibility(View.VISIBLE);
	}
	@Override
	public String getValue() {
		return insertEmailView.getText().toString();
	}

	@Override
	public ClassConcept getClassConceptValue() {
		return null;
	}

	@Override
	public EmailTypeInsertFragment create(String name) {
		this.name = name;
		return this;
	}
	@Override
	public EmailTypeInsertFragment create(String name, List<Object> objectList) {
		this.name = name;
		return this;
	}
	@Override
	public EmailTypeInsertFragment create(List<ClassConcept> classConceptList, String name) {
		this.name = name;
		return this;
	}
	@Override
	public EmailTypeInsertFragment create(String name, boolean value) {
		this.name = name;
		return this;
	}
}
