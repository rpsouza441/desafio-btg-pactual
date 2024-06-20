package br.dev.rodrigopinheiro.btgpactual.ordems.listener.dto;

import java.math.BigDecimal;

public record OrderItemEvent(
        String produto,
        Integer quantidade,
        BigDecimal preco
) {
}
