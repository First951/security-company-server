package com.first951.securitycompanyserver.schema.place;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PlaceDto {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty(value = "organizationId")
    @NotNull
    @Positive
    private Long organizationId;

    @JsonProperty("name")
    @NotBlank
    private String name;

}
