package android.mobilequare.analyst.model.dao;
import java.util.List;
import java.util.ArrayList;
import android.mobilequare.analyst.model.po.Object;
import android.mobilequare.analyst.exception.StorageException;
import android.mobilequare.analyst.exception.ConstraintCheckingException;
public abstract class DAOObject {
	//UNDO - REDO LISTS
	private List<Object> objectInsertedList;
	private List<Object> objectDeletedList;
	private List<Object> objectEditedList;
	private List<Object> objectEditedReversedList;
	//CONSTRUCTOR
	public DAOObject() {
		this.objectInsertedList = new ArrayList<Object>();
		this.objectDeletedList = new ArrayList<Object>();
		this.objectEditedList = new ArrayList<Object>();
		this.objectEditedReversedList = new ArrayList<Object>();
	}
	//OPERATIONS
	public abstract List<Object> listObject(String query) throws StorageException;
	public abstract boolean deleteObject(Object object, boolean isUndoOrRedo) throws StorageException;
	public abstract Object insertObject(Object object, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException;
	public abstract Object editObject(Object object, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException;
	//GETTERS
	public List<Object> getObjectInsertedList() {
		return this.objectInsertedList;
	}
	public List<Object> getObjectDeletedList() {
		return this.objectDeletedList;
	}
	public List<Object> getObjectEditedList() {
		return this.objectEditedList;
	}
	public List<Object> getObjectEditedReversedList() {
		return this.objectEditedReversedList;
	}
	//SETTERS
	public void setObjectInsertedList(List<Object> newObjectInsertedList) {
		this.objectInsertedList = newObjectInsertedList;
	}
	public void setObjectDeletedList(List<Object> newObjectDeletedList) {
		this.objectDeletedList = newObjectDeletedList;
	}
	public void setObjectEditedList(List<Object> newObjectEditedList) {
		this.objectEditedList = newObjectEditedList;
	}
	public void setObjectEditedReversedList(List<Object> newObjectEditedReversedList) {
		this.objectEditedReversedList = newObjectEditedReversedList;
	}
}