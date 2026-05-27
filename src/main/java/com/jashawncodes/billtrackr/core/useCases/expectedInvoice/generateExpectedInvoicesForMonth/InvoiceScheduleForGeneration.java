package com.jashawncodes.billtrackr.core.useCases.expectedInvoice.generateExpectedInvoicesForMonth;

import com.jashawncodes.billtrackr.core.model.invoiceSchedule.RecurrenceRule;
import com.jashawncodes.billtrackr.core.model.invoiceSchedule.TrackedInvoiceKey;
import com.jashawncodes.billtrackr.core.model.vendor.PaymentTerms;

import java.time.LocalDate;
import java.util.UUID;

public record InvoiceScheduleForGeneration(
        UUID Id,
        RecurrenceRule recurrenceRule,
        TrackedInvoiceKey trackedInvoiceKey,
        PaymentTerms paymentTerms,
        LocalDate expectedReceiveDate
) {
}
