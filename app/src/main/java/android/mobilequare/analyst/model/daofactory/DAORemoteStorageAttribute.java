package android.mobilequare.analyst.model.daofactory;
import java.util.List;
import java.util.ArrayList;
import android.mobilequare.analyst.model.po.Attribute;
import android.mobilequare.analyst.exception.StorageException;
import android.mobilequare.analyst.exception.ConstraintCheckingException;
import android.mobilequare.analyst.model.dao.DAOAttribute;
public class DAORemoteStorageAttribute extends DAOAttribute {
	public List<Attribute> listAttribute(String query) throws StorageException {
		//LIST IMPLEMENTATION FOR REMOTE STORAGE OF ATTRIBUTE 
		//TODO IMPLEMENT THIS
		return new ArrayList<Attribute>();
	}
	public boolean deleteAttribute(Attribute attribute, boolean isUndoOrRedo) throws StorageException {
		//DELETE IMPLEMENTATION FOR REMOTE STORAGE OF ATTRIBUTE 
		//TODO IMPLEMENT THIS
		return false;
	}
	public Attribute insertAttribute(Attribute attribute, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException {
		//INSERT IMPLEMENTATION FOR REMOTE STORAGE OF ATTRIBUTE 
		attribute.checkConstraints();
		//TODO IMPLEMENT THIS
		return null;
	}
	public Attribute editAttribute(Attribute attribute, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException {
		//EDIT IMPLEMENTATION FOR REMOTE STORAGE OF ATTRIBUTE 
		attribute.checkConstraints();
		//TODO IMPLEMENT THIS
		return null;
	}
}