package org.ats;

import org.ats.dao.DepartmentDao;
import org.ats.dao.DepartmentDaoImpl;
import org.ats.entities.Department;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        DepartmentDao departmentDao = new DepartmentDaoImpl();

        Department department = new  Department();
        department.setDepartmentName("SE");
        department.setDescription("Software Engineering");

        System.out.println(departmentDao.create(department));
    }
}