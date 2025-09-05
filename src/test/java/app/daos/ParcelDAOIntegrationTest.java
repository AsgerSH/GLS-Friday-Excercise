package app.daos;

import app.config.HibernateConfig;
import app.entities.DeliveryStatus;
import app.entities.Parcel;
import org.junit.jupiter.api.*;

import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ParcelDAOIntegrationTest {

    private static ParcelDAO parcelDAO;
    private static EntityManagerFactory emf;

    @BeforeAll
   static void setup() {
        HibernateConfig.setTest(true); // enable test DB mode
        emf = HibernateConfig.getEntityManagerFactoryForTest();
        parcelDAO = new ParcelDAO(emf);
    }


//    @Test
//    void testFindByStatus() {
//        Parcel parcel = Parcel.builder()
//                .trackingNumber("XYZ999")
//                .senderName("Chris")
//                .receiverName("David")
//                .deliveryStatus(DeliveryStatus.IN_TRANSIT)
//                .updated(LocalDateTime.now())
//                .build();
//
//        parcelDAO.createParcel(parcel);
//
//        List<Parcel> results = parcelDAO.findByStatus(DeliveryStatus.IN_TRANSIT);
//        assertFalse(results.isEmpty());
//        assertEquals(DeliveryStatus.IN_TRANSIT, results.get(0).getDeliveryStatus());
//    }
//
//    @Test
//    void testUpdateDeliveryStatus() {
//        Parcel parcel = Parcel.builder()
//                .trackingNumber("UPD123")
//                .senderName("Eve")
//                .receiverName("Frank")
//                .deliveryStatus(DeliveryStatus.PENDING)
//                .updated(LocalDateTime.now())
//                .build();
//
//        parcelDAO.createParcel(parcel);
//        parcelDAO.updateDeliveryStatus((long) parcel.getId(), DeliveryStatus.DELIVERED);
//
//        Optional<Parcel> updated = parcelDAO.findById((long) parcel.getId());
//        assertTrue(updated.isPresent());
//        assertEquals(DeliveryStatus.DELIVERED, updated.get().getDeliveryStatus());
//    }
//
//    @Test
//    void testDeleteParcelById() {
//        Parcel parcel = Parcel.builder()
//                .trackingNumber("DEL123")
//                .senderName("Grace")
//                .receiverName("Heidi")
//                .deliveryStatus(DeliveryStatus.PENDING)
//                .updated(LocalDateTime.now())
//                .build();
//
//        parcelDAO.createParcel(parcel);
//
//        boolean deleted = parcelDAO.deleteParcelById((long) parcel.getId());
//        assertTrue(deleted);
//
//        Optional<Parcel> found = parcelDAO.findById((long) parcel.getId());
//        assertTrue(found.isEmpty());
//    }
}
