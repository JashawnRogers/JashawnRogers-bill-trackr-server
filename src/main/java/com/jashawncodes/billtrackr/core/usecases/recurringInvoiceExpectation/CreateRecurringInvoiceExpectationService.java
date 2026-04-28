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
    public RecurringInvoiceExpectation createNewRecurringInvoiceExpectation(
            UUID vendorId,
            TrackedInvoiceKey trackedInvoiceKey,
            RecurrenceRule recurrenceRule
    ) {
//        1. Verify that the vendorId exists
        Vendor vendor = persistenceGatewayOutputPort.findByVendorId(vendorId)
                .orElseThrow(() -> new VendorDoesNotExistException("Vendor does not exist"));

//        2. Verify that the vendor is active
        if (!vendor.isActive()) {
            throw new InactiveVendorException("Vendor is inactive");
        }

//       3. Verify that a vendor does not have more than one recurring expectation with the same trackedInvoiceKey
        if (persistenceGatewayOutputPort.existsByTrackedInvoiceKeyAndVendorId(trackedInvoiceKey.trackedInvoiceKey(), vendor.getId())) {
            throw new DuplicateTrackedInvoiceKeyException("The vendor already has a tracked invoice key with the same name");
        }

//       4. Generate id for RecurringInvoiceExpectation
        UUID recurringInvoiceExpectationId = idGeneratorOutputPort.generateNewUUID();

//       5. Create RecurringInvoiceExpectation
        RecurringInvoiceExpectation recurringInvoiceExpectation = RecurringInvoiceExpectation.createNew(
                recurringInvoiceExpectationId,
                vendorId,
                trackedInvoiceKey,
                recurrenceRule
        );

        return persistenceGatewayOutputPort.save(recurringInvoiceExpectation);
    }
}
