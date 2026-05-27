package com.jashawncodes.billtrackr.core.useCases;

import java.util.List;

public record PageResponse<T>(List<T> content, int page, int size, long count) {
}
