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
        Session session = sessionFactory.openSession(); // Open connection
        session.persist(dept);
        return dept;

    }

    @Override
    public Department update(Department dept) {
        return null;
    }

    @Override
    public Optional<Department> getById(Long id) {
        Session session = sessionFactory.openSession();
        Department dept = session.find(Department.class, id);

        return dept == null ? Optional.empty() : Optional.of(dept);
    }


    @Override
    public Optional<Department> getByIdWithHibernate(Long id) {

        Session session = null;
        session = sessionFactory.openSession();
        Department dept = session.find(Department.class, id);

        Set<User> users = dept.getUsers();

        System.out.println(users);

        return dept == null ? Optional.empty() : Optional.of(dept);


    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Department> findAll() {
        return List.of();
    }
}
