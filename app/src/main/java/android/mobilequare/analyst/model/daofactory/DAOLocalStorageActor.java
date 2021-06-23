package android.mobilequare.analyst.model.daofactory;
import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.mobilequare.analyst.model.LocalStorageContentProvider;
import android.mobilequare.analyst.model.po.Actor;
import android.mobilequare.analyst.exception.StorageException;
import android.mobilequare.analyst.exception.ConstraintCheckingException;
import android.mobilequare.analyst.model.dao.DAOActor;
public class DAOLocalStorageActor extends DAOActor {
	private ContentResolver contentResolver;
	public DAOLocalStorageActor(Context context) {
		super();
		this.contentResolver = context.getContentResolver();
	}
	public List<Actor> listActor(String query) throws StorageException {
		//LIST IMPLEMENTATION FOR LOCALSTORAGE OF ACTOR 
		//return list(Actor; LocalStorage; query)
		if (query == null || query.compareTo("") == 0) {
			query = "1";
		}
		List<Actor> actorList = new ArrayList<Actor>();
		String sortOrder = "_ID ASC";
		String[] selectionArguments = null;
		Actor actor;
		Cursor cursor = contentResolver.query(LocalStorageContentProvider.ACTOR_URI, null, query, selectionArguments,
				sortOrder);
		try {
			if (cursor.moveToFirst()) {
				do {
					actor = new Actor();
					actor.set_Id(cursor.getString(cursor.getColumnIndex("_ID")));
					actor.setName(cursor.getString(cursor.getColumnIndex("NAME")));
					actorList.add(actor);
				} while (cursor.moveToNext());
			}
		} catch (Exception e) {
			throw new StorageException();
		} finally {
			if (cursor != null && !cursor.isClosed()) {
				cursor.close();
			}
		}
		return actorList;
	}
	public boolean deleteActor(Actor actor, boolean isUndoOrRedo) throws StorageException {
		//DELETE IMPLEMENTATION FOR LOCALSTORAGE OF ACTOR 
		try {
			contentResolver.delete(LocalStorageContentProvider.ACTOR_URI, "_id =\"" + actor.get_Id() + "\"", null);
		} catch (Exception e) {
			throw new StorageException();
		}
		if (!isUndoOrRedo) {
			this.getActorDeletedList().add(actor);
		}
		return true;
	}
	public Actor insertActor(Actor actor, boolean isUndoOrRedo) throws StorageException, ConstraintCheckingException {
		//INSERT IMPLEMENTATION FOR LOCALSTORAGE OF ACTOR 
		try {
			ContentValues actorValues = new ContentValues();
			actorValues.put("_ID", actor.get_Id());
			actorValues.put("NAME", actor.getName());
			Uri uri = contentResolver.insert(LocalStorageContentProvider.ACTOR_URI, actorValues);
		} catch (Exception e) {
			throw new StorageException();
		}
		if (!isUndoOrRedo) {
			this.getActorInsertedList().add(actor);
		}
		return actor;
	}
	public Actor editActor(Actor actor, boolean isUndoOrRedo) throws StorageException, ConstraintCheckingException {
		//EDIT IMPLEMENTATION FOR LOCAL STORAGE OF ACTOR 
		actor.checkConstraints();
		Actor actorBeforeEdition = listActor("_id = \"" + actor.get_Id() + "\"").get(0);
		try {
			ContentValues actorValues = new ContentValues();
			actorValues.put("NAME", actor.getName());
			contentResolver.update(LocalStorageContentProvider.ACTOR_URI, actorValues,
					"_id = \"" + actor.get_Id() + "\"", null);
		} catch (Exception e) {
			throw new StorageException();
		}
		if (!isUndoOrRedo) {
			this.getActorEditedReversedList().add(0, actorBeforeEdition);
			this.getActorEditedList().add(actor);
		}
		return actor;
	}
}