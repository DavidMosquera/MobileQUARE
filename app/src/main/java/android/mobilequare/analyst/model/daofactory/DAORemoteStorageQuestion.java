package android.mobilequare.analyst.model.daofactory;
import java.util.List;
import java.util.ArrayList;
import android.mobilequare.analyst.model.po.Question;
import android.mobilequare.analyst.exception.StorageException;
import android.mobilequare.analyst.exception.ConstraintCheckingException;
import android.mobilequare.analyst.model.dao.DAOQuestion;
public class DAORemoteStorageQuestion extends DAOQuestion {
	public List<Question> listQuestion(String query) throws StorageException {
		//LIST IMPLEMENTATION FOR REMOTE STORAGE OF QUESTION 
		//TODO IMPLEMENT THIS
		return new ArrayList<Question>();
	}
	public boolean deleteQuestion(Question question, boolean isUndoOrRedo) throws StorageException {
		//DELETE IMPLEMENTATION FOR REMOTE STORAGE OF QUESTION 
		//TODO IMPLEMENT THIS
		return false;
	}
	public Question insertQuestion(Question question, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException {
		//INSERT IMPLEMENTATION FOR REMOTE STORAGE OF QUESTION 
		question.checkConstraints();
		//TODO IMPLEMENT THIS
		return null;
	}
	public Question editQuestion(Question question, boolean isUndoOrRedo)
			throws StorageException, ConstraintCheckingException {
		//EDIT IMPLEMENTATION FOR REMOTE STORAGE OF QUESTION 
		question.checkConstraints();
		//TODO IMPLEMENT THIS
		return null;
	}
}