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
public class DataStoreInfoRepository {
    private final Logger LOG = LoggerFactory.getLogger(DataStoreInfoRepository.class);

    private Cluster cluster;
    private Session session;
    private CassandraOperations ops;
    private final InetAddress ADDRESS = InetAddress.getLocalHost();
    private final String TABLE_NAME = "datastoreinfo";

    private void setupSession() {
        final String KEYSPACE = "data_store_service";
        this.session = cluster.connect(KEYSPACE);
    }

    private void insertDataStoreInfo(final boolean isStored) {
        ops.insert(new DataStoreInfo(UUIDs.timeBased(), isStored));
    }

    private void updateDataStoreInfo(final UUID uuid, final boolean isStored) {
        ops.update(new DataStoreInfo(uuid, isStored));
    }

    private void writeDataStoreInfo(final UUID uuid, final boolean isStored) {
        final Optional<DataStoreInfo> optDataStoreInfo = findDataStoreInfoByUUID(uuid);
        if(optDataStoreInfo.isPresent()) {
            updateDataStoreInfo(uuid, isStored);
        } else {
            insertDataStoreInfo(isStored);
        }
    }


    public void setStoring(final UUID uuid) {
        writeDataStoreInfo(uuid, true);
    }

    public void setUnstoring(final UUID uuid) {
        writeDataStoreInfo(uuid, false);
    }

    private void setCluster() throws UnknownHostException {
        this.cluster = Cluster.builder().addContactPoints(ADDRESS).build();
    }

    public List<DataStoreInfo> loadAll() {
        final Select s = QueryBuilder.select().all().from(TABLE_NAME);
        return ops.select(s, DataStoreInfo.class);
    }

    public DataStoreInfoRepository() throws UnknownHostException{
        setCluster();
        setupSession();
        ops = new CassandraTemplate(session);
    }


    private Optional<DataStoreInfo> findDataStoreInfoByUUID(UUID uuid) {
        final Select s = QueryBuilder.select().from(TABLE_NAME).where((QueryBuilder.eq("id", uuid))).limit(1);
        final List<DataStoreInfo> select = ops.select(s, DataStoreInfo.class);
        return Optional.ofNullable(select.size() > 0?  select.get(0) : null );
    }



}
