package android.mobilequare.analyst.model.daofactory;
import java.util.List;
import java.util.ArrayList;
import android.mobilequare.analyst.model.po.AttributeRelationship;
import android.mobilequare.analyst.exception.StorageException;
import android.mobilequare.analyst.exception.ConstraintCheckingException;
import android.mobilequare.analyst.model.dao.DAOAttributeRelationship;
public class DAORemoteStorageAttributeRelationship extends DAOAttributeRelationship {
	public List<AttributeRelationship> listAttributeRelationship(String query) throws StorageException {
		//LIST IMPLEMENTATION FOR REMOTE STORAGE OF ATTRIBUTERELATIONSHIP 
		//TODO IMPLEMENT THIS
		return new ArrayList<AttributeRelationship>();
	}
	public boolean deleteAttributeRelationship(AttributeRelationship attributeRelationship, boolean isUndoOrRedo)
			throws StorageException {
		//DELETE IMPLEMENTATION FOR REMOTE STORAGE OF ATTRIBUTERELATIONSHIP 
		//TODO IMPLEMENT THIS
		return false;
	}
	public AttributeRelationship insertAttributeRelationship(AttributeRelationship attributeRelationship,
			boolean isUndoOrRedo) throws StorageException, ConstraintCheckingException {
		//INSERT IMPLEMENTATION FOR REMOTE STORAGE OF ATTRIBUTERELATIONSHIP 
		attributeRelationship.checkConstraints();
		//TODO IMPLEMENT THIS
		return null;
	}
	public AttributeRelationship editAttributeRelationship(AttributeRelationship attributeRelationship,
			boolean isUndoOrRedo) throws StorageException, ConstraintCheckingException {
		//EDIT IMPLEMENTATION FOR REMOTE STORAGE OF ATTRIBUTERELATIONSHIP 
		attributeRelationship.checkConstraints();
		//TODO IMPLEMENT THIS
		return null;
	}
}