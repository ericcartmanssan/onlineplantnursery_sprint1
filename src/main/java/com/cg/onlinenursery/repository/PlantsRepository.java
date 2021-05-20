package com.cg.onlinenursery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.onlinenursery.entity.Plants;

@Repository
public interface PlantsRepository extends JpaRepository<Plants, Integer> {

}
