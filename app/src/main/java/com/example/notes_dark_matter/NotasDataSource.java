package com.example.notes_dark_matter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class NotasDataSource {

	// Database fields
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
			MySQLiteHelper.COLUMN_TITLE, MySQLiteHelper.COLUMN_CONTENT };

	public NotasDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	// open connection
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	// close connection
	public void close() {
		dbHelper.close();//CERRAR CONEXION
	}

    //INSERT EN LA BASE DE DATOS
	public Nota createNota(Nota nuevaNota) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_TITLE, nuevaNota.getTitulo());
		values.put(MySQLiteHelper.COLUMN_CONTENT, nuevaNota.getContenido());

		// insert
		long newId = database.insert(MySQLiteHelper.TABLE_NOTES, null, values); //Devuelve el nuevo ID

		// get last record
		Cursor cursor = database.query(MySQLiteHelper.TABLE_NOTES, allColumns,
				MySQLiteHelper.COLUMN_ID + "=" + newId,
				null, null, null, null);
		cursor.moveToFirst();//Regresa el cursor al primer registro
		nuevaNota = cursorToNota(cursor);
		cursor.close();
		return nuevaNota;//La respuesta de la consulta
	}

	public void deleteNota(Nota nota) {
		long id = nota.getId();
		System.out.println("Eliminando nota con id: " + id);
		database.delete(MySQLiteHelper.TABLE_NOTES,
			MySQLiteHelper.COLUMN_ID + " = " + id, null);//Condicion "Where"
	}

	public ArrayList<Nota>getNotas() {
		ArrayList<Nota> notas = new ArrayList<Nota>();

		Cursor cursor = database.query(MySQLiteHelper.TABLE_NOTES,
			allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Nota nota = cursorToNota(cursor);
			notas.add(nota);
			cursor.moveToNext();
		}
		cursor.close();
		return notas;//Retorna la lista de notas de la base de datos, pero ya como una Array de Objetos
	}

	private Nota cursorToNota(Cursor cursor) {
		Nota nota = new Nota();
		nota.setId(cursor.getLong(0));
		nota.setTitulo(cursor.getString(1));
		nota.setContenido(cursor.getString(2));
		return nota;
	}

}
