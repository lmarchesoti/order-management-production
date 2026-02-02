package dev.lmarchesoti.order_management_test.services;

import dev.lmarchesoti.order_management_test.entities.Order;
import dev.lmarchesoti.order_management_test.external.producta.dto.ProductAOrder;
import dev.lmarchesoti.order_management_test.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @BeforeEach
    void setup() {
        orderService = spy(orderService);
    }

    @Test
    @DisplayName("Process order should create new orders")
    void testProcessOrder_whenAnOrderWithTheSameOrderIdDoesNotExist_shouldCreteANewOrder() {
        when(orderRepository.findByOrderId(anyLong())).thenReturn(Optional.empty());

        var productAOrder = new ProductAOrder(123L, null, null, null, null, null, null, null);

        orderService.processOrder(productAOrder);

        verify(orderService, times(1).description("Should have created a new order")).createOrder(eq(productAOrder));
        verify(orderService, never().description("Should not have updated order")).updateOrder(any(), any());
    }

    @Test
    @DisplayName("Process order should update existing orders")
    void testProcessOrder_whenAnOrderWithTheSameOrderIdDoesExists_shouldUpdateTheExistingOrder() {
        Order order = new Order();
        when(orderRepository.findByOrderId(anyLong())).thenReturn(Optional.of(order));

        var productAOrder = new ProductAOrder(123L, null, null, null, null, null, null, null);

        orderService.processOrder(productAOrder);

        verify(orderService, times(1).description("Should have updated the existing order")).updateOrder(eq(order), eq(productAOrder));
        verify(orderService, never().description("Should not have created a new order")).createOrder(any());
    }
}