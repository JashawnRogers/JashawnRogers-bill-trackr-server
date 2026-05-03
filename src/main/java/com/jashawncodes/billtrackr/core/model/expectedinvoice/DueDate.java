package com.jashawncodes.billtrackr.core.model.expectedinvoice;

import com.jashawncodes.billtrackr.core.model.vendor.PaymentTerms;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record DueDate(LocalDate expectedReceiveDate, PaymentTerms paymentTerms) {

//   Returns due date based on expectedReceiveDate + paymentTerms
  public static LocalDate of(LocalDate occurrenceDate, PaymentTerms paymentTerms) {
     Pattern regexPattern = Pattern.compile("\\d+");
     Matcher regexMatcher = regexPattern.matcher(paymentTerms.name());
     int numOfDays;

     if (regexMatcher.find()) {
        String numOfDaysString = regexMatcher.group();
        numOfDays = Integer.parseInt(numOfDaysString);
     } else {
        throw new InvalidPaymentTermsException("Invalid payment term");
     }

     return occurrenceDate.plusDays(numOfDays);
  }
}