package android.mobilequare.analyst.model.daofactory;
import java.util.List;
import java.util.ArrayList;
import android.mobilequare.analyst.model.po.Concept;
import android.mobilequare.analyst.exception.StorageException;
import android.mobilequare.analyst.exception.ConstraintCheckingException;
import android.mobilequare.analyst.model.dao.DAOConcept;
public class DAORemoteStorageConcept extends DAOConcept {
	public List<Concept> listConcept(String query) throws StorageException {
		//LIST IMPLEMENTATION FOR REMOTE STORAGE OF CONCEPT 
		//TODO IMPLEMENT THIS
		return new ArrayList<Concept>();
	}
	public boolean deleteConcept(Concept concept, boolean isUndoOrRedo) throws StorageException {
		//DELETE IMPLEMENTATION FOR REMOTE STORAGE OF CONCEPT 
		//TODO IMPLEMENT THIS
		return false;
	}
	public Concept insertConcept(Concept concept, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException {
		//INSERT IMPLEMENTATION FOR REMOTE STORAGE OF CONCEPT 
		concept.checkConstraints();
		//TODO IMPLEMENT THIS
		return null;
	}
	public Concept editConcept(Concept concept, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException {
		//EDIT IMPLEMENTATION FOR REMOTE STORAGE OF CONCEPT 
		concept.checkConstraints();
		//TODO IMPLEMENT THIS
		return null;
	}
}