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
public class LabelFragment extends Fragment implements InsertFragmentInterface<LabelFragment> {
	private String name;
	private View rootView;
	private TextView labelTextView;
	public LabelFragment() {
		// Required empty public constructor
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_label, container, false);
		labelTextView = rootView.findViewById(R.id.label_label_type_name_fragment);
		labelTextView.setText(name);
		return rootView;
	}
	@Override
	public void newInfo(String info) {
	}
	@Override
	public String getValue() {
		return "";
	}
	@Override
	public ClassConcept getClassConceptValue() {
		return null;
	}
	@Override
	public LabelFragment create(String name) {
		this.name = name;
		return this;
	}
	@Override
	public LabelFragment create(String name, List<Object> objectList) {
		this.name = name;
		return this;
	}
	@Override
	public LabelFragment create(List<ClassConcept> classConceptList, String name) {
		this.name = name;
		return this;
	}
	@Override
	public LabelFragment create(String name, boolean value) {
		this.name = name;
		return this;
	}
}