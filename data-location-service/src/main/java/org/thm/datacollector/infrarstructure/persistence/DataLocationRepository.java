package org.thm.datacollector.infrarstructure.persistence;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.datastax.driver.core.utils.UUIDs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.thm.datacollector.domain.model.DataLocation;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.UUID;


/**
 * Created by patrick.welter on 2/16/17.
 * (c) Janitza Electronics
 */
public class DataLocationRepository {
    private final Logger LOG = LoggerFactory.getLogger(DataLocationRepository.class);

    private Cluster cluster;
    private Session session;
    private CassandraOperations ops;
    private final InetAddress ADDRESS = InetAddress.getLocalHost();
    private final String TABLE_NAME = "datalocation";

    private void setupSession() {
        final String KEYSPACE = "data_location_service";
        this.session = cluster.connect(KEYSPACE);
    }

    public void insertDataLocation(final String url) {
        ops.insert(new DataLocation(UUIDs.timeBased(), url));
    }

    private void setCluster() throws UnknownHostException {
        this.cluster = Cluster.builder().addContactPoints(ADDRESS).build();
    }

    public List<DataLocation> getAllDataLocations() {
        final Select s = QueryBuilder.select().all().from(TABLE_NAME);
        return ops.select(s, DataLocation.class);
    }

    public DataLocationRepository() throws UnknownHostException{
        setCluster();
        setupSession();
        ops = new CassandraTemplate(session);
    }


    public DataLocation getDataLocationById(UUID uuid) {
        final Select s = QueryBuilder.select().from(TABLE_NAME).where((QueryBuilder.eq("id", uuid))).limit(1);
        return ops.select(s, DataLocation.class).get(0);
    }
}
