package com.first951.securitycompanyserver.schema.mark;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.first951.securitycompanyserver.schema.mark.type.MarkType;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.util.Date;

@Data
public class MarkDto {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty("scheduleId")
    private Long scheduleId;

    @JsonProperty("plan_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date planTimestamp;

    @JsonProperty("fact_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date factTimestamp;

    @JsonProperty("type")
    private MarkType type;

    @JsonProperty("comment")
    private String comment;

}
