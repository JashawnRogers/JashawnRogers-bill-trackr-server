package com.jashawncodes.billtrackr.core.useCases.expectedInvoice;

import com.jashawncodes.billtrackr.core.useCases.PageRequest;
import com.jashawncodes.billtrackr.core.useCases.SortDirection;

import java.time.YearMonth;

public record ListExpectedInvoicesQuery(
        YearMonth yearMonth,
        ExpectedInvoiceListFilter filter,
        String searchTerm,
        SortDirection sort,
        PageRequest pageRequest
) {
    private static final int MAX_SEARCH_LENGTH = 100;

    public static ListExpectedInvoicesQuery of(
            YearMonth yearMonth,
            ExpectedInvoiceListFilter filter,
            String searchTerm,
            SortDirection sort,
            PageRequest pageRequest
    ) {
        String normalizedSearchTerm = normalizeSearchTerm(searchTerm);

        if (normalizedSearchTerm != null && normalizedSearchTerm.length() > MAX_SEARCH_LENGTH) {
            throw new IllegalArgumentException("Search term cannot exceeds 100 characters");
        }

        return new ListExpectedInvoicesQuery(
                yearMonth == null ? YearMonth.now() : yearMonth,
                filter == null ? ExpectedInvoiceListFilter.ALL : filter,
                normalizedSearchTerm,
                sort == null ?  SortDirection.DESC : sort,
                pageRequest
        );
    }

    private static String normalizeSearchTerm(String search) {
        if (search == null || search.isBlank()) {
            return null;
        }

        return search.trim();
    }
}
