package android.mobilequare.analyst.model.daofactory;
import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.mobilequare.analyst.model.LocalStorageContentProvider;
import android.mobilequare.analyst.model.po.AttributeRelationship;
import android.mobilequare.analyst.exception.StorageException;
import android.mobilequare.analyst.exception.ConstraintCheckingException;
import android.mobilequare.analyst.model.dao.DAOAttributeRelationship;
public class DAOLocalStorageAttributeRelationship extends DAOAttributeRelationship {
	private ContentResolver contentResolver;
	public DAOLocalStorageAttributeRelationship(Context context) {
		super();
		this.contentResolver = context.getContentResolver();
	}
	public List<AttributeRelationship> listAttributeRelationship(String query) throws StorageException {
		//LIST IMPLEMENTATION FOR LOCALSTORAGE OF ATTRIBUTERELATIONSHIP 
		//return list(AttributeRelationship; LocalStorage; query)
		if (query == null || query.compareTo("") == 0) {
			query = "1";
		}
		List<AttributeRelationship> attributeRelationshipList = new ArrayList<AttributeRelationship>();
		String sortOrder = "_ID ASC";
		String[] selectionArguments = null;
		AttributeRelationship attributeRelationship;
		Cursor cursor = contentResolver.query(LocalStorageContentProvider.ATTRIBUTERELATIONSHIP_URI, null, query,
				selectionArguments, sortOrder);
		try {
			if (cursor.moveToFirst()) {
				do {
					attributeRelationship = new AttributeRelationship();
					attributeRelationship.set_Id(cursor.getString(cursor.getColumnIndex("_ID")));
					attributeRelationship.set_idAttribute(cursor.getString(cursor.getColumnIndex("ATTRIBUTE_ID")));
					attributeRelationship
							.set_idContainerConcept(cursor.getString(cursor.getColumnIndex("CONTAINERCONCEPT_ID")));
					attributeRelationshipList.add(attributeRelationship);
				} while (cursor.moveToNext());
			}
		} catch (Exception e) {
			throw new StorageException();
		} finally {
			if (cursor != null && !cursor.isClosed()) {
				cursor.close();
			}
		}
		return attributeRelationshipList;
	}
	public boolean deleteAttributeRelationship(AttributeRelationship attributeRelationship, boolean isUndoOrRedo)
			throws StorageException {
		//DELETE IMPLEMENTATION FOR LOCALSTORAGE OF ATTRIBUTERELATIONSHIP 
		try {
			contentResolver.delete(LocalStorageContentProvider.ATTRIBUTERELATIONSHIP_URI,
					"_id =\"" + attributeRelationship.get_Id() + "\"", null);
		} catch (Exception e) {
			throw new StorageException();
		}
		if (!isUndoOrRedo) {
			this.getAttributeRelationshipDeletedList().add(attributeRelationship);
		}
		return true;
	}
	public AttributeRelationship insertAttributeRelationship(AttributeRelationship attributeRelationship,
			boolean isUndoOrRedo) throws StorageException, ConstraintCheckingException {
		//INSERT IMPLEMENTATION FOR LOCALSTORAGE OF ATTRIBUTERELATIONSHIP 
		try {
			ContentValues attributeRelationshipValues = new ContentValues();
			attributeRelationshipValues.put("_ID", attributeRelationship.get_Id());
			attributeRelationshipValues.put("ATTRIBUTE_ID", attributeRelationship.get_idAttribute());
			attributeRelationshipValues.put("CONTAINERCONCEPT_ID", attributeRelationship.get_idContainerConcept());
			Uri uri = contentResolver.insert(LocalStorageContentProvider.ATTRIBUTERELATIONSHIP_URI,
					attributeRelationshipValues);
		} catch (Exception e) {
			throw new StorageException();
		}
		if (!isUndoOrRedo) {
			this.getAttributeRelationshipInsertedList().add(attributeRelationship);
		}
		return attributeRelationship;
	}
	public AttributeRelationship editAttributeRelationship(AttributeRelationship attributeRelationship,
			boolean isUndoOrRedo) throws StorageException, ConstraintCheckingException {
		//EDIT IMPLEMENTATION FOR LOCAL STORAGE OF ATTRIBUTERELATIONSHIP 
		attributeRelationship.checkConstraints();
		AttributeRelationship attributeRelationshipBeforeEdition = listAttributeRelationship(
				"_id = \"" + attributeRelationship.get_Id() + "\"").get(0);
		try {
			ContentValues attributeRelationshipValues = new ContentValues();
			attributeRelationshipValues.put("ATTRIBUTE_ID", attributeRelationship.get_idAttribute());
			attributeRelationshipValues.put("CONTAINERCONCEPT_ID", attributeRelationship.get_idContainerConcept());
			contentResolver.update(LocalStorageContentProvider.ATTRIBUTERELATIONSHIP_URI, attributeRelationshipValues,
					"_id = \"" + attributeRelationship.get_Id() + "\"", null);
		} catch (Exception e) {
			throw new StorageException();
		}
		if (!isUndoOrRedo) {
			this.getAttributeRelationshipEditedReversedList().add(0, attributeRelationshipBeforeEdition);
			this.getAttributeRelationshipEditedList().add(attributeRelationship);
		}
		return attributeRelationship;
	}
}