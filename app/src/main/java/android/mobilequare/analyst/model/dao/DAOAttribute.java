package android.mobilequare.analyst.model.dao;
import java.util.List;
import java.util.ArrayList;
import android.mobilequare.analyst.model.po.Attribute;
import android.mobilequare.analyst.exception.StorageException;
import android.mobilequare.analyst.exception.ConstraintCheckingException;
public abstract class DAOAttribute {
	//UNDO - REDO LISTS
	private List<Attribute> attributeInsertedList;
	private List<Attribute> attributeDeletedList;
	private List<Attribute> attributeEditedList;
	private List<Attribute> attributeEditedReversedList;
	//CONSTRUCTOR
	public DAOAttribute() {
		this.attributeInsertedList = new ArrayList<Attribute>();
		this.attributeDeletedList = new ArrayList<Attribute>();
		this.attributeEditedList = new ArrayList<Attribute>();
		this.attributeEditedReversedList = new ArrayList<Attribute>();
	}
	//OPERATIONS
	public abstract List<Attribute> listAttribute(String query) throws StorageException;
	public abstract boolean deleteAttribute(Attribute attribute, boolean isUndoOrRedo) throws StorageException;
	public abstract Attribute insertAttribute(Attribute attribute, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException;
	public abstract Attribute editAttribute(Attribute attribute, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException;
	//GETTERS
	public List<Attribute> getAttributeInsertedList() {
		return this.attributeInsertedList;
	}
	public List<Attribute> getAttributeDeletedList() {
		return this.attributeDeletedList;
	}
	public List<Attribute> getAttributeEditedList() {
		return this.attributeEditedList;
	}
	public List<Attribute> getAttributeEditedReversedList() {
		return this.attributeEditedReversedList;
	}
	//SETTERS
	public void setAttributeInsertedList(List<Attribute> newAttributeInsertedList) {
		this.attributeInsertedList = newAttributeInsertedList;
	}
	public void setAttributeDeletedList(List<Attribute> newAttributeDeletedList) {
		this.attributeDeletedList = newAttributeDeletedList;
	}
	public void setAttributeEditedList(List<Attribute> newAttributeEditedList) {
		this.attributeEditedList = newAttributeEditedList;
	}
	public void setAttributeEditedReversedList(List<Attribute> newAttributeEditedReversedList) {
		this.attributeEditedReversedList = newAttributeEditedReversedList;
	}
}