package android.mobilequare.analyst.model.converters;
public class BooleanToIntConverter {
	public static boolean integerToBoolean(int value) {
		return value == 1 ? true : false;
	}
	public static int booleanToInt(boolean value) {
		return value == true ? 1 : 0;
	}
}
