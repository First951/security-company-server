package com.first951.securitycompanyserver.schema.schedule;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ScheduleDto {

    @JsonProperty("id")
    private int id;

    @JsonProperty("postId")
    private int postId;

    @JsonProperty("begin")
    private Timestamp begin;

    @JsonProperty("end")
    private Timestamp end;

}
