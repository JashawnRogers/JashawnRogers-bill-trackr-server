package com.jashawncodes.billtrackr.core.useCases.createVendor;

import com.jashawncodes.billtrackr.core.model.vendor.PaymentTerms;
import com.jashawncodes.billtrackr.core.model.vendor.Vendor;
import com.jashawncodes.billtrackr.core.model.vendor.VendorName;
import com.jashawncodes.billtrackr.core.ports.in.CreateVendorUseCase;
import com.jashawncodes.billtrackr.core.ports.out.IdGeneratorOutputPort;
import com.jashawncodes.billtrackr.core.ports.out.gateways.VendorGatewayOutputPort;

import java.util.UUID;

public class CreateVendorService implements CreateVendorUseCase {

    private final VendorGatewayOutputPort vendorGateway;
    private final IdGeneratorOutputPort idGeneratorOutputPort;

    public CreateVendorService(VendorGatewayOutputPort vendorGateway, IdGeneratorOutputPort idGeneratorOutputPort) {
        this.vendorGateway = vendorGateway;
        this.idGeneratorOutputPort = idGeneratorOutputPort;
    }

    @Override
    public Vendor createNewVendor(VendorName vendorName, PaymentTerms paymentTerms) {
        String normalizedVendorName = vendorName.name();
        if (vendorGateway.existsByVendorName(normalizedVendorName)) {
            throw new DuplicateVendorException("A vendor with this name already exists");
        }

        UUID vendorId = idGeneratorOutputPort.generateNewUUID();
        Vendor vendor =  Vendor.createNew(
                vendorId,
                vendorName,
                paymentTerms
        );

        return vendorGateway.save(vendor);
    }
}
