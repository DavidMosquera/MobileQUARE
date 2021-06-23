package android.mobilequare.analyst.model.dao;
import java.util.List;
import java.util.ArrayList;
import android.mobilequare.analyst.model.po.AttributeRelationship;
import android.mobilequare.analyst.exception.StorageException;
import android.mobilequare.analyst.exception.ConstraintCheckingException;
public abstract class DAOAttributeRelationship {
	//UNDO - REDO LISTS
	private List<AttributeRelationship> attributeRelationshipInsertedList;
	private List<AttributeRelationship> attributeRelationshipDeletedList;
	private List<AttributeRelationship> attributeRelationshipEditedList;
	private List<AttributeRelationship> attributeRelationshipEditedReversedList;
	//CONSTRUCTOR
	public DAOAttributeRelationship() {
		this.attributeRelationshipInsertedList = new ArrayList<AttributeRelationship>();
		this.attributeRelationshipDeletedList = new ArrayList<AttributeRelationship>();
		this.attributeRelationshipEditedList = new ArrayList<AttributeRelationship>();
		this.attributeRelationshipEditedReversedList = new ArrayList<AttributeRelationship>();
	}
	//OPERATIONS
	public abstract List<AttributeRelationship> listAttributeRelationship(String query) throws StorageException;
	public abstract boolean deleteAttributeRelationship(AttributeRelationship attributeRelationship,
			boolean isUndoOrRedo) throws StorageException;
	public abstract AttributeRelationship insertAttributeRelationship(AttributeRelationship attributeRelationship,
			boolean isUndoOrRedo) throws StorageException, ConstraintCheckingException;
	public abstract AttributeRelationship editAttributeRelationship(AttributeRelationship attributeRelationship,
			boolean isUndoOrRedo) throws StorageException, ConstraintCheckingException;
	//GETTERS
	public List<AttributeRelationship> getAttributeRelationshipInsertedList() {
		return this.attributeRelationshipInsertedList;
	}
	public List<AttributeRelationship> getAttributeRelationshipDeletedList() {
		return this.attributeRelationshipDeletedList;
	}
	public List<AttributeRelationship> getAttributeRelationshipEditedList() {
		return this.attributeRelationshipEditedList;
	}
	public List<AttributeRelationship> getAttributeRelationshipEditedReversedList() {
		return this.attributeRelationshipEditedReversedList;
	}
	//SETTERS
	public void setAttributeRelationshipInsertedList(List<AttributeRelationship> newAttributeRelationshipInsertedList) {
		this.attributeRelationshipInsertedList = newAttributeRelationshipInsertedList;
	}
	public void setAttributeRelationshipDeletedList(List<AttributeRelationship> newAttributeRelationshipDeletedList) {
		this.attributeRelationshipDeletedList = newAttributeRelationshipDeletedList;
	}
	public void setAttributeRelationshipEditedList(List<AttributeRelationship> newAttributeRelationshipEditedList) {
		this.attributeRelationshipEditedList = newAttributeRelationshipEditedList;
	}
	public void setAttributeRelationshipEditedReversedList(
			List<AttributeRelationship> newAttributeRelationshipEditedReversedList) {
		this.attributeRelationshipEditedReversedList = newAttributeRelationshipEditedReversedList;
	}
}