package android.mobilequare.analyst.model.po;

import java.util.List;
import java.util.ArrayList;
import android.mobilequare.analyst.exception.ConstraintCheckingException;
public class Project extends ClassConcept {
	//ATTRIBUTES
	//PRIMITIVE ATTRIBUTES  
	private String name;
	private String description;
	//COMPLEX ATTRIBUTES 
	private List<Discourse> discourseList;
	private List<QuestionSet> questionSetList;
	//FOREING KEYS 
	//CONSTRUCTOR 
	public Project() {
		super();
		this.name = "";
		this.description = "";
		this.discourseList = new ArrayList<Discourse>();
		this.questionSetList = new ArrayList<QuestionSet>();
	}
	//CONSTRAINT CHECKING
	public void checkConstraints() throws ConstraintCheckingException {
	}
	//GETTERS 
	public String getName() {
		return this.name;
	}
	public String getDescription() {
		return this.description;
	}
	public List<Discourse> getDiscourseList() {
		return this.discourseList;
	}
	public List<QuestionSet> getQuestionSetList() {
		return this.questionSetList;
	}
	//SETTERS 
	public void setName(String newName) {
		this.name = newName;
	}
	public void setDescription(String newDescription) {
		this.description = newDescription;
	}
	public void setDiscourseList(List<Discourse> newDiscourseList) {
		this.discourseList = newDiscourseList;
	}
	public void setQuestionSetList(List<QuestionSet> newQuestionSetList) {
		this.questionSetList = newQuestionSetList;
	}
	public String toString() {//$A
		return this.name;//$A
	}//$A
	public String listInfo() {//$A
		return " " +this.name + " \n Description: "+ this.description;//$A
	}//$A
}
