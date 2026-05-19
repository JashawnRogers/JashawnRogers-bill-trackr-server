package com.jashawncodes.billtrackr.core.useCases.updateInvoiceSchedule;

import com.jashawncodes.billtrackr.core.model.invoiceSchedule.InvoiceSchedule;
import com.jashawncodes.billtrackr.core.ports.in.UpdateInvoiceScheduleUseCase;
import com.jashawncodes.billtrackr.core.ports.out.gateways.InvoiceScheduleGatewayOutputPort;


public class UpdateInvoiceScheduleService implements UpdateInvoiceScheduleUseCase {
    private final InvoiceScheduleGatewayOutputPort invoiceScheduleGateway;

    public UpdateInvoiceScheduleService (InvoiceScheduleGatewayOutputPort invoiceScheduleGateway) {
        this.invoiceScheduleGateway = invoiceScheduleGateway;
    }

    @Override
    public UpdateInvoiceScheduleResult updateInvoiceSchedule(UpdateInvoiceScheduleCommand command) {
        InvoiceSchedule invoiceSchedule = invoiceScheduleGateway.findById(command.invoiceScheduleId());

        invoiceSchedule.update(
                command.trackedInvoiceKey(),
                command.recurrenceRule(),
                command.paymentTerms(),
                command.active());

        InvoiceSchedule saved = invoiceScheduleGateway.save(invoiceSchedule);

        return UpdateInvoiceScheduleResult.of(
                saved.getId(),
                saved.getVendorId(),
                saved.getTrackedInvoiceKey(),
                saved.getRecurrenceRule(),
                saved.isActive(),
                saved.getPaymentTerms()
        );
    }
}
