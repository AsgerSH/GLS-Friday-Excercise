package app.daos;

import app.config.HibernateConfig;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipmentDAOTest {
    private static ShipmentDAO shipmentDAO;
    private static EntityManagerFactory emf;

    @BeforeAll
    static void setup() {
        HibernateConfig.setTest(true); // enable test DB mode
        emf = HibernateConfig.getEntityManagerFactoryForTest();
        shipmentDAO = new ShipmentDAO(emf);
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