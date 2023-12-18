package com.example.template_servlet2;

import com.example.template_servlet2.entities.Employee;
import com.example.template_servlet2.repositories.EmployeeRepository;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;

public class AssignPasswordCustomer {
    public static void main(String[] args) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2d, 16, 16);
        EmployeeRepository employeeRepository = new EmployeeRepository();
        EntityManager em = employeeRepository.getEntityManager();
        em.getTransaction().begin();
        char[] password;

        for (Employee employee : employeeRepository.findAll()) {
            password = employee.getId().toString().toCharArray();
            employee.setPassword(argon2.hash(2,16,1,password));
        }

        em.getTransaction().commit();
    }
}
