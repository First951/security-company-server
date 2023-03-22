package com.first951.securitycompanyserver.schema.mark;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.first951.securitycompanyserver.schema.mark.type.MarkType;
import com.first951.securitycompanyserver.schema.person.PersonDto;
import com.first951.securitycompanyserver.schema.schedule.ScheduleDto;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.util.Date;

@Data
public class MarkDto {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty(value = "scheduleId", access = JsonProperty.Access.WRITE_ONLY)
    private Long scheduleId;

    @JsonProperty(value = "personId", access = JsonProperty.Access.WRITE_ONLY)
    private Long personId;

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

    @JsonProperty(value = "schedule", access = JsonProperty.Access.READ_ONLY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ScheduleDto scheduleDto;

    @JsonProperty(value = "person", access = JsonProperty.Access.READ_ONLY)
    private PersonDto personDto;

}
