package com.first951.securitycompanyserver.schema.person;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PersonDto {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty("lastName")
    @NotBlank
    private String lastName;

    @JsonProperty("firstName")
    @NotBlank
    private String firstName;

    @JsonProperty("patronymic")
    private String patronymic;

    @JsonProperty("phoneNumber")
    @NotBlank
    private String phoneNumber;

}
