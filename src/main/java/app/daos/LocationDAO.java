package app.daos;

import app.entities.Location;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class LocationDAO implements IDAO<Location, Integer> {
    private final EntityManagerFactory emf;

    public LocationDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }


    @Override
    public Location create(Location location) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(location);
            em.getTransaction().commit();

            return location;
        }
    }

    @Override
    public List<Location> getAll() {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Location> query = em.createQuery("SELECT l FROM Location l", Location.class);
            return query.getResultList();
        }
    }

    @Override
    public Location getById(Integer id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Location.class, id);
        }
    }

    @Override
    public Location update(Location location) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(location);
            em.getTransaction().commit();
            return location;
        }
    }

    @Override
    public boolean delete(Integer id) {
        try (EntityManager em = emf.createEntityManager()) {

            Location student = em.find(Location.class, id);

            if (student != null) {
                em.getTransaction().begin();
                em.remove(student);
                em.getTransaction().commit();
                return true;
            } else {
                return false;
            }
        } catch (PersistenceException e) {
            return false;
        }
    }
}
