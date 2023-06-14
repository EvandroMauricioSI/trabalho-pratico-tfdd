package com.UFF.ProjetoRide.controllers;

import java.util.List;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.UFF.ProjetoRide.models.Carteira;
import com.UFF.ProjetoRide.models.Perfil;
import com.UFF.ProjetoRide.models.Usuario;
import com.UFF.ProjetoRide.repository.CarteiraDAO;
import com.UFF.ProjetoRide.repository.PerfilDAO;
import com.UFF.ProjetoRide.repository.UsuarioDAO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



@Controller
public class UsuarioController {
	
	UsuarioDAO usuariodao = new UsuarioDAO();
	PerfilDAO perfildao = new PerfilDAO();
	CarteiraDAO carteiradao = new CarteiraDAO();
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String paginaLogin() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "login";
		}
		return "redirect:/home";
		
	}	
	
	@RequestMapping(value="/login/registro", method=RequestMethod.POST)
	public String formRegistro(Usuario usuario) {
		
		usuario.setSenha("123");
		usuariodao.criarConta(usuario);
		
		Perfil perfil = new Perfil();
		perfil.setModelocarro("");
		perfil.setNotamotorista(0);
		perfil.setNotapassageiro(0);
		perfil.setPlacacarro("");
		perfil.setQtdgruposMcriados(0);
		perfil.setQtdgruposPcriados(0);
		perfil.setTipoperfil(1);
		perfil.setUsuario(usuario);
		perfildao.criarPerfil(perfil);
		
		Carteira carteira = new Carteira();
		carteira.setSaldoconta(0);
		carteira.setFormapagamento("");
		carteira.setUsuario(usuario);
		carteiradao.criarCarteira(carteira);
		
		return "redirect:/login";
		
	}
	@RequestMapping(value="/config", params = "editarusuario", method=RequestMethod.POST)
	public String atualizarUsuario(Usuario usuario) {
		
		usuariodao.atualizarUsuario(usuario);
		
		return "redirect:/config";
	}
	@RequestMapping(value="/config", params = "editarperfil", method=RequestMethod.POST)
	public String atualizarPerfil(Perfil perfil) {
		
		Perfil perfilantigo = perfildao.visualizarPerfil(perfil.getIdperfil());
		
		perfilantigo.setModelocarro(perfil.getModelocarro());
		perfilantigo.setPlacacarro(perfil.getPlacacarro());
		perfildao.atualizarPerfil(perfilantigo);
		
		return "redirect:/config";
	}
	@RequestMapping(value="/config", params = "trocarsenha", method=RequestMethod.POST)
	public String trocarSenha(int idusuario, String senhaatual, String novasenha, String novasenhaconfirmacao) {
		
		Usuario usuario = usuariodao.visualizarUsuario(idusuario);
		
		if(senhaatual.equals(usuario.getSenha())) {
			if(novasenha.equals(novasenhaconfirmacao)) {
				usuario.setSenha(novasenha);
				usuariodao.atualizarUsuario(usuario);
			}
		}
		
		return "redirect:/config";
	}
	@RequestMapping(value = "/usuarios", method = RequestMethod.GET)
	public @ResponseBody String listAllUsuarios(){
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.addObject("usuario", new Usuario());
	    List<Usuario> usuarioList = usuariodao.buscarUsuario();

	    Gson gson = new GsonBuilder().create();
	    String jsonString = gson.toJson(usuarioList);

	    return jsonString;
	}
}