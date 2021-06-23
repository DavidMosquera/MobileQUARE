package android.mobilequare.analyst.view.fragments;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.mobilequare.analyst.model.po.ClassConcept;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.mobilequare.analyst.R;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.List;
public class ObjectListInsertFragment extends Fragment implements InsertFragmentInterface<ObjectListInsertFragment> {
	private String name;
	private View rootView;
	private TextView labelTextView, infoTextView, objectListTextView;
	private Spinner insertClassConceptListView;
	private List<ClassConcept> classConceptList;
	public ObjectListInsertFragment() {
		// Required empty public constructor
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_object_list_type_insert, container, false);
		ClassConcept fistItem = new ClassConcept();
		fistItem.set_Id("Select \"" + this.name + "\" ...");
		classConceptList.add(0, fistItem);
		insertClassConceptListView = rootView.findViewById(R.id.input_object_list_type_fragment);
		insertClassConceptListView.setAdapter(new ArrayAdapter<ClassConcept>(rootView.getContext(),
				android.R.layout.simple_spinner_dropdown_item, classConceptList));
		insertClassConceptListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (insertClassConceptListView.getSelectedItemPosition() == 0) {
					objectListTextView.setText("\n\"" + name + "\" info: \n Select a/an \"" + name + "\" ...\n");
				} else {
					objectListTextView.setText("\n\"" + name + "\" info: \n"
							+ ((ClassConcept) insertClassConceptListView.getSelectedItem()).listInfo() + "\n");
				}
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		labelTextView = rootView.findViewById(R.id.label_object_list_type_name_fragment);
		labelTextView.setText(name);
		infoTextView = rootView.findViewById(R.id.info_object_list_type_fragment);
		objectListTextView = rootView.findViewById(R.id.list_info_type_fragment);
		objectListTextView.setText("\n\"" + name + "\" info: \n Select a/an \"" + name + "\" ...\n");
		return rootView;
	}
	@Override
	public void newInfo(String info) {
		infoTextView.setText(info);
		infoTextView.setVisibility(View.VISIBLE);
	}
	@Override
	public String getValue() {
		return insertClassConceptListView.getSelectedItem().toString();
	}
	@Override
	public ClassConcept getClassConceptValue() {
		if (insertClassConceptListView.getSelectedItemPosition() == 0) {
			return null;
		} else {
			return (ClassConcept) insertClassConceptListView.getSelectedItem();
		}
	}
	@Override
	public ObjectListInsertFragment create(String name) {
		this.name = name;
		return this;
	}
	@Override
	public ObjectListInsertFragment create(String name, List<Object> objectList) {
		this.name = name;
		return this;
	}
	@Override
	public ObjectListInsertFragment create(List<ClassConcept> classConceptList, String name) {
		this.name = name;
		this.classConceptList = classConceptList;
		return this;
	}
	@Override
	public ObjectListInsertFragment create(String name, boolean value) {
		this.name = name;
		return this;
	}
}
