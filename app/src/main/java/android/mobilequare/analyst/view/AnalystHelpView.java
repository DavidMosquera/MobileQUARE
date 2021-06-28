package android.mobilequare.analyst.view;
import android.content.DialogInterface;
import android.os.Bundle;
import android.mobilequare.analyst.R;
import android.mobilequare.analyst.controller.AnalystConfigurationController;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
public class AnalystHelpView extends AppCompatActivity {
	private AnalystConfigurationController analystConfigurationController;
	private AlertDialog helpAlert;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_analyst_help);
		analystConfigurationController = new AnalystConfigurationController(this);
		if (!analystConfigurationController.getAnalystConfiguration().getLeftHandView()) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}
		setTitle("Help");
	}
	public void showHelpAlertOkay() {
		helpAlert.cancel();
	}
	public void showHelpAlert(String helpText) {
		helpAlert = new AlertDialog.Builder(this).setMessage(helpText)
				.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						showHelpAlertOkay();
					}
				}).create();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.configuration_help_menu_layout, menu);
		return true;
	}
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		if (!analystConfigurationController.getAnalystConfiguration().getLeftHandView()) {
			menu.findItem(R.id.configuration_back_button).setVisible(false);
		}
		return super.onPrepareOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home :
			case R.id.configuration_back_button :
				back();
				return true;
			default :
				return super.onOptionsItemSelected(item);
		}
	}
	public void back() {
		finish();
	}
	public void analystCreatesProjectAlert(View view) {
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
	public void analystEstablishesQuestionSetAlert(View view) {
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
	public void analystProvidesDiscourseAlert(View view) {
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
	public void analystAsksQuestionAlert(View view) {
		helpAlert = new AlertDialog.Builder(this)
				.setMessage("In order to execute Analyst Asks Question, follow the next steps: \n"
						+ "- Select a/an QuestionSet from the list. \n"
						+ "- Press the button Ask. \n" //$E
						+ "- A project will be shown in screen. \n" //$A
						+ "- Press the button Ask below the project. \n"//$A
						+ "- Select the question that you like to ask. \n"//$A
						+ "- Press the button Ask at in the alert dialog. \n"//$A
						+ "- A set of concepts, actors, or functions will be shown with an Ask button below. \n" //$A
						+ "- Continue asking pressing the Ask button. \n" //$A
						+ "- The questions will be inserted. \n") //$E
				.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						showHelpAlertOkay();
					}
				}).create();
		helpAlert.show();
	}
}