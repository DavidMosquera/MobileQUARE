package android.mobilequare.analyst.controller;
import android.mobilequare.analyst.command.Command;
import android.content.Context;

import java.util.List;

import android.mobilequare.analyst.exception.*;
import android.mobilequare.analyst.view.AnalystConfigurationView;
import android.mobilequare.analyst.model.dao.*;
import android.mobilequare.analyst.model.factory.*;
import android.mobilequare.analyst.model.po.*;
import android.mobilequare.analyst.model.converters.*;
public class AnalystConfigurationController implements Command {
	//PLAIN OBJECTS
	private AnalystConfiguration analystConfiguration;
	//DATA ACCESS OBJECTS (DAO)
	private DAOAnalystConfiguration daoAnalystConfiguration;
	//CONSTRUCTOR
	public AnalystConfigurationController(Context context) {
		super();
		this.daoAnalystConfiguration = new LocalStorageFactory(context).getDAOAnalystConfiguration();
	}
	//OPERATIONS
	public void apply(AnalystConfigurationView analystConfigurationView,
			String viewTextAnalystCreatesProjectConfirmationMessage,
			String viewTextAnalystEstablishesQuestionSetConfirmationMessage,
			String viewTextAnalystProvidesDiscourseConfirmationMessage,
			String viewTextAnalystAsksQuestionConfirmationMessage, String viewTextNotificationTime,
			String viewTextAnalystCreatesProjectUndoRedoNotification,
			String viewTextAnalystEstablishesQuestionSetUndoRedoNotification,
			String viewTextAnalystProvidesDiscourseUndoRedoNotification,
			String viewTextAnalystAsksQuestionUndoRedoNotification, String viewTextLeftHandView) {

		//VIEW AnalystCreatesProject CONFIRMATION MESSAGE AND UNDO/REDO NOTIFICATION INITIALIZATION
		boolean viewAnalystCreatesProjectConfirmationMessage = false;
		try {
			viewAnalystCreatesProjectConfirmationMessage = TextToBooleanConverter
					.textToBoolean(viewTextAnalystCreatesProjectConfirmationMessage);
		} catch (Exception e) {
			analystConfigurationView.applyFails(e.getMessage());
		}
		boolean viewAnalystCreatesProjectUndoRedoNotification = false;
		try {
			viewAnalystCreatesProjectUndoRedoNotification = TextToBooleanConverter
					.textToBoolean(viewTextAnalystCreatesProjectUndoRedoNotification);
		} catch (Exception e) {
			analystConfigurationView.applyFails(e.getMessage());
		}
		//VIEW AnalystEstablishesQuestionSet CONFIRMATION MESSAGE AND UNDO/REDO NOTIFICATION INITIALIZATION
		boolean viewAnalystEstablishesQuestionSetConfirmationMessage = false;
		try {
			viewAnalystEstablishesQuestionSetConfirmationMessage = TextToBooleanConverter
					.textToBoolean(viewTextAnalystEstablishesQuestionSetConfirmationMessage);
		} catch (Exception e) {
			analystConfigurationView.applyFails(e.getMessage());
		}
		boolean viewAnalystEstablishesQuestionSetUndoRedoNotification = false;
		try {
			viewAnalystEstablishesQuestionSetUndoRedoNotification = TextToBooleanConverter
					.textToBoolean(viewTextAnalystEstablishesQuestionSetUndoRedoNotification);
		} catch (Exception e) {
			analystConfigurationView.applyFails(e.getMessage());
		}
		//VIEW AnalystProvidesDiscourse CONFIRMATION MESSAGE AND UNDO/REDO NOTIFICATION INITIALIZATION
		boolean viewAnalystProvidesDiscourseConfirmationMessage = false;
		try {
			viewAnalystProvidesDiscourseConfirmationMessage = TextToBooleanConverter
					.textToBoolean(viewTextAnalystProvidesDiscourseConfirmationMessage);
		} catch (Exception e) {
			analystConfigurationView.applyFails(e.getMessage());
		}
		boolean viewAnalystProvidesDiscourseUndoRedoNotification = false;
		try {
			viewAnalystProvidesDiscourseUndoRedoNotification = TextToBooleanConverter
					.textToBoolean(viewTextAnalystProvidesDiscourseUndoRedoNotification);
		} catch (Exception e) {
			analystConfigurationView.applyFails(e.getMessage());
		}
		//VIEW AnalystAsksQuestion CONFIRMATION MESSAGE AND UNDO/REDO NOTIFICATION INITIALIZATION
		boolean viewAnalystAsksQuestionConfirmationMessage = false;
		try {
			viewAnalystAsksQuestionConfirmationMessage = TextToBooleanConverter
					.textToBoolean(viewTextAnalystAsksQuestionConfirmationMessage);
		} catch (Exception e) {
			analystConfigurationView.applyFails(e.getMessage());
		}
		boolean viewAnalystAsksQuestionUndoRedoNotification = false;
		try {
			viewAnalystAsksQuestionUndoRedoNotification = TextToBooleanConverter
					.textToBoolean(viewTextAnalystAsksQuestionUndoRedoNotification);
		} catch (Exception e) {
			analystConfigurationView.applyFails(e.getMessage());
		}
		//VIEW NOTIFICATION TIME
		double viewNotificationTime = 10.0;
		try {
			viewNotificationTime = TextToDoubleConverter.textToDouble(viewTextNotificationTime);
		} catch (NumberFormatException e) {
			analystConfigurationView.applyFails(e.getMessage());
		}
		//VIEW LEFT HAND VIEW
		boolean viewLeftHandView = false;
		try {
			viewLeftHandView = TextToBooleanConverter.textToBoolean(viewTextLeftHandView);
		} catch (Exception e) {
			analystConfigurationView.applyFails(e.getMessage());
		}
		try {
			analystConfiguration = daoAnalystConfiguration.listAnalystConfiguration("_id = \"" + 1 + "\"").get(0);
			analystConfiguration.setCreatesProjectConfirmationMessage(viewAnalystCreatesProjectConfirmationMessage);
			analystConfiguration
					.setEstablishesQuestionSetConfirmationMessage(viewAnalystEstablishesQuestionSetConfirmationMessage);
			analystConfiguration
					.setProvidesDiscourseConfirmationMessage(viewAnalystProvidesDiscourseConfirmationMessage);
			analystConfiguration.setAsksQuestionConfirmationMessage(viewAnalystAsksQuestionConfirmationMessage);
			analystConfiguration.setCreatesProjectUndoRedoNotification(viewAnalystCreatesProjectUndoRedoNotification);
			analystConfiguration.setEstablishesQuestionSetUndoRedoNotification(
					viewAnalystEstablishesQuestionSetUndoRedoNotification);
			analystConfiguration
					.setProvidesDiscourseUndoRedoNotification(viewAnalystProvidesDiscourseUndoRedoNotification);
			analystConfiguration.setAsksQuestionUndoRedoNotification(viewAnalystAsksQuestionUndoRedoNotification);
			analystConfiguration.setNotificationTime(viewNotificationTime);
			analystConfiguration.setLeftHandView(viewLeftHandView);
			daoAnalystConfiguration.editAnalystConfiguration(analystConfiguration, false);
			analystConfigurationView.applySucceeds();
		} catch (ConstraintCheckingException e) {
			try {
				this.undo();
			} catch (Exception e1) {
			}
			analystConfigurationView.applyFails(e.getMessage());
		} catch (StorageException e) {
			try {
				this.undo();
			} catch (Exception e1) {
			}
			analystConfigurationView.applyFails(e.getMessage());
		}
	}
	public void undo() throws Exception {
		for (AnalystConfiguration analystConfiguration : daoAnalystConfiguration.getAnalystConfigurationDeletedList()) {
			daoAnalystConfiguration.insertAnalystConfiguration(analystConfiguration, true);
		}
		for (AnalystConfiguration analystConfiguration : daoAnalystConfiguration
				.getAnalystConfigurationEditedReversedList()) {
			daoAnalystConfiguration.editAnalystConfiguration(analystConfiguration, true);
		}
		for (AnalystConfiguration analystConfiguration : daoAnalystConfiguration
				.getAnalystConfigurationInsertedList()) {
			daoAnalystConfiguration.deleteAnalystConfiguration(analystConfiguration, true);
		}
	}
	public void redo() throws Exception {
		for (AnalystConfiguration analystConfiguration : daoAnalystConfiguration
				.getAnalystConfigurationInsertedList()) {
			daoAnalystConfiguration.insertAnalystConfiguration(analystConfiguration, true);
		}
		for (AnalystConfiguration analystConfiguration : daoAnalystConfiguration.getAnalystConfigurationEditedList()) {
			daoAnalystConfiguration.editAnalystConfiguration(analystConfiguration, true);
		}
		for (AnalystConfiguration analystConfiguration : daoAnalystConfiguration.getAnalystConfigurationDeletedList()) {
			daoAnalystConfiguration.deleteAnalystConfiguration(analystConfiguration, true);
		}
	}
	public AnalystConfiguration getAnalystConfiguration() {
		try {
			List<AnalystConfiguration> analystConfigurationList = daoAnalystConfiguration
					.listAnalystConfiguration("_id = \"" + 1 + "\"");
			if (analystConfigurationList.size() == 0) {
				AnalystConfiguration analystConfiguration = new AnalystConfiguration();
				analystConfiguration.set_Id("1");
				daoAnalystConfiguration.insertAnalystConfiguration(analystConfiguration, false);
				return analystConfiguration;
			} else {
				return analystConfigurationList.get(0);
			}
		} catch (StorageException | ConstraintCheckingException e) {
			return null;
		}
	}
}