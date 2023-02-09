package com.first951.securitycompanyserver.personrole;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Date;

@Data
public class PersonRoleDto {

    @JsonProperty("id")
    private int id;

    @JsonProperty("personId")
    private int personId;

    @JsonProperty("roleId")
    private int roleId;

    @JsonProperty("periodFrom")
    private Date periodFrom;

    @JsonProperty("periodTo")
    private Date periodTo;

}
