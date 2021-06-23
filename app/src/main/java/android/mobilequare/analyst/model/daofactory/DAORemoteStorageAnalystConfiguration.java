package android.mobilequare.analyst.model.daofactory;
import java.util.List;
import java.util.ArrayList;
import android.mobilequare.analyst.model.dao.DAOAnalystConfiguration;
import android.mobilequare.analyst.exception.StorageException;
import android.mobilequare.analyst.model.po.AnalystConfiguration;
public class DAORemoteStorageAnalystConfiguration extends DAOAnalystConfiguration {
	public List<AnalystConfiguration> listAnalystConfiguration(String query) throws StorageException {
		//LIST IMPLEMENTATION FOR  REMOTE STORAGE OF ANALYST CONFIGURATION
		//TODO IMPLEMENT THIS
		return new ArrayList<AnalystConfiguration>();
	}
	public boolean deleteAnalystConfiguration(AnalystConfiguration analystConfiguration, boolean isUndoOrRedo)
			throws StorageException {
		//DELETE IMPLEMENTATION FOR REMOTESTORAGE OF ANALYST CONFIGURATION
		//TODO IMPLEMENT THIS
		return false;
	}
	public AnalystConfiguration insertAnalystConfiguration(AnalystConfiguration analystConfiguration,
			boolean isUndoOrRedo) throws StorageException {
		//INSERT IMPLEMENTATION FOR REMOTE STORAGE OF ANALYST CONFIGURATION 
		//TODO IMPLEMENT THIS
		return null;
	}
	public AnalystConfiguration editAnalystConfiguration(AnalystConfiguration analystConfiguration,
			boolean isUndoOrRedo) throws StorageException {
		//EDIT IMPLEMENTATION FOR REMOTE STORAGE OF ANALYST CONFIGURATION 
		//TODO IMPLEMENT THIS
		return null;
	}
}