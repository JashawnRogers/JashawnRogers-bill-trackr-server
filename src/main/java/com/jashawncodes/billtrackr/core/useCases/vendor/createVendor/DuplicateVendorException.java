package com.jashawncodes.billtrackr.core.useCases.vendor.createVendor;

import com.jashawncodes.billtrackr.core.GenericInvoiceManagementException;

public class DuplicateVendorException extends GenericInvoiceManagementException {
    public DuplicateVendorException(String message) {
        super(message);
    }
}
