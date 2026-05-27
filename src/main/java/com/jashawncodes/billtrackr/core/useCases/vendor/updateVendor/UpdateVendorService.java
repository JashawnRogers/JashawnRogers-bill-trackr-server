package com.jashawncodes.billtrackr.core.useCases.vendor.updateVendor;

import com.jashawncodes.billtrackr.core.NotFoundException;
import com.jashawncodes.billtrackr.core.model.vendor.Vendor;
import com.jashawncodes.billtrackr.core.ports.in.UpdateVendorUseCase;
import com.jashawncodes.billtrackr.core.ports.out.gateways.VendorGatewayOutputPort;
import com.jashawncodes.billtrackr.core.useCases.vendor.createVendor.DuplicateVendorException;

public class UpdateVendorService implements UpdateVendorUseCase {
    private final VendorGatewayOutputPort vendorGateway;

    public UpdateVendorService (VendorGatewayOutputPort vendorGateway) {
        this.vendorGateway = vendorGateway;
    }

    @Override
    public UpdateVendorResult updateVendor(UpdateVendorCommand command) {
        Vendor vendor = vendorGateway.findById(command.vendorId())
                .orElseThrow(() -> new NotFoundException("Vendor not found"));

       command.vendorName().ifPresent(newVendorName -> {
           if (vendorGateway.existsByVendorNameAndNot(newVendorName.name(), command.vendorId())) {
               throw new DuplicateVendorException("A vendor with this name already exists");
           }
       });

        vendor.update(
                command.vendorName(),
                command.paymentTerms(),
                command.active()
        );

        Vendor saved = vendorGateway.save(vendor);

        return UpdateVendorResult.of(
                saved.getId(),
                saved.getVendorName(),
                saved.getPaymentTerms(),
                saved.isActive()
        );
    }
}
