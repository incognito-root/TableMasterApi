package com.tablemasterbackend.repository;

import com.tablemasterbackend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    public List<Order> findByFkCustomerId(Long userId);

    public int countByFkCustomerId(Long userId);

    @Query("SELECT SUM(o.orderAmount) FROM Order o")
    public double sumAmount();
}
