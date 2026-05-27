package com.jashawncodes.billtrackr.core.ports.in.vendor;

import com.jashawncodes.billtrackr.core.useCases.vendor.updateVendor.UpdateVendorCommand;
import com.jashawncodes.billtrackr.core.useCases.vendor.updateVendor.UpdateVendorResult;

public interface UpdateVendorUseCase {
    UpdateVendorResult updateVendor(UpdateVendorCommand command);
}
