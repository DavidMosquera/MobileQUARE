package android.mobilequare.analyst.model.daofactory;
import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.mobilequare.analyst.model.LocalStorageContentProvider;
import android.mobilequare.analyst.model.po.Concept;
import android.mobilequare.analyst.exception.StorageException;
import android.mobilequare.analyst.exception.ConstraintCheckingException;
import android.mobilequare.analyst.model.dao.DAOConcept;
public class DAOLocalStorageConcept extends DAOConcept {
	private ContentResolver contentResolver;
	public DAOLocalStorageConcept(Context context) {
		super();
		this.contentResolver = context.getContentResolver();
	}
	public List<Concept> listConcept(String query) throws StorageException {
		//LIST IMPLEMENTATION FOR LOCALSTORAGE OF CONCEPT 
		//return list(Concept; LocalStorage; query)
		if (query == null || query.compareTo("") == 0) {
			query = "1";
		}
		List<Concept> conceptList = new ArrayList<Concept>();
		String sortOrder = "_ID ASC";
		String[] selectionArguments = null;
		Concept concept;
		Cursor cursor = contentResolver.query(LocalStorageContentProvider.CONCEPT_URI, null, query, selectionArguments,
				sortOrder);
		try {
			if (cursor.moveToFirst()) {
				do {
					concept = new Concept();
					concept.set_Id(cursor.getString(cursor.getColumnIndex("_ID")));
					concept.setName(cursor.getString(cursor.getColumnIndex("NAME")));
					concept.set_idDiscourse(cursor.getString(cursor.getColumnIndex("DISCOURSE_ID")));
					conceptList.add(concept);
				} while (cursor.moveToNext());
			}
		} catch (Exception e) {
			throw new StorageException();
		} finally {
			if (cursor != null && !cursor.isClosed()) {
				cursor.close();
			}
		}
		return conceptList;
	}
	public boolean deleteConcept(Concept concept, boolean isUndoOrRedo) throws StorageException {
		//DELETE IMPLEMENTATION FOR LOCALSTORAGE OF CONCEPT 
		try {
			contentResolver.delete(LocalStorageContentProvider.CONCEPT_URI, "_id =\"" + concept.get_Id() + "\"", null);
		} catch (Exception e) {
			throw new StorageException();
		}
		if (!isUndoOrRedo) {
			this.getConceptDeletedList().add(concept);
		}
		return true;
	}
	public Concept insertConcept(Concept concept, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException {
		//INSERT IMPLEMENTATION FOR LOCALSTORAGE OF CONCEPT 
		try {
			ContentValues conceptValues = new ContentValues();
			conceptValues.put("_ID", concept.get_Id());
			conceptValues.put("NAME", concept.getName());
			conceptValues.put("DISCOURSE_ID", concept.get_idDiscourse());
			Uri uri = contentResolver.insert(LocalStorageContentProvider.CONCEPT_URI, conceptValues);
		} catch (Exception e) {
			throw new StorageException();
		}
		if (!isUndoOrRedo) {
			this.getConceptInsertedList().add(concept);
		}
		return concept;
	}
	public Concept editConcept(Concept concept, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException {
		//EDIT IMPLEMENTATION FOR LOCAL STORAGE OF CONCEPT 
		concept.checkConstraints();
		Concept conceptBeforeEdition = listConcept("_id = \"" + concept.get_Id() + "\"").get(0);
		try {
			ContentValues conceptValues = new ContentValues();
			conceptValues.put("NAME", concept.getName());
			conceptValues.put("DISCOURSE_ID", concept.get_idDiscourse());
			contentResolver.update(LocalStorageContentProvider.CONCEPT_URI, conceptValues,
					"_id = \"" + concept.get_Id() + "\"", null);
		} catch (Exception e) {
			throw new StorageException();
		}
		if (!isUndoOrRedo) {
			this.getConceptEditedReversedList().add(0, conceptBeforeEdition);
			this.getConceptEditedList().add(concept);
		}
		return concept;
	}
}