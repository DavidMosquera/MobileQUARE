package android.mobilequare.analyst.model.factory;
import android.mobilequare.analyst.model.dao.*;
public interface DAOFactory {
	DAOConcept getDAOConcept();
	DAOContainerConcept getDAOContainerConcept();
	DAOAttribute getDAOAttribute();
	DAOObject getDAOObject();
	DAOActor getDAOActor();
	DAOFunction getDAOFunction();
	DAOAttributeRelationship getDAOAttributeRelationship();
	DAODiscourse getDAODiscourse();
	DAOProject getDAOProject();
	DAOQuestionSet getDAOQuestionSet();
	DAOQuestion getDAOQuestion();
	DAOAnalyst getDAOAnalyst();
	DAOAnalystConfiguration getDAOAnalystConfiguration();
}
