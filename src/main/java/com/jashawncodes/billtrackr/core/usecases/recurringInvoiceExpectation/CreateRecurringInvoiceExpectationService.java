package com.jashawncodes.billtrackr.core.usecases.recurringInvoiceExpectation;

import com.jashawncodes.billtrackr.core.model.recurringinvoiceexpectation.RecurrenceRule;
import com.jashawncodes.billtrackr.core.model.recurringinvoiceexpectation.RecurringInvoiceExpectation;
import com.jashawncodes.billtrackr.core.model.recurringinvoiceexpectation.TrackedInvoiceKey;
import com.jashawncodes.billtrackr.core.model.vendor.Vendor;
import com.jashawncodes.billtrackr.core.ports.in.CreateRecurringInvoiceExpectationUseCase;
import com.jashawncodes.billtrackr.core.ports.out.IdGeneratorOutputPort;
import com.jashawncodes.billtrackr.core.ports.out.PersistenceGatewayOutputPort;

import java.util.UUID;

public class CreateRecurringInvoiceExpectationService implements CreateRecurringInvoiceExpectationUseCase {
    private final PersistenceGatewayOutputPort persistenceGatewayOutputPort;
    private final IdGeneratorOutputPort idGeneratorOutputPort;

    public CreateRecurringInvoiceExpectationService(PersistenceGatewayOutputPort persistenceGatewayOutputPort,
                                                    IdGeneratorOutputPort idGeneratorOutputPort
    ) {

        this.persistenceGatewayOutputPort = persistenceGatewayOutputPort;
        this.idGeneratorOutputPort = idGeneratorOutputPort;
    }

    @Override
    public CreateRecurringInvoiceExpectationResult createNewRecurringInvoiceExpectation(
            UUID vendorId,
            TrackedInvoiceKey trackedInvoiceKey,
            RecurrenceRule recurrenceRule
    ) {
        Vendor vendor = persistenceGatewayOutputPort.findByVendorId(vendorId)
                .orElseThrow(() -> new VendorDoesNotExistException("Vendor does not exist"));

        if (!vendor.isActive()) {
            throw new InactiveVendorException("Vendor is inactive");
        }

        if (persistenceGatewayOutputPort.existsByTrackedInvoiceKeyAndVendorId(trackedInvoiceKey.trackedInvoiceKey(), vendor.getId())) {
            throw new DuplicateTrackedInvoiceKeyException("The vendor already has a tracked invoice key with the same name");
        }

        UUID recurringInvoiceExpectationId = idGeneratorOutputPort.generateNewUUID();

        RecurringInvoiceExpectation recurringInvoiceExpectation = RecurringInvoiceExpectation.createNew(
                recurringInvoiceExpectationId,
                vendorId,
                trackedInvoiceKey,
                recurrenceRule
        );

        RecurringInvoiceExpectation saved = persistenceGatewayOutputPort.save(recurringInvoiceExpectation);

        return CreateRecurringInvoiceExpectationResult.of(
                saved.getId(),
                saved.getVendorId(),
                saved.getTrackedInvoiceKey().trackedInvoiceKey(),
                saved.getRecurrenceRule(),
                saved.isActive()
        );
    }
}
