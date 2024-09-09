package com.tablemasterbackend.repository;

import com.tablemasterbackend.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Integer> {
    public Discount findTopByOrderByDiscountIdDesc();
}
