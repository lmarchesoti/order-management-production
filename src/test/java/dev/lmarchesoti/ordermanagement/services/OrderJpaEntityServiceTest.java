package dev.lmarchesoti.ordermanagement.services;

import dev.lmarchesoti.ordermanagement.adapter.outbound.persistence.jparepository.OrderJpaRepository;
import dev.lmarchesoti.ordermanagement.domain.service.OrderService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrderJpaEntityServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderJpaRepository orderRepository;

//    @BeforeEach
//    void setup() {
//        orderService = spy(orderService);
//    }
//
//    @Test
//    @DisplayName("Process order should create new orders")
//    void testProcessOrder_whenAnOrderWithTheSameOrderIdDoesNotExist_shouldCreteANewOrder() {
//        when(orderRepository.findByOrderId(anyLong())).thenReturn(Optional.empty());
//
//        var productAOrder = new ProductAOrder(123L, null, null, null, null, null, null, null);
//
//        orderService.processOrder(productAOrder);
//
//        verify(orderService, times(1).description("Should have created a new order")).createOrder(eq(productAOrder));
//        verify(orderService, never().description("Should not have updated order")).updateOrder(any(), any());
//    }
//
//    @Test
//    @DisplayName("Process order should update existing orders")
//    void testProcessOrder_whenAnOrderWithTheSameOrderIdDoesExists_shouldUpdateTheExistingOrder() {
//        OrderJpaEntity order = new OrderJpaEntity();
//        when(orderRepository.findByOrderId(anyLong())).thenReturn(Optional.of(order));
//
//        var productAOrder = new ProductAOrder(123L, null, null, null, null, null, null, null);
//
//        orderService.processOrder(productAOrder);
//
//        verify(orderService, times(1).description("Should have updated the existing order")).updateOrder(eq(order), eq(productAOrder));
//        verify(orderService, never().description("Should not have created a new order")).createOrder(any());
//    }
}