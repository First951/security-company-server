package com.first951.securitycompanyserver.schema.place;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PlaceDto {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty(value = "organizationId")
    private Long organizationId;

    @JsonProperty("name")
    @NotBlank
    private String name;

}
