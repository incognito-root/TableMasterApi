package com.tablemasterbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_detail")
public class OrderDetail {
    private @Id
    long orderDetailId;
    private int orderDetailQuantity;
    private long fkMenuItemId;
    private long fkOrderId;

    public OrderDetail(int orderDetailQuantity, long fkMenuItemId, long fkOrderId) {
        this.orderDetailQuantity = orderDetailQuantity;
        this.fkMenuItemId = fkMenuItemId;
        this.fkOrderId = fkOrderId;
    }

    public OrderDetail(int orderDetailQuantity, long fkMenuItemId) {
        this.orderDetailQuantity = orderDetailQuantity;
        this.fkMenuItemId = fkMenuItemId;
    }

    public OrderDetail() {
    }

    public long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(long orderDetailId) {
        this.orderDetailId = orderDetailId;
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

    public long getFkOrderId() {
        return fkOrderId;
    }

    public void setFkOrderId(long fkOrderId) {
        this.fkOrderId = fkOrderId;
    }
}
