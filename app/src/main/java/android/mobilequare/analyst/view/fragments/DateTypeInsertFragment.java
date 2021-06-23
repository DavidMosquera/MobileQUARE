package android.mobilequare.analyst.view.fragments;
import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.mobilequare.analyst.model.po.ClassConcept;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.mobilequare.analyst.R;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Calendar;
import java.util.List;
public class DateTypeInsertFragment extends Fragment implements InsertFragmentInterface<DateTypeInsertFragment> {
	private String name;
	private View rootView;
	private TextView labelDateView, infoDateView;
	private EditText insertDateView;
	private Button removeButton;
	public DateTypeInsertFragment() {
		// Required empty public constructor
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_date_type_insert, container, false);
		insertDateView = rootView.findViewById(R.id.input_date_type_fragment);
		removeButton = rootView.findViewById(R.id.remove_date_input_type_button);
		removeButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				insertDateView.setText("");
			}
		});
		insertDateView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final Calendar calendar;
				calendar = Calendar.getInstance();
				int day = calendar.get(Calendar.DAY_OF_MONTH);
				int month = calendar.get(Calendar.MONTH);
				int year = calendar.get(Calendar.YEAR);
				DatePickerDialog picker = new DatePickerDialog(rootView.getContext(),
						new DatePickerDialog.OnDateSetListener() {
							@Override
							public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
								insertDateView.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
							}
						}, year, month, day);
				picker.show();
			}
		});
		insertDateView.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			@Override
			public void afterTextChanged(Editable s) {
				if (insertDateView.getText().toString().compareTo("") != 0) {
					removeButton.setVisibility(View.VISIBLE);
				} else {
					removeButton.setVisibility(View.INVISIBLE);
				}
			}
		});
		insertDateView.setHint("Insert \"" + name + "\" here (date)");
		labelDateView = rootView.findViewById(R.id.label_date_type_name_fragment);
		labelDateView.setText(name);
		infoDateView = rootView.findViewById(R.id.info_date_type_fragment);
		return rootView;
	}
	@Override
	public void newInfo(String info) {
		infoDateView.setText(info);
		infoDateView.setVisibility(View.VISIBLE);
	}
	@Override
	public String getValue() {
		return insertDateView.getText().toString();
	}

	@Override
	public ClassConcept getClassConceptValue() {
		return null;
	}

	@Override
	public DateTypeInsertFragment create(String name) {
		this.name = name;
		return this;
	}
	@Override
	public DateTypeInsertFragment create(String name, List<Object> objectList) {
		this.name = name;
		return this;
	}
	@Override
	public DateTypeInsertFragment create(List<ClassConcept> classConceptList, String name) {
		this.name = name;
		return this;
	}
	@Override
	public DateTypeInsertFragment create(String name, boolean value) {
		this.name = name;
		return this;
	}
}
