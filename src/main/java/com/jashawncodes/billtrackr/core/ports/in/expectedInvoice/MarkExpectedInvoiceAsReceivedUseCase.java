package com.jashawncodes.billtrackr.core.ports.in.expectedInvoice;

import com.jashawncodes.billtrackr.core.useCases.expectedInvoice.markExpectedInvoiceAsReceived.MarkExpectedInvoiceAsReceivedResult;

import java.time.LocalDate;
import java.util.UUID;

public interface MarkExpectedInvoiceAsReceivedUseCase {
    MarkExpectedInvoiceAsReceivedResult markExpectedInvoiceAsReceived(LocalDate receiveDate, UUID expectedInvoiceId);
}
