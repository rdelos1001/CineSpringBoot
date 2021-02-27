package com.example.demo.controller;

import java.util.ArrayList;


import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import com.example.demo.entity.Administrador;
import com.example.demo.entity.Director;
import com.example.demo.entity.Pelicula;
import com.example.demo.exceptions.DatosNoCorrectosException;
import com.example.demo.services.AdministradorService;
import com.example.demo.services.DirectorService;
import com.example.demo.services.PeliculaService;



/**
 * Servlet implementation class mainServlet
 */
/**
 * @author Raul
 *
 */
@Controller
public class ApplicationController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Lista de directores buscados 
	 * */
	private ArrayList<Director>directoresBuscados=new ArrayList<Director>();
	
	/**
	 * Administrador logeado actualmente
	 * */
	private Administrador adminLogeado=null;
	
	/**
	 * Mensajes de error
	 * */
	private ArrayList<String> errores=new ArrayList<String>();
	
	/**
	 * Servicio para gestionar los administradores
	 * */
	@Autowired
	private AdministradorService adminstradorService;
	
	/**
	 * Servicio para gestionar las peliculas
	 * */
	@Autowired
	private PeliculaService peliculaService;
	
	/**
	 * Servicio para gestionar los directores
	 * */
	@Autowired
	private DirectorService directorService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplicationController() {
        super();
    }
    
    /**
     * Muestra la vista de info.html
     * 
     * @return
     */
    @GetMapping("/info")
    public String info() {
    	return "views/info.html";
    }
    
    /**
     * Muestra la vista de index.html
	 * @param model Modelo recibido por par&aacute;metros
     * 
     * @return
     */
    @GetMapping("/")
    public String root(Model model) {
    	model.addAttribute("director",new Director());
    	model.addAttribute("errores",this.errores);
    	return "index.html";
    }
    
    /**
     * Muestra la vista de inicio.html o loginAdmin.html
     * @param model Modelo recibido por par&aacute;metros
     * 
     * @return
     */
    @GetMapping("/loginAdmin")
    public String handleRequest(Model model) {
    	String path="views/loginAdmin.html";
    	if(adminLogeado()) {
    		model.addAttribute("administradorLogeado",this.adminLogeado);    		
    		path="views/inicio.html";
    	}else {
    		model.addAttribute("adminLogin",new Administrador());
			model.addAttribute("errores", this.errores);

    	}
    	return path;
    }
    
    /**
     * Redirecciona a / tras cerrar sesi&oacute;n
     * 
     * @return
     */
    @GetMapping("/cerrarSesion")
    public String cerrarSesionHandleRequest() {
    	this.adminLogeado=null;
    	return "redirect:/";
    }
    
    /**
     * Muestra la vista de mantenimiento.html
     * @param model Modelo recibido por par&aacute;metros
     * @return
     */
    @GetMapping("/mantenimiento")
    public String mantenimientoPeliculas(Model model) {
    	ArrayList<Pelicula> peliculas =this.peliculaService.getAll();
		model.addAttribute("peliculas",peliculas);
		return "views/mantenimiento.html";
    }
    
    /**
     * Muestra la vista de insertarPelicula.html
     * @param model Modelo recibido por par&aacute;metros
     * @return
     */
    @GetMapping("/insertar")
    public String insertar(Model model) {
		ArrayList<Director> directoresDisponibles =this.directorService.getAll();
		model.addAttribute("directoresDisponibles", directoresDisponibles);
		return "views/insertarPelicula.html";
    }
    
    /**
     * Muestra la vista de eliminar.html
     * @param model Modelo recibido por par&aacute;metros
     * @return
     */
    @GetMapping("/eliminar")
    public String eliminarPeliculas(Model model) {
    	ArrayList<Pelicula> peliculas =this.peliculaService.getAll();
		model.addAttribute("peliculas",peliculas);
		return "views/eliminar.html";
    }
    
    /**
     * Redirecciona a /mantenimiento tras eliminar las peliculas seleccionadas
     * @param model Modelo recibido por par&aacute;metros
     * @param ids String con todos los id de peliculas que se desean eliminar
     * @return
     */
    @GetMapping("/eliminarPelicula")
    public String eliminarPelicula(Model model,@Param("ids")String ids) {
    	String id[]=ids.split(",");
    	for (String string : id) {
    		Pelicula peliculaDel= this.peliculaService.buscarPorId(Integer.parseInt(string));			
    		this.peliculaService.borrarPelicula(peliculaDel);
    	}
    	
    	return "redirect:/mantenimiento";
    }
    
    /**
     * Redirecciona a /loginAdmin tras insertar la pelicula
     * @param model Modelo recibido por par&aacute;metros
     * @param titulo Titulo de la pelicula que se desea insertar
     * @param edad edad de la pelicula que se desea insertar
     * @param fechaEstreno  Fecha del estreno de la pelicula que se desea insertar de la pelicula que se desea insertar
     * @return
     */
    @GetMapping("/insertarPelicula")
    public String insertarPelicula(Model model,
    		@Param("titulo")String titulo,
    		@Param("edad")int edad,
    		@Param("fechaEstreno")String fechaEstreno,
    		@Param("nombreDirector")String nombreDirector) {

		
		Director director=this.directorService.getDirectorPorNombre(new Director(nombreDirector));
		this.peliculaService.insertarPelicula(new Pelicula(titulo,edad,fechaEstreno,director));
		
		return "redirect:/loginAdmin";
    }

    /**
     * Muestra la vista de modificar.html
     * @param model Modelo recibido por par&aacute;metros
     * @param pos Posici&oacute;n de la pelicula que se desea modificar
     * @return
     */
    @GetMapping("/modificar")
    public String modificar(Model model,@Param("pos")int pos) {
				
		Pelicula peliculaModificar=this.peliculaService.buscarPeliculaPorPos(pos);
		model.addAttribute("pelicula", peliculaModificar);
		model.addAttribute("directoresDisponibles", this.directorService.getAll());

		return "views/modificar.html";
    	
    }
    
    /**
     * Redirecciona a la vista de /mantenimiento tras modificar una pelicula
     * @param model Modelo recibido por par&aacute;metros
     * @param titulo
     * @param edad
     * @param fechaEstreno
     * @param nombreDirector
     * @param id
     * @return
     */
    @PostMapping("/modificarPelicula")
    public String modificarPelicula(Model model,
    		@Param("titulo")String titulo,
    		@Param("edad")int edad,
    		@Param("fechaEstreno")String fechaEstreno,
    		@Param("nombreDirector")String nombreDirector,
    		@Param("id")int id) {
    	Director director=this.directorService.getDirectorPorNombre(new Director(nombreDirector));
    	
    	this.peliculaService.actualizarPelicula(id ,new Pelicula(titulo,edad,fechaEstreno,director));
    	
    	return "redirect:/mantenimiento";
    }
    
    /**
     * Muestra la vista de selectModificar.html
     * @param model Modelo recibido por par&aacute;metros
     * @return
     */
    @GetMapping("/selectModificar")
    public String selectModificar(Model model) {
    	ArrayList<Pelicula> peliculas =this.peliculaService.getAll();
		model.addAttribute("peliculas",peliculas);
    	return "views/selectModificar.html";
    }
    
    /**
     * Redirecciona a /loginAdmin tras añadir a un nuevo administrador
     * @param model Modelo recibido por par&aacute;metros
     * @param usuario Nombre de usuario del Administrador que se desea añadir
     * @param usuarioPass
     * @return
     */
    @PostMapping("/anadirAdmin")
    public String anadirAdmin(Model model,@Param("usuario")String usuario,@Param("usuarioPass")String usuarioPass) {
    	this.adminstradorService.anadirAdmin(new Administrador(usuario,usuarioPass));
    	
    	return "redirect:/loginAdmin";
    }
    
    /**
     * @param admin Administrador que se desea logear
     * @param model Modelo recibido por par&aacute;metros
     * @return la vista inicio.html o redirecciona a /loginAdmin
     */
    @PostMapping("logear")
    public String logear(@ModelAttribute("restaurantLogin")Administrador admin, ModelMap model) {
		String path="";
		admin = this.adminstradorService.logear(admin);
		if(admin!=null) {
			this.adminLogeado= admin;
			this.adminLogeado.setPwd("no disponible");
			model.addAttribute("administradorLogeado",this.adminLogeado);
			path="views/inicio.html";
		}else {
			this.errores.add("Usuario o contraseña incorrecto");
			System.out.println("LOGIN FALLIDO\n");
			path="redirect:/loginAdmin";
		}
    	 return path;
    }
    
    /**
     * Redirecciona a /
     * @return redirecciona a /
     */
    @GetMapping("nuevaSesion")
    public String nuevaSesion(){
    	this.directoresBuscados.clear();
    	return "redirect:/";
    }
    
    /**
     * @param director
     * @param model
     * @return
     */
    @PostMapping("/buscarPorDirector")
    public String buscarPorDirector(@ModelAttribute("director")Director director, Model model) {
    	String path="redirect:/";
		try {
			if(Director.validarNombre(director.getNombre())) {
				director = this.directorService.getDirectorPorNombre(director);

				if(director!=null) {
					ArrayList<Pelicula> peliculas =peliculaService.buscarPorDirector(director);
					model.addAttribute("peliculas",peliculas);
					this.anadirDirectorBuscado(director);
					
					System.out.println("DIRECTORES BUSCADOS ");
					for (Director directorItem : this.directoresBuscados) {
						System.out.println(directorItem.getNombre());
					}
					this.errores.clear();
					path="views/mostrarPeliculas.html";
				}else {
					this.anadirError("El director no existe");
					path="redirect:/";
				}
			}else {
				throw new DatosNoCorrectosException();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("director",new Director());
    	return path;
    }
    
    /**
     * @param director
     */
    private void anadirDirectorBuscado(Director director) {
    	boolean check=false;
    	
    	for (Director director2 : this.directoresBuscados) {
			if(director2.getNombre().equals(director.getNombre())) {
				check=true;
			}
		}
		if(!check) {
			this.directoresBuscados.add(director);
		}
	}

	/**
	 * @param model
	 * @return
	 */
	@GetMapping("mostrarDirectoresBuscados")
    public String mostrarDirectoresBuscados(Model model) {
    	model.addAttribute("directores", this.directoresBuscados);
    	return "views/mostrarDirectores.html";
    }
    
	/**
	 * A&ntilde;ade un error a la lista de errores si este no est&aacute; ya en la lista.
	 * @param error error que se desea a&ntilde;adir
	 * */
	private void anadirError(String error) {
		if(this.errores.size()<=0) {
			this.errores.add(error);
		}else {
			boolean check=false;
			for(String errorActual :this.errores) {
				if(errorActual.equals(error)) {
					check=true;
				}
			}
			if(!check) {
				this.errores.add(error);
			}			
		}
	}
	
	/**
	 * Comprueba si el usuario está o no logeado
	 * 
	 * @return
	 */
	public boolean adminLogeado() {
		boolean check=false;
		if(adminLogeado!=null) {
			check=true;
		}
		return check;
	}
	
	/**
	 * Obtiene el nombre del usuario logeado
	 * @return
	 */
	public String getAdminName() {
		return adminLogeado.getUsuario();
	}
	
	/**
	 * Obtiene la contraseña del usuario logeado
	 * 
	 * @return
	 */
	public String getAdminPass() {
		return adminLogeado.getPwd();
	}
}
