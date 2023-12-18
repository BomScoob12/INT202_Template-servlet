package com.example.template_servlet2.repositories;

import com.example.template_servlet2.entities.Employee;
import com.example.template_servlet2.entities.Office;
import com.example.template_servlet2.utils.EntityManagerBuilder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

import java.util.List;

public class OfficeRepository {
    private EntityManager em;

    private EntityManager getEntityManager() {
        if (em == null || !em.isOpen()) {
            em = EntityManagerBuilder.getEntityManager();
        }
        return em;
    }

    public List<Office> findAll() {
        return getEntityManager().createNamedQuery("OFFICE.FIND_ALL", Office.class).getResultList();
    }

    public Office findById(String id) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNamedQuery("OFFICE.FIND_OFFICE_BY_ID", Office.class);
            query.setParameter("id", id);
            return (Office) query.getSingleResult();
        } catch (NoResultException e) {
            e.getStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public List<Employee> getEmployeeList(String officeId) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNamedQuery("OFFICE.FIND_OFFICE_EMPLOYEE", Office.class);
            query.setParameter("id", officeId);
            return query.getResultList();
        } catch (NoResultException e) {
            e.getStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public boolean remove(String officeId) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Office office = em.find(Office.class, officeId);
            if (office != null) {
                em.remove(office);
                em.getTransaction().commit();
                return true;
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace(); // Log the exception or handle it accordingly
        } finally {
            em.close();
        }
        return false;
    }

    public boolean update(Office updatedOffice) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Office existingOffice = em.find(Office.class, updatedOffice.getId());
            if (existingOffice != null) {
                // Update the existing office with the values from the updated office
                existingOffice.setCity(updatedOffice.getCity());
                existingOffice.setPhone(updatedOffice.getPhone());
                existingOffice.setAddressLine1(updatedOffice.getAddressLine1());
                existingOffice.setAddressLine2(updatedOffice.getAddressLine2());
                existingOffice.setState(updatedOffice.getState());
                existingOffice.setCountry(updatedOffice.getCountry());
                existingOffice.setPostalCode(updatedOffice.getPostalCode());
                existingOffice.setTerritory(updatedOffice.getTerritory());

                em.getTransaction().commit();
                return true;
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace(); // Log the exception or handle it accordingly
        } finally {
            em.close();
        }
        return false;
    }
}
