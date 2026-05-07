package com.jashawncodes.billtrackr.core.ports.in;

import com.jashawncodes.billtrackr.core.useCases.markExpectedInvoiceAsReceived.MarkExpectedInvoiceAsReceivedResult;

import java.time.LocalDate;
import java.util.UUID;

public interface MarkExpectedInvoiceAsReceivedUseCase {
    MarkExpectedInvoiceAsReceivedResult markExpectedInvoiceAsReceived(LocalDate receiveDate, UUID expectedInvoiceId);
}
