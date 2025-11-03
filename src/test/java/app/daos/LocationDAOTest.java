package app.daos;

import app.config.HibernateConfig;
import app.entities.Location;
import app.populators.LocationPopulator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LocationDAOTest {

    private static EntityManagerFactory emf;
    private static LocationDAO locationDAO;

    private Location copenhagen;
    private Location aarhus;

    @BeforeAll
    static void setUpAll() {
        HibernateConfig.setTest(true);
        emf = HibernateConfig.getEntityManagerFactoryForTest();
        locationDAO = new LocationDAO(emf);
    }

    // Sletter alt fra tabellerne, samt "populerer" locations, fordi det er dem vi tester
    @BeforeEach
    void setUpEach() {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Shipment").executeUpdate();
            em.createQuery("DELETE FROM Parcel").executeUpdate();
            em.createQuery("DELETE FROM Location").executeUpdate();
            em.getTransaction().commit();
        }

        Location[] locations = LocationPopulator.populate(locationDAO);
        copenhagen = locations[0];
        aarhus = locations[1];
    }


    @AfterAll
    static void tearDownAll() {
        if (emf != null) {
            emf.close();
        }
    }

    @Test
    void testGetAll() {
        List<Location> locations = locationDAO.getAll();
        assertEquals(2, locations.size()); // Copenhagen + Aarhus
    }

    @Test
    void testGetById() {
        Location found = locationDAO.getById(copenhagen.getId());
        assertEquals("Copenhagen, Denmark", found.getAddress());
    }

    @Test
    void testUpdate() {
        copenhagen.setAddress("New Copenhagen Address");
        locationDAO.update(copenhagen);
        Location updated = locationDAO.getById(copenhagen.getId());
        assertEquals("New Copenhagen Address", updated.getAddress());
    }

    @Test
    void testDelete() {
        boolean deleted = locationDAO.delete(aarhus.getId());
        assertTrue(deleted);
        assertNull(locationDAO.getById(aarhus.getId()));
    }
}
