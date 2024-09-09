package com.tablemasterbackend.controller.response;

import com.tablemasterbackend.dto.DiscountModelDTO;
import com.tablemasterbackend.model.Order;

import java.util.List;

public class CustomerDashboardResponse {
    private Integer totalOrders;
    private DiscountModelDTO ongoingDiscount;
    private String address;
    private MenuItemResponse mostOrderedMenuItem;
    private List<Order> orders;

    public Integer getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(Integer totalOrders) {
        this.totalOrders = totalOrders;
    }

    public DiscountModelDTO getOngoingDiscount() {
        return ongoingDiscount;
    }

    public void setOngoingDiscount(DiscountModelDTO ongoingDiscount) {
        this.ongoingDiscount = ongoingDiscount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public MenuItemResponse getMostOrderedMenuItem() {
        return mostOrderedMenuItem;
    }

    public void setMostOrderedMenuItem(MenuItemResponse mostOrderedMenuItem) {
        this.mostOrderedMenuItem = mostOrderedMenuItem;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
