package org.thm.datacollector.infrastructure.persistence;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by patrick.welter on 2/28/17.
 * (c) Janitza Electronics
 */
public abstract class AbstractRepository<E> {
    protected final Cluster cluster;
    protected final Session session;
    protected final CassandraOperations ops;
    protected InetAddress address;
    protected final String keyspaceName;
    protected final String tableName;
    private final Class<E> clazz;


    public AbstractRepository(final String keyspaceName, final String tableName, final Class<E> clazz)  {
        try {
            this.address = InetAddress.getLocalHost();
        }catch(final UnknownHostException e) {
            address = InetAddress.getLoopbackAddress();
            e.printStackTrace();
        }
        this.clazz = clazz;
        this.keyspaceName = keyspaceName;
        this.tableName = tableName;
        this.cluster = Cluster.builder().addContactPoints(address).build();
        this.session = cluster.connect(this.keyspaceName);
        ops = new CassandraTemplate(session);
    }

    public List<E> loadAll() {
        final Select s = QueryBuilder.select().all().from(tableName);
        return ops.select(s, clazz);
    }
    public void removeById(final UUID uuid) {
        ops.deleteById(clazz, uuid);
    }

    public Optional<E> findByUUID(UUID uuid) {
        final Select s = QueryBuilder.select().from(tableName).where((QueryBuilder.eq("id", uuid))).limit(1);
        final List<E> select = ops.select(s, clazz);
        return Optional.ofNullable(select.size() > 0?  select.get(0) : null );
    }

    public void close() {
        this.cluster.close();
    }
}
