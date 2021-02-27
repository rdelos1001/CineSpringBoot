package com.example.demo.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Director;
import com.example.demo.entity.Pelicula;

public interface PeliculaRepository extends CrudRepository<Pelicula, Integer>{

	public ArrayList<Pelicula> findAllByDirector(Director director);

	public ArrayList<Pelicula> findAllByTituloAndEdadMinimaAndFechaEstrenoAndDirector(String titulo, int edadMinima, String fechaEstreno,
			Director director);
}
