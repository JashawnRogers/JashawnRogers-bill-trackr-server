package com.jashawncodes.billtrackr.core.useCases.generateExpectedInvoicesForMonth;

import com.jashawncodes.billtrackr.core.GenericInvoiceManagementException;

public class InvalidPaymentTermsException extends GenericInvoiceManagementException {
    public InvalidPaymentTermsException(String message) {
        super(message);
    }
}
