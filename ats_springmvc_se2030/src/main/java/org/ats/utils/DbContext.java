package org.ats.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class DbContext {
    public static EntityManager getEntityManager() {
        return Persistence.createEntityManagerFactory("ats-persistence-unit").createEntityManager();
    }
}
