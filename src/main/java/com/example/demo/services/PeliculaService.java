package com.example.demo.services;

import java.util.ArrayList;

import com.example.demo.entity.Director;
import com.example.demo.entity.Pelicula;

public interface PeliculaService {

	public ArrayList<Pelicula> buscarPorDirector(Director director);

	public ArrayList<Pelicula> getAll();

	public Pelicula insertarPelicula(Pelicula pelicula);

	public Pelicula buscarPeliculaPorPos(int pos);

	public Pelicula buscarPeliculaPorTodosLosDatos(Pelicula pelicula);

	public Pelicula actualizarPelicula(int i, Pelicula pelicula);

	public Pelicula buscarPorId(int parseInt);

	public Pelicula borrarPelicula(Pelicula peliculaDel);
}
