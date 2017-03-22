package org.thm.datacollector.infrastructure.stream;

import com.netflix.discovery.converters.Auto;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.ServiceActivator;
import org.thm.datacollector.domain.model.DataStoreIValInfo;
import org.thm.datacollector.infrastructure.persistence.DataStoreIValRepository;
import org.thm.datacollector.infrastructure.rest.Client;
import org.thm.datacollector.infrastructure.stream.channel.DataIValueSink;
import org.thm.datacollector.infrastructure.stream.channel.DataResourceSink;
import org.thm.datacollector.model.OnlineRecording;

import java.util.Optional;

/**
 * Created by patrick.welter on 3/6/17.
 * (c) Janitza Electronics
 */
@EnableBinding(DataIValueSink.class)
public class IValueDataReceiver {


    private final Logger log  = Logger.getLogger(LocationStateReceiverService.class);

    @Autowired
    private Client.SaveRecording saveRecording;

    @Autowired
    private DataStoreIValRepository repo;

    @ServiceActivator(inputChannel = DataIValueSink.INPUT)
    public void dataReceived(final OnlineRecording rec) {
        log.info("received:" + rec.toString());
        final Optional<DataStoreIValInfo> optInfo =
                repo.findFirstBy("ival", rec.getType());
        if(optInfo.isPresent()) {
            final DataStoreIValInfo info = optInfo.get();
            final boolean isStorable = info.getIval().equals(rec.getType()) && info.isStored();
            if(isStorable){
                log.info("persisting data: "+ rec.toString());
                saveRecording.dataLocation(rec);
            }
        } else {
            log.info("no store info found for:" + rec.toString());
        }
    }
}
