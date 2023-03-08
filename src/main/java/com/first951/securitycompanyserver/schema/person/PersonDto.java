package com.first951.securitycompanyserver.schema.person;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PersonDto {

    @JsonProperty("id")
    private int id;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("patronymic")
    private String patronymic;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

}
