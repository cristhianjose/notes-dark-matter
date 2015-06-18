package com.example.notes_dark_matter;

public class Nota {

	private long id;
	private String Titulo;
	private String Contenido;

	public long getId() {
		return this.id;
	}

	public void setId(long Id) {
		this.id = Id;
	}

	public String getTitulo() {
		return this.Titulo;
	}

	public void setTitulo(String titulo) {
		this.Titulo = titulo;
	}

	public String getContenido() {
		return this.Contenido;
	}

	public void setContenido(String contenido) {
		this.Contenido = contenido;
	}

	@Override
	public String toString() {
		return Titulo;
	}

}
