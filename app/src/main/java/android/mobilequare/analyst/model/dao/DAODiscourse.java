package android.mobilequare.analyst.model.dao;
import java.util.List;
import java.util.ArrayList;
import android.mobilequare.analyst.model.po.Discourse;
import android.mobilequare.analyst.exception.StorageException;
import android.mobilequare.analyst.exception.ConstraintCheckingException;
public abstract class DAODiscourse {
	//UNDO - REDO LISTS
	private List<Discourse> discourseInsertedList;
	private List<Discourse> discourseDeletedList;
	private List<Discourse> discourseEditedList;
	private List<Discourse> discourseEditedReversedList;
	//CONSTRUCTOR
	public DAODiscourse() {
		this.discourseInsertedList = new ArrayList<Discourse>();
		this.discourseDeletedList = new ArrayList<Discourse>();
		this.discourseEditedList = new ArrayList<Discourse>();
		this.discourseEditedReversedList = new ArrayList<Discourse>();
	}
	//OPERATIONS
	public abstract List<Discourse> listDiscourse(String query) throws StorageException;
	public abstract boolean deleteDiscourse(Discourse discourse, boolean isUndoOrRedo) throws StorageException;
	public abstract Discourse insertDiscourse(Discourse discourse, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException;
	public abstract Discourse editDiscourse(Discourse discourse, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException;
	//GETTERS
	public List<Discourse> getDiscourseInsertedList() {
		return this.discourseInsertedList;
	}
	public List<Discourse> getDiscourseDeletedList() {
		return this.discourseDeletedList;
	}
	public List<Discourse> getDiscourseEditedList() {
		return this.discourseEditedList;
	}
	public List<Discourse> getDiscourseEditedReversedList() {
		return this.discourseEditedReversedList;
	}
	//SETTERS
	public void setDiscourseInsertedList(List<Discourse> newDiscourseInsertedList) {
		this.discourseInsertedList = newDiscourseInsertedList;
	}
	public void setDiscourseDeletedList(List<Discourse> newDiscourseDeletedList) {
		this.discourseDeletedList = newDiscourseDeletedList;
	}
	public void setDiscourseEditedList(List<Discourse> newDiscourseEditedList) {
		this.discourseEditedList = newDiscourseEditedList;
	}
	public void setDiscourseEditedReversedList(List<Discourse> newDiscourseEditedReversedList) {
		this.discourseEditedReversedList = newDiscourseEditedReversedList;
	}
}