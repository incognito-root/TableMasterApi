package com.tablemasterbackend.model.mapper;

import com.tablemasterbackend.dto.OrderModelDTO;
import com.tablemasterbackend.model.Order;

public class OrderModelMapper {
    public Order mapToModel(OrderModelDTO orderModelDTO) {
        return new Order(orderModelDTO.getOrderDescription(), orderModelDTO.getOrderStatus(), orderModelDTO.getOrderPlacedDate(), orderModelDTO.getOrderAmount(), orderModelDTO.getOrderExtraCharges(), orderModelDTO.getFkCustomerId());
    }
}
