package android.mobilequare.analyst.model.daofactory;
import java.util.List;
import java.util.ArrayList;
import android.mobilequare.analyst.model.po.Analyst;
import android.mobilequare.analyst.exception.StorageException;
import android.mobilequare.analyst.exception.ConstraintCheckingException;
import android.mobilequare.analyst.model.dao.DAOAnalyst;
public class DAORemoteStorageAnalyst extends DAOAnalyst {
	public List<Analyst> listAnalyst(String query) throws StorageException {
		//LIST IMPLEMENTATION FOR REMOTE STORAGE OF ANALYST 
		//TODO IMPLEMENT THIS
		return new ArrayList<Analyst>();
	}
	public boolean deleteAnalyst(Analyst analyst, boolean isUndoOrRedo) throws StorageException {
		//DELETE IMPLEMENTATION FOR REMOTE STORAGE OF ANALYST 
		//TODO IMPLEMENT THIS
		return false;
	}
	public Analyst insertAnalyst(Analyst analyst, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException {
		//INSERT IMPLEMENTATION FOR REMOTE STORAGE OF ANALYST 
		analyst.checkConstraints();
		//TODO IMPLEMENT THIS
		return null;
	}
	public Analyst editAnalyst(Analyst analyst, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException {
		//EDIT IMPLEMENTATION FOR REMOTE STORAGE OF ANALYST 
		analyst.checkConstraints();
		//TODO IMPLEMENT THIS
		return null;
	}
}