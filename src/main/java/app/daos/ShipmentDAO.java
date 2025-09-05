package app.daos;

import app.entities.Shipment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ShipmentDAO implements IDAO<Shipment, Integer> {

    private final EntityManagerFactory emf;

    public ShipmentDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Shipment create(Shipment shipment) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(shipment);
            em.getTransaction().commit();
            return shipment;
        }
    }

    @Override
    public List<Shipment> getAll() {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Shipment> query = em.createQuery("SELECT s FROM Shipment s", Shipment.class);
            return query.getResultList();
        }
    }

    @Override
    public Shipment getById(Integer id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Shipment.class, id);
        }
    }

    @Override
    public Shipment update(Shipment shipment) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(shipment);
            em.getTransaction().commit();
            return shipment;
        }
    }

    @Override
    public boolean delete(Integer id) {
        try (EntityManager em = emf.createEntityManager()) {
            Shipment shipment = em.find(Shipment.class, id);

            if (shipment != null) {
                em.getTransaction().begin();
                em.remove(shipment);
                em.getTransaction().commit();
                return true;
            } else
                return false;
        } catch (PersistenceException e) {
            return false;
        }
    }
}
