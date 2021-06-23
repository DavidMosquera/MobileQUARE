package android.mobilequare.analyst.model.daofactory;
import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.mobilequare.analyst.model.LocalStorageContentProvider;
import android.mobilequare.analyst.model.po.Function;
import android.mobilequare.analyst.exception.StorageException;
import android.mobilequare.analyst.exception.ConstraintCheckingException;
import android.mobilequare.analyst.model.dao.DAOFunction;
public class DAOLocalStorageFunction extends DAOFunction {
	private ContentResolver contentResolver;
	public DAOLocalStorageFunction(Context context) {
		super();
		this.contentResolver = context.getContentResolver();
	}
	public List<Function> listFunction(String query) throws StorageException {
		//LIST IMPLEMENTATION FOR LOCALSTORAGE OF FUNCTION 
		//return list(Function; LocalStorage; query)
		if (query == null || query.compareTo("") == 0) {
			query = "1";
		}
		List<Function> functionList = new ArrayList<Function>();
		String sortOrder = "_ID ASC";
		String[] selectionArguments = null;
		Function function;
		Cursor cursor = contentResolver.query(LocalStorageContentProvider.FUNCTION_URI, null, query, selectionArguments,
				sortOrder);
		try {
			if (cursor.moveToFirst()) {
				do {
					function = new Function();
					function.set_Id(cursor.getString(cursor.getColumnIndex("_ID")));
					function.setActionVerb(cursor.getString(cursor.getColumnIndex("ACTIONVERB")));
					function.set_idActor(cursor.getString(cursor.getColumnIndex("ACTOR_ID")));
					function.set_idObject(cursor.getString(cursor.getColumnIndex("OBJECT_ID")));
					functionList.add(function);
				} while (cursor.moveToNext());
			}
		} catch (Exception e) {
			throw new StorageException();
		} finally {
			if (cursor != null && !cursor.isClosed()) {
				cursor.close();
			}
		}
		return functionList;
	}
	public boolean deleteFunction(Function function, boolean isUndoOrRedo) throws StorageException {
		//DELETE IMPLEMENTATION FOR LOCALSTORAGE OF FUNCTION 
		try {
			contentResolver.delete(LocalStorageContentProvider.FUNCTION_URI, "_id =\"" + function.get_Id() + "\"",
					null);
		} catch (Exception e) {
			throw new StorageException();
		}
		if (!isUndoOrRedo) {
			this.getFunctionDeletedList().add(function);
		}
		return true;
	}
	public Function insertFunction(Function function, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException {
		//INSERT IMPLEMENTATION FOR LOCALSTORAGE OF FUNCTION 
		try {
			ContentValues functionValues = new ContentValues();
			functionValues.put("_ID", function.get_Id());
			functionValues.put("ACTIONVERB", function.getActionVerb());
			functionValues.put("ACTOR_ID", function.get_idActor());
			functionValues.put("OBJECT_ID", function.get_idObject());
			Uri uri = contentResolver.insert(LocalStorageContentProvider.FUNCTION_URI, functionValues);
		} catch (Exception e) {
			throw new StorageException();
		}
		if (!isUndoOrRedo) {
			this.getFunctionInsertedList().add(function);
		}
		return function;
	}
	public Function editFunction(Function function, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException {
		//EDIT IMPLEMENTATION FOR LOCAL STORAGE OF FUNCTION 
		function.checkConstraints();
		Function functionBeforeEdition = listFunction("_id = \"" + function.get_Id() + "\"").get(0);
		try {
			ContentValues functionValues = new ContentValues();
			functionValues.put("ACTIONVERB", function.getActionVerb());
			functionValues.put("ACTOR_ID", function.get_idActor());
			functionValues.put("OBJECT_ID", function.get_idObject());
			contentResolver.update(LocalStorageContentProvider.FUNCTION_URI, functionValues,
					"_id = \"" + function.get_Id() + "\"", null);
		} catch (Exception e) {
			throw new StorageException();
		}
		if (!isUndoOrRedo) {
			this.getFunctionEditedReversedList().add(0, functionBeforeEdition);
			this.getFunctionEditedList().add(function);
		}
		return function;
	}
}