package org.ats.dao;

import org.ats.entities.Department;
import org.ats.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;

class DepartmentDaoImplTest {
    private static DepartmentDao departmentDao;

    @BeforeAll
    public static void setup() {
        departmentDao = new DepartmentDaoImpl();
    }

    @Test
    void create1() {
        // Input
        Department dept = Department.builder()
                .departmentName("FIN 3")
                .description("Finance 3")
                .build();

        // Actual - kết quả thực tế
        Department actualResult = departmentDao.create(dept);
        System.out.println(actualResult);
        System.out.println(dept);
        System.out.println(actualResult == dept);
        // Expected - kết quả mong đợi
        Assertions.assertEquals(dept, actualResult);

    }

    @Test
    void create2() {
    }

    @Test
    void create3() {
    }

    @Test
    void update() {
    }

    @Test
    void getById() {
        // Input
        Long departmentId = 1L;
        // Actual result
        Optional<Department> optional = departmentDao.getByIdWithHibernate(departmentId);

        Department dept = optional.isEmpty() ? null : optional.get();

        System.out.println(dept.toString());

        // Proxy Objects
        Set<User> users = dept.getUsers();

        // Execute Query: Select from users
        System.out.println(users);

        Assertions.assertFalse(optional.isEmpty());
    }


    @Test
    void getById2() {
        // Input
        Long departmentId = 10L;
        // Actual result
        Optional<Department> optional = departmentDao.getByIdWithHibernate(departmentId);

        Assertions.assertTrue(optional.isEmpty());
    }

    @Test
    void delete() {
    }

    @Test
    void findAll() {
    }
}