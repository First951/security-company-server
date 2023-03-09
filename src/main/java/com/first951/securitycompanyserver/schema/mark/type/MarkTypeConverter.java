package com.first951.securitycompanyserver.schema.mark.type;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class MarkTypeConverter implements AttributeConverter<MarkType, String> {

    @Override
    public String convertToDatabaseColumn(MarkType attribute) {
        return attribute.getCode();
    }

    @Override
    public MarkType convertToEntityAttribute(String dbData) {
        return Stream.of(MarkType.values())
                .filter(c -> c.getCode().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}