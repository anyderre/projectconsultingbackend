package com.WPM.ProjectConsultingGruppe1.controller;

/**
 * Created by Dany on 04/04/2023.
 */
import com.WPM.ProjectConsultingGruppe1.model.Event;
import com.WPM.ProjectConsultingGruppe1.service.EventHubService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;


//@PreAuthorize("hasRole('USER')")
@RestController
@RequestMapping("/api")
public class EventCaptureController {
    @Value("${projectconsulting.namespace}")
    private String eventHubName;

    @Value("${projectconsulting.connection_string}")
    private String connectionString;

    @PostMapping(value = "/events/unique", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void sendEvent(@RequestBody String event) throws ExecutionException, InterruptedException {
        System.out.println(event);
        EventHubService.publishEvents(event, eventHubName, connectionString);
    }

    @PostMapping("/events/batch")
    public void sendEventBatch(String [] event) {

    }
}
