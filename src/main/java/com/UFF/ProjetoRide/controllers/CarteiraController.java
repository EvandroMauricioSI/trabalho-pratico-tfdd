package com.UFF.ProjetoRide.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;

import com.UFF.ProjetoRide.models.Carteira;
import com.UFF.ProjetoRide.models.Grupo;
import com.UFF.ProjetoRide.models.Perfil;
import com.UFF.ProjetoRide.models.Usuario;
import com.UFF.ProjetoRide.repository.CarteiraDAO;
import com.UFF.ProjetoRide.repository.GrupoDAO;
import com.UFF.ProjetoRide.repository.MensagemDAO;
import com.UFF.ProjetoRide.repository.PerfilDAO;
import com.UFF.ProjetoRide.repository.UsuarioDAO;

@Controller
public class CarteiraController {
	
	UsuarioDAO usuariodao = new UsuarioDAO();
	PerfilDAO perfildao = new PerfilDAO();
	CarteiraDAO carteiradao = new CarteiraDAO();
	GrupoDAO grupodao = new GrupoDAO();
	MensagemDAO mensagemdao = new MensagemDAO();

  @GetMapping("/config")
	public ModelAndView config(Principal principal) {
		
		ModelAndView mv = new ModelAndView("config");
		
		List<Usuario> listausuario = usuariodao.buscarUsuarioPeloEmail(principal.getName());
		
		Usuario usuario = usuariodao.findUsingEnhancedForLoop(principal.getName(), listausuario);			
		Perfil perfil = perfildao.visualizarPerfil(usuario.getIdusuario()+1);
		Carteira carteira = carteiradao.visualizarCarteira(usuario.getIdusuario()+2);
		Iterable<Grupo> grupos = grupodao.buscarGrupoPorPerfil(perfil.getIdperfil());
		
		mv.addObject("usuario", usuario);
		mv.addObject("perfil", perfil);
		mv.addObject("carteira", carteira);
		mv.addObject("grupos", grupos);
		
		return mv;	
		
	}
	@RequestMapping(value="/config", params = "adicionarsaldo", method=RequestMethod.POST)
	public String adicionarSaldo(int idcarteira, double qtdadicional) {
		
		Carteira carteira = carteiradao.visualizarCarteira(idcarteira);
		
		carteira.setSaldoconta(carteira.getSaldoconta()+qtdadicional);
		carteiradao.atualizarCarteira(carteira);
		
		return "redirect:/config";
		
	}
	@RequestMapping(value="/config", params = "transferirsaldo", method=RequestMethod.POST)
	public String transferirSaldo(int idcarteira, double qtdtransferida) {
		
		Carteira carteira = carteiradao.visualizarCarteira(idcarteira);
		
		carteira.setSaldoconta(carteira.getSaldoconta()-qtdtransferida);
		
		if(carteira.getSaldoconta() < 0) {
			return "redirect:/config";
		
		}
		carteiradao.atualizarCarteira(carteira);
		return "redirect:/config";
		
	}
	
	

}
