package com.cg.onlinenursery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.onlinenursery.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
