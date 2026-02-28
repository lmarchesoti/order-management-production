package dev.lmarchesoti.ordermanagement.application.command;

import dev.lmarchesoti.ordermanagement.domain.model.Order;

public record InternalizeOrderCommand(Order order) {
}
