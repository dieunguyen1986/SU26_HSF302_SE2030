package org.ats.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.ats.entities.Department;
import org.ats.utils.DbContext;

import java.util.List;
import java.util.Optional;

public class DepartmentDaoImpl implements DepartmentDao {
    private EntityManager entityManager;

    public DepartmentDaoImpl() {
        entityManager = DbContext.getEntityManager();
    }

    @Override
    public Department create(Department dept) {
        // How to connect
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();

            transaction.begin();

            entityManager.persist(dept);

            transaction.commit();

            return dept;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public Department update(Department dept) {
        return null;
    }

    @Override
    public Optional<Department> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Department> findAll() {
        return List.of();
    }
}
