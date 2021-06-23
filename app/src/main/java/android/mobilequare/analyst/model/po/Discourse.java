package android.mobilequare.analyst.model.po;

import java.util.List;
import java.util.ArrayList;
import android.mobilequare.analyst.exception.ConstraintCheckingException;
public class Discourse extends ClassConcept {
	//ATTRIBUTES
	//PRIMITIVE ATTRIBUTES  
	private String id;
	private String content;
	//COMPLEX ATTRIBUTES 
	private List<Concept> conceptList;
	//FOREING KEYS 
	private String _idProject;
	//CONSTRUCTOR 
	public Discourse() {
		super();
		this.id = "";
		this.content = "";
		this.conceptList = new ArrayList<Concept>();
		this._idProject = "";
	}
	//CONSTRAINT CHECKING
	public void checkConstraints() throws ConstraintCheckingException {
	}
	//GETTERS 
	public String getId() {
		return this.id;
	}
	public String getContent() {
		return this.content;
	}
	public List<Concept> getConceptList() {
		return this.conceptList;
	}
	public String get_idProject() {
		return this._idProject;
	}
	//SETTERS 
	public void setId(String newId) {
		this.id = newId;
	}
	public void setContent(String newContent) {
		this.content = newContent;
	}
	public void setConceptList(List<Concept> newConceptList) {
		this.conceptList = newConceptList;
	}
	public void set_idProject(String new_idProject) {
		this._idProject = new_idProject;
	}
}
