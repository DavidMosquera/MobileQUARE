package android.mobilequare.analyst.model.dao;
import java.util.List;
import java.util.ArrayList;
import android.mobilequare.analyst.model.po.Analyst;
import android.mobilequare.analyst.exception.StorageException;
import android.mobilequare.analyst.exception.ConstraintCheckingException;
public abstract class DAOAnalyst {
	//UNDO - REDO LISTS
	private List<Analyst> analystInsertedList;
	private List<Analyst> analystDeletedList;
	private List<Analyst> analystEditedList;
	private List<Analyst> analystEditedReversedList;
	//CONSTRUCTOR
	public DAOAnalyst() {
		this.analystInsertedList = new ArrayList<Analyst>();
		this.analystDeletedList = new ArrayList<Analyst>();
		this.analystEditedList = new ArrayList<Analyst>();
		this.analystEditedReversedList = new ArrayList<Analyst>();
	}
	//OPERATIONS
	public abstract List<Analyst> listAnalyst(String query) throws StorageException;
	public abstract boolean deleteAnalyst(Analyst analyst, boolean isUndoOrRedo) throws StorageException;
	public abstract Analyst insertAnalyst(Analyst analyst, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException;
	public abstract Analyst editAnalyst(Analyst analyst, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException;
	//GETTERS
	public List<Analyst> getAnalystInsertedList() {
		return this.analystInsertedList;
	}
	public List<Analyst> getAnalystDeletedList() {
		return this.analystDeletedList;
	}
	public List<Analyst> getAnalystEditedList() {
		return this.analystEditedList;
	}
	public List<Analyst> getAnalystEditedReversedList() {
		return this.analystEditedReversedList;
	}
	//SETTERS
	public void setAnalystInsertedList(List<Analyst> newAnalystInsertedList) {
		this.analystInsertedList = newAnalystInsertedList;
	}
	public void setAnalystDeletedList(List<Analyst> newAnalystDeletedList) {
		this.analystDeletedList = newAnalystDeletedList;
	}
	public void setAnalystEditedList(List<Analyst> newAnalystEditedList) {
		this.analystEditedList = newAnalystEditedList;
	}
	public void setAnalystEditedReversedList(List<Analyst> newAnalystEditedReversedList) {
		this.analystEditedReversedList = newAnalystEditedReversedList;
	}
}