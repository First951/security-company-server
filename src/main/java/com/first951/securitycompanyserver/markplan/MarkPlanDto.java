package com.first951.securitycompanyserver.markplan;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class MarkPlanDto {

    @JsonProperty("id")
    private int id;

    @JsonProperty("scheduleId")
    private int scheduleId;

    @JsonProperty("timestamp")
    private Timestamp timestamp;

}
