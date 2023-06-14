package com.UFF.ProjetoRide.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.UFF.ProjetoRide.models.Carteira;
import com.UFF.ProjetoRide.models.Grupo;
import com.UFF.ProjetoRide.models.JPAUtil;


public class CarteiraDAO {
    
    private EntityManager em;
    
    public void criarCarteira(Carteira c) {
    
        em = JPAUtil.getEM();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(c);
        et.commit();
    
    }
    
    public Carteira visualizarCarteira(int idcarteira) {
    
        em = JPAUtil.getEM();
        EntityTransaction et = em.getTransaction();
        et.begin();
        Carteira c = em.find(Carteira.class, idcarteira);
        et.commit();
        em.close();
        return c;
    
    }
    
    public void atualizarCarteira(Carteira c) {
    	em = JPAUtil.getEM();
        EntityTransaction et = em.getTransaction();
        et.begin();
    	em.merge(c);
    	et.commit();
    	em.close();
    }
    
    public void excluirCarteira(int idcarteira) {
    
        em = JPAUtil.getEM();
        EntityTransaction et = em.getTransaction();
        et.begin();
        Carteira c = em.find(Carteira.class, idcarteira);
        em.remove(c);
        et.commit();
        em.close();
    
    }
    
    public List<Carteira> buscarCarteira() {
    
      String jpqlQuery = "SELECT c FROM Carteira c";
      em = JPAUtil.getEM();
      Query query = em.createQuery(jpqlQuery);
      List<Carteira> c = query.getResultList();
      em.close();
      return c;
   
    }
    
   public void adicionarFormaPagamento() {}
   
   public void alterarFormaPagamento() {}
   
   public void esquecerFormaPagamento() {}
   
   public void adicionarPromocao() {}
   
   
    
}