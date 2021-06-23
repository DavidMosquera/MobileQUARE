package android.mobilequare.analyst.model.po;
import java.util.UUID;

public class ClassConcept {
	//ATTRIBURTES 
	private String _id;
	//CONSTRUCTOR
	public ClassConcept() {
		this._id = UUID.randomUUID().toString();
	}
	//GETTERS
	public String get_Id() {
		return _id;
	}
	//SETTERS
	public void set_Id(String new_id) {
		this._id = new_id;
	}
	public String toString() {
		return this.get_Id();
	}
	public String listInfo() {
		return this.get_Id();
	}
}
