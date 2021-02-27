package com.example.demo.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Director;
import com.example.demo.repository.DirectorRepository;

@Service
public class DirectorServiceImpl implements DirectorService{

	@Autowired
	DirectorRepository repository;

	@Override
	public Director getDirectorPorNombre(Director director) {
		System.out.println("director.getNombre()");
		System.out.println(director.getNombre());
		try {
			return repository.findAllByNombre(director.getNombre()).get(0);			
		}catch(IndexOutOfBoundsException e) {
			System.out.println("EL DIRECTOR NO EXISTE ");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<Director> getAll() {
		return (ArrayList<Director>) repository.findAll();
	}
	

	
}
