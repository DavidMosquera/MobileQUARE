package android.mobilequare.analyst.view;
import androidx.appcompat.app.AppCompatActivity;
import android.mobilequare.analyst.controller.*;
import android.content.DialogInterface;
import android.os.Bundle;
import android.mobilequare.analyst.R;
import android.mobilequare.analyst.model.po.*;
import android.mobilequare.analyst.view.fragments.*;
import android.mobilequare.analyst.notifications.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button; //$A
import android.widget.LinearLayout;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
public class ProvidesDiscourseView extends AppCompatActivity {
	private ProvidesDiscourseController providesDiscourseController;
	private AnalystConfigurationController analystConfigurationController;
	private AlertDialog providesAlert, helpAlert, providesFailsAlert;
	private ObjectListInsertFragment projectListFragment;
	private Button button; //$A
	private DocumentFragment discourseContentInsertFragment; //$E
	//OPERATIONS
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_provides_discourse);
		analystConfigurationController = new AnalystConfigurationController(this);
		providesDiscourseController = new ProvidesDiscourseController(this);
		button = findViewById(R.id.provides_discourse_button);
		if (!analystConfigurationController.getAnalystConfiguration().getLeftHandView()) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}
		setTitle("Provide Discourse");
		providesAlert = new AlertDialog.Builder(this).setMessage("Are you sure you want to provide this discourse?")
				.setPositiveButton("Provide", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						providesDiscourseConfirm();
					}
				}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						providesDiscourseCancel();
					}
				}).create();
		projectListFragment = new ObjectListInsertFragment()
				.create(((List<ClassConcept>) (java.lang.Object) providesDiscourseController.listsProject(this, "")), "Project");//$E
		discourseContentInsertFragment = DocumentFragment.newInstance("Content *"); //$E
		LinearLayout fragmentContainer = (LinearLayout) findViewById(R.id.provides_discourse_fragment_linear_layout);
		getSupportFragmentManager().beginTransaction().add(fragmentContainer.getId(), projectListFragment).commit();
		getSupportFragmentManager().beginTransaction().add(fragmentContainer.getId(), discourseContentInsertFragment)
				.commit();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.action_menu_layout, menu);
		return true;
	}
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		if (!analystConfigurationController.getAnalystConfiguration().getLeftHandView()) {
			menu.findItem(R.id.back_button).setVisible(false);
		}
		return super.onPrepareOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home :
			case R.id.back_button :
				finish();
				return true;
			case R.id.help_button :
				help();
				return true;
			default :
				return super.onOptionsItemSelected(item);
		}
	}
	public void providesDiscourse(View view) {
		//"ANALYST PROVIDES DISCOURSE" VIEW SPECIFICATION
		if (analystConfigurationController.getAnalystConfiguration().getProvidesDiscourseConfirmationMessage()) {
			providesAlert.show();
		} else {
			providesDiscourseConfirm();
		}
	}
	public void providesDiscourseSucceeds() {
		//BEHAVIOR WHEN "ANALYST PROVIDES DISCOURSE" SUCCEEDS
		finish();
		if (analystConfigurationController.getAnalystConfiguration().getProvidesDiscourseUndoRedoNotification()) {
			UndoAnalystEventNotification.getInstance(this).setCommand(providesDiscourseController);
			UndoAnalystEventNotification.getInstance(this).create("MobileQUARE", "Discourse successfully Provided",
					this);
		}
	}
	public void closeProvidesFailsAlert() {
		providesFailsAlert.cancel();
	}
	public void providesDiscourseFails(String failMessage) {
		//BEHAVIOR WHEN "ANALYST PROVIDES DISCOURSE" FAILS
		providesFailsAlert = new AlertDialog.Builder(this).setMessage(failMessage)
				.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						closeProvidesFailsAlert();
					}
				}).create();
		providesFailsAlert.show();
	}
	public void discourseContentNewInfo(String info) {
		discourseContentInsertFragment.newInfo(info);
	}
	public void projectListNewInfo(String info) {
		projectListFragment.newInfo(info);
	}
	public void providesDiscourseConfirm() {
		providesAlert.cancel();
		providesDiscourseController.providesDiscourse(this, ((Project) projectListFragment.getClassConceptValue()),
				discourseContentInsertFragment.getValue());
	}
	public void providesDiscourseCancel() {
		providesAlert.cancel();
	}
	public void back() {
		finish();
	}
	public void help() {
		helpAlert = new AlertDialog.Builder(this)
				.setMessage("In order to execute Analyst Provides Discourse, follow the next steps: \n"
						+ "- Select a/an Project from the list. \n" + "- Insert a/an Discourse.Content (PDF file). \n" //$E
						+ "- Press the button Provide. \n"  + "- The Discourse will be inserted. \n")
				//$D"- The Actor will be inserted. \n"
				//$D+ "- The Object will be inserted. \n" + "- The Function will be inserted. \n"
				//$D+ "- The ContainerConcept will be inserted. \n" + "- The Attribute will be inserted. \n"
				//$D+ "- The AttributeRelationship will be inserted. \n"
				.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						showHelpAlertOkay();
					}
				}).create();
		helpAlert.show();
	}
	public void deactivateButton(){button.setEnabled(false);} //$A
	public void activateButton(){button.setEnabled(true);} //$A
	public void showHelpAlertOkay() {
		helpAlert.cancel();
	}
}