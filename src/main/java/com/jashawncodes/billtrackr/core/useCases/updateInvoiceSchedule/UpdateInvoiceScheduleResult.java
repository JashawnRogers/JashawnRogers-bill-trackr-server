package com.jashawncodes.billtrackr.core.useCases.updateInvoiceSchedule;

import com.jashawncodes.billtrackr.core.model.invoiceSchedule.RecurrenceRule;
import com.jashawncodes.billtrackr.core.model.invoiceSchedule.TrackedInvoiceKey;
import com.jashawncodes.billtrackr.core.model.vendor.PaymentTerms;

import java.util.UUID;

public record UpdateInvoiceScheduleResult(
        UUID id,
        UUID vendorId,
        TrackedInvoiceKey trackedInvoiceKey,
        RecurrenceRule recurrenceRule,
        boolean active,
        PaymentTerms paymentTerms
) {
    public static UpdateInvoiceScheduleResult of(
            UUID id,
            UUID vendorId,
            TrackedInvoiceKey trackedInvoiceKey,
            RecurrenceRule recurrenceRule,
            boolean active,
            PaymentTerms paymentTerms
    ) {
        return new UpdateInvoiceScheduleResult(
                id,
                vendorId,
                trackedInvoiceKey,
                recurrenceRule,
                active,
                paymentTerms
        );
    }
}
