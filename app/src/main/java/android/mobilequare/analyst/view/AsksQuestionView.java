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
public class AsksQuestionView extends AppCompatActivity {
	private AsksQuestionController asksQuestionController;
	private AnalystConfigurationController analystConfigurationController;
	private AlertDialog asksAlert, helpAlert, asksFailsAlert;
	private ObjectListInsertFragment questionSetListFragment;
	private PossibleValueListInsertFragment questionTypeInsertFragment;
	//OPERATIONS
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_asks_question);
		analystConfigurationController = new AnalystConfigurationController(this);
		asksQuestionController = new AsksQuestionController(this);
		if (!analystConfigurationController.getAnalystConfiguration().getLeftHandView()) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}
		setTitle("Ask Question");
		asksAlert = new AlertDialog.Builder(this).setMessage("Are you sure you want to ask this question?")
				.setPositiveButton("Ask", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						asksQuestionConfirm();
					}
				}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						asksQuestionCancel();
					}
				}).create();
		questionSetListFragment = new ObjectListInsertFragment().create(
				((List<ClassConcept>) (Object) asksQuestionController.listsQuestionSet(this, "")), "QuestionSet");
		questionTypeInsertFragment = new PossibleValueListInsertFragment().create("Type *",
				new ArrayList<>(Arrays.asList("Which are the project's discourse actors? ",
						"Which are the project's discourse concepts? ", "Which are the X's functions? ",
						"Which are the X's attributes? ")));
		LinearLayout fragmentContainer = (LinearLayout) findViewById(R.id.asks_question_fragment_linear_layout);
		getSupportFragmentManager().beginTransaction().add(fragmentContainer.getId(), questionSetListFragment).commit();
		getSupportFragmentManager().beginTransaction().add(fragmentContainer.getId(), questionTypeInsertFragment)
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
	public void asksQuestion(View view) {
		//"ANALYST ASKS QUESTION" VIEW SPECIFICATION
		if (analystConfigurationController.getAnalystConfiguration().getAsksQuestionConfirmationMessage()) {
			asksAlert.show();
		} else {
			asksQuestionConfirm();
		}
	}
	public void asksQuestionSucceeds() {
		//BEHAVIOR WHEN "ANALYST ASKS QUESTION" SUCCEEDS
		finish();
		if (analystConfigurationController.getAnalystConfiguration().getAsksQuestionUndoRedoNotification()) {
			UndoAnalystEventNotification.getInstance(this).setCommand(asksQuestionController);
			UndoAnalystEventNotification.getInstance(this).create("MobileQUARE", "Question successfully Asked", this);
		}
	}
	public void closeAsksFailsAlert() {
		asksFailsAlert.cancel();
	}
	public void asksQuestionFails(String failMessage) {
		//BEHAVIOR WHEN "ANALYST ASKS QUESTION" FAILS
		asksFailsAlert = new AlertDialog.Builder(this).setMessage(failMessage)
				.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						closeAsksFailsAlert();
					}
				}).create();
		asksFailsAlert.show();
	}
	public void questionTypeNewInfo(String info) {
		questionTypeInsertFragment.newInfo(info);
	}
	public void questionSetListNewInfo(String info) {
		questionSetListFragment.newInfo(info);
	}
	public void asksQuestionConfirm() {
		asksAlert.cancel();
		asksQuestionController.asksQuestion(this, ((QuestionSet) questionSetListFragment.getClassConceptValue()),
				questionTypeInsertFragment.getValue());
	}
	public void asksQuestionCancel() {
		asksAlert.cancel();
	}
	public void back() {
		finish();
	}
	public void help() {
		helpAlert = new AlertDialog.Builder(this)
				.setMessage("In order to execute Analyst Asks Question, follow the next steps: \n"
						+ "- Select a/an QuestionSet from the list. \n"
						+ "- Select a/an Question.Type from the list. \n" + "- Press the button Ask. \n"
						+ "- The Question will be inserted. \n" + "- The QuestionSet will be edited. \n")
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