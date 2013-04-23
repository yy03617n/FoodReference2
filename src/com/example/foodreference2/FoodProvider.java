package com.example.foodreference2;

import android.content.*;
import android.database.*;
import android.database.sqlite.*;
import android.net.*;

public class FoodProvider extends ContentProvider {
	/*
	 * I don't know what the URIs are going to be so for now they're empty
	 * strings.
	 */
	public static String AUTHORITY = "";
	public static final Uri CONTENT_URI = Uri.parse("");
	public static final String FOODS_MIME_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
    + "";
	public static final String FOOD_MIME_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
    + "";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "data";
	private static final String DATABASE_TABLE = "food";
	public static final String COLUMN_ROWID = "_id";
	public static final String COLUMN_CATEGORY = "categoryId";
	public static final String COLUMN_CONTENT = "contentId";
	public static final String COLUMN_FOOD = "foodId";
	public static final String COLUMN_DAY = "date";
	public static final String COLUMN_USER = "userId";
	private static final String DATABASE_CREATE_FOOD = "create table"
    + DATABASE_TABLE + " (" + COLUMN_ROWID
    + " integer primary key autoincrement, " + COLUMN_CATEGORY
    + " text not null, " + COLUMN_CONTENT + " text not null, "
    + COLUMN_FOOD + " text not null);";

	
	// the above string (DATABASE_CREATE_FOOD) should be used as an example for
	// creating the create command for each of our database tables
	// Replicate below (the one below has not been modified)
	// see line 111
	private static final String DATABASE_CREATE_CATEGORY = "create table"
    + DATABASE_TABLE + " (" + COLUMN_ROWID
    + " integer primary key autoincrement, " + COLUMN_CATEGORY
    + " text not null, " + COLUMN_CONTENT + " text not null, "
    + COLUMN_FOOD + " text not null, " + COLUMN_DAY + " text not null, " + COLUMN_USER + " text not null);";
	
	private static final String DATABASE_CREATE_CONTENT = "create table"
		    + DATABASE_TABLE + " (" + COLUMN_ROWID
		    + " integer primary key autoincrement, " + COLUMN_CATEGORY
		    + " text not null, " + COLUMN_CONTENT + " text not null, "
		    + COLUMN_FOOD + " text not null, " + COLUMN_DAY + " text not null, " + COLUMN_USER + " text not null);";
	private static final String DATABASE_CREATE_DAY = "create table"
		    + DATABASE_TABLE + " (" + COLUMN_ROWID
		    + " integer primary key autoincrement, " + COLUMN_CATEGORY
		    + " text not null, " + COLUMN_CONTENT + " text not null, "
		    + COLUMN_FOOD + " text not null, " + COLUMN_DAY + " text not null, " + COLUMN_USER + " text not null);";
	private static final String DATABASE_CREATE_USER = "create table"
		    + DATABASE_TABLE + " (" + COLUMN_ROWID
		    + " integer primary key autoincrement, " + COLUMN_CATEGORY
		    + " text not null, " + COLUMN_CONTENT + " text not null, "
		    + COLUMN_FOOD + " text not null, " + COLUMN_DAY + " text not null, " + COLUMN_USER + " text not null);";
	
	private static final int LIST_FOOD = 0;
	private static final int ITEM_FOOD = 1;
	private static final UriMatcher sURIMatcher = buildUriMatcher();
	private SQLiteDatabase mDd;
    
	private static UriMatcher buildUriMatcher() {
		UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
		matcher.addURI(AUTHORITY, "reminder", LIST_FOOD);
		matcher.addURI(AUTHORITY, "reminder/#", ITEM_FOOD);
		return matcher;
	}
    
	@Override
	public boolean onCreate() {
		mDd = new DatabaseHelper(getContext()).getWritableDatabase();
		return true;
	}
    
	@Override
	public Cursor query(Uri uri, String[] iD, String ignore,
                        String[] returned_column_Name, String column_Name) {
		Cursor c = mDd.query(uri.toString(), returned_column_Name, column_Name
                             + "=?", iD, null, null, null);
		return c;
	}
    
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		values.remove(FoodProvider.COLUMN_ROWID);
		long id = mDd.insertOrThrow(FoodProvider.DATABASE_TABLE, null, values);
		return ContentUris.withAppendedId(uri, id);
	}
    
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int count = mDd.delete(FoodProvider.DATABASE_TABLE,
                               FoodProvider.COLUMN_ROWID + "=?",
                               new String[] { Long.toString(ContentUris.parseId(uri)) });
		if (count > 0)
			getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}
    
	@Override
	public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
		int count = mDd.update(FoodProvider.DATABASE_TABLE, values,
                               COLUMN_ROWID + "=?",
                               new String[] { Long.toString(ContentUris.parseId(uri)) });
		if (count > 0)
			getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}
    
	@Override
	public String getType(Uri uri) {
		switch (sURIMatcher.match(uri)) {
            case LIST_FOOD:
                return FOOD_MIME_TYPE;
            case ITEM_FOOD:
                return FOOD_MIME_TYPE;
            default:
                throw new IllegalArgumentException("Unknown Uri: " + uri);
		}
	}
    
	protected static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
        
		@Override
		// insert created DB calls below, one for each table!!
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE_FOOD);
			db.execSQL(DATABASE_CREATE_CATEGORY);
			db.execSQL(DATABASE_CREATE_CONTENT);
			db.execSQL(DATABASE_CREATE_DAY);
			db.execSQL(DATABASE_CREATE_USER);
		}
        
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			throw new UnsupportedOperationException();
		}
	}
}