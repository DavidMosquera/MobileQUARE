package android.mobilequare.analyst.model.dao;
import java.util.List;
import java.util.ArrayList;
import android.mobilequare.analyst.model.po.Concept;
import android.mobilequare.analyst.exception.StorageException;
import android.mobilequare.analyst.exception.ConstraintCheckingException;
public abstract class DAOConcept {
	//UNDO - REDO LISTS
	private List<Concept> conceptInsertedList;
	private List<Concept> conceptDeletedList;
	private List<Concept> conceptEditedList;
	private List<Concept> conceptEditedReversedList;
	//CONSTRUCTOR
	public DAOConcept() {
		this.conceptInsertedList = new ArrayList<Concept>();
		this.conceptDeletedList = new ArrayList<Concept>();
		this.conceptEditedList = new ArrayList<Concept>();
		this.conceptEditedReversedList = new ArrayList<Concept>();
	}
	//OPERATIONS
	public abstract List<Concept> listConcept(String query) throws StorageException;
	public abstract boolean deleteConcept(Concept concept, boolean isUndoOrRedo) throws StorageException;
	public abstract Concept insertConcept(Concept concept, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException;
	public abstract Concept editConcept(Concept concept, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException;
	//GETTERS
	public List<Concept> getConceptInsertedList() {
		return this.conceptInsertedList;
	}
	public List<Concept> getConceptDeletedList() {
		return this.conceptDeletedList;
	}
	public List<Concept> getConceptEditedList() {
		return this.conceptEditedList;
	}
	public List<Concept> getConceptEditedReversedList() {
		return this.conceptEditedReversedList;
	}
	//SETTERS
	public void setConceptInsertedList(List<Concept> newConceptInsertedList) {
		this.conceptInsertedList = newConceptInsertedList;
	}
	public void setConceptDeletedList(List<Concept> newConceptDeletedList) {
		this.conceptDeletedList = newConceptDeletedList;
	}
	public void setConceptEditedList(List<Concept> newConceptEditedList) {
		this.conceptEditedList = newConceptEditedList;
	}
	public void setConceptEditedReversedList(List<Concept> newConceptEditedReversedList) {
		this.conceptEditedReversedList = newConceptEditedReversedList;
	}
}