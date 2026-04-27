package com.jashawncodes.billtrackr.core.model;

public class DomainValidator {
    public static <T> T notNull(T anything) {
        if (anything == null) throw new InvalidDomainObjectException("Property cannot be null");

        return anything;
    }

    public static String notBlank(String anyString) {
        if (notNull(anyString).matches("\\s*")) throw new InvalidDomainObjectException("Property cannot be blank");

        return anyString;
    }

    public static String normalizeText(String anyString) {
        if (anyString == null || anyString.isBlank()) throw new InvalidDomainObjectException("String must not be null or blank");

        return anyString.trim();
    }
}
