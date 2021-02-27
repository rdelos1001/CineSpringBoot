package com.example.demo.services;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Director;
import com.example.demo.entity.Pelicula;
import com.example.demo.repository.PeliculaRepository;

@Service
public class PeliculaServiceImpl implements PeliculaService{

	@Autowired
	PeliculaRepository repository;

	@Override
	public ArrayList<Pelicula> buscarPorDirector(Director director) {
		
		return repository.findAllByDirector(director);
	}

	@Override
	public ArrayList<Pelicula> getAll() {
		return (ArrayList<Pelicula>) repository.findAll();
	}

	@Override
	public Pelicula insertarPelicula(Pelicula pelicula) {
		return repository.save(pelicula);
	}

	@Override
	public Pelicula buscarPeliculaPorPos(int pos) {
		return ((ArrayList<Pelicula>)repository.findAll()).get(pos);
	}

	@Override
	public Pelicula buscarPeliculaPorTodosLosDatos(Pelicula pelicula) {
		return (repository.findAllByTituloAndEdadMinimaAndFechaEstrenoAndDirector(
				pelicula.getTitulo(),
				pelicula.getEdadMinima(),
				pelicula.getFechaEstreno(),
				pelicula.getDirector())).get(0);
	}

	@Override
	public Pelicula actualizarPelicula(int i,Pelicula pelicula) {
		try {
			if(repository.findById(i).isPresent()) {
				pelicula.setId(i);
				return repository.save(pelicula);
			}else {
				throw new Exception ("La pelicula no existe");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Pelicula buscarPorId(int id) {
		
		try {
			if(repository.findById(id).isPresent()) {
				return repository.findById(id).get();
			}else {
				throw new Exception("La pelicula no existe");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Pelicula borrarPelicula(Pelicula peliculaDel) {
		try {
			if(repository.findById(peliculaDel.getId()).isPresent()) {
				repository.delete(peliculaDel);
				return peliculaDel;
			}else {
				throw new Exception("La pelicula no existe");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	
}
