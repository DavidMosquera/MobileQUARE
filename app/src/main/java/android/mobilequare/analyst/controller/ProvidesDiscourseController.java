package android.mobilequare.analyst.controller;
import android.mobilequare.analyst.command.Command;
import android.content.Context;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.Object;
import java.net.HttpURLConnection;//$A
import java.net.MalformedURLException;
import java.net.URL;//$A
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.mobilequare.analyst.exception.*;
import android.mobilequare.analyst.view.ProvidesDiscourseView;
import android.mobilequare.analyst.model.dao.*;
import android.mobilequare.analyst.model.factory.*;
import android.mobilequare.analyst.model.po.*;
import android.os.AsyncTask; //$A
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

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
			for(Discourse discourseObject : daoDiscourse.listDiscourse("PROJECT_ID = \"" + viewProject.get_Id()+ "\"")){//$A
				concatenatedContent = ((concatenatedContent) + (discourseObject.getContent())); //$E
			}//$A
			//$D (null);
			String finalConcatenatedContent = concatenatedContent;//$A
			Project finalViewProject = viewProject; //$A
			Toast.makeText(providesDiscourseView, "Providing discourse", Toast.LENGTH_SHORT).show(); //$A
			providesDiscourseView.deactivateButton(); //$A
			ExecutorService executor = Executors.newSingleThreadExecutor(); //$A
			Handler handler = new Handler(Looper.getMainLooper()); //$A
			executor.execute(new Runnable() { //$A
				@Override//$A
				public void run() {//$A
					try {//$A
						URL quareURL = new URL("http://192.168.0.7:5000/QUARE");//$A
						HttpURLConnection myConnection = (HttpURLConnection) quareURL.openConnection();//$A
						String text = finalConcatenatedContent.replace("\n", "").replace("\r", "").replaceAll("\\p{C}", "").replaceAll("[\\p{Cc}\\p{Cf}\\p{Co}\\p{Cn}]", "").replaceAll("\\p{Sc}", "").replaceAll("\\P{Print}", "").replaceAll("\\\\","");//$A
						System.out.println(text);
						String jsonBody = "{\"text\":\""+ text +"\"}";//$A
						myConnection.setRequestProperty("Accept", "application/json");//$A
						myConnection.setRequestProperty("Content-Type", "application/json; utf-8");//$A
						myConnection.setRequestMethod("POST");//$A
						myConnection.setDoOutput(true);//$A
						try(OutputStream os = myConnection.getOutputStream()) {//$A
							byte[] input = jsonBody.getBytes("utf-8");//$A
							os.write(input, 0, input.length);//$A
						}//$A
						if(myConnection.getResponseCode() == 200) {//$A
							HashMap<String, Concept> conceptHashMap = new HashMap<>();//$A
							JsonReader jsonReader = Json.createReader(new InputStreamReader(myConnection.getInputStream(), "UTF-8"));//$A
							JsonObject jsonObject = jsonReader.readObject();//$A
							JsonObject concepts = jsonObject.getJsonObject("concepts");//$A
							JsonObject concept;//$A
							jsonReader.close();//$A
							Concept conceptStorage;//$A
							for (String conceptName : concepts.keySet()){//$A
								conceptStorage = new Concept();//$A
								conceptStorage.set_idDiscourse(discourse.get_Id());//$A
								conceptStorage.setIdentification((finalViewProject.getName()) + (conceptName)); //$E
								conceptStorage.setName(conceptName); //$A
								concept = concepts.getJsonObject(conceptName); //$A
								if(daoConcept.listConcept("IDENTIFICATION = \""+ conceptStorage.getIdentification() + "\"").size()==0) daoConcept.insertConcept(conceptStorage, false); //$A
								if (concept.getString("type").compareTo("ACTOR")==0){
									if(daoActor.listActor("IDENTIFICATION = \""+ conceptStorage.getIdentification() + "\"").size()==0) daoActor.insertActor(new Actor(conceptStorage), false); //$E
								} //$A
								conceptHashMap.put(conceptName, conceptStorage);//$A
							}//$A
							JsonObject functions = jsonObject.getJsonObject("functions");//$A
							JsonObject function;//$A
							Function functionStorage;//$A
							for (String functionId : functions.keySet()){//$A
								function = functions.getJsonObject(functionId);//$A
								functionStorage = new Function();//$A
								if(conceptHashMap.containsKey(function.getString("actor"))&&conceptHashMap.containsKey(function.getString("dir_object"))){//$A
									functionStorage.set_idActor(conceptHashMap.get(function.getString("actor")).get_Id());//$A
									functionStorage.set_idObject(conceptHashMap.get(function.getString("dir_object")).get_Id());//$A
									if (daoObject.listObject("IDENTIFICATION = \""+conceptHashMap.get(function.getString("dir_object")).getIdentification()+"\"").size()==0) daoObject.insertObject(new android.mobilequare.analyst.model.po.Object(conceptHashMap.get(function.getString("dir_object"))), false);//$A
									functionStorage.setActionVerb(function.getString("action_verb")); //$A
									if (daoFunction.listFunction("ACTOR_ID = \""+functionStorage.get_idActor()+"\" AND OBJECT_ID = \""+functionStorage.get_idObject()+"\" AND ACTIONVERB = \""+functionStorage.getActionVerb()+"\"").size()==0) daoFunction.insertFunction(functionStorage, false); //$E
								}//$A
							}//$A
							boolean firstInsertion;//$A
							AttributeRelationship attributeRelationship;//$A
							for (String conceptName : concepts.keySet()){//$A
								concept = concepts.getJsonObject(conceptName);//$A
								firstInsertion = true;//$A
								for (JsonValue jsonValue : concept.getJsonArray("attributes")) {//$A
									attributeRelationship = new AttributeRelationship();//$A
									attributeRelationship.set_idContainerConcept(conceptHashMap.get(conceptName).get_Id());//$A
									attributeRelationship.set_idAttribute(conceptHashMap.get(jsonValue.toString().replace("\"","")).get_Id());//$A
									if (firstInsertion){//$A
										if(daoContainerConcept.listContainerConcept("IDENTIFICATION = \""+conceptHashMap.get(conceptName).getIdentification()+"\"").size() == 0) daoContainerConcept.insertContainerConcept(new ContainerConcept(conceptHashMap.get(conceptName)), false); //$E
									}//$A
									if(daoAttribute.listAttribute("IDENTIFICATION = \""+conceptHashMap.get(jsonValue.toString().replace("\"","")).getIdentification()+"\"").size() == 0) daoAttribute.insertAttribute(new Attribute(conceptHashMap.get(jsonValue.toString().replace("\"",""))), false); //$E
									if(daoAttributeRelationship.listAttributeRelationship("ATTRIBUTE_ID = \""+attributeRelationship.get_idAttribute()+"\" AND CONTAINERCONCEPT_ID = \""+attributeRelationship.get_idContainerConcept()+"\"" ).size() == 0) daoAttributeRelationship.insertAttributeRelationship(attributeRelationship, false); //$E
								}//$A
							}//$A
							daoDiscourse.insertDiscourse(discourse, false);
							handler.post(new Runnable() {//$A
								@Override//$A
								public void run() {//$A
									providesDiscourseView.activateButton(); //$A
									providesDiscourseView.providesDiscourseSucceeds();//$A
								}//$A
							});//$A
						}else{//$A
							handler.post(new Runnable() {//$A
								@Override//$A
								public void run() {//$A
									providesDiscourseView.activateButton(); //$A
									providesDiscourseView.providesDiscourseFails("An error has occurred establishing connection with the server");//$A
								}//$A
							});//$A
						}//$A
					} catch ( IOException  | ConstraintCheckingException | StorageException e) {//$A
						handler.post(new Runnable() {//$A
							@Override//$A
							public void run() {//$A
								providesDiscourseView.activateButton(); //$A
								providesDiscourseView.providesDiscourseFails("An error has occurred during providing discourse: " + e.getMessage() );//$A
							}//$A
						});//$A
					}//$A
				}//$A
			});//$A
			//$Dconcept.setIdentification((project.getName()) + (concept.getName()));
			//$Dconcept.setIdentification((project.getName()) + (concept.getName()));
			//$Dconcept.setIdentification((project.getName()) + (concept.getName()));
		}
		//$Dcatch (ConstraintCheckingException e){
			//$Dtry{this.undo();} catch(Exception e1){}
			//$DprovidesDiscourseView.providesDiscourseFails(e.getMessage());
		//$D}
		catch (StorageException e){
			try{this.undo();} catch(Exception e1){}
			providesDiscourseView.activateButton(); //$A
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