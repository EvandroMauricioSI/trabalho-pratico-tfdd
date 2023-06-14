
package com.UFF.ProjetoRide.models;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Matheus
 */
public class JPAUtil {
    
    private static EntityManagerFactory emf;

    public static EntityManager getEM() {
    
        if(emf == null) {
            emf = Persistence.createEntityManagerFactory("ProjetoRidePU");
        }
        
        return emf.createEntityManager();    
    }
    
    public static void fechaEmf(){
        emf.close();
    }
    
}
