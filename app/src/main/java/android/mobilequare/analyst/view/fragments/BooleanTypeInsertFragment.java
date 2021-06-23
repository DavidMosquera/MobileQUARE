package android.mobilequare.analyst.view.fragments;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.mobilequare.analyst.model.po.ClassConcept;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.mobilequare.analyst.R;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.google.android.material.switchmaterial.SwitchMaterial;
import java.util.List;
public class BooleanTypeInsertFragment extends Fragment implements InsertFragmentInterface<BooleanTypeInsertFragment> {
	private String name;
	private View rootView;
	private TextView labelTextView, infoTextView;
	private SwitchMaterial insertBooleanView;
	private String booleanValue;
	private boolean value;
	public BooleanTypeInsertFragment() {
		// Required empty public constructor
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_boolean_type_insert, container, false);
		insertBooleanView = (SwitchMaterial) rootView.findViewById(R.id.input_boolean_type_fragment);
		booleanValue = String.valueOf(this.value);
		insertBooleanView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					booleanValue = "true";
				} else {
					booleanValue = "false";
				}
			}
		});
		insertBooleanView.setChecked(this.value);
		labelTextView = (TextView) rootView.findViewById(R.id.label_boolean_type_name_fragment);
		labelTextView.setText(name);
		infoTextView = (TextView) rootView.findViewById(R.id.info_boolean_type_fragment);
		return rootView;
	}
	@Override
	public void newInfo(String info) {
		infoTextView.setText(info);
		infoTextView.setVisibility(View.VISIBLE);
	}
	@Override
	public String getValue() {
		return booleanValue;
	}

	@Override
	public ClassConcept getClassConceptValue() {
		return null;
	}

	@Override
	public BooleanTypeInsertFragment create(String name) {
		this.name = name;
		return this;
	}
	@Override
	public BooleanTypeInsertFragment create(String name, List<Object> objectList) {
		this.name = name;
		return this;
	}
	@Override
	public BooleanTypeInsertFragment create(List<ClassConcept> classConceptList, String name) {
		this.name = name;
		return this;
	}
	@Override
	public BooleanTypeInsertFragment create(String name, boolean value) {
		this.name = name;
		this.value = value;
		return this;
	}
}