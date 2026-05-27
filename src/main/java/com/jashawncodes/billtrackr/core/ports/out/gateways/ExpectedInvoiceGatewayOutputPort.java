package com.jashawncodes.billtrackr.core.ports.out.gateways;

import com.jashawncodes.billtrackr.core.model.expectedInvoice.ExpectedInvoice;
import com.jashawncodes.billtrackr.core.model.expectedInvoice.InvoiceStatus;
import com.jashawncodes.billtrackr.core.useCases.PageResponse;
import com.jashawncodes.billtrackr.core.useCases.expectedInvoice.ListExpectedInvoicesQuery;
import com.jashawncodes.billtrackr.core.useCases.expectedInvoice.listExpectedInvoices.ExpectedInvoiceReadModel;
import com.jashawncodes.billtrackr.core.useCases.expectedInvoice.listExpectedMissingInvoicesForMonth.MissingExpectedInvoiceReadModel;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExpectedInvoiceGatewayOutputPort {
    ExpectedInvoice save(ExpectedInvoice expectedInvoice);

    Optional<ExpectedInvoice> findById(UUID id);

    List<ExpectedInvoice> findExistingForSchedulesBetween
            (List<UUID> scheduleIds, LocalDate startDate, LocalDate endDate);

    List<MissingExpectedInvoiceReadModel> findByInvoiceStatusAndExpectedReceiveDateBetween
            (InvoiceStatus invoiceStatus, LocalDate start, LocalDate end);

    PageResponse<ExpectedInvoiceReadModel> findAllPaginated(ListExpectedInvoicesQuery query);
}
