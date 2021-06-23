package android.mobilequare.analyst.model.daofactory;
import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.mobilequare.analyst.model.LocalStorageContentProvider;
import android.mobilequare.analyst.model.po.ContainerConcept;
import android.mobilequare.analyst.exception.StorageException;
import android.mobilequare.analyst.exception.ConstraintCheckingException;
import android.mobilequare.analyst.model.dao.DAOContainerConcept;
public class DAOLocalStorageContainerConcept extends DAOContainerConcept {
	private ContentResolver contentResolver;
	public DAOLocalStorageContainerConcept(Context context) {
		super();
		this.contentResolver = context.getContentResolver();
	}
	public List<ContainerConcept> listContainerConcept(String query) throws StorageException {
		//LIST IMPLEMENTATION FOR LOCALSTORAGE OF CONTAINERCONCEPT 
		//return list(ContainerConcept; LocalStorage; query)
		if (query == null || query.compareTo("") == 0) {
			query = "1";
		}
		List<ContainerConcept> containerConceptList = new ArrayList<ContainerConcept>();
		String sortOrder = "_ID ASC";
		String[] selectionArguments = null;
		ContainerConcept containerConcept;
		Cursor cursor = contentResolver.query(LocalStorageContentProvider.CONTAINERCONCEPT_URI, null, query,
				selectionArguments, sortOrder);
		try {
			if (cursor.moveToFirst()) {
				do {
					containerConcept = new ContainerConcept();
					containerConcept.set_Id(cursor.getString(cursor.getColumnIndex("_ID")));
					containerConcept.setName(cursor.getString(cursor.getColumnIndex("NAME")));
					containerConceptList.add(containerConcept);
				} while (cursor.moveToNext());
			}
		} catch (Exception e) {
			throw new StorageException();
		} finally {
			if (cursor != null && !cursor.isClosed()) {
				cursor.close();
			}
		}
		return containerConceptList;
	}
	public boolean deleteContainerConcept(ContainerConcept containerConcept, boolean isUndoOrRedo)
			throws StorageException {
		//DELETE IMPLEMENTATION FOR LOCALSTORAGE OF CONTAINERCONCEPT 
		try {
			contentResolver.delete(LocalStorageContentProvider.CONTAINERCONCEPT_URI,
					"_id =\"" + containerConcept.get_Id() + "\"", null);
		} catch (Exception e) {
			throw new StorageException();
		}
		if (!isUndoOrRedo) {
			this.getContainerConceptDeletedList().add(containerConcept);
		}
		return true;
	}
	public ContainerConcept insertContainerConcept(ContainerConcept containerConcept, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException {
		//INSERT IMPLEMENTATION FOR LOCALSTORAGE OF CONTAINERCONCEPT 
		try {
			ContentValues containerConceptValues = new ContentValues();
			containerConceptValues.put("_ID", containerConcept.get_Id());
			containerConceptValues.put("NAME", containerConcept.getName());
			Uri uri = contentResolver.insert(LocalStorageContentProvider.CONTAINERCONCEPT_URI, containerConceptValues);
		} catch (Exception e) {
			throw new StorageException();
		}
		if (!isUndoOrRedo) {
			this.getContainerConceptInsertedList().add(containerConcept);
		}
		return containerConcept;
	}
	public ContainerConcept editContainerConcept(ContainerConcept containerConcept, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException {
		//EDIT IMPLEMENTATION FOR LOCAL STORAGE OF CONTAINERCONCEPT 
		containerConcept.checkConstraints();
		ContainerConcept containerConceptBeforeEdition = listContainerConcept(
				"_id = \"" + containerConcept.get_Id() + "\"").get(0);
		try {
			ContentValues containerConceptValues = new ContentValues();
			containerConceptValues.put("NAME", containerConcept.getName());
			contentResolver.update(LocalStorageContentProvider.CONTAINERCONCEPT_URI, containerConceptValues,
					"_id = \"" + containerConcept.get_Id() + "\"", null);
		} catch (Exception e) {
			throw new StorageException();
		}
		if (!isUndoOrRedo) {
			this.getContainerConceptEditedReversedList().add(0, containerConceptBeforeEdition);
			this.getContainerConceptEditedList().add(containerConcept);
		}
		return containerConcept;
	}
}