package android.mobilequare.analyst.model.dao;
import java.util.List;
import java.util.ArrayList;
import android.mobilequare.analyst.model.po.Project;
import android.mobilequare.analyst.exception.StorageException;
import android.mobilequare.analyst.exception.ConstraintCheckingException;
public abstract class DAOProject {
	//UNDO - REDO LISTS
	private List<Project> projectInsertedList;
	private List<Project> projectDeletedList;
	private List<Project> projectEditedList;
	private List<Project> projectEditedReversedList;
	//CONSTRUCTOR
	public DAOProject() {
		this.projectInsertedList = new ArrayList<Project>();
		this.projectDeletedList = new ArrayList<Project>();
		this.projectEditedList = new ArrayList<Project>();
		this.projectEditedReversedList = new ArrayList<Project>();
	}
	//OPERATIONS
	public abstract List<Project> listProject(String query) throws StorageException;
	public abstract boolean deleteProject(Project project, boolean isUndoOrRedo) throws StorageException;
	public abstract Project insertProject(Project project, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException;
	public abstract Project editProject(Project project, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException;
	//GETTERS
	public List<Project> getProjectInsertedList() {
		return this.projectInsertedList;
	}
	public List<Project> getProjectDeletedList() {
		return this.projectDeletedList;
	}
	public List<Project> getProjectEditedList() {
		return this.projectEditedList;
	}
	public List<Project> getProjectEditedReversedList() {
		return this.projectEditedReversedList;
	}
	//SETTERS
	public void setProjectInsertedList(List<Project> newProjectInsertedList) {
		this.projectInsertedList = newProjectInsertedList;
	}
	public void setProjectDeletedList(List<Project> newProjectDeletedList) {
		this.projectDeletedList = newProjectDeletedList;
	}
	public void setProjectEditedList(List<Project> newProjectEditedList) {
		this.projectEditedList = newProjectEditedList;
	}
	public void setProjectEditedReversedList(List<Project> newProjectEditedReversedList) {
		this.projectEditedReversedList = newProjectEditedReversedList;
	}
}