package android.mobilequare.analyst.model.factory;
import android.mobilequare.analyst.model.dao.*;
import android.mobilequare.analyst.model.daofactory.*;
public class RemoteStorageFactory implements DAOFactory {
	public DAOConcept getDAOConcept() {
		return new DAORemoteStorageConcept();
	}
	public DAOContainerConcept getDAOContainerConcept() {
		return new DAORemoteStorageContainerConcept();
	}
	public DAOAttribute getDAOAttribute() {
		return new DAORemoteStorageAttribute();
	}
	public DAOObject getDAOObject() {
		return new DAORemoteStorageObject();
	}
	public DAOActor getDAOActor() {
		return new DAORemoteStorageActor();
	}
	public DAOFunction getDAOFunction() {
		return new DAORemoteStorageFunction();
	}
	public DAOAttributeRelationship getDAOAttributeRelationship() {
		return new DAORemoteStorageAttributeRelationship();
	}
	public DAODiscourse getDAODiscourse() {
		return new DAORemoteStorageDiscourse();
	}
	public DAOProject getDAOProject() {
		return new DAORemoteStorageProject();
	}
	public DAOQuestionSet getDAOQuestionSet() {
		return new DAORemoteStorageQuestionSet();
	}
	public DAOQuestion getDAOQuestion() {
		return new DAORemoteStorageQuestion();
	}
	public DAOAnalyst getDAOAnalyst() {
		return new DAORemoteStorageAnalyst();
	}
	public DAOAnalystConfiguration getDAOAnalystConfiguration() {
		return new DAORemoteStorageAnalystConfiguration();
	}
}