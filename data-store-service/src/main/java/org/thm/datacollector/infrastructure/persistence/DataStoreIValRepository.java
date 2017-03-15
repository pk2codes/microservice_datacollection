package org.thm.datacollector.infrastructure.persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.thm.datacollector.domain.model.DataStoreIValInfo;
import org.thm.datacollector.domain.model.DataStoreResourceInfo;

import java.util.Optional;

/**
 * Created by patrick.welter on 2/23/17.
 * (c) Janitza Electronics
 */
@Component
public class DataStoreIValRepository extends AbstractRepository<DataStoreIValInfo>{
    private final Logger LOG = LoggerFactory.getLogger(DataStoreIValRepository.class);


    private void insertDataStoreInfo(final String ival, final boolean isStored) {
        ops.insert(new DataStoreIValInfo(ival, isStored));
    }

    private void updateDataStoreInfo(final String ival, final boolean isStored) {
        ops.update(new DataStoreIValInfo(ival, isStored));
    }

    private void writeDataStoreInfo(final String ival, final boolean isStored) {
        final Optional<DataStoreIValInfo> optDataStoreInfo = findFirstBy("ival", ival);
        if(optDataStoreInfo.isPresent()) {
            updateDataStoreInfo(ival, isStored);
        } else {
            insertDataStoreInfo(ival, isStored);
        }
    }


    public void setStoring(final String ival) {
        writeDataStoreInfo(ival, true);
    }

    public void setUnstoring(final String ival) {
        writeDataStoreInfo(ival, false);
    }

    public DataStoreIValRepository(){
        super("data_store_service", "datastoreivalinfo", DataStoreIValInfo.class);
    }


}
