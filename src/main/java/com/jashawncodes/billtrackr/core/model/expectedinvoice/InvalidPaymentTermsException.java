package com.jashawncodes.billtrackr.core.model.expectedinvoice;

import com.jashawncodes.billtrackr.core.GenericInvoiceManagementException;

public class InvalidPaymentTermsException extends GenericInvoiceManagementException {
    public InvalidPaymentTermsException(String message) {
        super(message);
    }
}
