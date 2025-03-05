package model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuditEvent {
    @JsonProperty("username")
    private String username;
    @JsonProperty("actionString")
    private String actionString;
    @JsonProperty("epochTimestampSec")
    private long epochTimestampSec;
    @JsonProperty("orgId")
    private long orgId;
    @JsonProperty("extraMsg")
    private String extraMsg;

    public AuditEvent() {}

    public AuditEvent(String username, String actionString, long epochTimestampSec, long orgId, String extraMsg) {
        this.username = username;
        this.actionString = actionString;
        this.epochTimestampSec = epochTimestampSec;
        this.orgId = orgId;
        this.extraMsg = extraMsg;
    }

    public String getUsername() {
        return username;
    }

    public String getActionString() {
        return actionString;
    }

    public long getEpochTimestampSec() {
        return epochTimestampSec;
    }

    public long getOrgId() {
        return orgId;
    }

    public String getExtraMsg() {
        return extraMsg;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
