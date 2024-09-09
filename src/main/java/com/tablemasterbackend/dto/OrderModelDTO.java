package com.tablemasterbackend.dto;

import com.tablemasterbackend.controller.request.AddOrderRequest;
import com.tablemasterbackend.controller.request.OrderDetailRequest;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class OrderModelDTO {
    private long orderId;
    private String orderDescription;
    private String orderStatus;
    private LocalDate orderPlacedDate;
    private double orderAmount;
    private double orderExtraCharges;
    private long fkCustomerId;
    private ArrayList<OrderDetailModelDTO> orderDetails;

    public OrderModelDTO(String orderDescription, double orderAmount, double orderExtraCharges, long fkCustomerId, ArrayList<OrderDetailModelDTO> orderDetails) {
        this.orderDescription = orderDescription;
        this.orderAmount = orderAmount;
        this.orderExtraCharges = orderExtraCharges;
        this.fkCustomerId = fkCustomerId;
        this.orderDetails = orderDetails;

        setOrderStatus();
        setOrderPlacedDate();
    }

    public OrderModelDTO(long orderId, String orderDescription, String orderStatus, LocalDate orderPlacedDate, double orderAmount, double orderExtraCharges, long fkCustomerId) {
        this.orderId = orderId;
        this.orderDescription = orderDescription;
        this.orderStatus = orderStatus;
        this.orderPlacedDate = orderPlacedDate;
        this.orderAmount = orderAmount;
        this.orderExtraCharges = orderExtraCharges;
        this.fkCustomerId = fkCustomerId;
        this.orderDetails = new ArrayList<>();
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus() {
        this.orderStatus = "placed";
    }

    public LocalDate getOrderPlacedDate() {
        return orderPlacedDate;
    }

    public void setOrderPlacedDate() {
        this.orderPlacedDate = LocalDate.now();
    }

    public double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public double getOrderExtraCharges() {
        return orderExtraCharges;
    }

    public void setOrderExtraCharges(double orderExtraCharges) {
        this.orderExtraCharges = orderExtraCharges;
    }

    public long getFkCustomerId() {
        return fkCustomerId;
    }

    public void setFkCustomerId(long fkCustomerId) {
        this.fkCustomerId = fkCustomerId;
    }

    public ArrayList<OrderDetailModelDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(ArrayList<OrderDetailModelDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "desc: " + getOrderDescription() +
                "placed date: " + getOrderPlacedDate() +
                "status: " + getOrderStatus() +
                "amount: " + getOrderAmount() +
                "extra: " + getOrderExtraCharges() +
                "fk customer: " + getFkCustomerId();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        OrderModelDTO that = (OrderModelDTO) obj;
        return Objects.equals(orderDetails, that.orderDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderDetails);
    }
}
