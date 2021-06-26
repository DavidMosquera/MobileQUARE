package android.mobilequare.analyst.model.po;

import java.util.List;
import java.util.ArrayList;
import android.mobilequare.analyst.exception.ConstraintCheckingException;
public class ContainerConcept extends Concept {
	//ATTRIBUTES
	//PRIMITIVE ATTRIBUTES 
	//COMPLEX ATTRIBUTES 
	private List<AttributeRelationship> attributeRelationshipList;
	//FOREING KEYS 
	//CONSTRUCTOR
	public ContainerConcept (Concept concept){//$A
		super();//$A
		this.setName(concept.getName());//$A
		this.setIdentification(concept.getIdentification());//$A
		this.set_idDiscourse(concept.get_idDiscourse());//$A
	}//$A
	public ContainerConcept() {
		super();
		this.attributeRelationshipList = new ArrayList<AttributeRelationship>();
	}
	//CONSTRAINT CHECKING
	public void checkConstraints() throws ConstraintCheckingException {
	}
	//GETTERS   
	public List<AttributeRelationship> getAttributeRelationshipList() {
		return this.attributeRelationshipList;
	}
	//SETTERS   
	public void setAttributeRelationshipList(List<AttributeRelationship> newAttributeRelationshipList) {
		this.attributeRelationshipList = newAttributeRelationshipList;
	}
}
