package org.thm.datacollector.infrastructure.persistence;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.datastax.driver.core.utils.UUIDs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.thm.datacollector.domain.model.DataStoreInfo;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by patrick.welter on 2/23/17.
 * (c) Janitza Electronics
 */
public class DataStoreInfoRepository extends AbstractRepository<DataStoreInfo>{
    private final Logger LOG = LoggerFactory.getLogger(DataStoreInfoRepository.class);


    private void insertDataStoreInfo(final UUID uuid, final boolean isStored) {
        ops.insert(new DataStoreInfo(uuid, isStored));
    }

    private void updateDataStoreInfo(final UUID uuid, final boolean isStored) {
        ops.update(new DataStoreInfo(uuid, isStored));
    }

    private void writeDataStoreInfo(final UUID uuid, final boolean isStored) {
        final Optional<DataStoreInfo> optDataStoreInfo = findByUUID(uuid);
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

    public DataStoreInfoRepository(){
        super("data_store_service", "datastoreinfo", DataStoreInfo.class);
    }


}
