package app.daos;

import app.entities.DeliveryStatus;
import app.entities.Parcel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Optional;

public class ParcelDAO {
    private final EntityManagerFactory emf;


    public ParcelDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }


    // Create  (persist parcel)
    public void createParcel(Parcel p) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
        }
    }

    // Read (retrieve parcel by its tracking number
    public Optional<Parcel> findByTrackingNumber(String trackingNumber) {
        try (EntityManager em = emf.createEntityManager()) {
            Parcel foundParcel = em.find(Parcel.class, trackingNumber);
            return Optional.ofNullable(foundParcel);
        }
    }

    // Read (retrieve parcel by its tracking number
    public Optional<Parcel> findById(Long id) {
        try (EntityManager em = emf.createEntityManager()) {
            Parcel foundParcel = em.find(Parcel.class, id);
            return Optional.ofNullable(foundParcel);
        }
    }

    // Read (find by status)
    public List<Parcel> findByStatus(DeliveryStatus status) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery(
                            "SELECT p FROM Parcel p WHERE p.deliveryStatus = :status", Parcel.class)
                    .setParameter("status", status)
                    .getResultList();
        }
    }

    // Read (retrieve all parcels in the system)
    public List<Parcel> findAllParcel() {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("SELECT p FROM Parcel p", Parcel.class)
                    .getResultList();
        }
    }

    // Update (update delivery status of parcel)
    public void updateDeliveryStatus(Long id, DeliveryStatus status) {
        try (EntityManager em = emf.createEntityManager()) {
            Query query = em.createQuery("UPDATE Parcel p SET p.deliveryStatus = :status, p.updated = CURRENT_TIMESTAMP WHERE p.id = :id"
            );
            query.setParameter("status", status);
            query.setParameter("id", id);
            query.executeUpdate();
        }
    }


    public boolean deleteParcelById(Long id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Optional<Parcel> parcel = findById(id);
            if (parcel != null) {
                em.remove(parcel);
                em.getTransaction().commit();
                return true;
            } else {
                em.getTransaction().rollback();
                return false;
            }
        }
    }
}