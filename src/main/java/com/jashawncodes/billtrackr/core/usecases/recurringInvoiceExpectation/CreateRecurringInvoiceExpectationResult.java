package com.jashawncodes.billtrackr.core.usecases.recurringInvoiceExpectation;

import com.jashawncodes.billtrackr.core.model.recurringinvoiceexpectation.RecurrenceRule;

import java.util.UUID;

public record CreateRecurringInvoiceExpectationResult(
        UUID id,
        UUID vendorId,
        String trackedInvoiceKey,
        RecurrenceRule recurrenceRule,
        boolean active
) {
    public static CreateRecurringInvoiceExpectationResult of(
            UUID id,
            UUID vendorId,
            String trackedInvoiceKey,
            RecurrenceRule recurrenceRule,
            boolean active
    ) {
        return new CreateRecurringInvoiceExpectationResult(
                id,
                vendorId,
                trackedInvoiceKey,
                recurrenceRule,
                active
        );
    }
}
