package com.icaballero.orderapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.icaballero.orderapi.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
