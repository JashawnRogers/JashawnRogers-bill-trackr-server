package com.jashawncodes.billtrackr.core.useCases.createVendor;

import com.jashawncodes.billtrackr.core.GenericInvoiceManagementException;

public class DuplicateVendorException extends GenericInvoiceManagementException {
    public DuplicateVendorException(String message) {
        super(message);
    }
}
