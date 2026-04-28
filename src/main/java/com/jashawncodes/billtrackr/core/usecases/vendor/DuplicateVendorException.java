package com.jashawncodes.billtrackr.core.usecases.vendor;

import com.jashawncodes.billtrackr.core.GenericInvoiceManagementException;

public class DuplicateVendorException extends GenericInvoiceManagementException {
    public DuplicateVendorException(String message) {
        super(message);
    }
}
