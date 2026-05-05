package com.jashawncodes.billtrackr.core.model.expectedinvoice;

import com.jashawncodes.billtrackr.core.model.InvalidDomainObjectException;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.UUID;

import static com.jashawncodes.billtrackr.core.model.DomainValidator.*;

public class ExpectedInvoice {
    private final UUID id;
    private final UUID recurringInvoiceExpectationId;
    private final LocalDate expectedReceiveDate;
    private final LocalDate dueDate;
    private LocalDate receivedDate;
    private InvoiceStatus invoiceStatus;
    private final String note;

    public ExpectedInvoice(UUID id,
                           UUID recurringInvoiceExpectationId,
                           LocalDate expectedReceiveDate,
                           LocalDate dueDate,
                           LocalDate receivedDate,
                           InvoiceStatus invoiceStatus,
                           String note
    ) {
        this.id = notNull(id);
        this.recurringInvoiceExpectationId = notNull(recurringInvoiceExpectationId);
        this.expectedReceiveDate = notNull(expectedReceiveDate);
        this.dueDate = notNull(dueDate);
        this.receivedDate = receivedDate;
        this.invoiceStatus = notNull(invoiceStatus);
        this.note = normalizeOptionalNote(note);
        validateReceivedState();
    }

    public static ExpectedInvoice createNew(
            UUID id,
            UUID recurringInvoiceExpectationId,
            LocalDate expectedReceiveDate,
            LocalDate dueDate,
            String note
    ) {
        return new ExpectedInvoice(
                id,
                recurringInvoiceExpectationId,
                expectedReceiveDate,
                dueDate,
                null,
                InvoiceStatus.EXPECTED,
                note
        );
    }

    public void markAsReceived(LocalDate receivedDate) {
        this.receivedDate = notNull(receivedDate);
        this.invoiceStatus = InvoiceStatus.RECEIVED;
    }
    public void updateReceivedDate(LocalDate newReceivedDate) {
        this.receivedDate = notNull(newReceivedDate);
    }

    public void revertToExpected() {
        this.receivedDate = null;
        this.invoiceStatus = InvoiceStatus.EXPECTED;
    }

    public YearMonth calculateBillingPeriod() {
        return YearMonth.from(expectedReceiveDate);
    }

    private void validateReceivedState() {
        if (invoiceStatus == InvoiceStatus.RECEIVED && receivedDate == null) {
            throw new InvalidDomainObjectException("Received invoices must have a received date.");
        }
    }

    private static String normalizeOptionalNote(String note) {
        if (note == null || note.isBlank()) {
            return null;
        }
        return note.trim();
    }


//    Getters
    public UUID getId() {
        return id;
    }

    public LocalDate getExpectedReceiveDate() {
        return expectedReceiveDate;
    }

    public UUID getRecurringInvoiceExpectationId() {
        return recurringInvoiceExpectationId;
    }

    public LocalDate getReceivedDate() {
        return receivedDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public InvoiceStatus getInvoiceStatus() {
        return invoiceStatus;
    }

    public String getNote() {
        return note;
    }
}
