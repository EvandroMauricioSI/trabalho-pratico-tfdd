package com.UFF.ProjetoRide.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.UFF.ProjetoRide.models.JPAUtil;
import com.UFF.ProjetoRide.models.Perfil;


public class PerfilDAO {
    
    private EntityManager em;
    
    public void criarPerfil(Perfil p) {
    
        em = JPAUtil.getEM();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(p);
        et.commit();
    
    }
    
    public Perfil visualizarPerfil(int idperfil) {
    	
        em = JPAUtil.getEM();
        EntityTransaction et = em.getTransaction();
        et.begin();
        Perfil p = em.find(Perfil.class, idperfil);
        et.commit();
        em.close();
        return p;
    
    }
    
    public void atualizarPerfil(Perfil p) {
    	em = JPAUtil.getEM();
        EntityTransaction et = em.getTransaction();
        et.begin();
    	em.merge(p);
    	et.commit();
    	em.close();
    }
    
    public void excluirPerfil(int idperfil) {
    
        em = JPAUtil.getEM();
        EntityTransaction et = em.getTransaction();
        et.begin();
        Perfil p = em.find(Perfil.class, idperfil);
        em.remove(p);
        et.commit();
        em.close();
    
    }
    
    public List<Perfil> buscarPerfil() {
    
      String jpqlQuery = "SELECT p FROM Perfil p";
      em = JPAUtil.getEM();
      Query query = em.createQuery(jpqlQuery);
      List<Perfil> p = query.getResultList();
      em.close();
      return p;
   
    }
    
 
    
   public void darNotaMotorista() {}
   public void criarGrupoPassageiro() {}
   public void criarGrupoMotorista() {}
   public void adicionarCarro() {}
   public void alterarCarro() {}   
   public void excluirCarro() {}   
   
    
    
    
    
}
