package android.mobilequare.analyst.model.po;

import java.util.List;
import java.util.ArrayList;
import android.mobilequare.analyst.exception.ConstraintCheckingException;
public class Object extends Concept {
	//ATTRIBUTES
	//PRIMITIVE ATTRIBUTES 
	//COMPLEX ATTRIBUTES 
	private List<Function> functionList;
	//FOREING KEYS 
	//CONSTRUCTOR
	public Object (Concept concept){//$A
		super();//$A
		this.setName(concept.getName());//$A
		this.setIdentification(concept.getIdentification());//$A
		this.set_idDiscourse(concept.get_idDiscourse());//$A
	}//$A
	public Object() {
		super();
		this.functionList = new ArrayList<Function>();
	}
	//CONSTRAINT CHECKING
	public void checkConstraints() throws ConstraintCheckingException {
	}
	//GETTERS   
	public List<Function> getFunctionList() {
		return this.functionList;
	}
	//SETTERS   
	public void setFunctionList(List<Function> newFunctionList) {
		this.functionList = newFunctionList;
	}
}
