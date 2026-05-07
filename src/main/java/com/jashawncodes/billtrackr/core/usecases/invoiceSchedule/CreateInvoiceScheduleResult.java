package com.jashawncodes.billtrackr.core.usecases.invoiceSchedule;

import com.jashawncodes.billtrackr.core.model.invoiceSchedule.RecurrenceRule;
import com.jashawncodes.billtrackr.core.model.vendor.PaymentTerms;

import java.util.UUID;

public record CreateInvoiceScheduleResult(
        UUID id,
        UUID vendorId,
        String trackedInvoiceKey,
        RecurrenceRule recurrenceRule,
        PaymentTerms paymentTerms,
        boolean active
) {
    public static CreateInvoiceScheduleResult of(
            UUID id,
            UUID vendorId,
            String trackedInvoiceKey,
            RecurrenceRule recurrenceRule,
            PaymentTerms paymentTerms,
            boolean active
    ) {
        return new CreateInvoiceScheduleResult(
                id,
                vendorId,
                trackedInvoiceKey,
                recurrenceRule,
                paymentTerms,
                active
        );
    }
}
