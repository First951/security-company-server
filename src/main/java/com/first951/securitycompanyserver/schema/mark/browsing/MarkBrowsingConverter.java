package com.first951.securitycompanyserver.schema.mark.browsing;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class MarkBrowsingConverter implements AttributeConverter<MarkBrowsing, String> {

    @Override
    public String convertToDatabaseColumn(MarkBrowsing attribute) {
        return attribute.getCode();
    }

    @Override
    public MarkBrowsing convertToEntityAttribute(String dbData) {
        return Stream.of(MarkBrowsing.values())
                .filter(c -> c.getCode().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}