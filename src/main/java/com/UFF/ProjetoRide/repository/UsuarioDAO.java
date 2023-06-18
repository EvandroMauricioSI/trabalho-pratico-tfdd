package com.UFF.ProjetoRide.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.context.annotation.Configuration;

import com.UFF.ProjetoRide.models.JPAUtil;
import com.UFF.ProjetoRide.models.Perfil;
import com.UFF.ProjetoRide.models.Usuario;

@Configuration
public class UsuarioDAO {
    
    private EntityManager em;
    
    
    public void criarConta(Usuario u) {
    	
    
        em = JPAUtil.getEM();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(u);
        et.commit();
    
    }
    
    public Usuario visualizarUsuario(int idusuario) {
    
        em = JPAUtil.getEM();
        EntityTransaction et = em.getTransaction();
        et.begin();
        Usuario u = em.find(Usuario.class, idusuario);
        et.commit();
        em.close();
        return u;
    
    }
    
    public void atualizarUsuario(Usuario u) {
    	em = JPAUtil.getEM();
        EntityTransaction et = em.getTransaction();
        et.begin();
    	em.merge(u);
    	et.commit();
    	em.close();
    }
    
    
    public void excluirConta(int idusuario) {
    
        em = JPAUtil.getEM();
        EntityTransaction et = em.getTransaction();
        et.begin();
        Usuario u = em.find(Usuario.class, idusuario);
        em.remove(u);
        et.commit();
        em.close();
    
    }
    
    public List<Usuario> buscarUsuario() {
    
      String jpqlQuery = "SELECT u FROM Usuario u ORDER BY u.idusuario";
      em = JPAUtil.getEM();
      Query query = em.createQuery(jpqlQuery);
      List<Usuario> u = query.getResultList();
      em.close();
      return u;
   
    }
    
    public List<Usuario> buscarUsuarioPeloEmail(String email) {
        
    	String jpqlQuery = "SELECT u FROM Usuario u WHERE email= :email ";
        em = JPAUtil.getEM();
        Query query = em.createQuery(jpqlQuery);
        query.setParameter("email", email);
        List<Usuario> u = query.getResultList();
        em.close();
        return u;
     
    }
    
    public Usuario findUsingEnhancedForLoop(String email, List<Usuario> usuarios) {

    	for (Usuario usuario : usuarios) {
	        if (usuario.getEmail().equals(email)) {
	            return usuario;
	        }
	    	}
    		    return null;
    		}
    
    
   public void darNotaPassageiro() {}
   public void visualizarPerfil(Usuario usuario){}
   public void recuperarSenha() {}
   public void logar() {}
   public void sair() {}
   
   

}
