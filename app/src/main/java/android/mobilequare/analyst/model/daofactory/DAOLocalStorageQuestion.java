package android.mobilequare.analyst.model.daofactory;
import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.mobilequare.analyst.model.LocalStorageContentProvider;
import android.mobilequare.analyst.model.po.Question;
import android.mobilequare.analyst.exception.StorageException;
import android.mobilequare.analyst.exception.ConstraintCheckingException;
import android.mobilequare.analyst.model.dao.DAOQuestion;
public class DAOLocalStorageQuestion extends DAOQuestion {
	private ContentResolver contentResolver;
	public DAOLocalStorageQuestion(Context context) {
		super();
		this.contentResolver = context.getContentResolver();
	}
	public List<Question> listQuestion(String query) throws StorageException {
		//LIST IMPLEMENTATION FOR LOCALSTORAGE OF QUESTION 
		//return list(Question; LocalStorage; query)
		if (query == null || query.compareTo("") == 0) {
			query = "1";
		}
		List<Question> questionList = new ArrayList<Question>();
		String sortOrder = "_ID ASC";
		String[] selectionArguments = null;
		Question question;
		Cursor cursor = contentResolver.query(LocalStorageContentProvider.QUESTION_URI, null, query, selectionArguments,
				sortOrder);
		try {
			if (cursor.moveToFirst()) {
				do {
					question = new Question();
					question.set_Id(cursor.getString(cursor.getColumnIndex("_ID")));
					question.setType(cursor.getString(cursor.getColumnIndex("TYPE")));
					question.setTitle(cursor.getString(cursor.getColumnIndex("TITLE")));
					question.setAnswer(cursor.getString(cursor.getColumnIndex("ANSWER")));
					question.set_idQuestionSet(cursor.getString(cursor.getColumnIndex("QUESTIONSET_ID")));
					questionList.add(question);
				} while (cursor.moveToNext());
			}
		} catch (Exception e) {
			throw new StorageException();
		} finally {
			if (cursor != null && !cursor.isClosed()) {
				cursor.close();
			}
		}
		return questionList;
	}
	public boolean deleteQuestion(Question question, boolean isUndoOrRedo) throws StorageException {
		//DELETE IMPLEMENTATION FOR LOCALSTORAGE OF QUESTION 
		try {
			contentResolver.delete(LocalStorageContentProvider.QUESTION_URI, "_id =\"" + question.get_Id() + "\"",
					null);
		} catch (Exception e) {
			throw new StorageException();
		}
		if (!isUndoOrRedo) {
			this.getQuestionDeletedList().add(question);
		}
		return true;
	}
	public Question insertQuestion(Question question, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException {
		//INSERT IMPLEMENTATION FOR LOCALSTORAGE OF QUESTION 
		try {
			ContentValues questionValues = new ContentValues();
			questionValues.put("_ID", question.get_Id());
			questionValues.put("TYPE", question.getType());
			questionValues.put("TITLE", question.getTitle());
			questionValues.put("ANSWER", question.getAnswer());
			questionValues.put("QUESTIONSET_ID", question.get_idQuestionSet());
			Uri uri = contentResolver.insert(LocalStorageContentProvider.QUESTION_URI, questionValues);
		} catch (Exception e) {
			throw new StorageException();
		}
		if (!isUndoOrRedo) {
			this.getQuestionInsertedList().add(question);
		}
		return question;
	}
	public Question editQuestion(Question question, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException {
		//EDIT IMPLEMENTATION FOR LOCAL STORAGE OF QUESTION 
		question.checkConstraints();
		Question questionBeforeEdition = listQuestion("_id = \"" + question.get_Id() + "\"").get(0);
		try {
			ContentValues questionValues = new ContentValues();
			questionValues.put("TYPE", question.getType());
			questionValues.put("TITLE", question.getTitle());
			questionValues.put("ANSWER", question.getAnswer());
			questionValues.put("QUESTIONSET_ID", question.get_idQuestionSet());
			contentResolver.update(LocalStorageContentProvider.QUESTION_URI, questionValues,
					"_id = \"" + question.get_Id() + "\"", null);
		} catch (Exception e) {
			throw new StorageException();
		}
		if (!isUndoOrRedo) {
			this.getQuestionEditedReversedList().add(0, questionBeforeEdition);
			this.getQuestionEditedList().add(question);
		}
		return question;
	}
}