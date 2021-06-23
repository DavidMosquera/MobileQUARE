package android.mobilequare.analyst.model.converters;
public class TextToBooleanConverter {
	public static boolean textToBoolean(String value) throws Exception {
		if (value.compareTo("true") == 0 || value.compareTo("false") == 0) {
			return Boolean.parseBoolean(value);
		} else {
			throw new Exception();
		}
	}
}
