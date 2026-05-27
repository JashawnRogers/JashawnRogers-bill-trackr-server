package com.jashawncodes.billtrackr.core.useCases;

public class NormalizeSearchTerm {
    private static final int MAX_SEARCH_LENGTH = 100;

    public static String normalize(String searchTerm) {
        if (searchTerm != null && searchTerm.length() > MAX_SEARCH_LENGTH) {
            throw new IllegalArgumentException("Search term cannot exceeds 100 characters");
        }

        if (searchTerm == null || searchTerm.isBlank()) {
            return null;
        }

        return searchTerm.trim();
    }
}
