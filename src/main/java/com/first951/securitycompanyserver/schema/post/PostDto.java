package com.first951.securitycompanyserver.schema.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.first951.securitycompanyserver.schema.organization.OrganizationDto;
import com.first951.securitycompanyserver.schema.place.PlaceDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PostDto {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty(value = "placeId", access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Positive
    private Long placeId;

    @JsonProperty(value = "organizationId", access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Positive
    private Long organizationId;

    @JsonProperty("name")
    @NotBlank
    private String name;

    @JsonProperty("comment")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String comment;

    @JsonProperty(value = "place", access = JsonProperty.Access.READ_ONLY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PlaceDto placeDto;

    @JsonProperty(value = "organization", access = JsonProperty.Access.READ_ONLY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private OrganizationDto organizationDto;

}
