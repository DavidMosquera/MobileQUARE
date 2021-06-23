package android.mobilequare.analyst.model.daofactory;
import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.mobilequare.analyst.model.LocalStorageContentProvider;
import android.mobilequare.analyst.model.po.Analyst;
import android.mobilequare.analyst.exception.StorageException;
import android.mobilequare.analyst.exception.ConstraintCheckingException;
import android.mobilequare.analyst.model.dao.DAOAnalyst;
public class DAOLocalStorageAnalyst extends DAOAnalyst {
	private ContentResolver contentResolver;
	public DAOLocalStorageAnalyst(Context context) {
		super();
		this.contentResolver = context.getContentResolver();
	}
	public List<Analyst> listAnalyst(String query) throws StorageException {
		//LIST IMPLEMENTATION FOR LOCALSTORAGE OF ANALYST 
		//return list(Analyst; LocalStorage; query)
		if (query == null || query.compareTo("") == 0) {
			query = "1";
		}
		List<Analyst> analystList = new ArrayList<Analyst>();
		String sortOrder = "_ID ASC";
		String[] selectionArguments = null;
		Analyst analyst;
		Cursor cursor = contentResolver.query(LocalStorageContentProvider.ANALYST_URI, null, query, selectionArguments,
				sortOrder);
		try {
			if (cursor.moveToFirst()) {
				do {
					analyst = new Analyst();
					analyst.set_Id(cursor.getString(cursor.getColumnIndex("_ID")));
					analystList.add(analyst);
				} while (cursor.moveToNext());
			}
		} catch (Exception e) {
			throw new StorageException();
		} finally {
			if (cursor != null && !cursor.isClosed()) {
				cursor.close();
			}
		}
		return analystList;
	}
	public boolean deleteAnalyst(Analyst analyst, boolean isUndoOrRedo) throws StorageException {
		//DELETE IMPLEMENTATION FOR LOCALSTORAGE OF ANALYST 
		try {
			contentResolver.delete(LocalStorageContentProvider.ANALYST_URI, "_id =\"" + analyst.get_Id() + "\"", null);
		} catch (Exception e) {
			throw new StorageException();
		}
		if (!isUndoOrRedo) {
			this.getAnalystDeletedList().add(analyst);
		}
		return true;
	}
	public Analyst insertAnalyst(Analyst analyst, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException {
		//INSERT IMPLEMENTATION FOR LOCALSTORAGE OF ANALYST 
		try {
			ContentValues analystValues = new ContentValues();
			analystValues.put("_ID", analyst.get_Id());
			Uri uri = contentResolver.insert(LocalStorageContentProvider.ANALYST_URI, analystValues);
		} catch (Exception e) {
			throw new StorageException();
		}
		if (!isUndoOrRedo) {
			this.getAnalystInsertedList().add(analyst);
		}
		return analyst;
	}
	public Analyst editAnalyst(Analyst analyst, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException {
		//EDIT IMPLEMENTATION FOR LOCAL STORAGE OF ANALYST 
		analyst.checkConstraints();
		Analyst analystBeforeEdition = listAnalyst("_id = \"" + analyst.get_Id() + "\"").get(0);
		try {
			ContentValues analystValues = new ContentValues();
			contentResolver.update(LocalStorageContentProvider.ANALYST_URI, analystValues,
					"_id = \"" + analyst.get_Id() + "\"", null);
		} catch (Exception e) {
			throw new StorageException();
		}
		if (!isUndoOrRedo) {
			this.getAnalystEditedReversedList().add(0, analystBeforeEdition);
			this.getAnalystEditedList().add(analyst);
		}
		return analyst;
	}
}