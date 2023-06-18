package com.UFF.ProjetoRide.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import com.UFF.ProjetoRide.models.Grupo;
import com.UFF.ProjetoRide.models.JPAUtil;

public class GrupoDAO {
    
    private EntityManager em;
    
    public GrupoDAO() {
        em = JPAUtil.getEM();
    }
    
    public void criarGrupo(Grupo grupo) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(grupo);
        et.commit();
    }
    
    public Grupo visualizarGrupo(int idGrupo) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        Grupo grupo = em.find(Grupo.class, idGrupo);
        et.commit();
        return grupo;
    }
    
    public void atualizarGrupo(Grupo grupo) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.merge(grupo);
        et.commit();
    }
    
    public void finalizarGrupo(int idGrupo) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        Grupo grupo = em.find(Grupo.class, idGrupo);
        em.remove(grupo);
        et.commit();
    }
    
    public List<Grupo> buscarGrupoPorPerfil(int idPerfil) {
        String jpqlQuery = "SELECT g FROM Grupo g WHERE perfil1_idperfil = :id OR perfil2_idperfil = :id OR perfil3_idperfil = :id OR perfil4_idperfil = :id OR perfil5_idperfil = :id";
        Query query = em.createQuery(jpqlQuery);
        query.setParameter("id", idPerfil);
        List<Grupo> grupos = query.getResultList();
        return grupos;
    }
    
    public List<Grupo> listarGrupos() {
        String jpqlQuery = "SELECT g FROM Grupo g ORDER BY g.idgrupo";
        Query query = em.createQuery(jpqlQuery);
        List<Grupo> grupos = query.getResultList();
        return grupos;
    }
    
    public void compartilharGrupo() {
        // Implementação do método
    }
    
    public void pedirSocorro() {
        // Implementação do método
    }
    
    public void compartilharLocalizacao() {
        // Implementação do método
    }
    
    public void close() {
        em.close();
    }
}
