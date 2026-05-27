package com.jashawncodes.billtrackr.core.useCases.expectedInvoice.generateExpectedInvoicesForMonth;

import com.jashawncodes.billtrackr.core.GenericInvoiceManagementException;

public class InvalidExpectedReceiveDateException extends GenericInvoiceManagementException {
    public InvalidExpectedReceiveDateException(String message) {
        super(message);
    }
}
