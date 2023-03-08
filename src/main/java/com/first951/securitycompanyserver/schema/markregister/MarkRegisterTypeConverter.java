package com.first951.securitycompanyserver.schema.markregister;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class MarkRegisterTypeConverter implements AttributeConverter<MarkRegisterType, String> {

    @Override
    public String convertToDatabaseColumn(MarkRegisterType attribute) {
        return attribute.getCode();
    }

    @Override
    public MarkRegisterType convertToEntityAttribute(String dbData) {
        return Stream.of(MarkRegisterType.values())
                .filter(c -> c.getCode().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}