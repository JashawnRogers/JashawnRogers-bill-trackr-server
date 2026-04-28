package com.jashawncodes.billtrackr.core.ports.out;

import java.util.UUID;

public interface IdGeneratorOutputPort {
    UUID generateNewUUID();
}
