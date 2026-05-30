package org.ats.dao;

import org.ats.entities.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentDao {
    Department create(Department dept);

    Department update(Department dept);

    Optional<Department> getByIdWithHibernate(Long id);

    Optional<Department> getById(Long id);

    void delete(Long id);

    List<Department> findAll();
}
