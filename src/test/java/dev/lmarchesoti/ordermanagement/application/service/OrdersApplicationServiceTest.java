package dev.lmarchesoti.ordermanagement.application.service;

import dev.lmarchesoti.ordermanagement.application.command.InternalizeOrderCommand;
import dev.lmarchesoti.ordermanagement.application.port.out.OrderPersistencePort;
import dev.lmarchesoti.ordermanagement.domain.model.Order;
import dev.lmarchesoti.ordermanagement.domain.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrdersApplicationServiceTest {

    @Mock
    private OrderService orderService;

    @Mock
    private OrderPersistencePort orderPersistencePort;

    @InjectMocks
    private OrdersApplicationService ordersApplicationService;

    @BeforeEach
    void setup() {
        ordersApplicationService = spy(ordersApplicationService);
    }

    @Test
    @DisplayName("Process order should create new orders")
    void testProcessOrder_whenAnOrderWithTheSameOrderIdDoesNotExist_shouldCreteANewOrder() {
        when(orderPersistencePort.orderExists(anyLong())).thenReturn(false);
        doNothing().when(orderService).calculateFields(any(Order.class));

        var order = new Order(123L, null, null, null, null, null, null, null, null, null, null);

        ordersApplicationService.processOrder(new InternalizeOrderCommand(order));

        verify(orderPersistencePort, times(1).description("Should have created a new order")).create(eq(order));
        verify(orderPersistencePort, never().description("Should not have updated order")).updateOrderStatus(any(), any(), any());
    }

    @Test
    @DisplayName("Process order should update existing orders")
    void testProcessOrder_whenAnOrderWithTheSameOrderIdDoesExists_shouldUpdateTheExistingOrder() {
        when(orderPersistencePort.orderExists(anyLong())).thenReturn(true);

        var order = new Order(123L, null, "UPDATED", null, null, null, null, null, null, LocalDateTime.now(), null);

        ordersApplicationService.processOrder(new InternalizeOrderCommand(order));

        verify(orderPersistencePort, times(1).description("Should have updated the existing order"))
                .updateOrderStatus(eq(order.getOrderId()), eq(order.getStatus()), eq(order.getUpdatedAt()));
        verify(orderPersistencePort, never().description("Should not have created a new order")).create(any());
    }

}