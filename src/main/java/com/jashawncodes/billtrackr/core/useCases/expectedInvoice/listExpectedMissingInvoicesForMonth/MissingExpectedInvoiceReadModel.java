package com.jashawncodes.billtrackr.core.useCases.expectedInvoice.listExpectedMissingInvoicesForMonth;

import com.jashawncodes.billtrackr.core.model.expectedInvoice.InvoiceStatus;

import java.time.LocalDate;
import java.util.UUID;

public record MissingExpectedInvoiceReadModel(
        UUID expectedInvoiceId,
        String vendorName,
        String trackedInvoiceKey,
        LocalDate expectedReceivedDate,
        InvoiceStatus status
) {
    public static MissingExpectedInvoiceReadModel of(
            UUID expectedInvoiceId,
            String vendorName,
            String trackedInvoiceKey,
            LocalDate expectedReceivedDate,
            InvoiceStatus status
    ) {
        return new MissingExpectedInvoiceReadModel(
                expectedInvoiceId,
                vendorName,
                trackedInvoiceKey,
                expectedReceivedDate,
                status
        );
    }
}
