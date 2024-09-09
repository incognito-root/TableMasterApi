package com.tablemasterbackend.model.mapper;

import com.tablemasterbackend.dto.OrderDetailModelDTO;
import com.tablemasterbackend.model.OrderDetail;

public class OrderDetailModelMapper {
    public OrderDetail mapToModel(OrderDetailModelDTO dto) {
        return new OrderDetail(dto.getOrderDetailQuantity(), dto.getFkMenuItemId());
    }
}
