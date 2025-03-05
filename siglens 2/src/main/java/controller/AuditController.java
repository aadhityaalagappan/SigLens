package controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.AuditEvent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AuditController {
    static final String LOG_FILE = "audit.log.json";
    static final ObjectMapper MAPPER = new ObjectMapper();

    public static void createAuditEvent(String username, String actionString, long epochTimestampSec, long orgId, String extraMsg) {
        List<AuditEvent> events = readAllAuditEvents();
        AuditEvent event = new AuditEvent(username, actionString, epochTimestampSec, orgId, extraMsg);
        events.add(event);
        writeEvent(events);
    }

    public static List<AuditEvent> readAuditEvent(long orgId,long startEpochSec, long endEpochSec) {
        List<AuditEvent> events = readAllAuditEvents();
        List<AuditEvent> filteredEvents = events.stream().filter(event ->
            event.getOrgId() == orgId && event.getEpochTimestampSec() >= startEpochSec && event.getEpochTimestampSec() <= endEpochSec
        ).collect(Collectors.toList());
        return filteredEvents;

    }

    private static List<AuditEvent> readAllAuditEvents() {
        File file = new File(LOG_FILE);
        if(!file.exists()) {
            return new ArrayList<>();
        }

        try {
            return MAPPER.readValue(file, new TypeReference<List<AuditEvent>>(){});

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void writeEvent(List<AuditEvent> events){
        try {
            MAPPER.writerWithDefaultPrettyPrinter().writeValue(new File(LOG_FILE), events);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
