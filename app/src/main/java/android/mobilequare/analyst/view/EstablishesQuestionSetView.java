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
public class EstablishesQuestionSetView extends AppCompatActivity {
	private EstablishesQuestionSetController establishesQuestionSetController;
	private AnalystConfigurationController analystConfigurationController;
	private AlertDialog establishesAlert, helpAlert, establishesFailsAlert;
	private ObjectListInsertFragment projectListFragment;
	private TextTypeInsertFragment questionSetTitleInsertFragment;
	//OPERATIONS
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_establishes_questionset);
		analystConfigurationController = new AnalystConfigurationController(this);
		establishesQuestionSetController = new EstablishesQuestionSetController(this);
		if (!analystConfigurationController.getAnalystConfiguration().getLeftHandView()) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}
		setTitle("Establish QuestionSet");
		establishesAlert = new AlertDialog.Builder(this)
				.setMessage("Are you sure you want to establish this questionset?")
				.setPositiveButton("Establish", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						establishesQuestionSetConfirm();
					}
				}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						establishesQuestionSetCancel();
					}
				}).create();
		projectListFragment = new ObjectListInsertFragment().create(
				((List<ClassConcept>) (Object) establishesQuestionSetController.listsProject(this, "")), "Project");
		questionSetTitleInsertFragment = new TextTypeInsertFragment().create("Title *");
		LinearLayout fragmentContainer = (LinearLayout) findViewById(
				R.id.establishes_questionset_fragment_linear_layout);
		getSupportFragmentManager().beginTransaction().add(fragmentContainer.getId(), projectListFragment).commit();
		getSupportFragmentManager().beginTransaction().add(fragmentContainer.getId(), questionSetTitleInsertFragment)
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
	public void establishesQuestionSet(View view) {
		//"ANALYST ESTABLISHES QUESTIONSET" VIEW SPECIFICATION
		if (analystConfigurationController.getAnalystConfiguration().getEstablishesQuestionSetConfirmationMessage()) {
			establishesAlert.show();
		} else {
			establishesQuestionSetConfirm();
		}
	}
	public void establishesQuestionSetSucceeds() {
		//BEHAVIOR WHEN "ANALYST ESTABLISHES QUESTIONSET" SUCCEEDS
		finish();
		if (analystConfigurationController.getAnalystConfiguration().getEstablishesQuestionSetUndoRedoNotification()) {
			UndoAnalystEventNotification.getInstance(this).setCommand(establishesQuestionSetController);
			UndoAnalystEventNotification.getInstance(this).create("MobileQUARE", "QuestionSet successfully Established",
					this);
		}
	}
	public void closeEstablishesFailsAlert() {
		establishesFailsAlert.cancel();
	}
	public void establishesQuestionSetFails(String failMessage) {
		//BEHAVIOR WHEN "ANALYST ESTABLISHES QUESTIONSET" FAILS
		establishesFailsAlert = new AlertDialog.Builder(this).setMessage(failMessage)
				.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						closeEstablishesFailsAlert();
					}
				}).create();
		establishesFailsAlert.show();
	}
	public void questionSetTitleNewInfo(String info) {
		questionSetTitleInsertFragment.newInfo(info);
	}
	public void projectListNewInfo(String info) {
		projectListFragment.newInfo(info);
	}
	public void establishesQuestionSetConfirm() {
		establishesAlert.cancel();
		establishesQuestionSetController.establishesQuestionSet(this,
				((Project) projectListFragment.getClassConceptValue()), questionSetTitleInsertFragment.getValue());
	}
	public void establishesQuestionSetCancel() {
		establishesAlert.cancel();
	}
	public void back() {
		finish();
	}
	public void help() {
		helpAlert = new AlertDialog.Builder(this)
				.setMessage("In order to execute Analyst Establishes QuestionSet, follow the next steps: \n"
						+ "- Select a/an Project from the list. \n" + "- Insert a/an QuestionSet.Title (String). \n"
						+ "- Press the button Establish. \n" + "- The QuestionSet will be inserted. \n")
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