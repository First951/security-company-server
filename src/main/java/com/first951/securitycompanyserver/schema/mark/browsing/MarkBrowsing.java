package com.first951.securitycompanyserver.schema.mark.browsing;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum MarkBrowsing {

    VIEWED("V"),
    UNSEEN("U");

    @Getter
    private final String code;

}
