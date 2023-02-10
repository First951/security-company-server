package com.first951.securitycompanyserver.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ObjectDto {

    @JsonProperty("id")
    private int id;

    @JsonProperty("organizationId")
    private int organizationId;

    @JsonProperty("name")
    private String name;

}
