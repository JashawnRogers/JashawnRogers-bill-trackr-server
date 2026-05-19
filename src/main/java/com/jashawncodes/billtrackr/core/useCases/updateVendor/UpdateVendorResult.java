package com.jashawncodes.billtrackr.core.useCases.updateVendor;

import com.jashawncodes.billtrackr.core.model.vendor.PaymentTerms;

import java.util.UUID;

public record UpdateVendorResult(
        UUID vendorId,
        String vendorName,
        PaymentTerms paymentTerms,
        boolean active
) {
    public static UpdateVendorResult of (
            UUID vendorId,
            String vendorName,
            PaymentTerms paymentTerms,
            boolean active
    ) {
        return new UpdateVendorResult(
                vendorId,
                vendorName,
                paymentTerms,
                active
        );
    }
}
