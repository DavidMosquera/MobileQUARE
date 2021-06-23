package android.mobilequare.analyst.model.po;

import android.mobilequare.analyst.exception.ConstraintCheckingException;
public class Concept extends ClassConcept {
	//ATTRIBUTES
	//PRIMITIVE ATTRIBUTES  
	private String name;
	private String identification;
	//COMPLEX ATTRIBUTES 
	//FOREING KEYS 
	private String _idDiscourse;
	//CONSTRUCTOR 
	public Concept() {
		super();
		this.name = "";
		this.identification = "";
		this._idDiscourse = "";
	}
	//CONSTRAINT CHECKING
	public void checkConstraints() throws ConstraintCheckingException {
	}
	//GETTERS 
	public String getName() {
		return this.name;
	}
	public String getIdentification() {
		return this.identification;
	}
	public String get_idDiscourse() {
		return this._idDiscourse;
	}
	//SETTERS 
	public void setName(String newName) {
		this.name = newName;
	}
	public void setIdentification(String newIdentification) {
		this.identification = newIdentification;
	}
	public void set_idDiscourse(String new_idDiscourse) {
		this._idDiscourse = new_idDiscourse;
	}
}
