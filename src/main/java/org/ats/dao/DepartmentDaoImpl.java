package org.ats.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.ats.entities.Department;
import org.ats.entities.User;
import org.ats.utils.DbContext;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
        Department dept = entityManager.find(Department.class, id);

        return dept == null ? Optional.empty() : Optional.of(dept);
    }


    @Override
    public Optional<Department> getByIdWithHibernate(Long id) {

        Session session = null;
        try {
            session = entityManager.unwrap(Session.class);
            Department dept = session.find(Department.class, id);

            Set<User> users = dept.getUsers();

            System.out.println(users);

            return dept == null ? Optional.empty() : Optional.of(dept);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (session != null) {
                session.close();
            }
        }


    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Department> findAll() {
        return List.of();
    }
}
