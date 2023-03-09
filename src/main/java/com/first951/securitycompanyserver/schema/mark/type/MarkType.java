package com.first951.securitycompanyserver.schema.mark.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum MarkType {

    EMPTY("E"),
    AUTOMATICALLY("A"),
    MANUALLY("M");

    @Getter
    private final String code;

}
