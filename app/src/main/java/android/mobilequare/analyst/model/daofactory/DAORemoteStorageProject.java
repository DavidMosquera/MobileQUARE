package android.mobilequare.analyst.model.daofactory;
import java.util.List;
import java.util.ArrayList;
import android.mobilequare.analyst.model.po.Project;
import android.mobilequare.analyst.exception.StorageException;
import android.mobilequare.analyst.exception.ConstraintCheckingException;
import android.mobilequare.analyst.model.dao.DAOProject;
public class DAORemoteStorageProject extends DAOProject {
	public List<Project> listProject(String query) throws StorageException {
		//LIST IMPLEMENTATION FOR REMOTE STORAGE OF PROJECT 
		//TODO IMPLEMENT THIS
		return new ArrayList<Project>();
	}
	public boolean deleteProject(Project project, boolean isUndoOrRedo) throws StorageException {
		//DELETE IMPLEMENTATION FOR REMOTE STORAGE OF PROJECT 
		//TODO IMPLEMENT THIS
		return false;
	}
	public Project insertProject(Project project, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException {
		//INSERT IMPLEMENTATION FOR REMOTE STORAGE OF PROJECT 
		project.checkConstraints();
		//TODO IMPLEMENT THIS
		return null;
	}
	public Project editProject(Project project, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException {
		//EDIT IMPLEMENTATION FOR REMOTE STORAGE OF PROJECT 
		project.checkConstraints();
		//TODO IMPLEMENT THIS
		return null;
	}
}