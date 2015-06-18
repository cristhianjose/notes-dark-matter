package com.example.notes_dark_matter;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

	// Database table and columns
	public static final String TABLE_NOTES = "notas";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_TITLE = "titulo";
	public static final String COLUMN_CONTENT = "contenido";

	// Database file name and version
	private static final String DATABASE_NAME = "notes_dark_matter.db";
	private static final int DATABASE_VERSION = 1;

	// Database creation sql statement
	private static final String SQL_DB_CREATE = "create table "
			+ TABLE_NOTES + "("
			+ COLUMN_ID + " integer primary key autoincrement, "
			+ COLUMN_TITLE + " text not null, "
			+ COLUMN_CONTENT + " text not null);";

	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(SQL_DB_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(MySQLiteHelper.class.getName(),
			"Actualizando base de datos de la versión " + oldVersion + " a "
			+ newVersion + "... se perderán los datos!"
		);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
		onCreate(db);
	}

}
