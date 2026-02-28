package dev.lmarchesoti.ordermanagement.adapter.inbound.web.controller;

import dev.lmarchesoti.ordermanagement.adapter.inbound.web.dto.OrderDto;
import dev.lmarchesoti.ordermanagement.adapter.inbound.web.mapper.OrderDtoMapper;
import dev.lmarchesoti.ordermanagement.adapter.outbound.persistence.entity.OrderJpaEntity;
import dev.lmarchesoti.ordermanagement.application.port.in.ListOrdersUseCase;
import dev.lmarchesoti.ordermanagement.application.query.ListOrdersQuery;
import dev.lmarchesoti.ordermanagement.domain.model.Order;
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

    private final ListOrdersUseCase listOrdersUseCase;

    private final OrderDtoMapper orderDtoMapper;

    @GetMapping
    public ResponseEntity<Page<OrderDto>> listOrders(@RequestParam(required = false) Long customerId,
                                                     @RequestParam(required = false) LocalDateTime startCreatedAt,
                                                     @RequestParam(required = false) LocalDateTime endCreatedAt,
                                                     @RequestParam(defaultValue = "0") Integer page,
                                                     @RequestParam(defaultValue = "20") Integer pageSize) {

        Pageable pageRequest = PageRequest.of(page, pageSize);
        ListOrdersQuery listOrdersQuery = new ListOrdersQuery(customerId, startCreatedAt, endCreatedAt, pageRequest);

        Page<Order> orders = listOrdersUseCase.listOrders(listOrdersQuery);

        return ResponseEntity.ok(orders.map(orderDtoMapper::toDto));
    }
}
