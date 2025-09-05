package app.entities;

import app.config.HibernateConfig;
import app.daos.LocationDAO;
import app.daos.ShipmentDAO;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {
    private static LocationDAO locationDAO;
    private static EntityManagerFactory emf;

    @BeforeAll
    static void setup() {
        HibernateConfig.setTest(true); // enable test DB mode
        emf = HibernateConfig.getEntityManagerFactoryForTest();
        locationDAO = new LocationDAO(emf);
    }


    @Test
    void getId() {
    }

    @Test
    void getLatitude() {
    }

    @Test
    void getLongitude() {
    }

    @Test
    void getAddress() {
    }

    @Test
    void getSourceSet() {
    }

    @Test
    void getDestinationSet() {
    }

    @Test
    void testToString() {
    }

    @Test
    void builder() {
    }
}