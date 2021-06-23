package android.mobilequare.analyst.model.daofactory;
import java.util.List;
import java.util.ArrayList;
import android.mobilequare.analyst.model.po.Function;
import android.mobilequare.analyst.exception.StorageException;
import android.mobilequare.analyst.exception.ConstraintCheckingException;
import android.mobilequare.analyst.model.dao.DAOFunction;
public class DAORemoteStorageFunction extends DAOFunction {
	public List<Function> listFunction(String query) throws StorageException {
		//LIST IMPLEMENTATION FOR REMOTE STORAGE OF FUNCTION 
		//TODO IMPLEMENT THIS
		return new ArrayList<Function>();
	}
	public boolean deleteFunction(Function function, boolean isUndoOrRedo) throws StorageException {
		//DELETE IMPLEMENTATION FOR REMOTE STORAGE OF FUNCTION 
		//TODO IMPLEMENT THIS
		return false;
	}
	public Function insertFunction(Function function, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException {
		//INSERT IMPLEMENTATION FOR REMOTE STORAGE OF FUNCTION 
		function.checkConstraints();
		//TODO IMPLEMENT THIS
		return null;
	}
	public Function editFunction(Function function, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException {
		//EDIT IMPLEMENTATION FOR REMOTE STORAGE OF FUNCTION 
		function.checkConstraints();
		//TODO IMPLEMENT THIS
		return null;
	}
}