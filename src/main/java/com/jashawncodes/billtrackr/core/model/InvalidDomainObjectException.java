package com.jashawncodes.billtrackr.core.model;

import com.jashawncodes.billtrackr.core.GenericInvoiceManagementException;

public class InvalidDomainObjectException extends GenericInvoiceManagementException {
    public InvalidDomainObjectException(String message) {
        super(message);
    }
}
