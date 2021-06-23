package android.mobilequare.analyst.view.fragments;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.mobilequare.analyst.model.po.ClassConcept;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.mobilequare.analyst.R;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
public class PossibleValueListInsertFragment extends Fragment
		implements
			InsertFragmentInterface<PossibleValueListInsertFragment> {
	private String name;
	private View rootView;
	private TextView labelTextView, infoTextView;
	private Spinner insertPossibleValueListView;
	private List<Object> objectList;
	private int selectedIndex = -1;
	public PossibleValueListInsertFragment() {
		// Required empty public constructor
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_possible_value_list_insert, container, false);
		insertPossibleValueListView = rootView.findViewById(R.id.input_possible_value_list_type_fragment);
		insertPossibleValueListView.setAdapter(
				new ArrayAdapter<>(rootView.getContext(), android.R.layout.simple_spinner_dropdown_item, objectList));
		if (this.selectedIndex != -1) {
			insertPossibleValueListView.setSelection(this.selectedIndex);
		}
		labelTextView = rootView.findViewById(R.id.label_possible_value_list_type_name_fragment);
		labelTextView.setText(name);
		infoTextView = rootView.findViewById(R.id.info_possible_value_list_type_fragment);
		return rootView;
	}
	@Override
	public void newInfo(String info) {
		infoTextView.setText(info);
		infoTextView.setVisibility(View.VISIBLE);
	}
	@Override
	public String getValue() {
		return insertPossibleValueListView.getSelectedItem().toString();
	}
	@Override
	public ClassConcept getClassConceptValue() {
		return null;
	}
	@Override
	public PossibleValueListInsertFragment create(String name) {
		this.name = name;
		this.objectList = new ArrayList<>();
		return this;
	}
	@Override
	public PossibleValueListInsertFragment create(String name, List<Object> objectList) {
		this.name = name;
		this.objectList = objectList;
		return this;
	}
	@Override
	public PossibleValueListInsertFragment create(List<ClassConcept> classConceptList, String name) {
		this.name = name;
		this.objectList = new ArrayList<>();
		return this;
	}
	@Override
	public PossibleValueListInsertFragment create(String name, boolean value) {
		this.name = name;
		return this;
	}
	public PossibleValueListInsertFragment create(String name, List<Object> objectList, String selectedValue) {
		this.name = name;
		this.objectList = objectList;
		for (int i = 0; i < objectList.size(); i++) {
			if (objectList.get(i).toString().compareTo(selectedValue) == 0) {
				this.selectedIndex = i;
				break;
			}
		}
		return this;
	}
}
