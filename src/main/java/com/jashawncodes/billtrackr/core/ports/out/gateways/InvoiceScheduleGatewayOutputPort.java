package com.jashawncodes.billtrackr.core.ports.out.gateways;

import com.jashawncodes.billtrackr.core.model.invoiceSchedule.InvoiceSchedule;

import java.util.List;
import java.util.UUID;

public interface InvoiceScheduleGatewayOutputPort {
    InvoiceSchedule save(InvoiceSchedule invoiceSchedule);

    List<InvoiceSchedule> findAllByIsActive();

    InvoiceSchedule findById(UUID id);
}
