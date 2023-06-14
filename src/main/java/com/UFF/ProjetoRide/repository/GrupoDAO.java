package com.UFF.ProjetoRide.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.UFF.ProjetoRide.models.Grupo;
import com.UFF.ProjetoRide.models.JPAUtil;
import com.UFF.ProjetoRide.models.Perfil;


public class GrupoDAO {
    
    private EntityManager em;
    
    public void criarGrupo(Grupo g) {
        
        em = JPAUtil.getEM();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(g);
        et.commit();
    
    }
    
    
    public Grupo visualizarGrupo(int idgrupo) {
    
        em = JPAUtil.getEM();
        EntityTransaction et = em.getTransaction();
        et.begin();
        Grupo g = em.find(Grupo.class, idgrupo);
        et.commit();
        em.close();
        return g;
    
    }
    
    public void atualizarGrupo(Grupo g) {
    	em = JPAUtil.getEM();
        EntityTransaction et = em.getTransaction();
        et.begin();
    	em.merge(g);
    	et.commit();
    	em.close();
    }
    
    
    public void finalizarGrupo(int idgrupo) {
    
        em = JPAUtil.getEM();
        EntityTransaction et = em.getTransaction();
        et.begin();
        Grupo g = em.find(Grupo.class, idgrupo);
        em.remove(g);
        et.commit();
        em.close();
    
    }
    
    public List<Grupo> buscarGrupoPorPerfil(int idperfil) {
    	
      String jpqlQuery = "SELECT g FROM Grupo g WHERE perfil1_idperfil='"+idperfil+"' OR perfil2_idperfil='"+idperfil+"' OR perfil3_idperfil='"+idperfil+"' OR perfil4_idperfil='"+idperfil+"' OR perfil5_idperfil='"+idperfil+"'";
      em = JPAUtil.getEM();
      Query query = em.createQuery(jpqlQuery);
      List<Grupo> g = query.getResultList();
      em.close();
      return g;
   
    }
    
    
   public List<Grupo> listarGrupos() {
   
      String jpqlQuery = "SELECT g FROM Grupo g ORDER BY g.idgrupo";
      em = JPAUtil.getEM();
      Query query = em.createQuery(jpqlQuery);
      List<Grupo> g = query.getResultList();
      em.close();
      return g;
   }
   
   
    
   public void compartilharGrupo() {}
   
   public void pedirSocorro() {}
   
   public void compartilharLocalizacao() {}
   
    
}

