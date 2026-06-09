package com.transtu.pacbus.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class NullSafeIntegerConverterTest {

    private final NullSafeIntegerConverter converter = new NullSafeIntegerConverter();

    @Test
    void readsLiteralNullStringAsNull() {
        assertNull(converter.convertToEntityAttribute("NULL"));
        assertNull(converter.convertToEntityAttribute("null"));
    }

    @Test
    void readsBlankOrNullAsNull() {
        assertNull(converter.convertToEntityAttribute(null));
        assertNull(converter.convertToEntityAttribute(""));
        assertNull(converter.convertToEntityAttribute("   "));
    }

    @Test
    void readsValidNumber() {
        assertEquals(12345, converter.convertToEntityAttribute("12345"));
        assertEquals(42, converter.convertToEntityAttribute("  42 "));
    }

    @Test
    void readsUnparseableValueAsNull() {
        assertNull(converter.convertToEntityAttribute("12,345"));
        assertNull(converter.convertToEntityAttribute("abc"));
    }

    @Test
    void writesNumberAsString() {
        assertEquals("99", converter.convertToDatabaseColumn(99));
        assertNull(converter.convertToDatabaseColumn(null));
    }
}
