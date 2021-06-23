package android.mobilequare.analyst.model.daofactory;
import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.mobilequare.analyst.model.LocalStorageContentProvider;
import android.mobilequare.analyst.model.po.Project;
import android.mobilequare.analyst.exception.StorageException;
import android.mobilequare.analyst.exception.ConstraintCheckingException;
import android.mobilequare.analyst.model.dao.DAOProject;
public class DAOLocalStorageProject extends DAOProject {
	private ContentResolver contentResolver;
	public DAOLocalStorageProject(Context context) {
		super();
		this.contentResolver = context.getContentResolver();
	}
	public List<Project> listProject(String query) throws StorageException {
		//LIST IMPLEMENTATION FOR LOCALSTORAGE OF PROJECT 
		//return list(Project; LocalStorage; query)
		if (query == null || query.compareTo("") == 0) {
			query = "1";
		}
		List<Project> projectList = new ArrayList<Project>();
		String sortOrder = "_ID ASC";
		String[] selectionArguments = null;
		Project project;
		Cursor cursor = contentResolver.query(LocalStorageContentProvider.PROJECT_URI, null, query, selectionArguments,
				sortOrder);
		try {
			if (cursor.moveToFirst()) {
				do {
					project = new Project();
					project.set_Id(cursor.getString(cursor.getColumnIndex("_ID")));
					project.setName(cursor.getString(cursor.getColumnIndex("NAME")));
					project.setDescription(cursor.getString(cursor.getColumnIndex("DESCRIPTION")));
					projectList.add(project);
				} while (cursor.moveToNext());
			}
		} catch (Exception e) {
			throw new StorageException();
		} finally {
			if (cursor != null && !cursor.isClosed()) {
				cursor.close();
			}
		}
		return projectList;
	}
	public boolean deleteProject(Project project, boolean isUndoOrRedo) throws StorageException {
		//DELETE IMPLEMENTATION FOR LOCALSTORAGE OF PROJECT 
		try {
			contentResolver.delete(LocalStorageContentProvider.PROJECT_URI, "_id =\"" + project.get_Id() + "\"", null);
		} catch (Exception e) {
			throw new StorageException();
		}
		if (!isUndoOrRedo) {
			this.getProjectDeletedList().add(project);
		}
		return true;
	}
	public Project insertProject(Project project, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException {
		//INSERT IMPLEMENTATION FOR LOCALSTORAGE OF PROJECT 
		try {
			ContentValues projectValues = new ContentValues();
			projectValues.put("_ID", project.get_Id());
			projectValues.put("NAME", project.getName());
			projectValues.put("DESCRIPTION", project.getDescription());
			Uri uri = contentResolver.insert(LocalStorageContentProvider.PROJECT_URI, projectValues);
		} catch (Exception e) {
			throw new StorageException();
		}
		if (!isUndoOrRedo) {
			this.getProjectInsertedList().add(project);
		}
		return project;
	}
	public Project editProject(Project project, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException {
		//EDIT IMPLEMENTATION FOR LOCAL STORAGE OF PROJECT 
		project.checkConstraints();
		Project projectBeforeEdition = listProject("_id = \"" + project.get_Id() + "\"").get(0);
		try {
			ContentValues projectValues = new ContentValues();
			projectValues.put("NAME", project.getName());
			projectValues.put("DESCRIPTION", project.getDescription());
			contentResolver.update(LocalStorageContentProvider.PROJECT_URI, projectValues,
					"_id = \"" + project.get_Id() + "\"", null);
		} catch (Exception e) {
			throw new StorageException();
		}
		if (!isUndoOrRedo) {
			this.getProjectEditedReversedList().add(0, projectBeforeEdition);
			this.getProjectEditedList().add(project);
		}
		return project;
	}
}