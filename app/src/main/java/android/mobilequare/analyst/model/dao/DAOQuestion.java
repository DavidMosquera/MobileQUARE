package android.mobilequare.analyst.model.dao;
import java.util.List;
import java.util.ArrayList;
import android.mobilequare.analyst.model.po.Question;
import android.mobilequare.analyst.exception.StorageException;
import android.mobilequare.analyst.exception.ConstraintCheckingException;
public abstract class DAOQuestion {
	//UNDO - REDO LISTS
	private List<Question> questionInsertedList;
	private List<Question> questionDeletedList;
	private List<Question> questionEditedList;
	private List<Question> questionEditedReversedList;
	//CONSTRUCTOR
	public DAOQuestion() {
		this.questionInsertedList = new ArrayList<Question>();
		this.questionDeletedList = new ArrayList<Question>();
		this.questionEditedList = new ArrayList<Question>();
		this.questionEditedReversedList = new ArrayList<Question>();
	}
	//OPERATIONS
	public abstract List<Question> listQuestion(String query) throws StorageException;
	public abstract boolean deleteQuestion(Question question, boolean isUndoOrRedo) throws StorageException;
	public abstract Question insertQuestion(Question question, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException;
	public abstract Question editQuestion(Question question, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException;
	//GETTERS
	public List<Question> getQuestionInsertedList() {
		return this.questionInsertedList;
	}
	public List<Question> getQuestionDeletedList() {
		return this.questionDeletedList;
	}
	public List<Question> getQuestionEditedList() {
		return this.questionEditedList;
	}
	public List<Question> getQuestionEditedReversedList() {
		return this.questionEditedReversedList;
	}
	//SETTERS
	public void setQuestionInsertedList(List<Question> newQuestionInsertedList) {
		this.questionInsertedList = newQuestionInsertedList;
	}
	public void setQuestionDeletedList(List<Question> newQuestionDeletedList) {
		this.questionDeletedList = newQuestionDeletedList;
	}
	public void setQuestionEditedList(List<Question> newQuestionEditedList) {
		this.questionEditedList = newQuestionEditedList;
	}
	public void setQuestionEditedReversedList(List<Question> newQuestionEditedReversedList) {
		this.questionEditedReversedList = newQuestionEditedReversedList;
	}
}