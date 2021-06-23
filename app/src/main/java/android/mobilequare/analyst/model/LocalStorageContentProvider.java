package android.mobilequare.analyst.model;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
public class LocalStorageContentProvider extends ContentProvider {
	public static final String AUTHORITY_NAME = "android.mobilequare.analyst.model";
	private static final int CONCEPT = 100;
	private static final int CONCEPT_ID = 101;
	public static final Uri CONCEPT_URI = Uri.parse("content://" + AUTHORITY_NAME + "/CONCEPT");
	public static final Uri CONCEPT_URI_ID = Uri.parse("content://" + AUTHORITY_NAME + "/CONCEPT" + "/" + CONCEPT_ID);
	private static final int CONTAINERCONCEPT = 200;
	private static final int CONTAINERCONCEPT_ID = 201;
	public static final Uri CONTAINERCONCEPT_URI = Uri.parse("content://" + AUTHORITY_NAME + "/CONTAINERCONCEPT");
	public static final Uri CONTAINERCONCEPT_URI_ID = Uri
			.parse("content://" + AUTHORITY_NAME + "/CONTAINERCONCEPT" + "/" + CONTAINERCONCEPT_ID);
	private static final int ATTRIBUTE = 300;
	private static final int ATTRIBUTE_ID = 301;
	public static final Uri ATTRIBUTE_URI = Uri.parse("content://" + AUTHORITY_NAME + "/ATTRIBUTE");
	public static final Uri ATTRIBUTE_URI_ID = Uri
			.parse("content://" + AUTHORITY_NAME + "/ATTRIBUTE" + "/" + ATTRIBUTE_ID);
	private static final int OBJECT = 400;
	private static final int OBJECT_ID = 401;
	public static final Uri OBJECT_URI = Uri.parse("content://" + AUTHORITY_NAME + "/OBJECT");
	public static final Uri OBJECT_URI_ID = Uri.parse("content://" + AUTHORITY_NAME + "/OBJECT" + "/" + OBJECT_ID);
	private static final int ACTOR = 500;
	private static final int ACTOR_ID = 501;
	public static final Uri ACTOR_URI = Uri.parse("content://" + AUTHORITY_NAME + "/ACTOR");
	public static final Uri ACTOR_URI_ID = Uri.parse("content://" + AUTHORITY_NAME + "/ACTOR" + "/" + ACTOR_ID);
	private static final int FUNCTION = 600;
	private static final int FUNCTION_ID = 601;
	public static final Uri FUNCTION_URI = Uri.parse("content://" + AUTHORITY_NAME + "/FUNCTION");
	public static final Uri FUNCTION_URI_ID = Uri
			.parse("content://" + AUTHORITY_NAME + "/FUNCTION" + "/" + FUNCTION_ID);
	private static final int ATTRIBUTERELATIONSHIP = 700;
	private static final int ATTRIBUTERELATIONSHIP_ID = 701;
	public static final Uri ATTRIBUTERELATIONSHIP_URI = Uri
			.parse("content://" + AUTHORITY_NAME + "/ATTRIBUTERELATIONSHIP");
	public static final Uri ATTRIBUTERELATIONSHIP_URI_ID = Uri
			.parse("content://" + AUTHORITY_NAME + "/ATTRIBUTERELATIONSHIP" + "/" + ATTRIBUTERELATIONSHIP_ID);
	private static final int DISCOURSE = 800;
	private static final int DISCOURSE_ID = 801;
	public static final Uri DISCOURSE_URI = Uri.parse("content://" + AUTHORITY_NAME + "/DISCOURSE");
	public static final Uri DISCOURSE_URI_ID = Uri
			.parse("content://" + AUTHORITY_NAME + "/DISCOURSE" + "/" + DISCOURSE_ID);
	private static final int PROJECT = 900;
	private static final int PROJECT_ID = 901;
	public static final Uri PROJECT_URI = Uri.parse("content://" + AUTHORITY_NAME + "/PROJECT");
	public static final Uri PROJECT_URI_ID = Uri.parse("content://" + AUTHORITY_NAME + "/PROJECT" + "/" + PROJECT_ID);
	private static final int QUESTIONSET = 1000;
	private static final int QUESTIONSET_ID = 1001;
	public static final Uri QUESTIONSET_URI = Uri.parse("content://" + AUTHORITY_NAME + "/QUESTIONSET");
	public static final Uri QUESTIONSET_URI_ID = Uri
			.parse("content://" + AUTHORITY_NAME + "/QUESTIONSET" + "/" + QUESTIONSET_ID);
	private static final int QUESTION = 1100;
	private static final int QUESTION_ID = 1101;
	public static final Uri QUESTION_URI = Uri.parse("content://" + AUTHORITY_NAME + "/QUESTION");
	public static final Uri QUESTION_URI_ID = Uri
			.parse("content://" + AUTHORITY_NAME + "/QUESTION" + "/" + QUESTION_ID);
	private static final int ANALYST = 1200;
	private static final int ANALYST_ID = 1201;
	public static final Uri ANALYST_URI = Uri.parse("content://" + AUTHORITY_NAME + "/ANALYST");
	public static final Uri ANALYST_URI_ID = Uri.parse("content://" + AUTHORITY_NAME + "/ANALYST" + "/" + ANALYST_ID);
	private static final int ANALYST_CONFIGURATION = 110;
	private static final int ANALYST_CONFIGURATION_ID = 111;
	public static final Uri ANALYST_CONFIGURATION_URI = Uri
			.parse("content://" + AUTHORITY_NAME + "/ANALYST_CONFIGURATION");
	public static final Uri ANALYST_CONFIGURATION_URI_ID = Uri
			.parse("content://" + AUTHORITY_NAME + "/ANALYST_CONFIGURATION" + "/" + ANALYST_CONFIGURATION_ID);
	public static final UriMatcher aUriMatcher = buildUriMatcher();
	private LocalStorage localStorage;
	private static UriMatcher buildUriMatcher() {
		final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
		matcher.addURI(AUTHORITY_NAME, "CONCEPT", CONCEPT);
		matcher.addURI(AUTHORITY_NAME, "CONCEPT" + "/#", CONCEPT_ID);
		matcher.addURI(AUTHORITY_NAME, "CONTAINERCONCEPT", CONTAINERCONCEPT);
		matcher.addURI(AUTHORITY_NAME, "CONTAINERCONCEPT" + "/#", CONTAINERCONCEPT_ID);
		matcher.addURI(AUTHORITY_NAME, "ATTRIBUTE", ATTRIBUTE);
		matcher.addURI(AUTHORITY_NAME, "ATTRIBUTE" + "/#", ATTRIBUTE_ID);
		matcher.addURI(AUTHORITY_NAME, "OBJECT", OBJECT);
		matcher.addURI(AUTHORITY_NAME, "OBJECT" + "/#", OBJECT_ID);
		matcher.addURI(AUTHORITY_NAME, "ACTOR", ACTOR);
		matcher.addURI(AUTHORITY_NAME, "ACTOR" + "/#", ACTOR_ID);
		matcher.addURI(AUTHORITY_NAME, "FUNCTION", FUNCTION);
		matcher.addURI(AUTHORITY_NAME, "FUNCTION" + "/#", FUNCTION_ID);
		matcher.addURI(AUTHORITY_NAME, "ATTRIBUTERELATIONSHIP", ATTRIBUTERELATIONSHIP);
		matcher.addURI(AUTHORITY_NAME, "ATTRIBUTERELATIONSHIP" + "/#", ATTRIBUTERELATIONSHIP_ID);
		matcher.addURI(AUTHORITY_NAME, "DISCOURSE", DISCOURSE);
		matcher.addURI(AUTHORITY_NAME, "DISCOURSE" + "/#", DISCOURSE_ID);
		matcher.addURI(AUTHORITY_NAME, "PROJECT", PROJECT);
		matcher.addURI(AUTHORITY_NAME, "PROJECT" + "/#", PROJECT_ID);
		matcher.addURI(AUTHORITY_NAME, "QUESTIONSET", QUESTIONSET);
		matcher.addURI(AUTHORITY_NAME, "QUESTIONSET" + "/#", QUESTIONSET_ID);
		matcher.addURI(AUTHORITY_NAME, "QUESTION", QUESTION);
		matcher.addURI(AUTHORITY_NAME, "QUESTION" + "/#", QUESTION_ID);
		matcher.addURI(AUTHORITY_NAME, "ANALYST", ANALYST);
		matcher.addURI(AUTHORITY_NAME, "ANALYST" + "/#", ANALYST_ID);
		matcher.addURI(AUTHORITY_NAME, "ANALYST_CONFIGURATION", ANALYST_CONFIGURATION);
		matcher.addURI(AUTHORITY_NAME, "ANALYST_CONFIGURATION" + "/#", ANALYST_CONFIGURATION_ID);
		return matcher;
	}
	@Nullable
	@Override
	public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
			@Nullable String[] selectionArguments, @Nullable String sortOrder) {
		Cursor cursor = null;
		localStorage = LocalStorage.getInstance(getContext());
		SQLiteDatabase database = localStorage.getWritableDatabase();
		switch (aUriMatcher.match(uri)) {
			case CONCEPT :
				cursor = database.query("CONCEPT_TABLE", projection, selection, selectionArguments, null, null,
						sortOrder);
				break;
			case CONTAINERCONCEPT :
				cursor = database.query("CONTAINERCONCEPT_TABLE", projection, selection, selectionArguments, null, null,
						sortOrder);
				break;
			case ATTRIBUTE :
				cursor = database.query("ATTRIBUTE_TABLE", projection, selection, selectionArguments, null, null,
						sortOrder);
				break;
			case OBJECT :
				cursor = database.query("OBJECT_TABLE", projection, selection, selectionArguments, null, null,
						sortOrder);
				break;
			case ACTOR :
				cursor = database.query("ACTOR_TABLE", projection, selection, selectionArguments, null, null,
						sortOrder);
				break;
			case FUNCTION :
				cursor = database.query("FUNCTION_TABLE", projection, selection, selectionArguments, null, null,
						sortOrder);
				break;
			case ATTRIBUTERELATIONSHIP :
				cursor = database.query("ATTRIBUTERELATIONSHIP_TABLE", projection, selection, selectionArguments, null,
						null, sortOrder);
				break;
			case DISCOURSE :
				cursor = database.query("DISCOURSE_TABLE", projection, selection, selectionArguments, null, null,
						sortOrder);
				break;
			case PROJECT :
				cursor = database.query("PROJECT_TABLE", projection, selection, selectionArguments, null, null,
						sortOrder);
				break;
			case QUESTIONSET :
				cursor = database.query("QUESTIONSET_TABLE", projection, selection, selectionArguments, null, null,
						sortOrder);
				break;
			case QUESTION :
				cursor = database.query("QUESTION_TABLE", projection, selection, selectionArguments, null, null,
						sortOrder);
				break;
			case ANALYST :
				cursor = database.query("ANALYST_TABLE", projection, selection, selectionArguments, null, null,
						sortOrder);
				break;
			case ANALYST_CONFIGURATION :
				cursor = database.query("ANALYST_CONFIGURATION", projection, selection, selectionArguments, null, null,
						sortOrder);
				break;
		}
		return cursor;
	}
	@Nullable
	@Override
	public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
		Uri turi = null;
		localStorage = LocalStorage.getInstance(getContext());
		SQLiteDatabase database = localStorage.getWritableDatabase();
		long _101;
		switch (aUriMatcher.match(uri)) {
			case CONCEPT :
				_101 = database.insertOrThrow("CONCEPT_TABLE", null, contentValues);
				if (_101 > 0) {
					turi = ContentUris.withAppendedId(CONCEPT_URI, _101);
					getContext().getContentResolver().notifyChange(turi, null);
				}
				break;
			case CONTAINERCONCEPT :
				_101 = database.insertOrThrow("CONTAINERCONCEPT_TABLE", null, contentValues);
				if (_101 > 0) {
					turi = ContentUris.withAppendedId(CONTAINERCONCEPT_URI, _101);
					getContext().getContentResolver().notifyChange(turi, null);
				}
				break;
			case ATTRIBUTE :
				_101 = database.insertOrThrow("ATTRIBUTE_TABLE", null, contentValues);
				if (_101 > 0) {
					turi = ContentUris.withAppendedId(ATTRIBUTE_URI, _101);
					getContext().getContentResolver().notifyChange(turi, null);
				}
				break;
			case OBJECT :
				_101 = database.insertOrThrow("OBJECT_TABLE", null, contentValues);
				if (_101 > 0) {
					turi = ContentUris.withAppendedId(OBJECT_URI, _101);
					getContext().getContentResolver().notifyChange(turi, null);
				}
				break;
			case ACTOR :
				_101 = database.insertOrThrow("ACTOR_TABLE", null, contentValues);
				if (_101 > 0) {
					turi = ContentUris.withAppendedId(ACTOR_URI, _101);
					getContext().getContentResolver().notifyChange(turi, null);
				}
				break;
			case FUNCTION :
				_101 = database.insertOrThrow("FUNCTION_TABLE", null, contentValues);
				if (_101 > 0) {
					turi = ContentUris.withAppendedId(FUNCTION_URI, _101);
					getContext().getContentResolver().notifyChange(turi, null);
				}
				break;
			case ATTRIBUTERELATIONSHIP :
				_101 = database.insertOrThrow("ATTRIBUTERELATIONSHIP_TABLE", null, contentValues);
				if (_101 > 0) {
					turi = ContentUris.withAppendedId(ATTRIBUTERELATIONSHIP_URI, _101);
					getContext().getContentResolver().notifyChange(turi, null);
				}
				break;
			case DISCOURSE :
				_101 = database.insertOrThrow("DISCOURSE_TABLE", null, contentValues);
				if (_101 > 0) {
					turi = ContentUris.withAppendedId(DISCOURSE_URI, _101);
					getContext().getContentResolver().notifyChange(turi, null);
				}
				break;
			case PROJECT :
				_101 = database.insertOrThrow("PROJECT_TABLE", null, contentValues);
				if (_101 > 0) {
					turi = ContentUris.withAppendedId(PROJECT_URI, _101);
					getContext().getContentResolver().notifyChange(turi, null);
				}
				break;
			case QUESTIONSET :
				_101 = database.insertOrThrow("QUESTIONSET_TABLE", null, contentValues);
				if (_101 > 0) {
					turi = ContentUris.withAppendedId(QUESTIONSET_URI, _101);
					getContext().getContentResolver().notifyChange(turi, null);
				}
				break;
			case QUESTION :
				_101 = database.insertOrThrow("QUESTION_TABLE", null, contentValues);
				if (_101 > 0) {
					turi = ContentUris.withAppendedId(QUESTION_URI, _101);
					getContext().getContentResolver().notifyChange(turi, null);
				}
				break;
			case ANALYST :
				_101 = database.insertOrThrow("ANALYST_TABLE", null, contentValues);
				if (_101 > 0) {
					turi = ContentUris.withAppendedId(ANALYST_URI, _101);
					getContext().getContentResolver().notifyChange(turi, null);
				}
				break;
			case ANALYST_CONFIGURATION :
				_101 = database.insertOrThrow("ANALYST_CONFIGURATION", null, contentValues);
				if (_101 > 0) {
					turi = ContentUris.withAppendedId(ANALYST_CONFIGURATION_URI, _101);
					getContext().getContentResolver().notifyChange(turi, null);
				}
				break;
			default :
				throw new SQLException("Failed to insert row into " + uri);
		}
		return turi;
	}
	@Override
	public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArguments) {
		int count = 0;
		localStorage = LocalStorage.getInstance(getContext());
		SQLiteDatabase database = localStorage.getWritableDatabase();
		switch (aUriMatcher.match(uri)) {
			case CONCEPT :
			case CONCEPT_ID :
				count = database.delete("CONCEPT_TABLE", selection, selectionArguments);
				break;
			case CONTAINERCONCEPT :
			case CONTAINERCONCEPT_ID :
				count = database.delete("CONTAINERCONCEPT_TABLE", selection, selectionArguments);
				break;
			case ATTRIBUTE :
			case ATTRIBUTE_ID :
				count = database.delete("ATTRIBUTE_TABLE", selection, selectionArguments);
				break;
			case OBJECT :
			case OBJECT_ID :
				count = database.delete("OBJECT_TABLE", selection, selectionArguments);
				break;
			case ACTOR :
			case ACTOR_ID :
				count = database.delete("ACTOR_TABLE", selection, selectionArguments);
				break;
			case FUNCTION :
			case FUNCTION_ID :
				count = database.delete("FUNCTION_TABLE", selection, selectionArguments);
				break;
			case ATTRIBUTERELATIONSHIP :
			case ATTRIBUTERELATIONSHIP_ID :
				count = database.delete("ATTRIBUTERELATIONSHIP_TABLE", selection, selectionArguments);
				break;
			case DISCOURSE :
			case DISCOURSE_ID :
				count = database.delete("DISCOURSE_TABLE", selection, selectionArguments);
				break;
			case PROJECT :
			case PROJECT_ID :
				count = database.delete("PROJECT_TABLE", selection, selectionArguments);
				break;
			case QUESTIONSET :
			case QUESTIONSET_ID :
				count = database.delete("QUESTIONSET_TABLE", selection, selectionArguments);
				break;
			case QUESTION :
			case QUESTION_ID :
				count = database.delete("QUESTION_TABLE", selection, selectionArguments);
				break;
			case ANALYST :
			case ANALYST_ID :
				count = database.delete("ANALYST_TABLE", selection, selectionArguments);
				break;
			case ANALYST_CONFIGURATION :
			case ANALYST_CONFIGURATION_ID :
				count = database.delete("ANALYST_CONFIGURATION", selection, selectionArguments);
				break;
		}
		return count;
	}
	@Override
	public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection,
			@Nullable String[] selectionArguments) {
		int count = 0;
		localStorage = LocalStorage.getInstance(getContext());
		SQLiteDatabase database = localStorage.getWritableDatabase();
		switch (aUriMatcher.match(uri)) {
			case CONCEPT :
			case CONCEPT_ID :
				count = database.update("CONCEPT_TABLE", contentValues, selection, selectionArguments);
				break;
			case CONTAINERCONCEPT :
			case CONTAINERCONCEPT_ID :
				count = database.update("CONTAINERCONCEPT_TABLE", contentValues, selection, selectionArguments);
				break;
			case ATTRIBUTE :
			case ATTRIBUTE_ID :
				count = database.update("ATTRIBUTE_TABLE", contentValues, selection, selectionArguments);
				break;
			case OBJECT :
			case OBJECT_ID :
				count = database.update("OBJECT_TABLE", contentValues, selection, selectionArguments);
				break;
			case ACTOR :
			case ACTOR_ID :
				count = database.update("ACTOR_TABLE", contentValues, selection, selectionArguments);
				break;
			case FUNCTION :
			case FUNCTION_ID :
				count = database.update("FUNCTION_TABLE", contentValues, selection, selectionArguments);
				break;
			case ATTRIBUTERELATIONSHIP :
			case ATTRIBUTERELATIONSHIP_ID :
				count = database.update("ATTRIBUTERELATIONSHIP_TABLE", contentValues, selection, selectionArguments);
				break;
			case DISCOURSE :
			case DISCOURSE_ID :
				count = database.update("DISCOURSE_TABLE", contentValues, selection, selectionArguments);
				break;
			case PROJECT :
			case PROJECT_ID :
				count = database.update("PROJECT_TABLE", contentValues, selection, selectionArguments);
				break;
			case QUESTIONSET :
			case QUESTIONSET_ID :
				count = database.update("QUESTIONSET_TABLE", contentValues, selection, selectionArguments);
				break;
			case QUESTION :
			case QUESTION_ID :
				count = database.update("QUESTION_TABLE", contentValues, selection, selectionArguments);
				break;
			case ANALYST :
			case ANALYST_ID :
				count = database.update("ANALYST_TABLE", contentValues, selection, selectionArguments);
				break;
			case ANALYST_CONFIGURATION :
			case ANALYST_CONFIGURATION_ID :
				count = database.update("ANALYST_CONFIGURATION", contentValues, selection, selectionArguments);
				break;
		}
		return count;
	}
	@Nullable
	@Override
	public String getType(@NonNull Uri uri) {
		return null;
	}
	@Override
	public boolean onCreate() {
		Context context = getContext();
		localStorage = LocalStorage.getInstance(context);
		return true;
	}
}