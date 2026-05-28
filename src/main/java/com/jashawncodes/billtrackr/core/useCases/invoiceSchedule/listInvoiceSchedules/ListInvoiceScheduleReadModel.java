package com.jashawncodes.billtrackr.core.useCases.invoiceSchedule.listInvoiceSchedules;

import com.jashawncodes.billtrackr.core.model.invoiceSchedule.RecurrenceRule;
import com.jashawncodes.billtrackr.core.model.invoiceSchedule.TrackedInvoiceKey;
import com.jashawncodes.billtrackr.core.model.vendor.PaymentTerms;
import com.jashawncodes.billtrackr.core.model.vendor.VendorName;

import java.util.UUID;

public record ListInvoiceScheduleReadModel(
        UUID invoiceScheduleId,
        VendorName vendorName,
        TrackedInvoiceKey trackedInvoiceKey,
        RecurrenceRule recurrenceRule,
        boolean active,
        PaymentTerms paymentTerms
) {
}
