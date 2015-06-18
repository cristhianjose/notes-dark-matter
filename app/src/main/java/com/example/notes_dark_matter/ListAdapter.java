package com.example.notes_dark_matter;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class ListAdapter extends ArrayAdapter<Nota> {
	List<Nota> listItems;
	NotasDataSource dataSource;

    //Hacemos referencia al DataSource
	public ListAdapter(Context context, int textViewResourceId, List<Nota> items, NotasDataSource datasource) {
		super(context, textViewResourceId, items);
		listItems = items;
		dataSource = datasource;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = convertView;
		Nota item = getItem(position);
		if (view == null) {
			view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_note, null);//fragment_note es la pantalla que se va a dibujar
		}

		TextView textTitulo = (TextView) view.findViewById(R.id.textTitulo);
		textTitulo.setText(item.getTitulo());

		Button btnBorrar = (Button) view.findViewById(R.id.btnBorrar);
		btnBorrar.setOnClickListener(new View.OnClickListener(){
			public void onClick(View view){
				Nota nota = listItems.get(position);
				dataSource.deleteNota(nota);//Una ves vinculado el boton eliminar poder borrar en la base de datos
				listItems.remove(position);
				notifyDataSetChanged();
			}
		});

		return view;
	}
}
