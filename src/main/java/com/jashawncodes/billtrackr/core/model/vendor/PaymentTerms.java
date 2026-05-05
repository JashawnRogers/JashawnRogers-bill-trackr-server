package com.jashawncodes.billtrackr.core.model.vendor;

public enum PaymentTerms {
    NET_30(30),
    NET_25(25),
    NET_20(20),
    NET_15(15),
    NET_10(10),
    NET_7(7),
    NET_5(5),
    NET_0(0);

    final int convertedToInt;

    PaymentTerms(int convertedToInt) {
        this.convertedToInt = convertedToInt;
    }

    public int days() {
        return this.convertedToInt;
    }
}
