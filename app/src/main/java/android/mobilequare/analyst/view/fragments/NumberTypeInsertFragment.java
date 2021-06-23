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
public class NumberTypeInsertFragment extends Fragment implements InsertFragmentInterface<NumberTypeInsertFragment> {
	private String name;
	private View rootView;
	private TextView insertNumberView, labelTextView, infoTextView;
	public NumberTypeInsertFragment() {
		// Required empty public constructor
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_number_type_insert, container, false);
		insertNumberView = rootView.findViewById(R.id.input_number_type_fragment);
		insertNumberView.setHint("Insert \"" + name + "\" here (number)");
		labelTextView = rootView.findViewById(R.id.label_number_type_name_fragment);
		labelTextView.setText(name);
		infoTextView = rootView.findViewById(R.id.info_number_type_fragment);
		return rootView;
	}
	@Override
	public void newInfo(String info) {
		infoTextView.setText(info);
		infoTextView.setVisibility(View.VISIBLE);
	}
	@Override
	public String getValue() {
		return insertNumberView.getText().toString();
	}
	@Override
	public ClassConcept getClassConceptValue() {
		return null;
	}

	@Override
	public NumberTypeInsertFragment create(String name) {
		this.name = name;
		return this;
	}
	@Override
	public NumberTypeInsertFragment create(String name, List<Object> objectList) {
		this.name = name;
		return this;
	}
	@Override
	public NumberTypeInsertFragment create(List<ClassConcept> classConceptList, String name) {
		this.name = name;
		return this;
	}
	@Override
	public NumberTypeInsertFragment create(String name, boolean value) {
		this.name = name;
		return this;
	}
}
