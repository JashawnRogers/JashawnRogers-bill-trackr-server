package com.jashawncodes.billtrackr.core.model.expectedinvoice;

import com.jashawncodes.billtrackr.core.model.vendor.PaymentTerms;

import java.time.LocalDate;

public record DueDate(LocalDate occurrenceDate, PaymentTerms paymentTerms) {
   public DueDate {

   }
}