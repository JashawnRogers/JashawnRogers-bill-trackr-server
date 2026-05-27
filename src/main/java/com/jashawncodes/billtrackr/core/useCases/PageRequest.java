package com.jashawncodes.billtrackr.core.useCases;

public record PageRequest(int page, int size) {
    public int offset() {
        return (page - 1) * size;
    }
}
