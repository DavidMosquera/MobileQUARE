package android.mobilequare.analyst.model.po;

import android.mobilequare.analyst.exception.ConstraintCheckingException;
public class Question extends ClassConcept {
	//ATTRIBUTES
	//PRIMITIVE ATTRIBUTES  
	private String type;
	private String title;
	private String answer;
	//COMPLEX ATTRIBUTES 
	//FOREING KEYS 
	private String _idQuestionSet;
	//CONSTRUCTOR 
	public Question() {
		super();
		this.type = "";
		this.title = "";
		this.answer = "";
		this._idQuestionSet = "";
	}
	//CONSTRAINT CHECKING
	public void checkConstraints() throws ConstraintCheckingException {
	}
	//GETTERS 
	public String getType() {
		return this.type;
	}
	public String getTitle() {
		return this.title;
	}
	public String getAnswer() {
		return this.answer;
	}
	public String get_idQuestionSet() {
		return this._idQuestionSet;
	}
	//SETTERS 
	public void setType(String newType) {
		this.type = newType;
	}
	public void setTitle(String newTitle) {
		this.title = newTitle;
	}
	public void setAnswer(String newAnswer) {
		this.answer = newAnswer;
	}
	public void set_idQuestionSet(String new_idQuestionSet) {
		this._idQuestionSet = new_idQuestionSet;
	}
}
