package com.first951.securitycompanyserver.schema.markregister;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum MarkRegisterType {
    AUTOMATICALLY("A"),
    MANUALLY("M");

    @Getter
    private final String code;
}
