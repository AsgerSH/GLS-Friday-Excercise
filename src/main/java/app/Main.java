package app;

import app.config.HibernateConfig;
import app.entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // Create locations
            Location copenhagen = Location.builder()
                    .Latitude(55.6761)
                    .Longitude(12.5683)
                    .Address("Copenhagen, Denmark")
                    .build();

            Location aarhus = Location.builder()
                    .Latitude(56.1629)
                    .Longitude(10.2039)
                    .Address("Aarhus, Denmark")
                    .build();

            // Create a parcel
            Parcel parcel = Parcel.builder()
                    .trackingNumber("TRACK123456")
                    .senderName("Alice")
                    .receiverName("Bob")
                    .deliveryStatus(DeliveryStatus.IN_TRANSIT)
                    .build();

            // Create a shipment (linking parcel, source, and destination)
            Shipment shipment = Shipment.builder()
                    .parcel(parcel)
                    .source(copenhagen)
                    .destination(aarhus)
                    .shipmentDate(LocalDateTime.now())
                    .build();

            // Keep relationships consistent
            parcel.getShipmentSet().add(shipment);
            copenhagen.getSourceSet().add(shipment);
            aarhus.getDestinationSet().add(shipment);

            // Persist everything
            em.persist(copenhagen);
            em.persist(aarhus);
            em.persist(parcel);
            em.persist(shipment);

            em.getTransaction().commit();
            System.out.println("✅ Sample data inserted!");

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
