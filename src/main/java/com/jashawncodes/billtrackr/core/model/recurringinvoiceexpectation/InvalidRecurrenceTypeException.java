package com.jashawncodes.billtrackr.core.model.recurringinvoiceexpectation;

import com.jashawncodes.billtrackr.core.GenericInvoiceManagementException;

public class InvalidRecurrenceTypeException extends GenericInvoiceManagementException {
    public InvalidRecurrenceTypeException(String message) {
        super(message);
    }
}
