package com.first951.securitycompanyserver.dutyregister;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class DutyRegisterDto {

    @JsonProperty("id")
    private int id;

    @JsonProperty("postId")
    private int postId;

    @JsonProperty("guardId")
    private int guardId;

    @JsonProperty("begin")
    private Timestamp begin;

    @JsonProperty("end")
    private Timestamp end;

}
