package android.mobilequare.analyst.model.dao;
import java.util.List;
import java.util.ArrayList;
import android.mobilequare.analyst.model.po.Function;
import android.mobilequare.analyst.exception.StorageException;
import android.mobilequare.analyst.exception.ConstraintCheckingException;
public abstract class DAOFunction {
	//UNDO - REDO LISTS
	private List<Function> functionInsertedList;
	private List<Function> functionDeletedList;
	private List<Function> functionEditedList;
	private List<Function> functionEditedReversedList;
	//CONSTRUCTOR
	public DAOFunction() {
		this.functionInsertedList = new ArrayList<Function>();
		this.functionDeletedList = new ArrayList<Function>();
		this.functionEditedList = new ArrayList<Function>();
		this.functionEditedReversedList = new ArrayList<Function>();
	}
	//OPERATIONS
	public abstract List<Function> listFunction(String query) throws StorageException;
	public abstract boolean deleteFunction(Function function, boolean isUndoOrRedo) throws StorageException;
	public abstract Function insertFunction(Function function, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException;
	public abstract Function editFunction(Function function, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException;
	//GETTERS
	public List<Function> getFunctionInsertedList() {
		return this.functionInsertedList;
	}
	public List<Function> getFunctionDeletedList() {
		return this.functionDeletedList;
	}
	public List<Function> getFunctionEditedList() {
		return this.functionEditedList;
	}
	public List<Function> getFunctionEditedReversedList() {
		return this.functionEditedReversedList;
	}
	//SETTERS
	public void setFunctionInsertedList(List<Function> newFunctionInsertedList) {
		this.functionInsertedList = newFunctionInsertedList;
	}
	public void setFunctionDeletedList(List<Function> newFunctionDeletedList) {
		this.functionDeletedList = newFunctionDeletedList;
	}
	public void setFunctionEditedList(List<Function> newFunctionEditedList) {
		this.functionEditedList = newFunctionEditedList;
	}
	public void setFunctionEditedReversedList(List<Function> newFunctionEditedReversedList) {
		this.functionEditedReversedList = newFunctionEditedReversedList;
	}
}