package android.mobilequare.analyst.model;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class LocalStorage extends SQLiteOpenHelper {
	private static LocalStorage localStorage;
	public static synchronized LocalStorage getInstance(Context context) {
		if (localStorage == null) {
			localStorage = new LocalStorage(context.getApplicationContext());
		}
		return localStorage;
	}
	private LocalStorage(Context context) {
		super(context, "MobileQUARE", null, 1);
	}
	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {
		String conceptTable = "CREATE TABLE IF NOT EXISTS CONCEPT_TABLE (" + " NAME TEXT NOT NULL,"
				+ " DISCOURSE_ID TEXT," + "_ID TEXT PRIMARY KEY)";
		String containerConceptTable = "CREATE TABLE IF NOT EXISTS CONTAINERCONCEPT_TABLE (" + " NAME TEXT NOT NULL,"
				+ " DISCOURSE_ID TEXT," + "_ID TEXT PRIMARY KEY)";
		String attributeTable = "CREATE TABLE IF NOT EXISTS ATTRIBUTE_TABLE (" + " NAME TEXT NOT NULL,"
				+ " DISCOURSE_ID TEXT," + "_ID TEXT PRIMARY KEY)";
		String objectTable = "CREATE TABLE IF NOT EXISTS OBJECT_TABLE (" + " NAME TEXT NOT NULL,"
				+ " DISCOURSE_ID TEXT," + "_ID TEXT PRIMARY KEY)";
		String actorTable = "CREATE TABLE IF NOT EXISTS ACTOR_TABLE (" + " NAME TEXT NOT NULL," + " DISCOURSE_ID TEXT,"
				+ "_ID TEXT PRIMARY KEY)";
		String functionTable = "CREATE TABLE IF NOT EXISTS FUNCTION_TABLE (" + " ACTIONVERB TEXT NOT NULL,"
				+ " ACTOR_ID TEXT," + " OBJECT_ID TEXT," + "_ID TEXT PRIMARY KEY)";
		String attributeRelationshipTable = "CREATE TABLE IF NOT EXISTS ATTRIBUTERELATIONSHIP_TABLE ("
				+ " ATTRIBUTE_ID TEXT," + " CONTAINERCONCEPT_ID TEXT," + "_ID TEXT PRIMARY KEY)";
		String discourseTable = "CREATE TABLE IF NOT EXISTS DISCOURSE_TABLE (" + " ID TEXT NOT NULL,"
				+ " CONTENT TEXT NOT NULL," + " PROJECT_ID TEXT," + "_ID TEXT PRIMARY KEY)";
		String projectTable = "CREATE TABLE IF NOT EXISTS PROJECT_TABLE (" + " NAME TEXT NOT NULL,"
				+ " DESCRIPTION TEXT NOT NULL," + "_ID TEXT PRIMARY KEY)";
		String questionSetTable = "CREATE TABLE IF NOT EXISTS QUESTIONSET_TABLE (" + " TITLE TEXT NOT NULL,"
				+ " ANSWERDATE REAL," + " PROJECT_ID TEXT," + "_ID TEXT PRIMARY KEY)";
		String questionTable = "CREATE TABLE IF NOT EXISTS QUESTION_TABLE (" + " TYPE TEXT NOT NULL,"
				+ " TITLE TEXT NOT NULL," + " ANSWER TEXT NOT NULL," + " QUESTIONSET_ID TEXT,"
				+ "_ID TEXT PRIMARY KEY)";
		String analystTable = "CREATE TABLE IF NOT EXISTS ANALYST_TABLE (" + "_ID TEXT PRIMARY KEY)";
		String analystConfigurationTable = "CREATE TABLE IF NOT EXISTS ANALYST_CONFIGURATION ("
				+ " ANALYSTCREATESPROJECTUNDOREDONOTIFICATION INTEGER,"
				+ " ANALYSTCREATESPROJECTCONFIRMATIONMESSAGE INTEGER,"
				+ " ANALYSTESTABLISHESQUESTIONSETUNDOREDONOTIFICATION INTEGER,"
				+ " ANALYSTESTABLISHESQUESTIONSETCONFIRMATIONMESSAGE INTEGER,"
				+ " ANALYSTPROVIDESDISCOURSEUNDOREDONOTIFICATION INTEGER,"
				+ " ANALYSTPROVIDESDISCOURSECONFIRMATIONMESSAGE INTEGER,"
				+ " ANALYSTASKSQUESTIONUNDOREDONOTIFICATION INTEGER,"
				+ " ANALYSTASKSQUESTIONCONFIRMATIONMESSAGE INTEGER," + " LEFTHANDVIEW INTEGER,"
				+ " NOTIFICATIONTIME REAL," + " _ID TEXT PRIMARY KEY)";
		sqLiteDatabase.execSQL(conceptTable);
		sqLiteDatabase.execSQL(containerConceptTable);
		sqLiteDatabase.execSQL(attributeTable);
		sqLiteDatabase.execSQL(objectTable);
		sqLiteDatabase.execSQL(actorTable);
		sqLiteDatabase.execSQL(functionTable);
		sqLiteDatabase.execSQL(attributeRelationshipTable);
		sqLiteDatabase.execSQL(discourseTable);
		sqLiteDatabase.execSQL(projectTable);
		sqLiteDatabase.execSQL(questionSetTable);
		sqLiteDatabase.execSQL(questionTable);
		sqLiteDatabase.execSQL(analystTable);
		sqLiteDatabase.execSQL(analystConfigurationTable);
	}
	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
		if (oldVersion != newVersion) {
			sqLiteDatabase.execSQL("DROP TABLE IF EXISTS CONCEPT_TABLE");
			sqLiteDatabase.execSQL("DROP TABLE IF EXISTS CONTAINERCONCEPT_TABLE");
			sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ATTRIBUTE_TABLE");
			sqLiteDatabase.execSQL("DROP TABLE IF EXISTS OBJECT_TABLE");
			sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ACTOR_TABLE");
			sqLiteDatabase.execSQL("DROP TABLE IF EXISTS FUNCTION_TABLE");
			sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ATTRIBUTERELATIONSHIP_TABLE");
			sqLiteDatabase.execSQL("DROP TABLE IF EXISTS DISCOURSE_TABLE");
			sqLiteDatabase.execSQL("DROP TABLE IF EXISTS PROJECT_TABLE");
			sqLiteDatabase.execSQL("DROP TABLE IF EXISTS QUESTIONSET_TABLE");
			sqLiteDatabase.execSQL("DROP TABLE IF EXISTS QUESTION_TABLE");
			sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ANALYST_TABLE");
			sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ANALYST_CONFIGURATION");
			onCreate(sqLiteDatabase);
		}
	}
}