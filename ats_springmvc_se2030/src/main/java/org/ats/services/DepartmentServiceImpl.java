package org.ats.services;

import lombok.RequiredArgsConstructor;
import org.ats.dao.DepartmentDao;
import org.ats.entities.Department;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService{
    private final DepartmentDao departmentDao;

    @Override
    public List<Department> findAll() {
        return departmentDao.findAll();
    }
}
