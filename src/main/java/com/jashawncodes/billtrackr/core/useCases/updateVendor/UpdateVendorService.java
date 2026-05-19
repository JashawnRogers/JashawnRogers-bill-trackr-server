package com.jashawncodes.billtrackr.core.useCases.updateVendor;

import com.jashawncodes.billtrackr.core.model.vendor.Vendor;
import com.jashawncodes.billtrackr.core.ports.in.UpdateVendorUseCase;
import com.jashawncodes.billtrackr.core.ports.out.gateways.VendorGatewayOutputPort;

public class UpdateVendorService implements UpdateVendorUseCase {
    private final VendorGatewayOutputPort vendorGateway;

    public UpdateVendorService (VendorGatewayOutputPort vendorGateway) {
        this.vendorGateway = vendorGateway;
    }

    @Override
    public UpdateVendorResult updateVendor(UpdateVendorCommand command) {
        Vendor vendor = vendorGateway.findById(command.vendorId());



    }
}
