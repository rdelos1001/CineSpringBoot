package com.example.demo.services;


import java.util.ArrayList;

import com.example.demo.entity.Director;

public interface DirectorService {

	public Director getDirectorPorNombre(Director director);

	public ArrayList<Director> getAll();

}
