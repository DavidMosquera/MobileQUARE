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
import android.widget.LinearLayout;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
public class CreatesProjectView extends AppCompatActivity {
	private CreatesProjectController createsProjectController;
	private AnalystConfigurationController analystConfigurationController;
	private AlertDialog createsAlert, helpAlert, createsFailsAlert;
	private TextTypeInsertFragment projectNameInsertFragment;
	private TextTypeInsertFragment projectDescriptionInsertFragment;
	//OPERATIONS
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_creates_project);
		analystConfigurationController = new AnalystConfigurationController(this);
		createsProjectController = new CreatesProjectController(this);
		if (!analystConfigurationController.getAnalystConfiguration().getLeftHandView()) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}
		setTitle("Create Project");
		createsAlert = new AlertDialog.Builder(this).setMessage("Are you sure you want to create this project?")
				.setPositiveButton("Create", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						createsProjectConfirm();
					}
				}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						createsProjectCancel();
					}
				}).create();
		projectNameInsertFragment = new TextTypeInsertFragment().create("Name *");
		projectDescriptionInsertFragment = new TextTypeInsertFragment().create("Description *");
		LinearLayout fragmentContainer = (LinearLayout) findViewById(R.id.creates_project_fragment_linear_layout);
		getSupportFragmentManager().beginTransaction().add(fragmentContainer.getId(), projectNameInsertFragment)
				.commit();
		getSupportFragmentManager().beginTransaction().add(fragmentContainer.getId(), projectDescriptionInsertFragment)
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
	public void createsProject(View view) {
		//"ANALYST CREATES PROJECT" VIEW SPECIFICATION
		if (analystConfigurationController.getAnalystConfiguration().getCreatesProjectConfirmationMessage()) {
			createsAlert.show();
		} else {
			createsProjectConfirm();
		}
	}
	public void createsProjectSucceeds() {
		//BEHAVIOR WHEN "ANALYST CREATES PROJECT" SUCCEEDS
		finish();
		if (analystConfigurationController.getAnalystConfiguration().getCreatesProjectUndoRedoNotification()) {
			UndoAnalystEventNotification.getInstance(this).setCommand(createsProjectController);
			UndoAnalystEventNotification.getInstance(this).create("MobileQUARE", "Project successfully Created", this);
		}
	}
	public void closeCreatesFailsAlert() {
		createsFailsAlert.cancel();
	}
	public void createsProjectFails(String failMessage) {
		//BEHAVIOR WHEN "ANALYST CREATES PROJECT" FAILS
		createsFailsAlert = new AlertDialog.Builder(this).setMessage(failMessage)
				.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						closeCreatesFailsAlert();
					}
				}).create();
		createsFailsAlert.show();
	}
	public void projectDescriptionNewInfo(String info) {
		projectDescriptionInsertFragment.newInfo(info);
	}
	public void projectNameNewInfo(String info) {
		projectNameInsertFragment.newInfo(info);
	}
	public void createsProjectConfirm() {
		createsAlert.cancel();
		createsProjectController.createsProject(this, projectNameInsertFragment.getValue(),
				projectDescriptionInsertFragment.getValue());
	}
	public void createsProjectCancel() {
		createsAlert.cancel();
	}
	public void back() {
		finish();
	}
	public void help() {
		helpAlert = new AlertDialog.Builder(this)
				.setMessage("In order to execute Analyst Creates Project, follow the next steps: \n"
						+ "- Insert a/an Project.Name (String). \n" + "- Insert a/an Project.Description (String). \n"
						+ "- Press the button Create. \n" + "- The Project will be inserted. \n")
				.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						showHelpAlertOkay();
					}
				}).create();
		helpAlert.show();
	}
	public void showHelpAlertOkay() {
		helpAlert.cancel();
	}
}