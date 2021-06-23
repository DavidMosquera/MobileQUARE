package android.mobilequare.analyst.model.dao;
import java.util.List;
import java.util.ArrayList;
import android.mobilequare.analyst.model.po.ContainerConcept;
import android.mobilequare.analyst.exception.StorageException;
import android.mobilequare.analyst.exception.ConstraintCheckingException;
public abstract class DAOContainerConcept {
	//UNDO - REDO LISTS
	private List<ContainerConcept> containerConceptInsertedList;
	private List<ContainerConcept> containerConceptDeletedList;
	private List<ContainerConcept> containerConceptEditedList;
	private List<ContainerConcept> containerConceptEditedReversedList;
	//CONSTRUCTOR
	public DAOContainerConcept() {
		this.containerConceptInsertedList = new ArrayList<ContainerConcept>();
		this.containerConceptDeletedList = new ArrayList<ContainerConcept>();
		this.containerConceptEditedList = new ArrayList<ContainerConcept>();
		this.containerConceptEditedReversedList = new ArrayList<ContainerConcept>();
	}
	//OPERATIONS
	public abstract List<ContainerConcept> listContainerConcept(String query) throws StorageException;
	public abstract boolean deleteContainerConcept(ContainerConcept containerConcept, boolean isUndoOrRedo)
			throws StorageException;
	public abstract ContainerConcept insertContainerConcept(ContainerConcept containerConcept, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException;
	public abstract ContainerConcept editContainerConcept(ContainerConcept containerConcept, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException;
	//GETTERS
	public List<ContainerConcept> getContainerConceptInsertedList() {
		return this.containerConceptInsertedList;
	}
	public List<ContainerConcept> getContainerConceptDeletedList() {
		return this.containerConceptDeletedList;
	}
	public List<ContainerConcept> getContainerConceptEditedList() {
		return this.containerConceptEditedList;
	}
	public List<ContainerConcept> getContainerConceptEditedReversedList() {
		return this.containerConceptEditedReversedList;
	}
	//SETTERS
	public void setContainerConceptInsertedList(List<ContainerConcept> newContainerConceptInsertedList) {
		this.containerConceptInsertedList = newContainerConceptInsertedList;
	}
	public void setContainerConceptDeletedList(List<ContainerConcept> newContainerConceptDeletedList) {
		this.containerConceptDeletedList = newContainerConceptDeletedList;
	}
	public void setContainerConceptEditedList(List<ContainerConcept> newContainerConceptEditedList) {
		this.containerConceptEditedList = newContainerConceptEditedList;
	}
	public void setContainerConceptEditedReversedList(List<ContainerConcept> newContainerConceptEditedReversedList) {
		this.containerConceptEditedReversedList = newContainerConceptEditedReversedList;
	}
}