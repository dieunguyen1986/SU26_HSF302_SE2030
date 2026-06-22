package org.ats.dao;

import lombok.RequiredArgsConstructor;
import org.ats.entities.Department;
import org.ats.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository // Spring Bean
@RequiredArgsConstructor
public class DepartmentDaoImpl implements DepartmentDao {

    // Inject: SessionFactory
    private final SessionFactory sessionFactory;

    @Override
    @Transactional
    public Department create(Department dept) {
        // How to connect
        Session session = sessionFactory.getCurrentSession(); // Open connection
        session.persist(dept);
        return dept;

    }

    @Override
    public Department update(Department dept) {
        Session session = sessionFactory.getCurrentSession();
        Department existedDepartment = session.get(Department.class, dept.getId());

        if (existedDepartment != null) {
            session.update(dept);
        }
        return dept;
    }

    @Override
    public Optional<Department> getById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Department dept = session.find(Department.class, id);

        return dept == null ? Optional.empty() : Optional.of(dept);
    }

    @Override
    public Optional<Department> getByIdWithHibernate(Long id) {

        Session session = null;
        session = sessionFactory.getCurrentSession();
        Department dept = session.find(Department.class, id);

        if (dept != null) {
            Set<User> users = dept.getUsers();
            System.out.println(users);
        }


        return dept == null ? Optional.empty() : Optional.of(dept);

    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();

        Department dept = session.get(Department.class, id);
        if (dept != null) {
            session.remove(dept);
        }
    }

    @Override
    public List<Department> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Department", Department.class).list();
    }
}
