package android.mobilequare.analyst.model.po;

import android.mobilequare.analyst.exception.ConstraintCheckingException;
public class Function extends ClassConcept {
	//ATTRIBUTES
	//PRIMITIVE ATTRIBUTES  
	private String actionVerb;
	//COMPLEX ATTRIBUTES 
	//FOREING KEYS 
	private String _idActor;
	private String _idObject;
	//CONSTRUCTOR 
	public Function() {
		super();
		this.actionVerb = "";
		this._idActor = "";
		this._idObject = "";
	}
	//CONSTRAINT CHECKING
	public void checkConstraints() throws ConstraintCheckingException {
	}
	//GETTERS 
	public String getActionVerb() {
		return this.actionVerb;
	}
	public String get_idActor() {
		return this._idActor;
	}
	public String get_idObject() {
		return this._idObject;
	}
	//SETTERS 
	public void setActionVerb(String newActionVerb) {
		this.actionVerb = newActionVerb;
	}
	public void set_idActor(String new_idActor) {
		this._idActor = new_idActor;
	}
	public void set_idObject(String new_idObject) {
		this._idObject = new_idObject;
	}
}
