package com.first951.securitycompanyserver.schema.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PostDto {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty("placeId")
    @NotNull
    @Positive
    private Long placeId;

    @JsonProperty("name")
    @NotBlank
    private String name;

    @JsonProperty("comment")
    private String comment;

    @JsonProperty("address")
    @NotBlank
    private String address;

}
