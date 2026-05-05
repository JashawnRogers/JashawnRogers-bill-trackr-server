package com.jashawncodes.billtrackr.core.model.expectedinvoice;

import com.jashawncodes.billtrackr.core.GenericInvoiceManagementException;

public class InvalidExpectedReceiveDateException extends GenericInvoiceManagementException {
    public InvalidExpectedReceiveDateException(String message) {
        super(message);
    }
}
