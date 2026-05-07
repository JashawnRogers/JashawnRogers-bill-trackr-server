package com.jashawncodes.billtrackr.core.useCases.createVendor;

import com.jashawncodes.billtrackr.core.model.vendor.PaymentTerms;
import com.jashawncodes.billtrackr.core.model.vendor.Vendor;
import com.jashawncodes.billtrackr.core.model.vendor.VendorName;
import com.jashawncodes.billtrackr.core.ports.in.CreateVendorUseCase;
import com.jashawncodes.billtrackr.core.ports.out.IdGeneratorOutputPort;
import com.jashawncodes.billtrackr.core.ports.out.PersistenceGatewayOutputPort;

import java.util.UUID;

public class CreateVendorService implements CreateVendorUseCase {

    private final PersistenceGatewayOutputPort persistenceGateway;
    private final IdGeneratorOutputPort idGeneratorOutputPort;

    public CreateVendorService(PersistenceGatewayOutputPort persistenceGateway, IdGeneratorOutputPort idGeneratorOutputPort) {
        this.persistenceGateway = persistenceGateway;
        this.idGeneratorOutputPort = idGeneratorOutputPort;
    }

    @Override
    public Vendor createNewVendor(VendorName vendorName, PaymentTerms paymentTerms) {
        String normalizedVendorName = vendorName.name();
        if (persistenceGateway.existsByVendorName(normalizedVendorName)) {
            throw new DuplicateVendorException("A vendor with this name already exists");
        }

        UUID vendorId = idGeneratorOutputPort.generateNewUUID();
        Vendor vendor =  Vendor.createNew(
                vendorId,
                vendorName,
                paymentTerms
        );

        return persistenceGateway.save(vendor);
    }
}
