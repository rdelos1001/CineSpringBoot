package com.example.demo.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Director;

public interface DirectorRepository extends CrudRepository<Director, Integer>{
	public ArrayList<Director> findAllByNombre(String nombre);
}
