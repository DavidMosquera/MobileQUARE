package android.mobilequare.analyst.model.daofactory;
import java.util.List;
import java.util.ArrayList;
import android.mobilequare.analyst.model.po.Actor;
import android.mobilequare.analyst.exception.StorageException;
import android.mobilequare.analyst.exception.ConstraintCheckingException;
import android.mobilequare.analyst.model.dao.DAOActor;
public class DAORemoteStorageActor extends DAOActor {
	public List<Actor> listActor(String query) throws StorageException {
		//LIST IMPLEMENTATION FOR REMOTE STORAGE OF ACTOR 
		//TODO IMPLEMENT THIS
		return new ArrayList<Actor>();
	}
	public boolean deleteActor(Actor actor, boolean isUndoOrRedo) throws StorageException {
		//DELETE IMPLEMENTATION FOR REMOTE STORAGE OF ACTOR 
		//TODO IMPLEMENT THIS
		return false;
	}
	public Actor insertActor(Actor actor, boolean isUndoOrRedo) throws StorageException, ConstraintCheckingException {
		//INSERT IMPLEMENTATION FOR REMOTE STORAGE OF ACTOR 
		actor.checkConstraints();
		//TODO IMPLEMENT THIS
		return null;
	}
	public Actor editActor(Actor actor, boolean isUndoOrRedo) throws StorageException, ConstraintCheckingException {
		//EDIT IMPLEMENTATION FOR REMOTE STORAGE OF ACTOR 
		actor.checkConstraints();
		//TODO IMPLEMENT THIS
		return null;
	}
}