package android.mobilequare.analyst.model.dao;
import java.util.List;
import java.util.ArrayList;
import android.mobilequare.analyst.model.po.QuestionSet;
import android.mobilequare.analyst.exception.StorageException;
import android.mobilequare.analyst.exception.ConstraintCheckingException;
public abstract class DAOQuestionSet {
	//UNDO - REDO LISTS
	private List<QuestionSet> questionSetInsertedList;
	private List<QuestionSet> questionSetDeletedList;
	private List<QuestionSet> questionSetEditedList;
	private List<QuestionSet> questionSetEditedReversedList;
	//CONSTRUCTOR
	public DAOQuestionSet() {
		this.questionSetInsertedList = new ArrayList<QuestionSet>();
		this.questionSetDeletedList = new ArrayList<QuestionSet>();
		this.questionSetEditedList = new ArrayList<QuestionSet>();
		this.questionSetEditedReversedList = new ArrayList<QuestionSet>();
	}
	//OPERATIONS
	public abstract List<QuestionSet> listQuestionSet(String query) throws StorageException;
	public abstract boolean deleteQuestionSet(QuestionSet questionSet, boolean isUndoOrRedo) throws StorageException;
	public abstract QuestionSet insertQuestionSet(QuestionSet questionSet, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException;
	public abstract QuestionSet editQuestionSet(QuestionSet questionSet, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException;
	//GETTERS
	public List<QuestionSet> getQuestionSetInsertedList() {
		return this.questionSetInsertedList;
	}
	public List<QuestionSet> getQuestionSetDeletedList() {
		return this.questionSetDeletedList;
	}
	public List<QuestionSet> getQuestionSetEditedList() {
		return this.questionSetEditedList;
	}
	public List<QuestionSet> getQuestionSetEditedReversedList() {
		return this.questionSetEditedReversedList;
	}
	//SETTERS
	public void setQuestionSetInsertedList(List<QuestionSet> newQuestionSetInsertedList) {
		this.questionSetInsertedList = newQuestionSetInsertedList;
	}
	public void setQuestionSetDeletedList(List<QuestionSet> newQuestionSetDeletedList) {
		this.questionSetDeletedList = newQuestionSetDeletedList;
	}
	public void setQuestionSetEditedList(List<QuestionSet> newQuestionSetEditedList) {
		this.questionSetEditedList = newQuestionSetEditedList;
	}
	public void setQuestionSetEditedReversedList(List<QuestionSet> newQuestionSetEditedReversedList) {
		this.questionSetEditedReversedList = newQuestionSetEditedReversedList;
	}
}