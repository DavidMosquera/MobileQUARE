package android.mobilequare.analyst.view;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.mobilequare.analyst.R;
import android.mobilequare.analyst.controller.*;
import android.mobilequare.analyst.view.fragments.*;
import android.mobilequare.analyst.notifications.*;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.Arrays;
public class AnalystConfigurationView extends AppCompatActivity {
	private AnalystConfigurationController analystConfigurationController;
	private AlertDialog applyAlert, restoreAlert, applyFailsAlert;
	private BooleanTypeInsertFragment analystCreatesProjectConfirmationMessageFragment;
	private BooleanTypeInsertFragment analystEstablishesQuestionSetConfirmationMessageFragment;
	private BooleanTypeInsertFragment analystProvidesDiscourseConfirmationMessageFragment;
	private BooleanTypeInsertFragment analystAsksQuestionConfirmationMessageFragment;
	private BooleanTypeInsertFragment analystCreatesProjectUndoRedoNotificationFragment;
	private BooleanTypeInsertFragment analystEstablishesQuestionSetUndoRedoNotificationFragment;
	private BooleanTypeInsertFragment analystProvidesDiscourseUndoRedoNotificationFragment;
	private BooleanTypeInsertFragment analystAsksQuestionUndoRedoNotificationFragment;
	private BooleanTypeInsertFragment leftHandViewFragment;
	private PossibleValueListInsertFragment notificationTimeFragment;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_analyst_configuration);
		analystConfigurationController = new AnalystConfigurationController(this);
		if (!analystConfigurationController.getAnalystConfiguration().getLeftHandView()) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}
		setTitle("Configuration");
		applyAlert = new AlertDialog.Builder(this).setMessage("Are you sure you want to apply these configurations?")
				.setPositiveButton("Apply", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						applyConfirm();
					}
				}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						applyCancel();
					}
				}).create();
		restoreAlert = new AlertDialog.Builder(this)
				.setMessage("Are you sure you want to restore these configurations?")
				.setPositiveButton("Restore", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						restoreConfirm();
					}
				}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						restoreCancel();
					}
				}).create();
		analystCreatesProjectConfirmationMessageFragment = new BooleanTypeInsertFragment().create("Create Project",
				analystConfigurationController.getAnalystConfiguration().getCreatesProjectConfirmationMessage());
		analystEstablishesQuestionSetConfirmationMessageFragment = new BooleanTypeInsertFragment()
				.create("Establish QuestionSet", analystConfigurationController.getAnalystConfiguration()
						.getEstablishesQuestionSetConfirmationMessage());
		analystProvidesDiscourseConfirmationMessageFragment = new BooleanTypeInsertFragment().create(
				"Provide Discourse",
				analystConfigurationController.getAnalystConfiguration().getProvidesDiscourseConfirmationMessage());
		analystAsksQuestionConfirmationMessageFragment = new BooleanTypeInsertFragment().create("Ask Question",
				analystConfigurationController.getAnalystConfiguration().getAsksQuestionConfirmationMessage());
		analystCreatesProjectUndoRedoNotificationFragment = new BooleanTypeInsertFragment().create("Create Project",
				analystConfigurationController.getAnalystConfiguration().getCreatesProjectUndoRedoNotification());
		analystEstablishesQuestionSetUndoRedoNotificationFragment = new BooleanTypeInsertFragment()
				.create("Establish QuestionSet", analystConfigurationController.getAnalystConfiguration()
						.getEstablishesQuestionSetUndoRedoNotification());
		analystProvidesDiscourseUndoRedoNotificationFragment = new BooleanTypeInsertFragment().create(
				"Provide Discourse",
				analystConfigurationController.getAnalystConfiguration().getProvidesDiscourseUndoRedoNotification());
		analystAsksQuestionUndoRedoNotificationFragment = new BooleanTypeInsertFragment().create("Ask Question",
				analystConfigurationController.getAnalystConfiguration().getAsksQuestionUndoRedoNotification());
		leftHandViewFragment = new BooleanTypeInsertFragment().create("Left Hand View",
				analystConfigurationController.getAnalystConfiguration().getLeftHandView());
		notificationTimeFragment = new PossibleValueListInsertFragment().create("Notification Time (sec)",
				new ArrayList<>(Arrays.asList("5.0", "10.0", "15.0", "20.0")),
				String.valueOf(analystConfigurationController.getAnalystConfiguration().getNotificationTime()));
		LinearLayout fragmentContainer = (LinearLayout) findViewById(R.id.configuration_fragment_linear_layout);
		getSupportFragmentManager().beginTransaction()
				.add(fragmentContainer.getId(), new LabelFragment().create("Confirmation Messages")).commit();
		getSupportFragmentManager().beginTransaction()
				.add(fragmentContainer.getId(), analystCreatesProjectConfirmationMessageFragment).commit();
		getSupportFragmentManager().beginTransaction()
				.add(fragmentContainer.getId(), analystEstablishesQuestionSetConfirmationMessageFragment).commit();
		getSupportFragmentManager().beginTransaction()
				.add(fragmentContainer.getId(), analystProvidesDiscourseConfirmationMessageFragment).commit();
		//$D getSupportFragmentManager().beginTransaction()
		//$D .add(fragmentContainer.getId(), analystAsksQuestionConfirmationMessageFragment).commit();
		getSupportFragmentManager().beginTransaction()
				.add(fragmentContainer.getId(), new LabelFragment().create("Undo/Redo Notifications")).commit();
		getSupportFragmentManager().beginTransaction()
				.add(fragmentContainer.getId(), analystCreatesProjectUndoRedoNotificationFragment).commit();
		getSupportFragmentManager().beginTransaction()
				.add(fragmentContainer.getId(), analystEstablishesQuestionSetUndoRedoNotificationFragment).commit();
		getSupportFragmentManager().beginTransaction()
				.add(fragmentContainer.getId(), analystProvidesDiscourseUndoRedoNotificationFragment).commit();
		//$D getSupportFragmentManager().beginTransaction()
		//$D .add(fragmentContainer.getId(), analystAsksQuestionUndoRedoNotificationFragment).commit();
		getSupportFragmentManager().beginTransaction()
				.add(fragmentContainer.getId(), new LabelFragment().create("Other Configurations")).commit();
		getSupportFragmentManager().beginTransaction().add(fragmentContainer.getId(), leftHandViewFragment).commit();
		getSupportFragmentManager().beginTransaction().add(fragmentContainer.getId(), notificationTimeFragment)
				.commit();
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
	public void restore(View view) {
		restoreAlert.show();
	}
	public void apply(View view) {
		applyAlert.show();
	}
	public void applySucceeds() {
		finish();
		UndoAnalystEventNotification.getInstance(this).setCommand(analystConfigurationController);
		UndoAnalystEventNotification.getInstance(this).create("MobileQUARE", "Configurations successfully applied",
				this);
	}
	public void applyFailsOkay() {
		applyFailsAlert.cancel();
	}
	public void applyFails(String failMessage) {
		applyAlert.cancel();
		applyFailsAlert = new AlertDialog.Builder(this).setMessage(failMessage)
				.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						applyFailsOkay();
					}
				}).create();
		applyFailsAlert.show();
	}
	public void restoreConfirm() {
		restoreAlert.cancel();
		finish();
		startActivity(new Intent(this, AnalystConfigurationView.class));
	}
	public void applyConfirm() {
		applyAlert.cancel();
		analystConfigurationController.apply(this, analystCreatesProjectConfirmationMessageFragment.getValue(),
				analystEstablishesQuestionSetConfirmationMessageFragment.getValue(),
				analystProvidesDiscourseConfirmationMessageFragment.getValue(),
				analystAsksQuestionConfirmationMessageFragment.getValue(), notificationTimeFragment.getValue(),
				analystCreatesProjectUndoRedoNotificationFragment.getValue(),
				analystEstablishesQuestionSetUndoRedoNotificationFragment.getValue(),
				analystProvidesDiscourseUndoRedoNotificationFragment.getValue(),
				analystAsksQuestionUndoRedoNotificationFragment.getValue(), leftHandViewFragment.getValue());
	}
	public void restoreCancel() {
		restoreAlert.cancel();
	}
	public void applyCancel() {
		applyAlert.cancel();
	}
}