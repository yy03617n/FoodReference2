package com.example.foodreference2;

import android.content.*;
import android.database.*;
import android.database.sqlite.*;
import android.net.*;

public class FoodProvider extends ContentProvider {
	/* I don't know what the URIs are going to be so for now they're empty
 	   strings. */
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
	public static final String COLUMN_CONTAINS = "containsId";
	public static final String COLUMN_CONTENT = "contentId";
	public static final String COLUMN_FOOD = "foodId";
	private static final String DATABASE_CREATE = "create table"
			+ DATABASE_TABLE + " (" + COLUMN_ROWID
			+ " integer primary key autoincrement, " + COLUMN_CATEGORY
			+ " text not null, " + COLUMN_CONTAINS + " text not null, "
			+ COLUMN_CONTENT + " text not null, " + COLUMN_FOOD
			+ " text not null);";
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
	public Cursor query(Uri uri, String[] ignored1, String ignored2,
			String[] ignored3, String ignored4) {
		String[] projection = new String[] { FoodProvider.COLUMN_ROWID,
				FoodProvider.COLUMN_CATEGORY, FoodProvider.COLUMN_CONTAINS,
				FoodProvider.COLUMN_CONTENT, FoodProvider.COLUMN_FOOD };
		Cursor c;
		switch (sURIMatcher.match(uri)) {
		case LIST_FOOD:
			c = mDd.query(FoodProvider.DATABASE_TABLE, projection, null, null,
					null, null, null);
			break;
		case ITEM_FOOD:
			c = mDd.query(FoodProvider.DATABASE_TABLE, projection,
					FoodProvider.COLUMN_CATEGORY + "+?",
					new String[] { Long.toString(ContentUris.parseId(uri)) },
					null, null, null, null);
			if (c != null && c.getCount() > 0) {
				c.moveToFirst();
			}
			break;
		default:
			throw new IllegalArgumentException("Unknown Uri: " + uri);
		}
		c.setNotificationUri(getContext().getContentResolver(), uri);
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
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			throw new UnsupportedOperationException();
		}
	}
}