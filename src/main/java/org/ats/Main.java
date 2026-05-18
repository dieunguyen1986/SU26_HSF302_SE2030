package org.ats;

import org.ats.dao.DepartmentDao;
import org.ats.dao.DepartmentDaoImpl;
import org.ats.entities.Department;

import java.util.HashSet;
import java.util.Set;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        DepartmentDao departmentDao = new DepartmentDaoImpl();

        Department department1 = new  Department();
        department1.setDepartmentName("SE");
        department1.setDescription("Software Engineering");
        System.out.println("department 1: " + department1.hashCode());

//        System.out.println(departmentDao.create(department1));

        Department department2 = new  Department();
        department2.setDepartmentName("SE");
        department2.setDescription("Software Engineering");
        System.out.println("department 2: " + department2.hashCode());

        Set<Department> setDept = new HashSet<>();
        setDept.add(department1);// hashCode = 123456
        setDept.add(department2); // hashCode = 123456, equals(): false

        System.out.println("equals: " + department1.equals(department2));

        System.out.println(setDept.size());
    }
}