package android.mobilequare.analyst.model.dao;
import java.util.List;
import java.util.ArrayList;
import android.mobilequare.analyst.model.po.AnalystConfiguration;
import android.mobilequare.analyst.exception.StorageException;
import android.mobilequare.analyst.exception.ConstraintCheckingException;
public abstract class DAOAnalystConfiguration {
	//UNDO - REDO LISTS
	private List<AnalystConfiguration> analystConfigurationInsertedList;
	private List<AnalystConfiguration> analystConfigurationDeletedList;
	private List<AnalystConfiguration> analystConfigurationEditedList;
	private List<AnalystConfiguration> analystConfigurationEditedReversedList;
	//CONSTRUCTOR
	public DAOAnalystConfiguration() {
		this.analystConfigurationInsertedList = new ArrayList<AnalystConfiguration>();
		this.analystConfigurationDeletedList = new ArrayList<AnalystConfiguration>();
		this.analystConfigurationEditedList = new ArrayList<AnalystConfiguration>();
		this.analystConfigurationEditedReversedList = new ArrayList<AnalystConfiguration>();
	}
	//OPERATIONS
	public abstract List<AnalystConfiguration> listAnalystConfiguration(String query) throws StorageException;
	public abstract boolean deleteAnalystConfiguration(AnalystConfiguration analystConfiguration, boolean isUndoOrRedo)
			throws StorageException;
	public abstract AnalystConfiguration insertAnalystConfiguration(AnalystConfiguration analystConfiguration,
			boolean isUndoOrRedo) throws StorageException, ConstraintCheckingException;
	public abstract AnalystConfiguration editAnalystConfiguration(AnalystConfiguration analystConfiguration,
			boolean isUndoOrRedo) throws StorageException, ConstraintCheckingException;
	//GETTERS
	public List<AnalystConfiguration> getAnalystConfigurationInsertedList() {
		return this.analystConfigurationInsertedList;
	}
	public List<AnalystConfiguration> getAnalystConfigurationDeletedList() {
		return this.analystConfigurationDeletedList;
	}
	public List<AnalystConfiguration> getAnalystConfigurationEditedList() {
		return this.analystConfigurationEditedList;
	}
	public List<AnalystConfiguration> getAnalystConfigurationEditedReversedList() {
		return this.analystConfigurationEditedReversedList;
	}
	//SETTERS
	public void setAnalystConfigurationInsertedList(List<AnalystConfiguration> newAnalystConfigurationInsertedList) {
		this.analystConfigurationInsertedList = newAnalystConfigurationInsertedList;
	}
	public void setAnalystConfigurationDeletedList(List<AnalystConfiguration> newAnalystConfigurationDeletedList) {
		this.analystConfigurationDeletedList = newAnalystConfigurationDeletedList;
	}
	public void setAnalystConfigurationEditedList(List<AnalystConfiguration> newAnalystConfigurationEditedList) {
		this.analystConfigurationEditedList = newAnalystConfigurationEditedList;
	}
	public void setAnalystConfigurationEditedReversedList(
			List<AnalystConfiguration> newAnalystConfigurationEditedReversedList) {
		this.analystConfigurationEditedReversedList = newAnalystConfigurationEditedReversedList;
	}
}