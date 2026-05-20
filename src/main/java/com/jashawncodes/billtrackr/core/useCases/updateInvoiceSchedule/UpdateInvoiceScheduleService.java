package com.jashawncodes.billtrackr.core.useCases.updateInvoiceSchedule;

import com.jashawncodes.billtrackr.core.NotFoundException;
import com.jashawncodes.billtrackr.core.model.invoiceSchedule.InvoiceSchedule;
import com.jashawncodes.billtrackr.core.ports.in.UpdateInvoiceScheduleUseCase;
import com.jashawncodes.billtrackr.core.ports.out.gateways.InvoiceScheduleGatewayOutputPort;
import com.jashawncodes.billtrackr.core.ports.out.gateways.VendorGatewayOutputPort;
import com.jashawncodes.billtrackr.core.useCases.createInvoiceSchedule.DuplicateTrackedInvoiceKeyException;


public class UpdateInvoiceScheduleService implements UpdateInvoiceScheduleUseCase {
    private final InvoiceScheduleGatewayOutputPort invoiceScheduleGateway;
    private final VendorGatewayOutputPort vendorGateway;

    public UpdateInvoiceScheduleService (InvoiceScheduleGatewayOutputPort invoiceScheduleGateway,
                                         VendorGatewayOutputPort vendorGateway) {
        this.invoiceScheduleGateway = invoiceScheduleGateway;
        this.vendorGateway = vendorGateway;
    }

    @Override
    public UpdateInvoiceScheduleResult updateInvoiceSchedule(UpdateInvoiceScheduleCommand command) {
        InvoiceSchedule invoiceSchedule = invoiceScheduleGateway.findById(command.invoiceScheduleId())
                .orElseThrow(() -> new NotFoundException("Invoice schedule not found"));

        command.trackedInvoiceKey().ifPresent(newKey -> {
            if (vendorGateway.existsByTrackedInvoiceKeyAndVendorIdAndNot(
                    newKey.trackedInvoiceKey(),
                    invoiceSchedule.getVendorId(),
                    invoiceSchedule.getId()
            )) {
                throw new DuplicateTrackedInvoiceKeyException("The vendor already has a tracked invoice key with the same name");
            }
        });

        invoiceSchedule.update(
                command.trackedInvoiceKey(),
                command.recurrenceRule(),
                command.active());

        InvoiceSchedule saved = invoiceScheduleGateway.save(invoiceSchedule);

        return UpdateInvoiceScheduleResult.of(
                saved.getId(),
                saved.getVendorId(),
                saved.getTrackedInvoiceKey(),
                saved.getRecurrenceRule(),
                saved.isActive()
        );
    }
}
