package org.thm.datacollector.infrastructure.persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.thm.datacollector.domain.model.DataStoreResourceInfo;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by patrick.welter on 2/23/17.
 * (c) Janitza Electronics
 */
@Component
public class DataStoreResourceRepository extends AbstractRepository<DataStoreResourceInfo>{
    private final Logger LOG = LoggerFactory.getLogger(DataStoreResourceRepository.class);


    private void insertDataStoreInfo(final UUID uuid, final boolean isStored) {
        ops.insert(new DataStoreResourceInfo(uuid, isStored));
    }

    private void updateDataStoreInfo(final UUID uuid, final boolean isStored) {
        ops.update(new DataStoreResourceInfo(uuid, isStored));
    }

    private void writeDataStoreInfo(final UUID uuid, final boolean isStored) {
        final Optional<DataStoreResourceInfo> optDataStoreInfo = findByUUID(uuid);
        if(optDataStoreInfo.isPresent()) {
            updateDataStoreInfo(uuid, isStored);
        } else {
            insertDataStoreInfo(uuid, isStored);
        }
    }


    public void setStoring(final UUID uuid) {
        writeDataStoreInfo(uuid, true);
    }

    public void setUnstoring(final UUID uuid) {
        writeDataStoreInfo(uuid, false);
    }

    public DataStoreResourceRepository(){
        super("data_store_service", "datastoreresourceinfo", DataStoreResourceInfo.class);
    }


}
