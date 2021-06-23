package android.mobilequare.analyst.model.converters;
public class TextToDoubleConverter {
	public static double textToDouble(String value) throws NumberFormatException {
		return Double.parseDouble(value);
	}
}
