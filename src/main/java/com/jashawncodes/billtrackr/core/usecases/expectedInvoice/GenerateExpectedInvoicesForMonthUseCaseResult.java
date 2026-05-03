package com.jashawncodes.billtrackr.core.usecases.expectedInvoice;

import com.jashawncodes.billtrackr.core.model.expectedinvoice.InvoiceStatus;
import com.jashawncodes.billtrackr.core.model.vendor.PaymentTerms;

import java.time.LocalDate;
import java.util.UUID;

public record GenerateExpectedInvoicesForMonthUseCaseResult(
        UUID id,
        String trackedInvoiceKey,
        LocalDate expectedReceiveDate,
        PaymentTerms paymentTerms,
        LocalDate receivedDate,
        InvoiceStatus invoiceStatus,
        String note

) {
    public static GenerateExpectedInvoicesForMonthUseCaseResult of(
            UUID id,
            String trackedInvoiceKey,
            LocalDate expectedReceiveDate,
            PaymentTerms paymentTerms,
            LocalDate receivedDate,
            InvoiceStatus invoiceStatus,
            String note
    ) {
        return new GenerateExpectedInvoicesForMonthUseCaseResult(
                id,
                trackedInvoiceKey,
                expectedReceiveDate,
                paymentTerms,
                receivedDate,
                invoiceStatus,
                note
        );
    }
}
