package com.jashawncodes.billtrackr.core.useCases.invoiceSchedule.listInvoiceSchedules;

import com.jashawncodes.billtrackr.core.useCases.ActiveFilter;
import com.jashawncodes.billtrackr.core.useCases.NormalizeSearchTerm;
import com.jashawncodes.billtrackr.core.useCases.PageRequest;
import com.jashawncodes.billtrackr.core.useCases.SortDirection;

public record ListInvoiceScheduleQuery(
        String searchTerm,
        ActiveFilter filter,
        SortDirection sort,
        PageRequest pageRequest
) {
    public static ListInvoiceScheduleQuery of(
            String searchTerm,
            ActiveFilter filter,
            SortDirection sort,
            PageRequest pageRequest
    ) {
        String normalizedSearchTerm = NormalizeSearchTerm.normalize(searchTerm);

        return new ListInvoiceScheduleQuery(
                normalizedSearchTerm,
                filter == null ? ActiveFilter.ALL : filter,
                sort == null ? SortDirection.DESC : sort,
                pageRequest
        );
    }
}
