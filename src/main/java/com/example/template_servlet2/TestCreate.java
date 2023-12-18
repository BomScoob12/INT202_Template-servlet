package com.example.template_servlet2;

import com.example.template_servlet2.entities.Employee;
import com.example.template_servlet2.repositories.EmployeeRepository;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class TestCreate {
    public static void main(String[] args) {
        testCreateEmployee();
//        testCreateProductOffice();
    }

    private static void testCreateEmployee(){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2d, 16, 16);
        EmployeeRepository er = new EmployeeRepository();
        Employee employee = new Employee();
        Integer id = 999;
        char [] idChar = id.toString().toCharArray();
        employee.setId(id);
        employee.setFirstName("Sarawit");
        employee.setLastName("Kraukham");
        employee.setEmail("saraaaa@gmail.com");
        employee.setExtension("x5800");
        employee.setPassword(argon2.hash(2,16,1, idChar));
        er.create(employee);
        System.out.println(er.findById(999));
    }
}
