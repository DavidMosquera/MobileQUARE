package android.mobilequare.analyst.model.po;

import android.mobilequare.analyst.exception.ConstraintCheckingException;
public class AttributeRelationship extends ClassConcept {
	//ATTRIBUTES
	//PRIMITIVE ATTRIBUTES 
	//COMPLEX ATTRIBUTES 
	//FOREING KEYS 
	private String _idAttribute;
	private String _idContainerConcept;
	//CONSTRUCTOR 
	public AttributeRelationship() {
		super();
		this._idAttribute = "";
		this._idContainerConcept = "";
	}
	//CONSTRAINT CHECKING
	public void checkConstraints() throws ConstraintCheckingException {
	}
	//GETTERS    
	public String get_idAttribute() {
		return this._idAttribute;
	}
	public String get_idContainerConcept() {
		return this._idContainerConcept;
	}
	//SETTERS    
	public void set_idAttribute(String new_idAttribute) {
		this._idAttribute = new_idAttribute;
	}
	public void set_idContainerConcept(String new_idContainerConcept) {
		this._idContainerConcept = new_idContainerConcept;
	}
}
