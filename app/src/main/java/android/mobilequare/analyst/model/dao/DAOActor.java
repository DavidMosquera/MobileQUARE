package android.mobilequare.analyst.model.dao;
import java.util.List;
import java.util.ArrayList;
import android.mobilequare.analyst.model.po.Actor;
import android.mobilequare.analyst.exception.StorageException;
import android.mobilequare.analyst.exception.ConstraintCheckingException;
public abstract class DAOActor {
	//UNDO - REDO LISTS
	private List<Actor> actorInsertedList;
	private List<Actor> actorDeletedList;
	private List<Actor> actorEditedList;
	private List<Actor> actorEditedReversedList;
	//CONSTRUCTOR
	public DAOActor() {
		this.actorInsertedList = new ArrayList<Actor>();
		this.actorDeletedList = new ArrayList<Actor>();
		this.actorEditedList = new ArrayList<Actor>();
		this.actorEditedReversedList = new ArrayList<Actor>();
	}
	//OPERATIONS
	public abstract List<Actor> listActor(String query) throws StorageException;
	public abstract boolean deleteActor(Actor actor, boolean isUndoOrRedo) throws StorageException;
	public abstract Actor insertActor(Actor actor, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException;
	public abstract Actor editActor(Actor actor, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException;
	//GETTERS
	public List<Actor> getActorInsertedList() {
		return this.actorInsertedList;
	}
	public List<Actor> getActorDeletedList() {
		return this.actorDeletedList;
	}
	public List<Actor> getActorEditedList() {
		return this.actorEditedList;
	}
	public List<Actor> getActorEditedReversedList() {
		return this.actorEditedReversedList;
	}
	//SETTERS
	public void setActorInsertedList(List<Actor> newActorInsertedList) {
		this.actorInsertedList = newActorInsertedList;
	}
	public void setActorDeletedList(List<Actor> newActorDeletedList) {
		this.actorDeletedList = newActorDeletedList;
	}
	public void setActorEditedList(List<Actor> newActorEditedList) {
		this.actorEditedList = newActorEditedList;
	}
	public void setActorEditedReversedList(List<Actor> newActorEditedReversedList) {
		this.actorEditedReversedList = newActorEditedReversedList;
	}
}