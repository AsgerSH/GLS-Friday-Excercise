package app.populators;

import app.daos.ShipmentDAO;
import app.entities.*;

import java.time.LocalDateTime;

public class ShipmentPopulator {

    public static Shipment[] populate(ShipmentDAO shipmentDAO, Location source, Location destination) {
        Parcel parcel = Parcel.builder()
                .trackingNumber("TEST123")
                .senderName("Alice")
                .receiverName("Bob")
                .deliveryStatus(DeliveryStatus.IN_TRANSIT)
                .build();

        Shipment shipment = Shipment.builder()
                .parcel(parcel)
                .source(source)
                .destination(destination)
                .shipmentDate(LocalDateTime.now())
                .build();

        // Hold relationer konsistente
        parcel.getShipmentSet().add(shipment);
        source.getSourceSet().add(shipment);
        destination.getDestinationSet().add(shipment);

        // Gem via DAO
        shipmentDAO.create(shipment);

        return new Shipment[]{shipment};
    }
}
