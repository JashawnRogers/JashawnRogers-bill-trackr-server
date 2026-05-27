package com.jashawncodes.billtrackr.core.useCases;

public final class PageRequestValidator {
    private static final int MAX_PAGE_SIZE = 100;

    private PageRequestValidator() {}

    public static void validate(PageRequest pageRequest) {
        if (pageRequest.page() < 1 || pageRequest.size() < 1) {
            throw new IllegalArgumentException("Page and size parameters must be greater than zero");
        }

        if (pageRequest.size() > MAX_PAGE_SIZE) {
            throw new IllegalArgumentException("Max page size exceeded");
        }
    }
}
