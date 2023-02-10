package com.first951.securitycompanyserver.organization;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrganizationDto {

    @JsonProperty("id")
    private int id;

    @JsonProperty("address")
    private String address;

    @JsonProperty("name")
    private String name;

}
