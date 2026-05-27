package com.jashawncodes.billtrackr.core.useCases.invoiceSchedule.createInvoiceSchedule;

import com.jashawncodes.billtrackr.core.GenericInvoiceManagementException;

public class InactiveVendorException extends GenericInvoiceManagementException {
    public InactiveVendorException(String message) {
        super(message);
    }
}
