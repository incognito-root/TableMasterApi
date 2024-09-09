package com.tablemasterbackend.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class AddOrderRequest {
    private String orderDescription;
    private double orderAmount;
    private double orderExtraCharges;
    private long fkCustomerId;
    private ArrayList<OrderDetailRequest> orderDetails;
}
