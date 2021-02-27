package com.example.demo.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Administrador;
import com.example.demo.repository.AdministradorRepository;

@Service
public class AdministradorServiceImpl implements AdministradorService{

	@Autowired
	AdministradorRepository repository;
	
	public Administrador logear(Administrador admin) {
		ArrayList<Administrador>respuesta = repository.findAllByUsuarioAndPwd(admin.getUsuario(), admin.getPwd());
		
		if(!respuesta.isEmpty()) {
			return respuesta.get(0);
		}
		
		return null;
	}

	@Override
	public Administrador anadirAdmin(Administrador administrador) {
		return repository.save(administrador);
	}
}
