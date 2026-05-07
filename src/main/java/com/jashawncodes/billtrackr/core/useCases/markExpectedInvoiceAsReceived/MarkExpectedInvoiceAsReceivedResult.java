package com.jashawncodes.billtrackr.core.useCases.markExpectedInvoiceAsReceived;

import com.jashawncodes.billtrackr.core.model.expectedInvoice.InvoiceStatus;

import java.time.LocalDate;
import java.util.UUID;

public record MarkExpectedInvoiceAsReceivedResult(
        UUID id,
        LocalDate expectedReceiveDate,
        LocalDate receivedDate,
        InvoiceStatus status,
        String note

) {
    public static MarkExpectedInvoiceAsReceivedResult of(
            UUID id,
            LocalDate expectedReceiveDate,
            LocalDate receivedDate,
            InvoiceStatus status,
            String note
    ) {
        return new MarkExpectedInvoiceAsReceivedResult(
                id,
                expectedReceiveDate,
                receivedDate,
                status,
                note
        );
    }
}
