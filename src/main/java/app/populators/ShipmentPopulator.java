package app.populators;

import app.daos.LocationDAO;
import app.daos.ShipmentDAO;
import app.entities.*;

import java.time.LocalDateTime;

public class ShipmentPopulator {

    public static Shipment[] populate(ShipmentDAO shipmentDAO, LocationDAO locationDAO) {
        // Brug LocationPopulator til at oprette lokationer
        Location[] locations = LocationPopulator.populate(locationDAO);
        Location copenhagen = locations[0];
        Location aarhus = locations[1];

        // Bygger parcel
        Parcel parcel = Parcel.builder()
                .trackingNumber("TEST1234")
                .senderName("Alice")
                .receiverName("Bob")
                .deliveryStatus(DeliveryStatus.IN_TRANSIT)
                .build();

        // Bygger shipment
        Shipment shipment = Shipment.builder()
                .parcel(parcel)
                .source(copenhagen)
                .destination(aarhus)
                .shipmentDate(LocalDateTime.now())
                .build();

        parcel.getShipmentSet().add(shipment);
        copenhagen.getSourceSet().add(shipment);
        aarhus.getDestinationSet().add(shipment);

        shipmentDAO.create(shipment);

        return new Shipment[]{shipment};
    }
}
