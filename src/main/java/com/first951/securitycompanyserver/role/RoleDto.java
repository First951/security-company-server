package com.first951.securitycompanyserver.role;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RoleDto {

    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

}
