package com.tablemasterbackend.repository;

import com.tablemasterbackend.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    public List<OrderDetail> findByFkOrderId(Long orderId);
}
