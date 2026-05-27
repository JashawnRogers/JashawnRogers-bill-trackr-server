package com.jashawncodes.billtrackr.core.ports.in.vendor;

import com.jashawncodes.billtrackr.core.useCases.PageResponse;
import com.jashawncodes.billtrackr.core.useCases.vendor.listVendors.ListVendorsQuery;
import com.jashawncodes.billtrackr.core.useCases.vendor.listVendors.ListVendorsReadModel;

public interface ListVendorsUseCase {
    PageResponse<ListVendorsReadModel> listVendors(ListVendorsQuery query);
}
