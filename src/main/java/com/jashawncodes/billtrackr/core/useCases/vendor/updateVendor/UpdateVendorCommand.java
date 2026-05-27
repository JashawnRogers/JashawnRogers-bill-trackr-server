package com.jashawncodes.billtrackr.core.useCases.vendor.updateVendor;

import com.jashawncodes.billtrackr.core.model.vendor.PaymentTerms;
import com.jashawncodes.billtrackr.core.model.vendor.VendorName;

import java.util.Optional;
import java.util.UUID;

public record UpdateVendorCommand(
        UUID vendorId,
        Optional<VendorName> vendorName,
        Optional<PaymentTerms> paymentTerms,
        Optional<Boolean> active
) {}
