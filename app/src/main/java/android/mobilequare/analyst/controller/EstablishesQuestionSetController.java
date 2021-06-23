package android.mobilequare.analyst.controller;
import android.mobilequare.analyst.command.Command;
import android.content.Context;

import java.util.List;
import java.util.ArrayList;
import android.mobilequare.analyst.exception.*;
import android.mobilequare.analyst.view.EstablishesQuestionSetView;
import android.mobilequare.analyst.model.dao.*;
import android.mobilequare.analyst.model.factory.*;
import android.mobilequare.analyst.model.po.*;

public class EstablishesQuestionSetController implements Command {
	private QuestionSet questionSet;
	//DATA ACCESS OBJECTS (DAO)
	private DAOProject daoProject;
	private DAOQuestionSet daoQuestionSet;
	//CONSTRUCTOR 
	public EstablishesQuestionSetController(Context context) {
		questionSet = new QuestionSet();
		daoProject = new LocalStorageFactory(context).getDAOProject();
		daoQuestionSet = new LocalStorageFactory(context).getDAOQuestionSet();
	}
	//OPERATIONS
	public void establishesQuestionSet(EstablishesQuestionSetView establishesQuestionSetView, Project viewProject,
			String viewTextTitle) {
		//TYPE AND MANDATORY CONSTRAINTS 
		//PROJECT NULLABILITY CHECKING
		if (viewProject == null) {
			viewProject = new Project();
			viewProject.set_Id("");
		}
		//QUESTIONSET.TITLE TYPE AND MANDATORY CONSTRAINTS CHECKING 
		if (viewTextTitle.compareTo("") == 0) {
			establishesQuestionSetView.establishesQuestionSetFails("QuestionSet.Title is mandatory.");
			establishesQuestionSetView.questionSetTitleNewInfo("QuestionSet.Title is mandatory.");
			return;
		}
		String viewTitle = viewTextTitle;
		//LOCAL VARIABLES 
		//"ANALYST ESTABLISHES QUESTIONSET" SPECIFICATION
		try {
			questionSet.set_idProject(viewProject.get_Id());
			questionSet.setTitle(viewTitle);
			daoQuestionSet.insertQuestionSet(questionSet, false);
			establishesQuestionSetView.establishesQuestionSetSucceeds();
		} catch (ConstraintCheckingException e) {
			try {
				this.undo();
			} catch (Exception e1) {
			}
			establishesQuestionSetView.establishesQuestionSetFails(e.getMessage());
		} catch (StorageException e) {
			try {
				this.undo();
			} catch (Exception e1) {
			}
			establishesQuestionSetView.establishesQuestionSetFails(e.getMessage());
		}
	}

	public List<Project> listsProject(EstablishesQuestionSetView establishesQuestionSetView, String query) {
		try {
			List<Project> projectList = daoProject.listProject(query);
			return projectList;
		} catch (StorageException e) {
			establishesQuestionSetView.establishesQuestionSetFails(e.getMessage());
			return new ArrayList<Project>();
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
		//UNDO STATEMENTS FOR QUESTIONSET OBJECTS
		for (QuestionSet questionSet : daoQuestionSet.getQuestionSetDeletedList()) {
			daoQuestionSet.insertQuestionSet(questionSet, true);
		}
		for (QuestionSet questionSet : daoQuestionSet.getQuestionSetEditedReversedList()) {
			daoQuestionSet.editQuestionSet(questionSet, true);
		}
		for (QuestionSet questionSet : daoQuestionSet.getQuestionSetInsertedList()) {
			daoQuestionSet.deleteQuestionSet(questionSet, true);
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
		//REDO STATEMENTS FOR QUESTIONSET OBJECTS
		for (QuestionSet questionSet : daoQuestionSet.getQuestionSetInsertedList()) {
			daoQuestionSet.insertQuestionSet(questionSet, true);
		}
		for (QuestionSet questionSet : daoQuestionSet.getQuestionSetEditedList()) {
			daoQuestionSet.editQuestionSet(questionSet, true);
		}
		for (QuestionSet questionSet : daoQuestionSet.getQuestionSetDeletedList()) {
			daoQuestionSet.deleteQuestionSet(questionSet, true);
		}
	}
}