package org.thm.datacollector.infrastructure.persistence;

import com.datastax.driver.core.utils.UUIDs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thm.datacollector.domain.model.DataLocation;

import java.net.UnknownHostException;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by patrick.welter on 2/16/17.
 * (c) Janitza Electronics
 */
public class DataLocationRepository extends AbstractRepository<DataLocation>{
    private final Logger LOG = LoggerFactory.getLogger(DataLocationRepository.class);

    public void insertDataLocation(final String url) {
        ops.insert(new DataLocation(UUIDs.timeBased(), url));
    }

    public DataLocationRepository()  {
       super("data_location_service", "datalocation", DataLocation.class);
    }

    public DataLocation getDataLocationById(final UUID uuid) {
        final Optional<DataLocation> location = findByUUID(uuid);
        return location.orElseGet(null);
    }

}
