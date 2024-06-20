package br.dev.rodrigopinheiro.btgpactual.ordems.service;

import br.dev.rodrigopinheiro.btgpactual.ordems.entity.OrderEntity;
import br.dev.rodrigopinheiro.btgpactual.ordems.entity.OrderItem;
import br.dev.rodrigopinheiro.btgpactual.ordems.listener.dto.OrderCreatedEvent;
import br.dev.rodrigopinheiro.btgpactual.ordems.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void save(OrderCreatedEvent event) {

        var entity = new OrderEntity();
        entity.setOrderId(event.codigoPedido());
        entity.setCustomerId(event.codigoCliente());
        entity.setOrderItems(getOrderItems(event));
        entity.setTotal(getTotal(event));
        orderRepository.save(entity);
    }

    private BigDecimal getTotal(OrderCreatedEvent event) {
        return event.itens()
                .stream()
                .map( i->
                        i.preco().multiply(BigDecimal.valueOf(i.quantidade())))
                        .reduce(BigDecimal::add)
                        .orElse(BigDecimal.ZERO);
    }

    private static List<OrderItem> getOrderItems(OrderCreatedEvent event) {
        return event.itens().stream().map(
                i -> new OrderItem(i.produto(), i.quantidade(), i.preco())).toList();
    }
}
