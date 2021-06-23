package android.mobilequare.analyst.model.daofactory;
import java.util.List;
import java.util.ArrayList;
import android.mobilequare.analyst.model.po.ContainerConcept;
import android.mobilequare.analyst.exception.StorageException;
import android.mobilequare.analyst.exception.ConstraintCheckingException;
import android.mobilequare.analyst.model.dao.DAOContainerConcept;
public class DAORemoteStorageContainerConcept extends DAOContainerConcept {
	public List<ContainerConcept> listContainerConcept(String query) throws StorageException {
		//LIST IMPLEMENTATION FOR REMOTE STORAGE OF CONTAINERCONCEPT 
		//TODO IMPLEMENT THIS
		return new ArrayList<ContainerConcept>();
	}
	public boolean deleteContainerConcept(ContainerConcept containerConcept, boolean isUndoOrRedo)
			throws StorageException {
		//DELETE IMPLEMENTATION FOR REMOTE STORAGE OF CONTAINERCONCEPT 
		//TODO IMPLEMENT THIS
		return false;
	}
	public ContainerConcept insertContainerConcept(ContainerConcept containerConcept, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException {
		//INSERT IMPLEMENTATION FOR REMOTE STORAGE OF CONTAINERCONCEPT 
		containerConcept.checkConstraints();
		//TODO IMPLEMENT THIS
		return null;
	}
	public ContainerConcept editContainerConcept(ContainerConcept containerConcept, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException {
		//EDIT IMPLEMENTATION FOR REMOTE STORAGE OF CONTAINERCONCEPT 
		containerConcept.checkConstraints();
		//TODO IMPLEMENT THIS
		return null;
	}
}