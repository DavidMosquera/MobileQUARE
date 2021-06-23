package android.mobilequare.analyst.controller;
import android.mobilequare.analyst.command.Command;
import android.content.Context;

import java.util.List;
import java.util.ArrayList;
import android.mobilequare.analyst.exception.*;
import android.mobilequare.analyst.view.ProvidesDiscourseView;
import android.mobilequare.analyst.model.dao.*;
import android.mobilequare.analyst.model.factory.*;
import android.mobilequare.analyst.model.po.*;

public class ProvidesDiscourseController implements Command {
	private Discourse discourse;
	//DATA ACCESS OBJECTS (DAO)
	private DAODiscourse daoDiscourse;
	private DAOProject daoProject;
	private DAOActor daoActor;
	private DAOFunction daoFunction;
	private DAOContainerConcept daoContainerConcept;
	private DAOAttribute daoAttribute;
	private DAOAttributeRelationship daoAttributeRelationship;
	private DAOConcept daoConcept;
	private DAOObject daoObject;
	//CONSTRUCTOR 
	public ProvidesDiscourseController(Context context) {
		discourse = new Discourse();
		daoDiscourse = new LocalStorageFactory(context).getDAODiscourse();
		daoProject = new LocalStorageFactory(context).getDAOProject();
		daoActor = new LocalStorageFactory(context).getDAOActor();
		daoFunction = new LocalStorageFactory(context).getDAOFunction();
		daoContainerConcept = new LocalStorageFactory(context).getDAOContainerConcept();
		daoAttribute = new LocalStorageFactory(context).getDAOAttribute();
		daoAttributeRelationship = new LocalStorageFactory(context).getDAOAttributeRelationship();
		daoConcept = new LocalStorageFactory(context).getDAOConcept();
		daoObject = new LocalStorageFactory(context).getDAOObject();
	}
	//OPERATIONS
	public void providesDiscourse(ProvidesDiscourseView providesDiscourseView, Project viewProject, String viewTextContent){
		//TYPE AND MANDATORY CONSTRAINTS 
		//PROJECT NULLABILITY CHECKING
		if (viewProject == null){
			viewProject = new Project();
			viewProject.set_Id("");
		}
		//DISCOURSE.CONTENT TYPE AND MANDATORY CONSTRAINTS CHECKING 
		if (viewTextContent.compareTo("") == 0){
			providesDiscourseView.providesDiscourseFails("Discourse.Content is mandatory.");
			providesDiscourseView.discourseContentNewInfo("Discourse.Content is mandatory.");
			return;
		}
		String viewContent = viewTextContent;
		//LOCAL VARIABLES 
		String concatenatedContent;
		//"ANALYST PROVIDES DISCOURSE" SPECIFICATION
		try{  
			discourse.set_idProject(viewProject.get_Id());   
			discourse.setContent(viewContent);  
			concatenatedContent = (discourse.getContent());  
			concatenatedContent = ((concatenatedContent) + (discourse.getContent()));  
			(null);  
			concept.setIdentification((project.getName()) + (concept.getName()));   
			daoActor.insertActor(actor, false);   
			concept.setIdentification((project.getName()) + (concept.getName()));   
			daoObject.insertObject(object, false);    
			daoFunction.insertFunction(function, false);   
			concept.setIdentification((project.getName()) + (concept.getName()));   
			daoContainerConcept.insertContainerConcept(containerConcept, false);   
			concept.setIdentification((project.getName()) + (concept.getName()));   
			daoAttribute.insertAttribute(attribute, false);    
			daoAttributeRelationship.insertAttributeRelationship(attributeRelationship, false);    
			daoDiscourse.insertDiscourse(discourse, false);                  
			providesDiscourseView.providesDiscourseSucceeds();
		}
		catch (ConstraintCheckingException e){
			try{this.undo();} catch(Exception e1){}
			providesDiscourseView.providesDiscourseFails(e.getMessage());
		}
		catch (StorageException e){
			try{this.undo();} catch(Exception e1){}
			providesDiscourseView.providesDiscourseFails(e.getMessage());
		}
	}

