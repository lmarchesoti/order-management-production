package dev.lmarchesoti.ordermanagement.application.port.in;

import dev.lmarchesoti.ordermanagement.application.command.InternalizeOrderCommand;

public interface ProcessOrderUseCase {
    void processOrder(InternalizeOrderCommand command);
}
