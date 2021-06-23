package android.mobilequare.analyst.model.daofactory;
import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.mobilequare.analyst.model.LocalStorageContentProvider;
import android.mobilequare.analyst.model.po.Attribute;
import android.mobilequare.analyst.exception.StorageException;
import android.mobilequare.analyst.exception.ConstraintCheckingException;
import android.mobilequare.analyst.model.dao.DAOAttribute;
public class DAOLocalStorageAttribute extends DAOAttribute {
	private ContentResolver contentResolver;
	public DAOLocalStorageAttribute(Context context) {
		super();
		this.contentResolver = context.getContentResolver();
	}
	public List<Attribute> listAttribute(String query) throws StorageException {
		//LIST IMPLEMENTATION FOR LOCALSTORAGE OF ATTRIBUTE 
		//return list(Attribute; LocalStorage; query)
		if (query == null || query.compareTo("") == 0) {
			query = "1";
		}
		List<Attribute> attributeList = new ArrayList<Attribute>();
		String sortOrder = "_ID ASC";
		String[] selectionArguments = null;
		Attribute attribute;
		Cursor cursor = contentResolver.query(LocalStorageContentProvider.ATTRIBUTE_URI, null, query,
				selectionArguments, sortOrder);
		try {
			if (cursor.moveToFirst()) {
				do {
					attribute = new Attribute();
					attribute.set_Id(cursor.getString(cursor.getColumnIndex("_ID")));
					attribute.setName(cursor.getString(cursor.getColumnIndex("NAME")));
					attributeList.add(attribute);
				} while (cursor.moveToNext());
			}
		} catch (Exception e) {
			throw new StorageException();
		} finally {
			if (cursor != null && !cursor.isClosed()) {
				cursor.close();
			}
		}
		return attributeList;
	}
	public boolean deleteAttribute(Attribute attribute, boolean isUndoOrRedo) throws StorageException {
		//DELETE IMPLEMENTATION FOR LOCALSTORAGE OF ATTRIBUTE 
		try {
			contentResolver.delete(LocalStorageContentProvider.ATTRIBUTE_URI, "_id =\"" + attribute.get_Id() + "\"",
					null);
		} catch (Exception e) {
			throw new StorageException();
		}
		if (!isUndoOrRedo) {
			this.getAttributeDeletedList().add(attribute);
		}
		return true;
	}
	public Attribute insertAttribute(Attribute attribute, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException {
		//INSERT IMPLEMENTATION FOR LOCALSTORAGE OF ATTRIBUTE 
		try {
			ContentValues attributeValues = new ContentValues();
			attributeValues.put("_ID", attribute.get_Id());
			attributeValues.put("NAME", attribute.getName());
			Uri uri = contentResolver.insert(LocalStorageContentProvider.ATTRIBUTE_URI, attributeValues);
		} catch (Exception e) {
			throw new StorageException();
		}
		if (!isUndoOrRedo) {
			this.getAttributeInsertedList().add(attribute);
		}
		return attribute;
	}
	public Attribute editAttribute(Attribute attribute, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException {
		//EDIT IMPLEMENTATION FOR LOCAL STORAGE OF ATTRIBUTE 
		attribute.checkConstraints();
		Attribute attributeBeforeEdition = listAttribute("_id = \"" + attribute.get_Id() + "\"").get(0);
		try {
			ContentValues attributeValues = new ContentValues();
			attributeValues.put("NAME", attribute.getName());
			contentResolver.update(LocalStorageContentProvider.ATTRIBUTE_URI, attributeValues,
					"_id = \"" + attribute.get_Id() + "\"", null);
		} catch (Exception e) {
			throw new StorageException();
		}
		if (!isUndoOrRedo) {
			this.getAttributeEditedReversedList().add(0, attributeBeforeEdition);
			this.getAttributeEditedList().add(attribute);
		}
		return attribute;
	}
}