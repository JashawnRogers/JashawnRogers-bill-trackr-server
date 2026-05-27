package com.jashawncodes.billtrackr.core.useCases.invoiceSchedule.createInvoiceSchedule;

import com.jashawncodes.billtrackr.core.model.invoiceSchedule.RecurrenceRule;

import java.util.UUID;

public record CreateInvoiceScheduleResult(
        UUID id,
        UUID vendorId,
        String trackedInvoiceKey,
        RecurrenceRule recurrenceRule,
        boolean active
) {
    public static CreateInvoiceScheduleResult of(
            UUID id,
            UUID vendorId,
            String trackedInvoiceKey,
            RecurrenceRule recurrenceRule,
            boolean active
    ) {
        return new CreateInvoiceScheduleResult(
                id,
                vendorId,
                trackedInvoiceKey,
                recurrenceRule,
                active
        );
    }
}
