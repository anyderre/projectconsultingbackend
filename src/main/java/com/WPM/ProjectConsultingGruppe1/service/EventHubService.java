package com.WPM.ProjectConsultingGruppe1.service;
/**
 * Created by Dany on 04/04/2023.
 */
import com.azure.core.amqp.AmqpTransportType;
import com.azure.core.util.BinaryData;
import com.azure.identity.AzureAuthorityHosts;
import com.azure.identity.DefaultAzureCredential;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.messaging.eventhubs.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;


@Slf4j
public class EventHubService {

    public static void  publishEvents(String data, String eventHubName, String connectionString ) throws ExecutionException, InterruptedException {
        Logger logger = LoggerFactory.getLogger(EventHubService.class);

        EventHubProducerClient eventHubProducer = new EventHubClientBuilder()
                .transportType(AmqpTransportType.AMQP)
                .connectionString(connectionString)
                .buildProducerClient();
        logger.info("Created a new EventHubProducerClient!");

        EventHubProperties eventHubProperties = eventHubProducer.getEventHubProperties();
        logger.info("Event Hub created at: " + eventHubProperties.getCreatedAt());
        for (String partitionId: eventHubProperties.getPartitionIds()) {
            logger.info(eventHubProperties.getName() + " has this partition ID: " + partitionId);
        }
        PartitionProperties properties = eventHubProducer.getPartitionProperties("0");
        logger.info("Event Hub name is: " + properties.getEventHubName());
        logger.info("Is Partition empty: " + properties.isEmpty());

        // Prepare a batch of events to send to the event hub
        logger.info("Creating a new batch...");
        EventDataBatch batch = eventHubProducer.createBatch();
        logger.info("Done!");
        batch.tryAdd(new EventData(data));
        // send the batch of events to the event hub
        eventHubProducer.send(batch);

        // close the producer
        eventHubProducer.close();

    }

}
