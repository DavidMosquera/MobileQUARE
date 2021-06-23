package android.mobilequare.analyst.model.converters;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
public class TextToDateConverter {
	public static Date textToDate(String value) throws ParseException {
		return new SimpleDateFormat("dd/MM/yyyy").parse(value);
	}
}
