package com.UFF.ProjetoRide.controllers;

import java.security.Principal;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;

import com.UFF.ProjetoRide.models.Grupo;
import com.UFF.ProjetoRide.models.Perfil;
import com.UFF.ProjetoRide.models.Usuario;
import com.UFF.ProjetoRide.repository.GrupoDAO;
import com.UFF.ProjetoRide.repository.PerfilDAO;
import com.UFF.ProjetoRide.repository.UsuarioDAO;

@Controller
public class PerfilController {
	
	UsuarioDAO usuariodao = new UsuarioDAO();
	PerfilDAO perfildao = new PerfilDAO();
	GrupoDAO grupodao = new GrupoDAO();
	
  @GetMapping("/virarMotorista")
	public String virarMotorista(Principal principal) {
		
		List<Usuario> listausuario = usuariodao.buscarUsuarioPeloEmail(principal.getName());
		Usuario usuario = usuariodao.findUsingEnhancedForLoop(principal.getName(), listausuario);	
		
		Perfil perfil = perfildao.visualizarPerfil(usuario.getIdusuario()+1);
		
		perfil.setTipoperfil(2);
		
		perfildao.atualizarPerfil(perfil);
		
		
		return "redirect:/home";
	}
  
  @GetMapping("/virarPassageiro")
	public String virarPassageiro(Principal principal) {
		
		List<Usuario> listausuario = usuariodao.buscarUsuarioPeloEmail(principal.getName());
		Usuario usuario = usuariodao.findUsingEnhancedForLoop(principal.getName(), listausuario);	
		
		Perfil perfil = perfildao.visualizarPerfil(usuario.getIdusuario()+1);
		
		perfil.setTipoperfil(1);
		
		perfildao.atualizarPerfil(perfil);
		
		
		return "redirect:/home";
	}
}
