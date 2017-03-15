package org.thm.datacollector.infrastructure.persistence;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by patrick.welter on 3/14/17.
 * (c) Janitza Electronics
 */
public class PersistenceTestSuite {
    private final Cluster cluster;
    private Session session;
    private final String tableName;
    protected InetAddress address;
    private final String keyspaceName;

    public PersistenceTestSuite(final String keyspaceName, final String tableName) {
        try {
            this.address = InetAddress.getLocalHost();
        }catch(final UnknownHostException e) {
            address = InetAddress.getLoopbackAddress();
            e.printStackTrace();
        }
        this.tableName = tableName;
        this.keyspaceName = keyspaceName;
        this.cluster = Cluster.builder().addContactPoints(address).build();

        this.session = cluster.connect();

    }

    private void setupKeySpace() {
        this.session.execute("CREATE KEYSPACE "+keyspaceName+" WITH replication "
                + "= {'class':'SimpleStrategy', 'replication_factor':3};");
    }

    public void setupTestDB(final String tableAttributes) {
        setupKeySpace();
        this.session.execute("CREATE TABLE "+keyspaceName+"."+tableName+" (" + tableAttributes + ");");
    }

    public void cleanTestDB() {
        this.session =  cluster.connect(keyspaceName);
        this.session.execute("DROP TABLE "+keyspaceName+"."+tableName+" ;");
        this.session.execute("DROP KEYSPACE "+keyspaceName+";");
        this.cluster.close();
    }

}