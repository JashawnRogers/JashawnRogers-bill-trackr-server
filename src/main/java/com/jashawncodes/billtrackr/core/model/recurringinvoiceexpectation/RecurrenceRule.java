package com.jashawncodes.billtrackr.core.model.recurringinvoiceexpectation;

import java.time.LocalDate;

import static com.jashawncodes.billtrackr.core.model.DomainValidator.notNull;

public record RecurrenceRule(RecurrenceType recurrenceType, LocalDate anchorDate) {
    public RecurrenceRule {
        notNull(anchorDate);
        notNull(recurrenceType);
    }
}
