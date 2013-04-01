package com.example.foodreference2;

import android.content.*;
import android.database.Cursor;
import android.database.sqlite.*;
import android.net.Uri;

public class FoodProvider extends ContentProvider {
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

	private SQLiteDatabase mDd;

	@Override
	public boolean onCreate() {
		mDd = new DatabaseHelper(getContext()).getWritableDatabase();
		return true;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}

	/*@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}*/
	
	@Override
	public Cursor query(Uri uri, String[] ignored1, String[] ignored2, String[] ignored3, String ignored4) {
		String[] projection = new String[] {FoodProvider.COLUMN_ROWID, FoodProvider.COLUMN_CATEGORY, FoodProvider.COLUMN_CONTAINS, FoodProvider.COLUMN_CONTENT, FoodProvider.COLUMN_FOOD};

	Cursor c;
	
	switch (sURIMatcher.match(uri)) {
	case LIST_REMINDER:
		c = mDd.query(FoodProvider.DATABASE_TABLE, projection, null, null, null, null, null);
		break;
	}
	case ITEM_REMINDER:
		c = mDd.query(FoodProvider.DATABASE_TABLE, projection, FoodProvider.COLUMN_CATEGORY + "+?", new String[] { Long.toString(ContentUris.parseId(uri)) }, null, null, null, null);
		if (c != null && c.getCount() > 0) {
			c.moveToFirst();
		}
	}
	break;
	default:
		throw new IllegalArgumentException("Unknown Uri: " + uri);
	}
	
	c.setNotificationUri(getContext().getContentResolver(), uri);
	return c;

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	protected static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE);

		}
	}

}