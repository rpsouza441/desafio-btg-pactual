package br.dev.rodrigopinheiro.btgpactual.ordems.controller.dto;

import java.util.List;

public record ApiResponse<T>(
        List<T> data,
        PaginationResponse pagination
) {
}
