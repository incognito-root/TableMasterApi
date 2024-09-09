package com.tablemasterbackend.controller.response;

import com.tablemasterbackend.dto.DiscountModelDTO;
import com.tablemasterbackend.model.Order;

import java.util.List;

public class AdminDashboardResponse {
    private long totalOrders;
    private List<Order> orders;
    private double revenue;
    private long totalCustomers;

    public AdminDashboardResponse(Integer totalOrders, List<Order> orders, double revenue, Integer totalCustomers) {
        this.totalOrders = totalOrders;
        this.orders = orders;
        this.revenue = revenue;
        this.totalCustomers = totalCustomers;
    }

    public AdminDashboardResponse() {
    }

    public long getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(long totalOrders) {
        this.totalOrders = totalOrders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public long getTotalCustomers() {
        return totalCustomers;
    }

    public void setTotalCustomers(long totalCustomers) {
        this.totalCustomers = totalCustomers;
    }
}
