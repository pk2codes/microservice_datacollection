package org.thm.datacollector.infrastructure.persistence;

import com.datastax.driver.core.utils.UUIDs;
import org.apache.log4j.Logger;
import org.thm.datacollector.domain.model.DataLocation;

import java.net.UnknownHostException;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by patrick.welter on 2/16/17.
 * (c) Janitza Electronics
 */
public class DataLocationRepository extends AbstractRepository<DataLocation>{
    private final Logger log = Logger.getLogger(DataLocationRepository.class);

    public void insertDataLocation(final String url) {
        insertDataLocationWithId(UUIDs.timeBased(), url);
    }

    public DataLocationRepository()  {
       super("data_location_service", "datalocation", DataLocation.class);
    }

    public DataLocationRepository(final String keySpaceName, final String tableName)  {
        super(keySpaceName, tableName, DataLocation.class);
    }

    public DataLocation getDataLocationById(final UUID uuid) {
        final Optional<DataLocation> location = findByUUID(uuid);
        return location.orElseGet(null);
    }

    public void insertDataLocationWithId(final UUID id, final String url) {
        log.info("insert: " + url+ " id: "+ id.toString());
        ops.insert(new DataLocation(id, url));

    }

}
