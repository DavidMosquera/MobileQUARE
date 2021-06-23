package android.mobilequare.analyst.model.po;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import android.mobilequare.analyst.exception.ConstraintCheckingException;
public class QuestionSet extends ClassConcept {
	//ATTRIBUTES
	//PRIMITIVE ATTRIBUTES  
	private String title;
	private Date answerDate;
	//COMPLEX ATTRIBUTES 
	private List<Question> questionList;
	//FOREING KEYS 
	private String _idProject;
	//CONSTRUCTOR 
	public QuestionSet() {
		super();
		this.title = "";
		this.answerDate = new Date(0);
		this.questionList = new ArrayList<Question>();
		this._idProject = "";
	}
	//CONSTRAINT CHECKING
	public void checkConstraints() throws ConstraintCheckingException {
	}
	//GETTERS 
	public String getTitle() {
		return this.title;
	}
	public Date getAnswerDate() {
		return this.answerDate;
	}
	public List<Question> getQuestionList() {
		return this.questionList;
	}
	public String get_idProject() {
		return this._idProject;
	}
	//SETTERS 
	public void setTitle(String newTitle) {
		this.title = newTitle;
	}
	public void setAnswerDate(Date newAnswerDate) {
		this.answerDate = newAnswerDate;
	}
	public void setQuestionList(List<Question> newQuestionList) {
		this.questionList = newQuestionList;
	}
	public void set_idProject(String new_idProject) {
		this._idProject = new_idProject;
	}
}
