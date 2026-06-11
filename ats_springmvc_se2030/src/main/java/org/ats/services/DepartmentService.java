package org.ats.services;

import org.ats.entities.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> findAll();
}
