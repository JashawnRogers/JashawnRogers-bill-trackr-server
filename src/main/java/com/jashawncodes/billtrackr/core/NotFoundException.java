package com.jashawncodes.billtrackr.core;

public class NotFoundException extends GenericInvoiceManagementException {
    public NotFoundException(String message) {
        super(message);
    }
}
