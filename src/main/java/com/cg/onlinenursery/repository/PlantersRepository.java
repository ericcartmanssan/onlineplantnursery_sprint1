package com.cg.onlinenursery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.onlinenursery.entity.Planters;

@Repository
public interface PlantersRepository extends JpaRepository<Planters, Integer> {

	public List<Planters> planterShape(String plantersShape);

}
