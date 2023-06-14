package com.UFF.ProjetoRide.controllers;

import java.security.Principal;
import java.util.Calendar;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.UFF.ProjetoRide.models.Grupo;
import com.UFF.ProjetoRide.models.Mensagem;
import com.UFF.ProjetoRide.models.Perfil;
import com.UFF.ProjetoRide.models.Usuario;
import com.UFF.ProjetoRide.repository.GrupoDAO;
import com.UFF.ProjetoRide.repository.MensagemDAO;
import com.UFF.ProjetoRide.repository.PerfilDAO;
import com.UFF.ProjetoRide.repository.UsuarioDAO;

@Controller
public class GrupoController {

	UsuarioDAO usuariodao = new UsuarioDAO();
	PerfilDAO perfildao = new PerfilDAO();
	GrupoDAO grupodao = new GrupoDAO();
	MensagemDAO mensagemdao = new MensagemDAO();
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public ModelAndView listaGruposIndex(Principal principal){
		
		ModelAndView mv = new ModelAndView("home");
		
		if(principal != null) {
			List<Usuario> listausuario = usuariodao.buscarUsuarioPeloEmail(principal.getName());
			Usuario usuario = usuariodao.findUsingEnhancedForLoop(principal.getName(), listausuario);	
			Perfil perfil = perfildao.visualizarPerfil(usuario.getIdusuario()+1);
			mv.addObject("usuario", usuario);
			mv.addObject("perfil", perfil);
		}
		Iterable<Grupo> grupos = grupodao.listarGrupos();
		mv.addObject("grupos", grupos);
		
		return mv;		
	}
	@RequestMapping(value="/home", params = "criargrupo", method=RequestMethod.POST)
	public String criarGrupo(Principal principal, Grupo grupo){
		
		
		List<Usuario> listausuario = usuariodao.buscarUsuarioPeloEmail(principal.getName());
		Usuario usuario = usuariodao.findUsingEnhancedForLoop(principal.getName(), listausuario);
		
		Perfil perfil = perfildao.visualizarPerfil(usuario.getIdusuario()+1);
		System.out.println(perfil);
		grupo.setQtdatual(1);
		if(perfil.getTipoperfil() == 1) {
			
			grupo.setTipogrupo(1);
			int a = perfil.getQtdgruposPcriados();
			a++;
			perfil.setQtdgruposPcriados(a);
			perfildao.atualizarPerfil(perfil);
		}
		if(perfil.getTipoperfil() == 2) {
			
			grupo.setTipogrupo(2);
			int a = perfil.getQtdgruposMcriados();
			a++;
			perfil.setQtdgruposMcriados(a);
			perfildao.atualizarPerfil(perfil);
		}
		grupo.setPerfil1(perfil);
		System.out.println(grupo);
		grupodao.criarGrupo(grupo);

		return "redirect:/grupo/"+grupo.getIdgrupo();
	}
	@RequestMapping(value="/grupo/{codigo}", method=RequestMethod.GET)
	public ModelAndView entrarGrupo(@PathVariable("codigo") int codigo, Principal principal){
		
		List<Usuario> listausuario = usuariodao.buscarUsuarioPeloEmail(principal.getName());
		Usuario usuario = usuariodao.findUsingEnhancedForLoop(principal.getName(), listausuario);
		
		Perfil perfil = perfildao.visualizarPerfil(usuario.getIdusuario()+1);
		
		Grupo grupo = grupodao.visualizarGrupo(codigo);
		
		System.out.println(perfil.equals(grupo.getPerfil1()));
		System.out.println(perfil.equals(grupo.getPerfil2()));
		System.out.println(perfil.equals(grupo.getPerfil3()));
		System.out.println(perfil.equals(grupo.getPerfil4()));
		System.out.println(perfil.equals(grupo.getPerfil5()));
		System.out.println("");
		
		
		if(perfil.equals(grupo.getPerfil1()) || perfil.equals(grupo.getPerfil2()) || perfil.equals(grupo.getPerfil3()) || perfil.equals(grupo.getPerfil4()) || perfil.equals(grupo.getPerfil5())) {
			System.out.println("Usuario repetido.");
		}
		else {
			if(grupo.getQtdparticipantes() > grupo.getQtdatual() && grupo.getDatafinalizacao() == null) {
			
				if((grupo.getPerfil2() == null)) {
					
					grupo.setPerfil2(perfil);	
					grupo.setQtdatual(grupo.getQtdatual()+1);
					grupodao.atualizarGrupo(grupo);
				}
				else if(grupo.getPerfil3() == null) {
					
					grupo.setPerfil3(perfil);
					grupo.setQtdatual(grupo.getQtdatual()+1);
					grupodao.atualizarGrupo(grupo);
				}
				else if(grupo.getPerfil4() == null) {
					
					grupo.setPerfil4(perfil);
					grupo.setQtdatual(grupo.getQtdatual()+1);
					grupodao.atualizarGrupo(grupo);
				}
				else if(grupo.getPerfil5() == null) {
					
					grupo.setPerfil5(perfil);
					grupo.setQtdatual(grupo.getQtdatual()+1);
					grupodao.atualizarGrupo(grupo);
				}
			
			}
			else {
				
				return new ModelAndView("redirect:/home");
			}
		}
		Iterable<Mensagem> mensagens = mensagemdao.buscarMensagemDoGrupo(codigo);
		int tamanho = mensagemdao.qtdDeMsg(mensagens);
		System.out.println(mensagens);
		System.out.println(tamanho);
		ModelAndView mv = new ModelAndView("corrida");
		mv.addObject("grupo", grupo);
		mv.addObject("mensagens", mensagens);
		mv.addObject("perfil", perfil);
		mv.addObject("usuario", usuario);
		mv.addObject("tamanho", tamanho);

		return mv;	
		
	}
	@RequestMapping(value="/sairGrupo/{codigo}", method=RequestMethod.GET)
	public String sairGrupo(@PathVariable("codigo") int codigo, Principal principal) {
		
		List<Usuario> listausuario = usuariodao.buscarUsuarioPeloEmail(principal.getName());
		Usuario usuario = usuariodao.findUsingEnhancedForLoop(principal.getName(), listausuario);
		
		Perfil perfil = perfildao.visualizarPerfil(usuario.getIdusuario()+1);
		
		Grupo grupo = grupodao.visualizarGrupo(codigo);
		
		if(perfil.equals(grupo.getPerfil1())) {
			grupo.setPerfil1(null);
			grupo.setQtdatual(grupo.getQtdatual()-1);
			grupodao.atualizarGrupo(grupo);
			
		}
		else if(perfil.equals(grupo.getPerfil2())) {
			grupo.setPerfil2(null);
			grupo.setQtdatual(grupo.getQtdatual()-1);
			grupodao.atualizarGrupo(grupo);
			
		}
		else if(perfil.equals(grupo.getPerfil3())) {
			grupo.setPerfil3(null);
			grupo.setQtdatual(grupo.getQtdatual()-1);
			grupodao.atualizarGrupo(grupo);		
					
				}
		else if(perfil.equals(grupo.getPerfil4())) {
			grupo.setPerfil4(null);
			grupo.setQtdatual(grupo.getQtdatual()-1);
			grupodao.atualizarGrupo(grupo);
			
		}
		else if(perfil.equals(grupo.getPerfil5())) {
			grupo.setPerfil5(null);
			grupo.setQtdatual(grupo.getQtdatual()-1);
			grupodao.atualizarGrupo(grupo);
			
		}
	
		return "redirect:/home";
	}
	@RequestMapping(value="/finalizarCorrida/{codigo}", method=RequestMethod.GET)
	public String finalizarCorrida(@PathVariable("codigo") int codigo) {
		
		Grupo grupo = grupodao.visualizarGrupo(codigo);
		
		grupo.setDatafinalizacao(Calendar.getInstance().getTime());
		grupodao.atualizarGrupo(grupo);
		
		return "redirect:/home";
		
	}
	
}
