package com.jashawncodes.billtrackr.core.useCases.invoiceSchedule.createInvoiceSchedule;

import com.jashawncodes.billtrackr.core.model.invoiceSchedule.RecurrenceRule;
import com.jashawncodes.billtrackr.core.model.invoiceSchedule.InvoiceSchedule;
import com.jashawncodes.billtrackr.core.model.invoiceSchedule.TrackedInvoiceKey;
import com.jashawncodes.billtrackr.core.model.vendor.Vendor;
import com.jashawncodes.billtrackr.core.ports.in.CreateInvoiceScheduleUseCase;
import com.jashawncodes.billtrackr.core.ports.out.IdGeneratorOutputPort;
import com.jashawncodes.billtrackr.core.ports.out.gateways.InvoiceScheduleGatewayOutputPort;
import com.jashawncodes.billtrackr.core.ports.out.gateways.VendorGatewayOutputPort;

import java.util.UUID;

public class CreateInvoiceScheduleService implements CreateInvoiceScheduleUseCase {
    private final InvoiceScheduleGatewayOutputPort invoiceScheduleGateway;
    private final VendorGatewayOutputPort vendorGateway;
    private final IdGeneratorOutputPort idGeneratorOutputPort;

    public CreateInvoiceScheduleService(InvoiceScheduleGatewayOutputPort invoiceScheduleGateway,
                                        VendorGatewayOutputPort vendorGateway,
                                        IdGeneratorOutputPort idGeneratorOutputPort
    ) {

        this.invoiceScheduleGateway = invoiceScheduleGateway;
        this.vendorGateway = vendorGateway;
        this.idGeneratorOutputPort = idGeneratorOutputPort;
    }

    @Override
    public CreateInvoiceScheduleResult createNewInvoiceSchedule(
            UUID vendorId,
            TrackedInvoiceKey trackedInvoiceKey,
            RecurrenceRule recurrenceRule
    ) {
         Vendor vendor = vendorGateway.findById(vendorId)
                .orElseThrow(() -> new VendorDoesNotExistException("Vendor does not exist"));

        if (!vendor.isActive()) {
            throw new InactiveVendorException("Vendor is inactive");
        }

        if (vendorGateway.existsByTrackedInvoiceKeyAndVendorId(trackedInvoiceKey.trackedInvoiceKey(), vendor.getId())) {
            throw new DuplicateTrackedInvoiceKeyException("The vendor already has a tracked invoice key with the same name");
        }

        UUID invoiceScheduleId = idGeneratorOutputPort.generateNewUUID();

        InvoiceSchedule invoiceSchedule = InvoiceSchedule.createNew(
                invoiceScheduleId,
                vendorId,
                trackedInvoiceKey,
                recurrenceRule
        );

        InvoiceSchedule saved = invoiceScheduleGateway.save(invoiceSchedule);

        return CreateInvoiceScheduleResult.of(
                saved.getId(),
                saved.getVendorId(),
                saved.getTrackedInvoiceKey().trackedInvoiceKey(),
                saved.getRecurrenceRule(),
                saved.isActive()
        );
    }
}
