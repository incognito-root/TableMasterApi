package com.tablemasterbackend.dto.mapper;

import com.tablemasterbackend.controller.request.AddOrderRequest;
import com.tablemasterbackend.controller.request.OrderDetailRequest;
import com.tablemasterbackend.dto.OrderDetailModelDTO;
import com.tablemasterbackend.dto.OrderModelDTO;

import java.util.ArrayList;

public class OrderModelDTOMapper {
    public OrderModelDTO mapToDTO(AddOrderRequest order) {

        ArrayList<OrderDetailModelDTO> orderDetails = new ArrayList<>();

        for (OrderDetailRequest orderDetail : order.getOrderDetails()) {
            OrderDetailModelDTO orderDetailToAdd = new OrderDetailDTOMapper().mapToDto(orderDetail);
            orderDetails.add(orderDetailToAdd);
        }

        return new OrderModelDTO(order.getOrderDescription(), order.getOrderAmount(), order.getOrderExtraCharges(), order.getFkCustomerId(), orderDetails);
    }
}
