package android.mobilequare.analyst.model.factory;
import android.mobilequare.analyst.model.dao.*;
import android.mobilequare.analyst.model.daofactory.*;
import android.content.Context;
public class LocalStorageFactory implements DAOFactory {
	private Context context;
	public LocalStorageFactory(Context context) {
		this.context = context;
	}
	public DAOConcept getDAOConcept() {
		return new DAOLocalStorageConcept(context);
	}
	public DAOContainerConcept getDAOContainerConcept() {
		return new DAOLocalStorageContainerConcept(context);
	}
	public DAOAttribute getDAOAttribute() {
		return new DAOLocalStorageAttribute(context);
	}
	public DAOObject getDAOObject() {
		return new DAOLocalStorageObject(context);
	}
	public DAOActor getDAOActor() {
		return new DAOLocalStorageActor(context);
	}
	public DAOFunction getDAOFunction() {
		return new DAOLocalStorageFunction(context);
	}
	public DAOAttributeRelationship getDAOAttributeRelationship() {
		return new DAOLocalStorageAttributeRelationship(context);
	}
	public DAODiscourse getDAODiscourse() {
		return new DAOLocalStorageDiscourse(context);
	}
	public DAOProject getDAOProject() {
		return new DAOLocalStorageProject(context);
	}
	public DAOQuestionSet getDAOQuestionSet() {
		return new DAOLocalStorageQuestionSet(context);
	}
	public DAOQuestion getDAOQuestion() {
		return new DAOLocalStorageQuestion(context);
	}
	public DAOAnalyst getDAOAnalyst() {
		return new DAOLocalStorageAnalyst(context);
	}
	public DAOAnalystConfiguration getDAOAnalystConfiguration() {
		return new DAOLocalStorageAnalystConfiguration(context);
	}
}