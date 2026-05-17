package com.jashawncodes.billtrackr.core.ports.out.gateways;

import com.jashawncodes.billtrackr.core.model.expectedInvoice.ExpectedInvoice;
import com.jashawncodes.billtrackr.core.model.expectedInvoice.InvoiceStatus;
import com.jashawncodes.billtrackr.core.useCases.listExpectedMissingInvoicesForMonth.MissingExpectedInvoiceReadModel;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExpectedInvoiceGatewayOutputPort {
    ExpectedInvoice save(ExpectedInvoice expectedInvoice);

    Optional<ExpectedInvoice> findById(UUID id);

    Optional<ExpectedInvoice> findByExpectedReceiveDateAndRecurringInvoiceExpectationId(LocalDate date, UUID recurringInvoiceExpectationId);

    List<MissingExpectedInvoiceReadModel> findByInvoiceStatusAndExpectedReceiveDateBetween(InvoiceStatus invoiceStatus, LocalDate start, LocalDate end);
}
