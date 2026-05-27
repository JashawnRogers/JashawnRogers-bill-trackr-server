package com.jashawncodes.billtrackr.core.useCases.invoiceSchedule.createInvoiceSchedule;

import com.jashawncodes.billtrackr.core.GenericInvoiceManagementException;

public class VendorDoesNotExistException extends GenericInvoiceManagementException {
    public VendorDoesNotExistException(String message) {
        super(message);
    }
}
