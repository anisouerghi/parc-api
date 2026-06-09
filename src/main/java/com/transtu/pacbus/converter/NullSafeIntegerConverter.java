package com.transtu.pacbus.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Convertisseur tolérant pour les colonnes numériques stockées en texte.
 *
 * <p>Certaines lignes de la base contiennent la chaîne littérale {@code "NULL"}
 * (ou une valeur vide) dans des colonnes mappées en {@link Integer}, ce que le
 * driver MySQL ne peut pas convertir. Ce convertisseur lit ces valeurs comme
 * {@code null} au lieu de provoquer une {@code DataConversionException}.</p>
 */
@Converter
public class NullSafeIntegerConverter implements AttributeConverter<Integer, String> {

    @Override
    public String convertToDatabaseColumn(Integer attribute) {
        return attribute == null ? null : attribute.toString();
    }

    @Override
    public Integer convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        String value = dbData.trim();
        if (value.isEmpty() || "NULL".equalsIgnoreCase(value)) {
            return null;
        }
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException ex) {
            return null;
        }
    }
}
