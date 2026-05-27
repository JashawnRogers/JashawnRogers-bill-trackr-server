package com.jashawncodes.billtrackr.core.useCases.invoiceSchedule.updateInvoiceSchedule;

import com.jashawncodes.billtrackr.core.model.invoiceSchedule.RecurrenceRule;
import com.jashawncodes.billtrackr.core.model.invoiceSchedule.TrackedInvoiceKey;

import java.util.UUID;

public record UpdateInvoiceScheduleResult(
        UUID id,
        UUID vendorId,
        TrackedInvoiceKey trackedInvoiceKey,
        RecurrenceRule recurrenceRule,
        boolean active
) {
    public static UpdateInvoiceScheduleResult of(
            UUID id,
            UUID vendorId,
            TrackedInvoiceKey trackedInvoiceKey,
            RecurrenceRule recurrenceRule,
            boolean active
    ) {
        return new UpdateInvoiceScheduleResult(
                id,
                vendorId,
                trackedInvoiceKey,
                recurrenceRule,
                active
        );
    }
}