	public List<Project> listsProject(ProvidesDiscourseView providesDiscourseView, String query) {
		try {
			List<Project> projectList = daoProject.listProject(query);
			return projectList;
		} catch (StorageException e) {
			providesDiscourseView.providesDiscourseFails(e.getMessage());
			return new ArrayList<Project>();
		}
	}
	public void undo() throws Exception {
		//UNDO STATEMENTS FOR DISCOURSE OBJECTS
		for (Discourse discourse : daoDiscourse.getDiscourseDeletedList()) {
			daoDiscourse.insertDiscourse(discourse, true);
		}
		for (Discourse discourse : daoDiscourse.getDiscourseEditedReversedList()) {
			daoDiscourse.editDiscourse(discourse, true);
		}
		for (Discourse discourse : daoDiscourse.getDiscourseInsertedList()) {
			daoDiscourse.deleteDiscourse(discourse, true);
		}
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
		//UNDO STATEMENTS FOR ACTOR OBJECTS
		for (Actor actor : daoActor.getActorDeletedList()) {
			daoActor.insertActor(actor, true);
		}
		for (Actor actor : daoActor.getActorEditedReversedList()) {
			daoActor.editActor(actor, true);
		}
		for (Actor actor : daoActor.getActorInsertedList()) {
			daoActor.deleteActor(actor, true);
		}
		//UNDO STATEMENTS FOR FUNCTION OBJECTS
		for (Function function : daoFunction.getFunctionDeletedList()) {
			daoFunction.insertFunction(function, true);
		}
		for (Function function : daoFunction.getFunctionEditedReversedList()) {
			daoFunction.editFunction(function, true);
		}
		for (Function function : daoFunction.getFunctionInsertedList()) {
			daoFunction.deleteFunction(function, true);
		}
		//UNDO STATEMENTS FOR CONTAINERCONCEPT OBJECTS
		for (ContainerConcept containerConcept : daoContainerConcept.getContainerConceptDeletedList()) {
			daoContainerConcept.insertContainerConcept(containerConcept, true);
		}
		for (ContainerConcept containerConcept : daoContainerConcept.getContainerConceptEditedReversedList()) {
			daoContainerConcept.editContainerConcept(containerConcept, true);
		}
		for (ContainerConcept containerConcept : daoContainerConcept.getContainerConceptInsertedList()) {
			daoContainerConcept.deleteContainerConcept(containerConcept, true);
		}
		//UNDO STATEMENTS FOR ATTRIBUTE OBJECTS
		for (Attribute attribute : daoAttribute.getAttributeDeletedList()) {
			daoAttribute.insertAttribute(attribute, true);
		}
		for (Attribute attribute : daoAttribute.getAttributeEditedReversedList()) {
			daoAttribute.editAttribute(attribute, true);
		}
		for (Attribute attribute : daoAttribute.getAttributeInsertedList()) {
			daoAttribute.deleteAttribute(attribute, true);
		}
		//UNDO STATEMENTS FOR ATTRIBUTERELATIONSHIP OBJECTS
		for (AttributeRelationship attributeRelationship : daoAttributeRelationship
				.getAttributeRelationshipDeletedList()) {
			daoAttributeRelationship.insertAttributeRelationship(attributeRelationship, true);
		}
		for (AttributeRelationship attributeRelationship : daoAttributeRelationship
				.getAttributeRelationshipEditedReversedList()) {
			daoAttributeRelationship.editAttributeRelationship(attributeRelationship, true);
		}
		for (AttributeRelationship attributeRelationship : daoAttributeRelationship
				.getAttributeRelationshipInsertedList()) {
			daoAttributeRelationship.deleteAttributeRelationship(attributeRelationship, true);
		}
		//UNDO STATEMENTS FOR CONCEPT OBJECTS
		for (Concept concept : daoConcept.getConceptDeletedList()) {
			daoConcept.insertConcept(concept, true);
		}
		for (Concept concept : daoConcept.getConceptEditedReversedList()) {
			daoConcept.editConcept(concept, true);
		}
		for (Concept concept : daoConcept.getConceptInsertedList()) {
			daoConcept.deleteConcept(concept, true);
		}
		//UNDO STATEMENTS FOR OBJECT OBJECTS
		for (Object object : daoObject.getObjectDeletedList()) {
			daoObject.insertObject(object, true);
		}
		for (Object object : daoObject.getObjectEditedReversedList()) {
			daoObject.editObject(object, true);
		}
		for (Object object : daoObject.getObjectInsertedList()) {
			daoObject.deleteObject(object, true);
		}
	}
	public void redo() throws Exception {
		//REDO STATEMENTS FOR DISCOURSE OBJECTS
		for (Discourse discourse : daoDiscourse.getDiscourseInsertedList()) {
			daoDiscourse.insertDiscourse(discourse, true);
		}
		for (Discourse discourse : daoDiscourse.getDiscourseEditedList()) {
			daoDiscourse.editDiscourse(discourse, true);
		}
		for (Discourse discourse : daoDiscourse.getDiscourseDeletedList()) {
			daoDiscourse.deleteDiscourse(discourse, true);
		}
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
		//REDO STATEMENTS FOR ACTOR OBJECTS
		for (Actor actor : daoActor.getActorInsertedList()) {
			daoActor.insertActor(actor, true);
		}
		for (Actor actor : daoActor.getActorEditedList()) {
			daoActor.editActor(actor, true);
		}
		for (Actor actor : daoActor.getActorDeletedList()) {
			daoActor.deleteActor(actor, true);
		}
		//REDO STATEMENTS FOR FUNCTION OBJECTS
		for (Function function : daoFunction.getFunctionInsertedList()) {
			daoFunction.insertFunction(function, true);
		}
		for (Function function : daoFunction.getFunctionEditedList()) {
			daoFunction.editFunction(function, true);
		}
		for (Function function : daoFunction.getFunctionDeletedList()) {
			daoFunction.deleteFunction(function, true);
		}
		//REDO STATEMENTS FOR CONTAINERCONCEPT OBJECTS
		for (ContainerConcept containerConcept : daoContainerConcept.getContainerConceptInsertedList()) {
			daoContainerConcept.insertContainerConcept(containerConcept, true);
		}
		for (ContainerConcept containerConcept : daoContainerConcept.getContainerConceptEditedList()) {
			daoContainerConcept.editContainerConcept(containerConcept, true);
		}
		for (ContainerConcept containerConcept : daoContainerConcept.getContainerConceptDeletedList()) {
			daoContainerConcept.deleteContainerConcept(containerConcept, true);
		}
		//REDO STATEMENTS FOR ATTRIBUTE OBJECTS
		for (Attribute attribute : daoAttribute.getAttributeInsertedList()) {
			daoAttribute.insertAttribute(attribute, true);
		}
		for (Attribute attribute : daoAttribute.getAttributeEditedList()) {
			daoAttribute.editAttribute(attribute, true);
		}
		for (Attribute attribute : daoAttribute.getAttributeDeletedList()) {
			daoAttribute.deleteAttribute(attribute, true);
		}
		//REDO STATEMENTS FOR ATTRIBUTERELATIONSHIP OBJECTS
		for (AttributeRelationship attributeRelationship : daoAttributeRelationship
				.getAttributeRelationshipInsertedList()) {
			daoAttributeRelationship.insertAttributeRelationship(attributeRelationship, true);
		}
		for (AttributeRelationship attributeRelationship : daoAttributeRelationship
				.getAttributeRelationshipEditedList()) {
			daoAttributeRelationship.editAttributeRelationship(attributeRelationship, true);
		}
		for (AttributeRelationship attributeRelationship : daoAttributeRelationship
				.getAttributeRelationshipDeletedList()) {
			daoAttributeRelationship.deleteAttributeRelationship(attributeRelationship, true);
		}
		//REDO STATEMENTS FOR CONCEPT OBJECTS
		for (Concept concept : daoConcept.getConceptInsertedList()) {
			daoConcept.insertConcept(concept, true);
		}
		for (Concept concept : daoConcept.getConceptEditedList()) {
			daoConcept.editConcept(concept, true);
		}
		for (Concept concept : daoConcept.getConceptDeletedList()) {
			daoConcept.deleteConcept(concept, true);
		}
		//REDO STATEMENTS FOR OBJECT OBJECTS
		for (Object object : daoObject.getObjectInsertedList()) {
			daoObject.insertObject(object, true);
		}
		for (Object object : daoObject.getObjectEditedList()) {
			daoObject.editObject(object, true);
		}
		for (Object object : daoObject.getObjectDeletedList()) {
			daoObject.deleteObject(object, true);
		}
	}
}