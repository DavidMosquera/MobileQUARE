package android.mobilequare.analyst.model.daofactory;
import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.mobilequare.analyst.model.LocalStorageContentProvider;
import android.mobilequare.analyst.model.po.Object;
import android.mobilequare.analyst.exception.StorageException;
import android.mobilequare.analyst.exception.ConstraintCheckingException;
import android.mobilequare.analyst.model.dao.DAOObject;
public class DAOLocalStorageObject extends DAOObject {
	private ContentResolver contentResolver;
	public DAOLocalStorageObject(Context context) {
		super();
		this.contentResolver = context.getContentResolver();
	}
	public List<Object> listObject(String query) throws StorageException {
		//LIST IMPLEMENTATION FOR LOCALSTORAGE OF OBJECT 
		//return list(Object; LocalStorage; query)
		if (query == null || query.compareTo("") == 0) {
			query = "1";
		}
		List<Object> objectList = new ArrayList<Object>();
		String sortOrder = "_ID ASC";
		String[] selectionArguments = null;
		Object object;
		Cursor cursor = contentResolver.query(LocalStorageContentProvider.OBJECT_URI, null, query, selectionArguments,
				sortOrder);
		try {
			if (cursor.moveToFirst()) {
				do {
					object = new Object();
					object.set_Id(cursor.getString(cursor.getColumnIndex("_ID")));
					object.setName(cursor.getString(cursor.getColumnIndex("NAME")));
					objectList.add(object);
				} while (cursor.moveToNext());
			}
		} catch (Exception e) {
			throw new StorageException();
		} finally {
			if (cursor != null && !cursor.isClosed()) {
				cursor.close();
			}
		}
		return objectList;
	}
	public boolean deleteObject(Object object, boolean isUndoOrRedo) throws StorageException {
		//DELETE IMPLEMENTATION FOR LOCALSTORAGE OF OBJECT 
		try {
			contentResolver.delete(LocalStorageContentProvider.OBJECT_URI, "_id =\"" + object.get_Id() + "\"", null);
		} catch (Exception e) {
			throw new StorageException();
		}
		if (!isUndoOrRedo) {
			this.getObjectDeletedList().add(object);
		}
		return true;
	}
	public Object insertObject(Object object, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException {
		//INSERT IMPLEMENTATION FOR LOCALSTORAGE OF OBJECT 
		try {
			ContentValues objectValues = new ContentValues();
			objectValues.put("_ID", object.get_Id());
			objectValues.put("NAME", object.getName());
			Uri uri = contentResolver.insert(LocalStorageContentProvider.OBJECT_URI, objectValues);
		} catch (Exception e) {
			throw new StorageException();
		}
		if (!isUndoOrRedo) {
			this.getObjectInsertedList().add(object);
		}
		return object;
	}
	public Object editObject(Object object, boolean isUndoOrRedo) throws StorageException, ConstraintCheckingException {
		//EDIT IMPLEMENTATION FOR LOCAL STORAGE OF OBJECT 
		object.checkConstraints();
		Object objectBeforeEdition = listObject("_id = \"" + object.get_Id() + "\"").get(0);
		try {
			ContentValues objectValues = new ContentValues();
			objectValues.put("NAME", object.getName());
			contentResolver.update(LocalStorageContentProvider.OBJECT_URI, objectValues,
					"_id = \"" + object.get_Id() + "\"", null);
		} catch (Exception e) {
			throw new StorageException();
		}
		if (!isUndoOrRedo) {
			this.getObjectEditedReversedList().add(0, objectBeforeEdition);
			this.getObjectEditedList().add(object);
		}
		return object;
	}
}