package com.first951.securitycompanyserver.organization;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrganizationDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("address")
    @NotBlank
    private String address;

    @JsonProperty("name")
    @NotBlank
    private String name;

}
