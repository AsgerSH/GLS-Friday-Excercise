package app;

import app.config.HibernateConfig;
import app.daos.ParcelDAO;
import app.entities.DeliveryStatus;
import app.entities.Parcel;

import jakarta.persistence.EntityManagerFactory;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        // 1. Get EntityManagerFactory for dev database
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        ParcelDAO parcelDAO = new ParcelDAO(emf);

        // 2. Create a new Parcel
        Parcel parcel = Parcel.builder()
                .trackingNumber("TRACK123")
                .senderName("Alice")
                .receiverName("Bob")
                .deliveryStatus(DeliveryStatus.PENDING)
                .updated(LocalDateTime.now())
                .build();

        parcelDAO.createParcel(parcel);
        System.out.println("Parcel created: " + parcel);

        // 3. Find by ID
        Optional<Parcel> foundParcel = parcelDAO.findById((long) parcel.getId());
        foundParcel.ifPresent(p -> System.out.println("Found parcel: " + p));

        // 4. Update delivery status
        parcelDAO.updateDeliveryStatus((long) parcel.getId(), DeliveryStatus.IN_TRANSIT);
        System.out.println("Parcel status updated to IN_TRANSIT");

        // 5. Find by status
        List<Parcel> inTransitParcels = parcelDAO.findByStatus(DeliveryStatus.IN_TRANSIT);
        System.out.println("Parcels with IN_TRANSIT status: " + inTransitParcels);

        // 6. Delete the parcel
        boolean deleted = parcelDAO.deleteParcelById((long) parcel.getId());
        System.out.println("Parcel deleted: " + deleted);

        // 7. Close the EntityManagerFactory
        emf.close();
    }
}
