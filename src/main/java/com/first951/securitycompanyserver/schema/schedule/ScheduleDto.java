package com.first951.securitycompanyserver.schema.schedule;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.first951.securitycompanyserver.schema.post.PostDto;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.Date;

@Data
public class ScheduleDto {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty("postId")
    @NotNull
    @Positive
    private Long postId;

    @JsonProperty("begin")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date begin;

    @JsonProperty("end")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date end;

    @JsonProperty(value = "post", access = JsonProperty.Access.READ_ONLY)
    private PostDto postDto;

}
