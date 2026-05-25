package com.jashawncodes.billtrackr.core.ports.out.gateways;

import com.jashawncodes.billtrackr.core.model.invoiceSchedule.InvoiceSchedule;
import com.jashawncodes.billtrackr.core.useCases.generateExpectedInvoicesForMonth.InvoiceScheduleForGeneration;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InvoiceScheduleGatewayOutputPort {
    InvoiceSchedule save(InvoiceSchedule invoiceSchedule);

    List<InvoiceSchedule> findAllByIsActive();

    Optional<InvoiceSchedule> findById(UUID id);

    List<InvoiceScheduleForGeneration> loadActiveSchedulesWithPaymentTerms();
}
