package br.dev.rodrigopinheiro.btgpactual.ordems.controller;

import br.dev.rodrigopinheiro.btgpactual.ordems.controller.dto.ApiResponse;
import br.dev.rodrigopinheiro.btgpactual.ordems.controller.dto.OrderResponse;
import br.dev.rodrigopinheiro.btgpactual.ordems.controller.dto.PaginationResponse;
import br.dev.rodrigopinheiro.btgpactual.ordems.service.OrderService;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class OrderController {

    public final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/customers/{customerId}/orders")
    public ResponseEntity<ApiResponse<OrderResponse>> getOrders(@PathVariable("customerId") Long customerId,
                                                         @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                         @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {

        var pageResponse = orderService.findAllByCustomerId(customerId, PageRequest.of(page, pageSize));
        var totalOnOrders = orderService.findTotalOnOrdersByCustomerId(customerId);

        return  ResponseEntity.ok(new ApiResponse<>(
                Map.of("TotalOnOrders", totalOnOrders),
                pageResponse.getContent(),
                PaginationResponse.fromPage(pageResponse)
        ));

    }
}
