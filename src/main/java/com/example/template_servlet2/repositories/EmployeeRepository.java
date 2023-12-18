package com.example.template_servlet2.repositories;

import com.example.template_servlet2.entities.Employee;
import com.example.template_servlet2.entities.Office;
import com.example.template_servlet2.utils.EntityManagerBuilder;
import jakarta.persistence.*;
import org.hibernate.QueryException;

import java.util.List;

public class EmployeeRepository {

    private EntityManager em;

    public EntityManager getEntityManager() {
        if (em == null || !em.isOpen()) {
            em = EntityManagerBuilder.getEntityManager();
        }
        return em;
    }

    public List<Employee> findAll() {
        return getEntityManager().createNamedQuery("EMP.FIND_ALL", Employee.class).getResultList();
    }

    public Employee findById(Integer employeeId) {
        try {
            Query query = getEntityManager().createNamedQuery("EMP.FIND_BY_ID", Employee.class);
            query.setParameter("id", employeeId);
            return (Employee) query.getSingleResult();
        } catch (NoResultException e) {
            e.getStackTrace();
            return null;
        } finally {
            getEntityManager().close();
        }
    }

    public Employee findByEmail(String email) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNamedQuery("EMP.FIND_BY_EMAIL", Employee.class);
            query.setParameter("email", email);
            return (Employee) query.getSingleResult();
        } catch (QueryException e){
            e.getStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public void create(Employee employee) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void update(Employee employee) {
        Employee existingEmployee = this.findById(employee.getId());
        if (existingEmployee == null) {
            throw new EntityNotFoundException("Employee with ID " + employee.getId() + " can not found!");
        }
        // Update the fields of the existing employee
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setExtension(employee.getExtension());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setPassword(employee.getPassword());

        // Handle the relationship with the Office entity
        Office updatedOffice = employee.getOffice();
        if (updatedOffice != null) {
            existingEmployee.setOffice(updatedOffice);
        }

        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(existingEmployee);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void delete(Employee employee) {
        if (employee.getId() == null) {
            throw new IllegalArgumentException("Deleted Employee must not have null id");
        }

        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(employee);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
        }
    }
}
