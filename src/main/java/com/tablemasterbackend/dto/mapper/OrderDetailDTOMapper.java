package com.tablemasterbackend.dto.mapper;

import com.tablemasterbackend.controller.request.OrderDetailRequest;
import com.tablemasterbackend.dto.OrderDetailModelDTO;
import com.tablemasterbackend.model.OrderDetail;

public class OrderDetailDTOMapper {
    public OrderDetailModelDTO mapToDto(OrderDetailRequest orderDetail) {
        return new OrderDetailModelDTO(orderDetail.getOrderDetailQuantity(), orderDetail.getFkMenuItemId());
    }
}
