package android.mobilequare.analyst.controller;
import android.mobilequare.analyst.command.Command;
import android.content.Context;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import android.mobilequare.analyst.exception.*;
import android.mobilequare.analyst.model.po.Object; //$A
import android.mobilequare.analyst.view.AsksQuestionView;
import android.mobilequare.analyst.model.dao.*;
import android.mobilequare.analyst.model.factory.*;
import android.mobilequare.analyst.model.po.*;
import android.mobilequare.analyst.view.fragments.ConceptFragment; //$A
import android.mobilequare.analyst.view.fragments.QuestionFragment;

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
	private DAOProject daoProject; //$A
	private DAODiscourse daoDiscourse; //$A
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
		daoProject = new LocalStorageFactory(context).getDAOProject(); //$A
		daoDiscourse = new LocalStorageFactory(context).getDAODiscourse(); //$A
	}
	//OPERATIONS
	public void asksQuestion(AsksQuestionView asksQuestionView, QuestionSet viewQuestionSet, String viewTextType, Concept conceptView, String questionTitle) { //$E
		//TYPE AND MANDATORY CONSTRAINTS 
		//QUESTIONSET NULLABILITY CHECKING
		List<ConceptFragment> conceptFragmentList = new ArrayList<>(); //$A
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
		Date currentDate = new Date(); //$E
		//"ANALYST ASKS QUESTION" SPECIFICATION
		try {
			question = new Question(); //$A
			question.set_idQuestionSet(viewQuestionSet.get_Id());
			question.setType(viewType);
			question.setAnswer("");
			if ((question.getType()).compareTo ("Which are the project's discourse actors?") == 0) { //$E
				question.setTitle("Which are the project's discourse actors?");
				for (Discourse discourse : daoDiscourse.listDiscourse("PROJECT_ID = \""+viewQuestionSet.get_idProject()+"\"")){//$A
					for(Actor concept : daoActor.listActor("DISCOURSE_ID = \""+discourse.get_Id()+"\"")){//$A
						question.setAnswer((question.getAnswer()) + ((concept.getName()) + (", ")));
						conceptFragmentList.add(ConceptFragment.newInstance(concept, getProjectFromQuestionSet(viewQuestionSet), this, viewQuestionSet));//$A
					}//$A
				}//$A
			}
			if ((question.getType()).compareTo ("Which are the project's discourse concepts?") == 0) { //$E
				question.setTitle("Which are the project's discourse concepts?");
				for (Discourse discourse : daoDiscourse.listDiscourse("PROJECT_ID = \""+viewQuestionSet.get_idProject()+"\"")){//$A
					for(Concept concept : daoConcept.listConcept("DISCOURSE_ID = \""+discourse.get_Id()+"\"")){//$A
						question.setAnswer((question.getAnswer()) + ((concept.getName()) + (", ")));
						conceptFragmentList.add(ConceptFragment.newInstance(concept, getProjectFromQuestionSet(viewQuestionSet), this, viewQuestionSet));//$A
					}//$A
				}//$A
			}
			if ((question.getType()).compareTo ("Which are the X's functions?") == 0) { //$E
 				question.setTitle(("Which are the ") + ((conceptView.getName()) + ("'s functions?"))); //$E
				for (Function function : daoFunction.listFunction("ACTOR_ID = \""+conceptView.get_Id()+"\"")) {//$A
					question.setAnswer(
							(question.getAnswer()) + ((function.getActionVerb()) + " " +((daoObject.listObject("_ID = \""+function.get_idObject()+"\"").get(0).getName()) + (", ")))); //$E
					conceptFragmentList.add(ConceptFragment.newInstance(function, daoActor.listActor("_ID = \""+conceptView.get_Id()+"\"").get(0), ((daoObject.listObject("_ID = \""+function.get_idObject()+"\"").get(0))), getProjectFromQuestionSet(viewQuestionSet), this, viewQuestionSet));//$A
				}//$A
			}
			if ((question.getType()).compareTo("Which are the X's attributes?") == 0) {//$E
				question.setTitle(("Which are the ") + ((conceptView.getName()) + ("'s attributes?")));//$E
				for (AttributeRelationship attributeRelationship : daoAttributeRelationship.listAttributeRelationship("CONTAINERCONCEPT_ID = \""+conceptView.get_Id()+"\"")){ //$A
					question.setAnswer((question.getAnswer()) + ((daoAttribute.listAttribute("_ID = \""+attributeRelationship.get_idAttribute()+"\"").get(0).getName()) + (", "))); //$E
					conceptFragmentList.add(ConceptFragment.newInstance((daoAttribute.listAttribute("_ID = \""+attributeRelationship.get_idAttribute()+"\"").get(0)), getProjectFromQuestionSet(viewQuestionSet), this, viewQuestionSet)); //$A
				}//$A
			}//$A
			if ((question.getAnswer()) == ("")) {
			}
			daoQuestion.insertQuestion(question, false);
			viewQuestionSet.setAnswerDate(currentDate); //$E
			daoQuestionSet.editQuestionSet(viewQuestionSet, false); //$E
			asksQuestionView.asksQuestionSucceeds(conceptFragmentList, questionTitle);
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
			e.printStackTrace();
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
	public List<QuestionFragment> getQuestions(QuestionSet questionSet){//$A
		try {//$A
			List<QuestionFragment> questionFragments = new ArrayList<>();//$A
			for (Question question: daoQuestion.listQuestion("QUESTIONSET_ID = \""+questionSet.get_Id()+"\"")) {//$A
				questionFragments.add(QuestionFragment.newInstance(question.getTitle(), question.getAnswer()));//$A
			}//$A
			return questionFragments;//$A
		} catch (StorageException e) {//$A
			return null;//$A
		}//$A
	}//$A
	public Project getProjectFromQuestionSet(QuestionSet questionSet) {//$A
		try {//$A
			return daoProject.listProject("_ID = \"" + questionSet.get_idProject()+"\"").get(0);//$A
		} catch (StorageException e) {//$A
			return null;//$A
		}//$A
	}//$A
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
		for (android.mobilequare.analyst.model.po.Object object : daoObject.getObjectDeletedList()) {//$E
			daoObject.insertObject(object, true);
		}
		for (android.mobilequare.analyst.model.po.Object object : daoObject.getObjectEditedReversedList()) {//$E
			daoObject.editObject(object, true);
		}
		for (android.mobilequare.analyst.model.po.Object object : daoObject.getObjectInsertedList()) {//$E
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
		for (android.mobilequare.analyst.model.po.Object object : daoObject.getObjectInsertedList()) {//$E
			daoObject.insertObject(object, true);
		}
		for (android.mobilequare.analyst.model.po.Object object : daoObject.getObjectEditedList()) {//$E
			daoObject.editObject(object, true);
		}
		for (android.mobilequare.analyst.model.po.Object object : daoObject.getObjectDeletedList()) {//$E
			daoObject.deleteObject(object, true);
		}
	}
}