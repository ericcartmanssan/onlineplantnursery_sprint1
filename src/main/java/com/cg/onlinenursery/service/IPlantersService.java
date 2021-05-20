package com.cg.onlinenursery.service;

import java.util.List;

import com.cg.onlinenursery.entity.Planters;
import com.cg.onlinenursery.entity.Plants;
import com.cg.onlinenursery.entity.Seed;

public interface IPlantersService {

	public Planters addPlanters(Planters planters);

	public Planters updatePlanters(Planters planters);

	public Planters deletePlanters(int plantersId);

	public Planters viewPlanters(int plantersId);

	public List<Planters> viewPlanters(String plantersShape);

	public List<Planters> viewAllPlanters();

	public List<Planters> viewAllPlanters(double minCost, double maxCost);

	public List<Plants> viewPlants(int planterId);

	public List<Seed> viewSeeds(int planterId);
}

//List<Planters>