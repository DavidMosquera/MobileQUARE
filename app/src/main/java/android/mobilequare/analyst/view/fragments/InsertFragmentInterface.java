package android.mobilequare.analyst.view.fragments;
import android.mobilequare.analyst.model.po.ClassConcept;
import java.util.List;
public interface InsertFragmentInterface<T> {
	void newInfo(String info);
	String getValue();
	ClassConcept getClassConceptValue();
	T create(String name);
	T create(String name, List<Object> objectList);
	T create(List<ClassConcept> classConceptList, String name);
	T create(String name, boolean value);
}