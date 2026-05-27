package com.jashawncodes.billtrackr.core.useCases.vendor.listVendors;

import com.jashawncodes.billtrackr.core.model.vendor.PaymentTerms;
import com.jashawncodes.billtrackr.core.model.vendor.VendorName;

import java.util.UUID;

public record ListVendorsReadModel(
        UUID vendorId,
        VendorName vendorName,
        PaymentTerms paymentTerms,
        boolean active
) {
}
