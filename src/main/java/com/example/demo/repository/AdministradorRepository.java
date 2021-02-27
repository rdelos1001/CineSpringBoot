package com.example.demo.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Administrador;

public interface AdministradorRepository extends CrudRepository<Administrador, Integer>{

	public ArrayList<Administrador> findAllByUsuarioAndPwd(String usuario,String pwd);
}
