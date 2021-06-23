package android.mobilequare.analyst.model.daofactory;
import java.util.List;
import java.util.ArrayList;
import android.mobilequare.analyst.model.po.QuestionSet;
import android.mobilequare.analyst.exception.StorageException;
import android.mobilequare.analyst.exception.ConstraintCheckingException;
import android.mobilequare.analyst.model.dao.DAOQuestionSet;
public class DAORemoteStorageQuestionSet extends DAOQuestionSet {
	public List<QuestionSet> listQuestionSet(String query) throws StorageException {
		//LIST IMPLEMENTATION FOR REMOTE STORAGE OF QUESTIONSET 
		//TODO IMPLEMENT THIS
		return new ArrayList<QuestionSet>();
	}
	public boolean deleteQuestionSet(QuestionSet questionSet, boolean isUndoOrRedo) throws StorageException {
		//DELETE IMPLEMENTATION FOR REMOTE STORAGE OF QUESTIONSET 
		//TODO IMPLEMENT THIS
		return false;
	}
	public QuestionSet insertQuestionSet(QuestionSet questionSet, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException {
		//INSERT IMPLEMENTATION FOR REMOTE STORAGE OF QUESTIONSET 
		questionSet.checkConstraints();
		//TODO IMPLEMENT THIS
		return null;
	}
	public QuestionSet editQuestionSet(QuestionSet questionSet, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException {
		//EDIT IMPLEMENTATION FOR REMOTE STORAGE OF QUESTIONSET 
		questionSet.checkConstraints();
		//TODO IMPLEMENT THIS
		return null;
	}
}