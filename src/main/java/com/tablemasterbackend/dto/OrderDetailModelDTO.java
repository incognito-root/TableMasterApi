package com.tablemasterbackend.dto;

import java.util.Objects;

public class OrderDetailModelDTO {
    private int orderDetailQuantity;
    private long fkMenuItemId;

    public OrderDetailModelDTO(int orderDetailQuantity, long fkMenuItemId) {
        this.orderDetailQuantity = orderDetailQuantity;
        this.fkMenuItemId = fkMenuItemId;
    }

    public OrderDetailModelDTO(long orderDetailId, int orderDetailQuantity, long fkMenuItemId) {
        this.orderDetailQuantity = orderDetailQuantity;
        this.fkMenuItemId = fkMenuItemId;
    }

    public int getOrderDetailQuantity() {
        return orderDetailQuantity;
    }

    public void setOrderDetailQuantity(int orderDetailQuantity) {
        this.orderDetailQuantity = orderDetailQuantity;
    }

    public long getFkMenuItemId() {
        return fkMenuItemId;
    }

    public void setFkMenuItemId(long fkMenuItemId) {
        this.fkMenuItemId = fkMenuItemId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        OrderDetailModelDTO that = (OrderDetailModelDTO) obj;
        return orderDetailQuantity == that.orderDetailQuantity && fkMenuItemId == that.fkMenuItemId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderDetailQuantity, fkMenuItemId);
    }
}
