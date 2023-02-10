package com.first951.securitycompanyserver.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PostDto {

    @JsonProperty("id")
    private int id;

    @JsonProperty("objectId")
    private int objectId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("comment")
    private String comment;

    @JsonProperty("address")
    private String address;

}
