package org.ats.dao;

import org.ats.entities.Department;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
    }

    @Test
    void delete() {
    }

    @Test
    void findAll() {
    }
}