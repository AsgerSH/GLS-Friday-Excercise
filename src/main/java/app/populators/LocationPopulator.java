package app.populators;

import app.daos.LocationDAO;
import app.entities.Location;

public class LocationPopulator {

    public static Location[] populate(LocationDAO locationDAO) {
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

        locationDAO.create(copenhagen);
        locationDAO.create(aarhus);

        return new Location[]{copenhagen, aarhus};
    }
}
