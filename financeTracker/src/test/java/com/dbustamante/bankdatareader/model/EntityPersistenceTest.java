/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbustamante.bankdatareader.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author daniel.bustamante
 */
public class EntityPersistenceTest {
    
    
    
    @Test
    public void shouldStoreUser() {
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("test");
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        User user = new User();
        user.setName("Daniel");
        em.persist(user);
        em.getTransaction().commit();
        User dbUser = em.find(User.class, user.getId());
        
        assertThat(dbUser.getName()).isEqualTo(user.getName());
    }
}
