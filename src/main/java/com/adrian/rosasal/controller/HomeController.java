package com.adrian.rosasal.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.adrian.rosasal.model.Perfil;
import com.adrian.rosasal.model.Usuario;
import com.adrian.rosasal.model.Vacante;
import com.adrian.rosasal.service.IntUsuariosService;
import com.adrian.rosasal.service.IntVacantesService;

@Controller
public class HomeController {
	
	@Autowired
	private IntUsuariosService usuariosService;
	
	@Autowired
	private IntVacantesService vacantesService;
	
	@PostMapping("/guardar")
	public String guardarUsuario(Usuario usuario) {
		String modificar = "{noop}" + usuario.getPassword();
		usuario.setPassword(modificar);
		usuario.setEstatus(1);
		usuario.setFechaRegistro(LocalDate.now());
		Perfil perfil = new Perfil();
		perfil.setId(3); //Rol- usuario
		usuario.agregar(perfil);
		
		System.out.println(usuario);
		usuariosService.guardar(usuario);
		
		return "redirect:/login?logout";
	}
	
	@GetMapping("/crear")
	public String crearUsuario(Usuario usuario) {
		return "formRegistro";
	}
	
	@GetMapping("/acerca")
	public String acerca() {
		return "acerca";
	}
	
	@GetMapping("/")
	public String mostrarHome(Model model) {
		List<Vacante> vacantes = 
				vacantesService.todasDestacadas();
		model.addAttribute("vacantes", vacantes);
		return "home";
	}
	
}
