package app.daos;

import app.config.HibernateConfig;
import app.entities.Location;
import app.entities.Shipment;
import app.populators.LocationPopulator;
import app.populators.ShipmentPopulator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipmentDAOTest {

    private static EntityManagerFactory emf;
    private static ShipmentDAO shipmentDAO;
    private static LocationDAO locationDAO;

    private Shipment shipment;


    @BeforeAll
    static void setup() {
        HibernateConfig.setTest(true);
        emf = HibernateConfig.getEntityManagerFactoryForTest();
        shipmentDAO = new ShipmentDAO(emf);
        locationDAO = new LocationDAO(emf);
    }

    @BeforeEach
    void setUpEach() {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Shipment").executeUpdate();
            em.createQuery("DELETE FROM Parcel").executeUpdate();
            em.createQuery("DELETE FROM Location").executeUpdate();
            em.getTransaction().commit();
        }

        // Brug både ShipmentPopulator og LocationPopulator indirekte
        Shipment[] shipments = ShipmentPopulator.populate(shipmentDAO, new LocationDAO(emf));
        shipment = shipments[0];
    }


    @AfterAll
    static void tearDownAll() {
        if (emf != null) {
            emf.close();
        }
    }

    @Test
    void create() {
    }

    @Test
    void getAll() {
    }

    @Test
    void getById() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}