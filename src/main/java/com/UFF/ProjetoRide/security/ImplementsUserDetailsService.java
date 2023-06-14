package com.UFF.ProjetoRide.security;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.UFF.ProjetoRide.models.Usuario;
import com.UFF.ProjetoRide.repository.UsuarioDAO;


@Repository
@Transactional
public class ImplementsUserDetailsService implements UserDetailsService{

	@Autowired
	private UsuarioDAO usuariodao;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		List<Usuario> usuariolista = usuariodao.buscarUsuarioPeloEmail(email);
		Usuario usuario = usuariodao.findUsingEnhancedForLoop(email, usuariolista);
		
		
		if(usuario == null){
			throw new UsernameNotFoundException("Usuario não encontrado!");
		}
		return usuario;
	}

}