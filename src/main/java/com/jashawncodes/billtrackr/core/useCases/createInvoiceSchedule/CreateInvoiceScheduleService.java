package com.jashawncodes.billtrackr.core.useCases.createInvoiceSchedule;

import com.jashawncodes.billtrackr.core.model.invoiceSchedule.RecurrenceRule;
import com.jashawncodes.billtrackr.core.model.invoiceSchedule.InvoiceSchedule;
import com.jashawncodes.billtrackr.core.model.invoiceSchedule.TrackedInvoiceKey;
import com.jashawncodes.billtrackr.core.model.vendor.PaymentTerms;
import com.jashawncodes.billtrackr.core.model.vendor.Vendor;
import com.jashawncodes.billtrackr.core.ports.in.CreateInvoiceScheduleUseCase;
import com.jashawncodes.billtrackr.core.ports.out.IdGeneratorOutputPort;
import com.jashawncodes.billtrackr.core.ports.out.PersistenceGatewayOutputPort;

import java.util.UUID;

public class CreateInvoiceScheduleService implements CreateInvoiceScheduleUseCase {
    private final PersistenceGatewayOutputPort persistenceGatewayOutputPort;
    private final IdGeneratorOutputPort idGeneratorOutputPort;

    public CreateInvoiceScheduleService(PersistenceGatewayOutputPort persistenceGatewayOutputPort,
                                        IdGeneratorOutputPort idGeneratorOutputPort
    ) {

        this.persistenceGatewayOutputPort = persistenceGatewayOutputPort;
        this.idGeneratorOutputPort = idGeneratorOutputPort;
    }

    @Override
    public CreateInvoiceScheduleResult createNewInvoiceSchedule(
            UUID vendorId,
            TrackedInvoiceKey trackedInvoiceKey,
            RecurrenceRule recurrenceRule,
            PaymentTerms paymentTerms
    ) {
         Vendor vendor = persistenceGatewayOutputPort.findByVendorId(vendorId)
                .orElseThrow(() -> new VendorDoesNotExistException("Vendor does not exist"));

        if (!vendor.isActive()) {
            throw new InactiveVendorException("Vendor is inactive");
        }

        if (persistenceGatewayOutputPort.existsByTrackedInvoiceKeyAndVendorId(trackedInvoiceKey.trackedInvoiceKey(), vendor.getId())) {
            throw new DuplicateTrackedInvoiceKeyException("The vendor already has a tracked invoice key with the same name");
        }

        UUID invoiceScheduleId = idGeneratorOutputPort.generateNewUUID();

        InvoiceSchedule invoiceSchedule = InvoiceSchedule.createNew(
                invoiceScheduleId,
                vendorId,
                trackedInvoiceKey,
                recurrenceRule,
                paymentTerms
        );

        InvoiceSchedule saved = persistenceGatewayOutputPort.save(invoiceSchedule);

        return CreateInvoiceScheduleResult.of(
                saved.getId(),
                saved.getVendorId(),
                saved.getTrackedInvoiceKey().trackedInvoiceKey(),
                saved.getRecurrenceRule(),
                saved.getPaymentTerms(),
                saved.isActive()
        );
    }
}
