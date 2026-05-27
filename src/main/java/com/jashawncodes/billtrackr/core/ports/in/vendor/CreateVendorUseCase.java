package com.jashawncodes.billtrackr.core.ports.in.vendor;

import com.jashawncodes.billtrackr.core.model.vendor.PaymentTerms;
import com.jashawncodes.billtrackr.core.model.vendor.Vendor;
import com.jashawncodes.billtrackr.core.model.vendor.VendorName;

public interface CreateVendorUseCase {
    Vendor createNewVendor(VendorName vendorName, PaymentTerms paymentTerms);
}
