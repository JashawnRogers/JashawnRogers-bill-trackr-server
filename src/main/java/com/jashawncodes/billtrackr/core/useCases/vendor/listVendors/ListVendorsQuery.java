package com.jashawncodes.billtrackr.core.useCases.vendor.listVendors;

import com.jashawncodes.billtrackr.core.useCases.ActiveFilter;
import com.jashawncodes.billtrackr.core.useCases.NormalizeSearchTerm;
import com.jashawncodes.billtrackr.core.useCases.PageRequest;
import com.jashawncodes.billtrackr.core.useCases.SortDirection;


public record ListVendorsQuery(
        SortDirection sort,
        String searchTerm,
        PageRequest pageRequest,
        ActiveFilter filter
) {
    public static ListVendorsQuery of(
            SortDirection sort,
            String searchTerm,
            PageRequest pageRequest,
            ActiveFilter filter
    ) {
        String normalizedSearchTerm = NormalizeSearchTerm.normalize(searchTerm);

        return new ListVendorsQuery(
                sort == null ? SortDirection.DESC : sort,
                normalizedSearchTerm,
                pageRequest,
                filter == null ? ActiveFilter.ALL : filter
        );
    }
}
