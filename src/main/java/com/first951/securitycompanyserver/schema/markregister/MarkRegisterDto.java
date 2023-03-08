package com.first951.securitycompanyserver.schema.markregister;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class MarkRegisterDto {

    @JsonProperty("id")
    private int id;

    @JsonProperty("markPlanId")
    private int markPlanId;

    @JsonProperty("timestamp")
    private Timestamp timestamp;

    @JsonProperty("type")
    private MarkRegisterType type;

    @JsonProperty("comment")
    private String comment;

}
