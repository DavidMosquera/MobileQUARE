package android.mobilequare.analyst.model.daofactory;
import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.mobilequare.analyst.model.LocalStorageContentProvider;
import android.mobilequare.analyst.model.po.Discourse;
import android.mobilequare.analyst.exception.StorageException;
import android.mobilequare.analyst.exception.ConstraintCheckingException;
import android.mobilequare.analyst.model.dao.DAODiscourse;
public class DAOLocalStorageDiscourse extends DAODiscourse {
	private ContentResolver contentResolver;
	public DAOLocalStorageDiscourse(Context context) {
		super();
		this.contentResolver = context.getContentResolver();
	}
	public List<Discourse> listDiscourse(String query) throws StorageException {
		//LIST IMPLEMENTATION FOR LOCALSTORAGE OF DISCOURSE 
		//return list(Discourse; LocalStorage; query)
		if (query == null || query.compareTo("") == 0) {
			query = "1";
		}
		List<Discourse> discourseList = new ArrayList<Discourse>();
		String sortOrder = "_ID ASC";
		String[] selectionArguments = null;
		Discourse discourse;
		Cursor cursor = contentResolver.query(LocalStorageContentProvider.DISCOURSE_URI, null, query,
				selectionArguments, sortOrder);
		try {
			if (cursor.moveToFirst()) {
				do {
					discourse = new Discourse();
					discourse.set_Id(cursor.getString(cursor.getColumnIndex("_ID")));
					discourse.setId(cursor.getString(cursor.getColumnIndex("ID")));
					discourse.setContent(cursor.getString(cursor.getColumnIndex("CONTENT")));
					discourse.set_idProject(cursor.getString(cursor.getColumnIndex("PROJECT_ID")));
					discourseList.add(discourse);
				} while (cursor.moveToNext());
			}
		} catch (Exception e) {
			throw new StorageException();
		} finally {
			if (cursor != null && !cursor.isClosed()) {
				cursor.close();
			}
		}
		return discourseList;
	}
	public boolean deleteDiscourse(Discourse discourse, boolean isUndoOrRedo) throws StorageException {
		//DELETE IMPLEMENTATION FOR LOCALSTORAGE OF DISCOURSE 
		try {
			contentResolver.delete(LocalStorageContentProvider.DISCOURSE_URI, "_id =\"" + discourse.get_Id() + "\"",
					null);
		} catch (Exception e) {
			throw new StorageException();
		}
		if (!isUndoOrRedo) {
			this.getDiscourseDeletedList().add(discourse);
		}
		return true;
	}
	public Discourse insertDiscourse(Discourse discourse, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException {
		//INSERT IMPLEMENTATION FOR LOCALSTORAGE OF DISCOURSE 
		try {
			ContentValues discourseValues = new ContentValues();
			discourseValues.put("_ID", discourse.get_Id());
			discourseValues.put("ID", discourse.getId());
			discourseValues.put("CONTENT", discourse.getContent());
			discourseValues.put("PROJECT_ID", discourse.get_idProject());
			Uri uri = contentResolver.insert(LocalStorageContentProvider.DISCOURSE_URI, discourseValues);
		} catch (Exception e) {
			throw new StorageException();
		}
		if (!isUndoOrRedo) {
			this.getDiscourseInsertedList().add(discourse);
		}
		return discourse;
	}
	public Discourse editDiscourse(Discourse discourse, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException {
		//EDIT IMPLEMENTATION FOR LOCAL STORAGE OF DISCOURSE 
		discourse.checkConstraints();
		Discourse discourseBeforeEdition = listDiscourse("_id = \"" + discourse.get_Id() + "\"").get(0);
		try {
			ContentValues discourseValues = new ContentValues();
			discourseValues.put("ID", discourse.getId());
			discourseValues.put("CONTENT", discourse.getContent());
			discourseValues.put("PROJECT_ID", discourse.get_idProject());
			contentResolver.update(LocalStorageContentProvider.DISCOURSE_URI, discourseValues,
					"_id = \"" + discourse.get_Id() + "\"", null);
		} catch (Exception e) {
			throw new StorageException();
		}
		if (!isUndoOrRedo) {
			this.getDiscourseEditedReversedList().add(0, discourseBeforeEdition);
			this.getDiscourseEditedList().add(discourse);
		}
		return discourse;
	}
}