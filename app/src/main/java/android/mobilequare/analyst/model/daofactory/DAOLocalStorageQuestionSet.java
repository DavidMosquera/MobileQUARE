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
import android.mobilequare.analyst.model.po.QuestionSet;
import android.mobilequare.analyst.exception.StorageException;
import android.mobilequare.analyst.exception.ConstraintCheckingException;
import android.mobilequare.analyst.model.dao.DAOQuestionSet;
public class DAOLocalStorageQuestionSet extends DAOQuestionSet {
	private ContentResolver contentResolver;
	public DAOLocalStorageQuestionSet(Context context) {
		super();
		this.contentResolver = context.getContentResolver();
	}
	public List<QuestionSet> listQuestionSet(String query) throws StorageException {
		//LIST IMPLEMENTATION FOR LOCALSTORAGE OF QUESTIONSET 
		//return list(QuestionSet; LocalStorage; query)
		if (query == null || query.compareTo("") == 0) {
			query = "1";
		}
		List<QuestionSet> questionSetList = new ArrayList<QuestionSet>();
		String sortOrder = "_ID ASC";
		String[] selectionArguments = null;
		QuestionSet questionSet;
		Cursor cursor = contentResolver.query(LocalStorageContentProvider.QUESTIONSET_URI, null, query,
				selectionArguments, sortOrder);
		try {
			if (cursor.moveToFirst()) {
				do {
					questionSet = new QuestionSet();
					questionSet.set_Id(cursor.getString(cursor.getColumnIndex("_ID")));
					questionSet.setTitle(cursor.getString(cursor.getColumnIndex("TITLE")));
					questionSet.setAnswerDate(
							DateToRealConverter.fromTimestamp(cursor.getLong(cursor.getColumnIndex("ANSWERDATE"))));
					questionSet.set_idProject(cursor.getString(cursor.getColumnIndex("PROJECT_ID")));
					questionSetList.add(questionSet);
				} while (cursor.moveToNext());
			}
		} catch (Exception e) {
			throw new StorageException();
		} finally {
			if (cursor != null && !cursor.isClosed()) {
				cursor.close();
			}
		}
		return questionSetList;
	}
	public boolean deleteQuestionSet(QuestionSet questionSet, boolean isUndoOrRedo) throws StorageException {
		//DELETE IMPLEMENTATION FOR LOCALSTORAGE OF QUESTIONSET 
		try {
			contentResolver.delete(LocalStorageContentProvider.QUESTIONSET_URI, "_id =\"" + questionSet.get_Id() + "\"",
					null);
		} catch (Exception e) {
			throw new StorageException();
		}
		if (!isUndoOrRedo) {
			this.getQuestionSetDeletedList().add(questionSet);
		}
		return true;
	}
	public QuestionSet insertQuestionSet(QuestionSet questionSet, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException {
		//INSERT IMPLEMENTATION FOR LOCALSTORAGE OF QUESTIONSET 
		try {
			ContentValues questionSetValues = new ContentValues();
			questionSetValues.put("_ID", questionSet.get_Id());
			questionSetValues.put("TITLE", questionSet.getTitle());
			questionSetValues.put("ANSWERDATE", DateToRealConverter.dateToTimestamp(questionSet.getAnswerDate()));
			questionSetValues.put("PROJECT_ID", questionSet.get_idProject());
			Uri uri = contentResolver.insert(LocalStorageContentProvider.QUESTIONSET_URI, questionSetValues);
		} catch (Exception e) {
			throw new StorageException();
		}
		if (!isUndoOrRedo) {
			this.getQuestionSetInsertedList().add(questionSet);
		}
		return questionSet;
	}
	public QuestionSet editQuestionSet(QuestionSet questionSet, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException {
		//EDIT IMPLEMENTATION FOR LOCAL STORAGE OF QUESTIONSET 
		questionSet.checkConstraints();
		QuestionSet questionSetBeforeEdition = listQuestionSet("_id = \"" + questionSet.get_Id() + "\"").get(0);
		try {
			ContentValues questionSetValues = new ContentValues();
			questionSetValues.put("TITLE", questionSet.getTitle());
			questionSetValues.put("ANSWERDATE", DateToRealConverter.dateToTimestamp(questionSet.getAnswerDate()));
			questionSetValues.put("PROJECT_ID", questionSet.get_idProject());
			contentResolver.update(LocalStorageContentProvider.QUESTIONSET_URI, questionSetValues,
					"_id = \"" + questionSet.get_Id() + "\"", null);
		} catch (Exception e) {
			throw new StorageException();
		}
		if (!isUndoOrRedo) {
			this.getQuestionSetEditedReversedList().add(0, questionSetBeforeEdition);
			this.getQuestionSetEditedList().add(questionSet);
		}
		return questionSet;
	}
}