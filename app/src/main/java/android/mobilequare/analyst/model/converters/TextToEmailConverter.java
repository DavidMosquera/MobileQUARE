package android.mobilequare.analyst.model.converters;
import java.util.regex.Pattern;
public class TextToEmailConverter {
	public static String textToEmail(String value) throws Exception {
		Pattern pattern = Pattern.compile(
				"^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");
		if (pattern.matcher(value).matches()) {
			return value;
		} else {
			throw new Exception();
		}
	}
}
