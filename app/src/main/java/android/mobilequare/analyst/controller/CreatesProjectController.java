package android.mobilequare.analyst.controller;
import android.mobilequare.analyst.command.Command;
import android.content.Context;
import android.mobilequare.analyst.exception.*;
import android.mobilequare.analyst.view.CreatesProjectView;
import android.mobilequare.analyst.model.dao.*;
import android.mobilequare.analyst.model.factory.*;
import android.mobilequare.analyst.model.po.*;

public class CreatesProjectController implements Command {
	private Project project;
	//DATA ACCESS OBJECTS (DAO)
	private DAOProject daoProject;
	//CONSTRUCTOR 
	public CreatesProjectController(Context context) {
		project = new Project();
		daoProject = new LocalStorageFactory(context).getDAOProject();
	}
	//OPERATIONS
	public void createsProject(CreatesProjectView createsProjectView, String viewTextName, String viewTextDescription) {
		//TYPE AND MANDATORY CONSTRAINTS 
		//PROJECT.NAME TYPE AND MANDATORY CONSTRAINTS CHECKING 
		if (viewTextName.compareTo("") == 0) {
			createsProjectView.createsProjectFails("Project.Name is mandatory.");
			createsProjectView.projectNameNewInfo("Project.Name is mandatory.");
			return;
		}
		String viewName = viewTextName;
		//PROJECT.DESCRIPTION TYPE AND MANDATORY CONSTRAINTS CHECKING 
		if (viewTextDescription.compareTo("") == 0) {
			createsProjectView.createsProjectFails("Project.Description is mandatory.");
			createsProjectView.projectDescriptionNewInfo("Project.Description is mandatory.");
			return;
		}
		String viewDescription = viewTextDescription;
		//LOCAL VARIABLES 
		//"ANALYST CREATES PROJECT" SPECIFICATION
		try {
			project.setName(viewName);
			project.setDescription(viewDescription);
			daoProject.insertProject(project, false);
			createsProjectView.createsProjectSucceeds();
		} catch (ConstraintCheckingException e) {
			try {
				this.undo();
			} catch (Exception e1) {
			}
			createsProjectView.createsProjectFails(e.getMessage());
		} catch (StorageException e) {
			try {
				this.undo();
			} catch (Exception e1) {
			}
			createsProjectView.createsProjectFails(e.getMessage());
		}
	}

	public void undo() throws Exception {
		//UNDO STATEMENTS FOR PROJECT OBJECTS
		for (Project project : daoProject.getProjectDeletedList()) {
			daoProject.insertProject(project, true);
		}
		for (Project project : daoProject.getProjectEditedReversedList()) {
			daoProject.editProject(project, true);
		}
		for (Project project : daoProject.getProjectInsertedList()) {
			daoProject.deleteProject(project, true);
		}
	}
	public void redo() throws Exception {
		//REDO STATEMENTS FOR PROJECT OBJECTS
		for (Project project : daoProject.getProjectInsertedList()) {
			daoProject.insertProject(project, true);
		}
		for (Project project : daoProject.getProjectEditedList()) {
			daoProject.editProject(project, true);
		}
		for (Project project : daoProject.getProjectDeletedList()) {
			daoProject.deleteProject(project, true);
		}
	}
}