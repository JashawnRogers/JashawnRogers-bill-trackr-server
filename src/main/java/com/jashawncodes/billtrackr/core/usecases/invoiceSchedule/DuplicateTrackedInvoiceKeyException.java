package com.jashawncodes.billtrackr.core.usecases.invoiceSchedule;

import com.jashawncodes.billtrackr.core.GenericInvoiceManagementException;

public class DuplicateTrackedInvoiceKeyException extends GenericInvoiceManagementException {
    public DuplicateTrackedInvoiceKeyException(String message) {
        super(message);
    }
}
