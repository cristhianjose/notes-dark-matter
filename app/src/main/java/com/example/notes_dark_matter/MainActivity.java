package com.example.notes_dark_matter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity {

	private ArrayList<Nota> Notas = new ArrayList<Nota>();
	private ListAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		if (adapter == null) {
			adapter = new ListAdapter(this, 0, Notas);
		}

		ListView list = (ListView) findViewById(R.id.listNotas);
		list.setAdapter(adapter);

		Button btnNuevo = (Button)findViewById(R.id.btnNuevo);
		btnNuevo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent NuevaNota = new Intent(MainActivity.this, NewActivity.class);
				startActivityForResult(NuevaNota, 1);
			}
		});

		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				Intent VerNota = new Intent(MainActivity.this, ViewActivity.class);
				//VerNota.putExtra("titulo", Notas[i]);
				//VerNota.putExtra("contenido", );
				startActivity(VerNota);
			}
		});
    }

	@Override
	public void onActivityResult(int RequestCode, int ResultCode, Intent data) {

		if(RequestCode == 1) {
			if(ResultCode == RESULT_OK) {
				Nota nueva_nota = new Nota();
				nueva_nota.setTitulo(data.getStringExtra("titulo"));
				nueva_nota.setContenido(data.getStringExtra("contenido"));
				Notas.add(nueva_nota);
				adapter.notifyDataSetChanged();
			}
		}
	}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
