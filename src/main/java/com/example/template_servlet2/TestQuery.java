package com.example.template_servlet2;

import com.example.template_servlet2.entities.Employee;
import com.example.template_servlet2.entities.Office;
import com.example.template_servlet2.repositories.EmployeeRepository;
import com.example.template_servlet2.repositories.OfficeRepository;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class TestQuery {
    public static void main(String[] args) {
        testQuery();
//        testRepository();
//        assignPassword();
    }

    public static void testRepository() {
        OfficeRepository officeRepository = new OfficeRepository();
        List<Employee> employees1 = officeRepository.getEmployeeList("1");
        employees1.forEach(System.out::println);
    }

    public static void testQuery() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        List<Office> offices = em.createNamedQuery("OFFICE.FIND_ALL", Office.class).getResultList();
        Office office1 = em.createQuery("select o from Office o where o.id = :id", Office.class)
                .setParameter("id", "1")
                .getSingleResult();
        System.out.println(office1);


    }

    public static void assignPassword() {
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
