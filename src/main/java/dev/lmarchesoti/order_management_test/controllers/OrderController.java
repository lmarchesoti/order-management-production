package dev.lmarchesoti.order_management_test.controllers;

import dev.lmarchesoti.order_management_test.dto.OrderDto;
import dev.lmarchesoti.order_management_test.entities.Order;
import dev.lmarchesoti.order_management_test.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<Page<OrderDto>> listOrders(@RequestParam(required = false) Long customerId,
                                                     @RequestParam(required = false) LocalDateTime startCreatedAt,
                                                     @RequestParam(required = false) LocalDateTime endCreatedAt,
                                                     @RequestParam(defaultValue = "0") Integer page,
                                                     @RequestParam(defaultValue = "20") Integer pageSize) {
        Pageable pageRequest = PageRequest.of(page, pageSize);
        Page<Order> orders = orderService.getOrders(customerId, startCreatedAt, endCreatedAt, pageRequest);

        return ResponseEntity.ok(orders.map(OrderDto::fromOrder));
    }
}
