package android.mobilequare.analyst.model.converters;
import java.util.Date;
public class DateToRealConverter {
	public static Date fromTimestamp(Long value) {
		return value == null ? null : new Date(value);
	}
	public static Long dateToTimestamp(Date date) {
		return date == null ? null : date.getTime();
	}
}
