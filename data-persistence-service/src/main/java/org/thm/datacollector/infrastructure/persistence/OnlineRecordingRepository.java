package org.thm.datacollector.infrastructure.persistence;


import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.datastax.driver.core.utils.UUIDs;
import org.thm.datacollector.infrastructure.persistence.model.OnlineRecordingDBEntity;
import org.thm.datacollector.model.OnlineRecording;
import org.thm.datacollector.infrastructure.persistence.AbstractRepository;

import java.util.List;
import java.util.UUID;

/**
 * Created by patrick.welter on 3/6/17.
 * (c) Janitza Electronics
 */
public class OnlineRecordingRepository extends AbstractRepository<OnlineRecordingDBEntity> {

    public OnlineRecordingRepository() {
        super("data_persistence_service", "onlinerecording", OnlineRecordingDBEntity.class);
    }

    public void insertDataStoreInfo(final OnlineRecording rec) {
        ops.insert(new OnlineRecordingDBEntity(UUIDs.timeBased(), rec));
    }

    public List<OnlineRecordingDBEntity> loadLastN(final int n) {
        final Select s = QueryBuilder.select().from(tableName).limit(n);
        return ops.select(s, OnlineRecordingDBEntity.class);
    }


}
