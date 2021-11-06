package com.hashkart.orderManagement.repo;

import com.hashkart.orderManagement.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<OrderDetails, String> {
}
