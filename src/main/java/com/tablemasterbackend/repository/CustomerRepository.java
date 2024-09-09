package com.tablemasterbackend.repository;

import com.tablemasterbackend.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findCustomerByCustomerEmail(String customerEmail);

    Customer findCustomerByCustomerId(Long customerId);

    @Modifying
    @Transactional
    @Query("UPDATE Customer c SET c.customerAddress = :address WHERE c.customerId = :customerId")
    int updateCustomerAddress(@Param("customerId") long customerId, @Param("address") String address);
}
