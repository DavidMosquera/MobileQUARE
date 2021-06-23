package android.mobilequare.analyst.model.daofactory;
import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.mobilequare.analyst.model.converters.*;
import android.mobilequare.analyst.model.LocalStorageContentProvider;
import android.mobilequare.analyst.exception.StorageException;
import android.mobilequare.analyst.model.po.AnalystConfiguration;
import android.mobilequare.analyst.model.dao.DAOAnalystConfiguration;
public class DAOLocalStorageAnalystConfiguration extends DAOAnalystConfiguration {
	private ContentResolver contentResolver;
	public DAOLocalStorageAnalystConfiguration(Context context) {
		super();
		this.contentResolver = context.getContentResolver();
	}
	public List<AnalystConfiguration> listAnalystConfiguration(String query) throws StorageException {
		//LIST IMPLEMENTATION FOR LOCALSTORAGE OF ANALYST CONFIGURATION
		//return list(AnalystConfiguration; LocalStorage; query)
		if (query == null || query.compareTo("") == 0) {
			query = "1";
		}
		List<AnalystConfiguration> analystConfigurationList = new ArrayList<AnalystConfiguration>();
		String sortOrder = "_ID ASC";
		String[] selectionArguments = null;
		AnalystConfiguration analystConfiguration;
		Cursor cursor = contentResolver.query(LocalStorageContentProvider.ANALYST_CONFIGURATION_URI, null, query,
				selectionArguments, sortOrder);
		try {
			if (cursor.moveToFirst()) {
				do {
					analystConfiguration = new AnalystConfiguration();
					analystConfiguration.set_Id(cursor.getString(cursor.getColumnIndex("_ID")));
					analystConfiguration.setCreatesProjectUndoRedoNotification(BooleanToIntConverter.integerToBoolean(
							cursor.getInt(cursor.getColumnIndex("ANALYSTCREATESPROJECTUNDOREDONOTIFICATION"))));
					analystConfiguration.setCreatesProjectConfirmationMessage(BooleanToIntConverter.integerToBoolean(
							cursor.getInt(cursor.getColumnIndex("ANALYSTCREATESPROJECTCONFIRMATIONMESSAGE"))));
					analystConfiguration.setEstablishesQuestionSetUndoRedoNotification(
							BooleanToIntConverter.integerToBoolean(cursor.getInt(
									cursor.getColumnIndex("ANALYSTESTABLISHESQUESTIONSETUNDOREDONOTIFICATION"))));
					analystConfiguration.setEstablishesQuestionSetConfirmationMessage(
							BooleanToIntConverter.integerToBoolean(cursor.getInt(
									cursor.getColumnIndex("ANALYSTESTABLISHESQUESTIONSETCONFIRMATIONMESSAGE"))));
					analystConfiguration
							.setProvidesDiscourseUndoRedoNotification(BooleanToIntConverter.integerToBoolean(cursor
									.getInt(cursor.getColumnIndex("ANALYSTPROVIDESDISCOURSEUNDOREDONOTIFICATION"))));
					analystConfiguration.setProvidesDiscourseConfirmationMessage(BooleanToIntConverter.integerToBoolean(
							cursor.getInt(cursor.getColumnIndex("ANALYSTPROVIDESDISCOURSECONFIRMATIONMESSAGE"))));
					analystConfiguration.setAsksQuestionUndoRedoNotification(BooleanToIntConverter.integerToBoolean(
							cursor.getInt(cursor.getColumnIndex("ANALYSTASKSQUESTIONUNDOREDONOTIFICATION"))));
					analystConfiguration.setAsksQuestionConfirmationMessage(BooleanToIntConverter.integerToBoolean(
							cursor.getInt(cursor.getColumnIndex("ANALYSTASKSQUESTIONCONFIRMATIONMESSAGE"))));
					analystConfiguration.setLeftHandView(BooleanToIntConverter
							.integerToBoolean(cursor.getInt(cursor.getColumnIndex("LEFTHANDVIEW"))));
					analystConfiguration
							.setNotificationTime(cursor.getDouble(cursor.getColumnIndex("NOTIFICATIONTIME")));
					analystConfigurationList.add(analystConfiguration);
				} while (cursor.moveToNext());
			}
		} catch (Exception e) {
			throw new StorageException();
		} finally {
			if (cursor != null && !cursor.isClosed()) {
				cursor.close();
			}
		}
		return analystConfigurationList;
	}
	public boolean deleteAnalystConfiguration(AnalystConfiguration analystConfiguration, boolean isUndoOrRedo)
			throws StorageException {
		//DELETE IMPLEMENTATION FOR LOCALSTORAGE OF ANALYST CONFIGURATION
		try {
			contentResolver.delete(LocalStorageContentProvider.ANALYST_CONFIGURATION_URI,
					"_id =\"" + analystConfiguration.get_Id() + "\"", null);
		} catch (Exception e) {
			throw new StorageException();
		}
		if (!isUndoOrRedo) {
			this.getAnalystConfigurationDeletedList().add(analystConfiguration);
		}
		return true;
	}
	public AnalystConfiguration insertAnalystConfiguration(AnalystConfiguration analystConfiguration,
			boolean isUndoOrRedo) throws StorageException {
		//INSERT IMPLEMENTATION FOR LOCALSTORAGE OF ANALYST CONFIGURATION 
		//analystConfigurationInserted = insert(AnalystConfiguration; LocalStorage; analystConfiguration)
		AnalystConfiguration analystConfigurationInserted = null;
		try {
			ContentValues analystConfigurationValues = new ContentValues();
			analystConfigurationValues.put("_ID", analystConfiguration.get_Id());
			analystConfigurationValues.put("ANALYSTCREATESPROJECTUNDOREDONOTIFICATION",
					BooleanToIntConverter.booleanToInt(analystConfiguration.getCreatesProjectUndoRedoNotification()));
			analystConfigurationValues.put("ANALYSTCREATESPROJECTCONFIRMATIONMESSAGE",
					BooleanToIntConverter.booleanToInt(analystConfiguration.getCreatesProjectConfirmationMessage()));
			analystConfigurationValues.put("ANALYSTESTABLISHESQUESTIONSETUNDOREDONOTIFICATION", BooleanToIntConverter
					.booleanToInt(analystConfiguration.getEstablishesQuestionSetUndoRedoNotification()));
			analystConfigurationValues.put("ANALYSTESTABLISHESQUESTIONSETCONFIRMATIONMESSAGE", BooleanToIntConverter
					.booleanToInt(analystConfiguration.getEstablishesQuestionSetConfirmationMessage()));
			analystConfigurationValues.put("ANALYSTPROVIDESDISCOURSEUNDOREDONOTIFICATION", BooleanToIntConverter
					.booleanToInt(analystConfiguration.getProvidesDiscourseUndoRedoNotification()));
			analystConfigurationValues.put("ANALYSTPROVIDESDISCOURSECONFIRMATIONMESSAGE",
					BooleanToIntConverter.booleanToInt(analystConfiguration.getProvidesDiscourseConfirmationMessage()));
			analystConfigurationValues.put("ANALYSTASKSQUESTIONUNDOREDONOTIFICATION",
					BooleanToIntConverter.booleanToInt(analystConfiguration.getAsksQuestionUndoRedoNotification()));
			analystConfigurationValues.put("ANALYSTASKSQUESTIONCONFIRMATIONMESSAGE",
					BooleanToIntConverter.booleanToInt(analystConfiguration.getAsksQuestionConfirmationMessage()));
			analystConfigurationValues.put("LEFTHANDVIEW", analystConfiguration.getLeftHandView());
			analystConfigurationValues.put("NOTIFICATIONTIME", analystConfiguration.getNotificationTime());
			Uri uri = contentResolver.insert(LocalStorageContentProvider.ANALYST_CONFIGURATION_URI,
					analystConfigurationValues);
		} catch (Exception e) {
			throw new StorageException();
		}
		if (!isUndoOrRedo) {
			this.getAnalystConfigurationInsertedList().add(analystConfiguration);
		}
		return analystConfiguration;
	}
	public AnalystConfiguration editAnalystConfiguration(AnalystConfiguration analystConfiguration,
			boolean isUndoOrRedo) throws StorageException {
		//EDIT IMPLEMENTATION FOR LOCAL STORAGE OF ANALYST CONFIGURATION 
		AnalystConfiguration analystConfigurationBeforeEdition = listAnalystConfiguration(
				"_id = \"" + analystConfiguration.get_Id() + "\"").get(0);
		try {
			ContentValues analystConfigurationValues = new ContentValues();
			analystConfigurationValues.put("_ID", analystConfiguration.get_Id());
			analystConfigurationValues.put("ANALYSTCREATESPROJECTUNDOREDONOTIFICATION",
					BooleanToIntConverter.booleanToInt(analystConfiguration.getCreatesProjectUndoRedoNotification()));
			analystConfigurationValues.put("ANALYSTCREATESPROJECTCONFIRMATIONMESSAGE",
					BooleanToIntConverter.booleanToInt(analystConfiguration.getCreatesProjectConfirmationMessage()));
			analystConfigurationValues.put("ANALYSTESTABLISHESQUESTIONSETUNDOREDONOTIFICATION", BooleanToIntConverter
					.booleanToInt(analystConfiguration.getEstablishesQuestionSetUndoRedoNotification()));
			analystConfigurationValues.put("ANALYSTESTABLISHESQUESTIONSETCONFIRMATIONMESSAGE", BooleanToIntConverter
					.booleanToInt(analystConfiguration.getEstablishesQuestionSetConfirmationMessage()));
			analystConfigurationValues.put("ANALYSTPROVIDESDISCOURSEUNDOREDONOTIFICATION", BooleanToIntConverter
					.booleanToInt(analystConfiguration.getProvidesDiscourseUndoRedoNotification()));
			analystConfigurationValues.put("ANALYSTPROVIDESDISCOURSECONFIRMATIONMESSAGE",
					BooleanToIntConverter.booleanToInt(analystConfiguration.getProvidesDiscourseConfirmationMessage()));
			analystConfigurationValues.put("ANALYSTASKSQUESTIONUNDOREDONOTIFICATION",
					BooleanToIntConverter.booleanToInt(analystConfiguration.getAsksQuestionUndoRedoNotification()));
			analystConfigurationValues.put("ANALYSTASKSQUESTIONCONFIRMATIONMESSAGE",
					BooleanToIntConverter.booleanToInt(analystConfiguration.getAsksQuestionConfirmationMessage()));
			analystConfigurationValues.put("LEFTHANDVIEW", analystConfiguration.getLeftHandView());
			analystConfigurationValues.put("NOTIFICATIONTIME", analystConfiguration.getNotificationTime());
			contentResolver.update(LocalStorageContentProvider.ANALYST_CONFIGURATION_URI, analystConfigurationValues,
					"_id = \"" + analystConfiguration.get_Id() + "\"", null);
		} catch (Exception e) {
			throw new StorageException();
		}
		if (!isUndoOrRedo) {
			this.getAnalystConfigurationEditedReversedList().add(0, analystConfigurationBeforeEdition);
			this.getAnalystConfigurationEditedList().add(analystConfiguration);
		}
		return analystConfiguration;
	}
}