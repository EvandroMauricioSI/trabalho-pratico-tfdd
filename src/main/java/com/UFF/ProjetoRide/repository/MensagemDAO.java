package com.UFF.ProjetoRide.repository;

import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.UFF.ProjetoRide.models.JPAUtil;
import com.UFF.ProjetoRide.models.Mensagem;


public class MensagemDAO {
    
    private EntityManager em;
    
    public void enviarMensagem(Mensagem m) {
    
        em = JPAUtil.getEM();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(m);
        et.commit();
    
    }
    
    public Mensagem visualizarMensagem(int idmensagem) {
    
        em = JPAUtil.getEM();
        EntityTransaction et = em.getTransaction();
        et.begin();
        Mensagem m = em.find(Mensagem.class, idmensagem);
        et.commit();
        em.close();
        return m;
    
    }
    
    public void deletarMensagem(int idmensagem) {
    
        em = JPAUtil.getEM();
        EntityTransaction et = em.getTransaction();
        et.begin();
        Mensagem m = em.find(Mensagem.class, idmensagem);
        em.remove(m);
        et.commit();
        em.close();
    
    }
    
    public List<Mensagem> buscarMensagemDoGrupo(int idgrupo) {
    
      String jpqlQuery = "SELECT m FROM Mensagem m WHERE grupo_idgrupo= :id ORDER BY m.datacriacao";
      em = JPAUtil.getEM();
      Query query = em.createQuery(jpqlQuery);
      query.setParameter("id", idgrupo);
      List<Mensagem> m = query.getResultList();
      em.close();
      return m;
   
    }
    
   public List<Mensagem> listarMensagens() {
   
      String jpqlQuery = "SELECT * FROM Mensagem";
      em = JPAUtil.getEM();
      Query query = em.createQuery(jpqlQuery);
      List<Mensagem> m = query.getResultList();
      em.close();
      return m;
   }
   
   public int qtdDeMsg(Iterable<Mensagem> mensagens) {
		  if (mensagens instanceof Collection)
		    return ((Collection<?>)mensagens).size();

		  // else iterate

		  int i = 0;
		  for (Object obj : mensagens) i++;
		  return i;
		}
    
}