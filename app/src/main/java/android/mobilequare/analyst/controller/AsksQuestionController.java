package android.mobilequare.analyst.controller;
import android.mobilequare.analyst.command.Command;
import android.content.Context;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import android.mobilequare.analyst.exception.*;
import android.mobilequare.analyst.view.AsksQuestionView;
import android.mobilequare.analyst.model.dao.*;
import android.mobilequare.analyst.model.factory.*;
import android.mobilequare.analyst.model.po.*;

public class AsksQuestionController implements Command {
	private Question question;
	//DATA ACCESS OBJECTS (DAO)
	private DAOQuestionSet daoQuestionSet;
	private DAOQuestion daoQuestion;
	private DAOActor daoActor;
	private DAOAttribute daoAttribute;
	private DAOAttributeRelationship daoAttributeRelationship;
	private DAOConcept daoConcept;
	private DAOContainerConcept daoContainerConcept;
	private DAOFunction daoFunction;
	private DAOObject daoObject;
	//CONSTRUCTOR 
	public AsksQuestionController(Context context) {
		question = new Question();
		daoQuestionSet = new LocalStorageFactory(context).getDAOQuestionSet();
		daoQuestion = new LocalStorageFactory(context).getDAOQuestion();
		daoActor = new LocalStorageFactory(context).getDAOActor();
		daoAttribute = new LocalStorageFactory(context).getDAOAttribute();
		daoAttributeRelationship = new LocalStorageFactory(context).getDAOAttributeRelationship();
		daoConcept = new LocalStorageFactory(context).getDAOConcept();
		daoContainerConcept = new LocalStorageFactory(context).getDAOContainerConcept();
		daoFunction = new LocalStorageFactory(context).getDAOFunction();
		daoObject = new LocalStorageFactory(context).getDAOObject();
	}
	//OPERATIONS
	public void asksQuestion(AsksQuestionView asksQuestionView, QuestionSet viewQuestionSet, String viewTextType) {
		//TYPE AND MANDATORY CONSTRAINTS 
		//QUESTIONSET NULLABILITY CHECKING
		if (viewQuestionSet == null) {
			viewQuestionSet = new QuestionSet();
			viewQuestionSet.set_Id("");
		}
		//QUESTION.TYPE TYPE AND MANDATORY CONSTRAINTS CHECKING 
		if (viewTextType.compareTo("") == 0) {
			asksQuestionView.asksQuestionFails("Question.Type is mandatory.");
			asksQuestionView.questionTypeNewInfo("Question.Type is mandatory.");
			return;
		}
		String viewType = viewTextType;
		//LOCAL VARIABLES 
		Date currentDate;
		//"ANALYST ASKS QUESTION" SPECIFICATION
		try {
			question.set_idQuestionSet(viewQuestionSet.get_Id());
			question.setType(viewType);
			question.setAnswer("");
			if ((question.getType()) == ("Which are the project's discourse actors?")) {
				question.setTitle("Which are the project's discrourse concepts?");
				question.setAnswer((question.getAnswer()) + ((concept.getName()) + (", ")));
			}
			if ((question.getTitle()) == ("Which are the project's discourse concepts?")) {
				question.setTitle("Which are the project's discrourse concepts?");
				question.setAnswer((question.getAnswer()) + ((concept.getName()) + (", ")));
			}
			if ((question.getType()) == ("Which are the X's functions?")) {
				question.setTitle(((concept.getName()) + ("'s functions?")) + ("Which are the "));
				question.setAnswer(
						(question.getAnswer()) + ((function.getActionVerb()) + ((concept.getName()) + (", "))));
			}
			if ((question.getType()) == ("Which are the X's attributes?")) {
				question.setTitle(("Which are the ") + ((concept.getName()) + ("'s attributes?")));
				question.setAnswer((question.getAnswer()) == ((concept.getName()) + (", ")));
			}
			if ((question.getAnswer()) == ("")) {
			}
			daoQuestion.insertQuestion(question, false);
			questionSet.setAnswerDate(currentDate);
			daoQuestionSet.editQuestionSet(questionSet, false);
			asksQuestionView.asksQuestionSucceeds();
		} catch (ConstraintCheckingException e) {
			try {
				this.undo();
			} catch (Exception e1) {
			}
			asksQuestionView.asksQuestionFails(e.getMessage());
		} catch (StorageException e) {
			try {
				this.undo();
			} catch (Exception e1) {
			}
			asksQuestionView.asksQuestionFails(e.getMessage());
		}
	}

	public List<QuestionSet> listsQuestionSet(AsksQuestionView asksQuestionView, String query) {
		try {
			List<QuestionSet> questionSetList = daoQuestionSet.listQuestionSet(query);
			return questionSetList;
		} catch (StorageException e) {
			asksQuestionView.asksQuestionFails(e.getMessage());
			return new ArrayList<QuestionSet>();
		}
	}
	public void undo() throws Exception {
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
		//UNDO STATEMENTS FOR QUESTION OBJECTS
		for (Question question : daoQuestion.getQuestionDeletedList()) {
			daoQuestion.insertQuestion(question, true);
		}
		for (Question question : daoQuestion.getQuestionEditedReversedList()) {
			daoQuestion.editQuestion(question, true);
		}
		for (Question question : daoQuestion.getQuestionInsertedList()) {
			daoQuestion.deleteQuestion(question, true);
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
		//REDO STATEMENTS FOR QUESTION OBJECTS
		for (Question question : daoQuestion.getQuestionInsertedList()) {
			daoQuestion.insertQuestion(question, true);
		}
		for (Question question : daoQuestion.getQuestionEditedList()) {
			daoQuestion.editQuestion(question, true);
		}
		for (Question question : daoQuestion.getQuestionDeletedList()) {
			daoQuestion.deleteQuestion(question, true);
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