package android.mobilequare.analyst.model.daofactory;
import java.util.List;
import java.util.ArrayList;
import android.mobilequare.analyst.model.po.Discourse;
import android.mobilequare.analyst.exception.StorageException;
import android.mobilequare.analyst.exception.ConstraintCheckingException;
import android.mobilequare.analyst.model.dao.DAODiscourse;
public class DAORemoteStorageDiscourse extends DAODiscourse {
	public List<Discourse> listDiscourse(String query) throws StorageException {
		//LIST IMPLEMENTATION FOR REMOTE STORAGE OF DISCOURSE 
		//TODO IMPLEMENT THIS
		return new ArrayList<Discourse>();
	}
	public boolean deleteDiscourse(Discourse discourse, boolean isUndoOrRedo) throws StorageException {
		//DELETE IMPLEMENTATION FOR REMOTE STORAGE OF DISCOURSE 
		//TODO IMPLEMENT THIS
		return false;
	}
	public Discourse insertDiscourse(Discourse discourse, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException {
		//INSERT IMPLEMENTATION FOR REMOTE STORAGE OF DISCOURSE 
		discourse.checkConstraints();
		//TODO IMPLEMENT THIS
		return null;
	}
	public Discourse editDiscourse(Discourse discourse, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException {
		//EDIT IMPLEMENTATION FOR REMOTE STORAGE OF DISCOURSE 
		discourse.checkConstraints();
		//TODO IMPLEMENT THIS
		return null;
	}
}