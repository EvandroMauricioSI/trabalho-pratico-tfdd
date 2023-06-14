package com.UFF.ProjetoRide.controllers;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.UFF.ProjetoRide.models.Grupo;
import com.UFF.ProjetoRide.models.Mensagem;
import com.UFF.ProjetoRide.models.Perfil;
import com.UFF.ProjetoRide.repository.GrupoDAO;
import com.UFF.ProjetoRide.repository.MensagemDAO;
import com.UFF.ProjetoRide.repository.PerfilDAO;

@Controller
public class MensagemController {
	
	GrupoDAO grupodao = new GrupoDAO();
	MensagemDAO mensagemdao = new MensagemDAO();
	PerfilDAO perfildao = new PerfilDAO();
	
	@RequestMapping(value="/grupo/{codigo}", params = "enviarmensagem", method=RequestMethod.POST)
	public String enviarMensagem(@PathVariable("codigo") int codigo, Mensagem mensagem, int idperfil){
		
		Perfil perfil = perfildao.visualizarPerfil(idperfil);
		Grupo grupo = grupodao.visualizarGrupo(codigo);		
		mensagem.setDatacriacao(Calendar.getInstance().getTime());
		
		
		mensagem.setGrupo(grupo);
		mensagem.setPerfil(perfil);

		mensagemdao.enviarMensagem(mensagem);

		return "redirect:/grupo/{codigo}";
	}

}
