package com.jashawncodes.billtrackr.core.useCases.expectedInvoice.listExpectedInvoices;

import com.jashawncodes.billtrackr.core.model.expectedInvoice.InvoiceStatus;
import com.jashawncodes.billtrackr.core.model.invoiceSchedule.TrackedInvoiceKey;
import com.jashawncodes.billtrackr.core.model.vendor.VendorName;

import java.time.LocalDate;
import java.util.UUID;

public record ExpectedInvoiceReadModel(
        UUID expectedInvoiceId,
        VendorName vendorName,
        TrackedInvoiceKey trackedInvoiceKey,
        LocalDate expectedReceiveDate,
        LocalDate dueDate,
        LocalDate receivedDate,
        InvoiceStatus status
) {
    public static ExpectedInvoiceReadModel of(
            UUID expectedInvoiceId,
            VendorName vendorName,
            TrackedInvoiceKey trackedInvoiceKey,
            LocalDate expectedReceiveDate,
            LocalDate dueDate,
            LocalDate receivedDate,
            InvoiceStatus invoiceStatus
    ) {
        return new ExpectedInvoiceReadModel(
                expectedInvoiceId,
                vendorName,
                trackedInvoiceKey,
                expectedReceiveDate,
                dueDate,
                receivedDate,
                invoiceStatus
        );
    }
}
