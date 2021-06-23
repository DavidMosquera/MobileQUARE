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
public class TextTypeInsertFragment extends Fragment implements InsertFragmentInterface<TextTypeInsertFragment> {
	private String name;
	private View rootView;
	private TextView insertTextView, labelTextView, infoTextView;
	public TextTypeInsertFragment() {
		// Required empty public constructor
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_text_type_insert, container, false);
		insertTextView = rootView.findViewById(R.id.input_text_type_fragment);
		insertTextView.setHint("Insert \"" + name + "\" here");
		labelTextView = rootView.findViewById(R.id.label_text_type_name_fragment);
		labelTextView.setText(name);
		infoTextView = rootView.findViewById(R.id.info_text_type_fragment);
		return rootView;
	}
	@Override
	public void newInfo(String info) {
		infoTextView.setText(info);
		infoTextView.setVisibility(View.VISIBLE);
	}
	@Override
	public String getValue() {
		return insertTextView.getText().toString();
	}
	@Override
	public ClassConcept getClassConceptValue() {
		return null;
	}
	@Override
	public TextTypeInsertFragment create(String name) {
		this.name = name;
		return this;
	}
	@Override
	public TextTypeInsertFragment create(String name, List<Object> objectList) {
		this.name = name;
		return this;
	}
	@Override
	public TextTypeInsertFragment create(List<ClassConcept> classConceptList, String name) {
		this.name = name;
		return this;
	}
	@Override
	public TextTypeInsertFragment create(String name, boolean value) {
		this.name = name;
		return this;
	}
}
