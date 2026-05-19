package com.jashawncodes.billtrackr.core.ports.in;

import com.jashawncodes.billtrackr.core.useCases.updateVendor.UpdateVendorCommand;
import com.jashawncodes.billtrackr.core.useCases.updateVendor.UpdateVendorResult;

public interface UpdateVendorUseCase {
    UpdateVendorResult updateVendor(UpdateVendorCommand command);
}
