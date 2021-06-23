package android.mobilequare.analyst.model.daofactory;
import java.util.List;
import java.util.ArrayList;
import android.mobilequare.analyst.model.po.Object;
import android.mobilequare.analyst.exception.StorageException;
import android.mobilequare.analyst.exception.ConstraintCheckingException;
import android.mobilequare.analyst.model.dao.DAOObject;
public class DAORemoteStorageObject extends DAOObject {
	public List<Object> listObject(String query) throws StorageException {
		//LIST IMPLEMENTATION FOR REMOTE STORAGE OF OBJECT 
		//TODO IMPLEMENT THIS
		return new ArrayList<Object>();
	}
	public boolean deleteObject(Object object, boolean isUndoOrRedo) throws StorageException {
		//DELETE IMPLEMENTATION FOR REMOTE STORAGE OF OBJECT 
		//TODO IMPLEMENT THIS
		return false;
	}
	public Object insertObject(Object object, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException {
		//INSERT IMPLEMENTATION FOR REMOTE STORAGE OF OBJECT 
		object.checkConstraints();
		//TODO IMPLEMENT THIS
		return null;
	}
	public Object editObject(Object object, boolean isUndoOrRedo) throws StorageException, ConstraintCheckingException {
		//EDIT IMPLEMENTATION FOR REMOTE STORAGE OF OBJECT 
		object.checkConstraints();
		//TODO IMPLEMENT THIS
		return null;
	}
}