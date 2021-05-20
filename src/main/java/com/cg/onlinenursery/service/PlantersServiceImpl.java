package com.cg.onlinenursery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlinenursery.entity.Planters;
import com.cg.onlinenursery.entity.Plants;
import com.cg.onlinenursery.entity.Seed;
import com.cg.onlinenursery.repository.PlantersRepository;
import com.cg.onlinenursery.repository.SeedRepository;

@Service
public class PlantersServiceImpl implements IPlantersService {

	@Autowired
	private PlantersRepository plantersRepository;

	@Autowired
	private SeedRepository seedRepository;

	public List<Planters> viewPlanters(String plantersShape) {
		List<Planters> planters = plantersRepository.planterShape(plantersShape);
		return planters;
	}

	public List<Planters> viewAllPlanters() {
		List<Planters> plantersList = plantersRepository.findAll();
		return plantersList;
	}

	public Planters viewPlanters(int plantersId) {
		Optional<Planters> optional = plantersRepository.findById(plantersId);
		Planters planters = null;
		if (optional.isPresent()) {
			planters = optional.get();
		}
		return planters;

	}

	public List<Seed> viewSeeds(int plantersId) {
		Planters planters = viewPlanters(plantersId);
		System.out.println(planters + "*****");
		List<Seed> seeds = null;
		if (planters != null) {
			seeds = planters.getSeeds();
		}
		return seeds;
	}

	// @Override
	public List<Plants> viewPlants(int plantersId) {
		Planters planters = viewPlanters(plantersId);
		System.out.println(planters + "*****");
		List<Plants> plants = null;
		if (planters != null) {
			plants = planters.getPlants();
		}
		return plants;
	}

	@Override

	public Planters addPlanters(Planters planters) {
		Planters plantersexist = viewPlanters(planters.getPlanterId());
		if (plantersexist == null) {
			planters = plantersRepository.save(planters);
		}
		return planters;
	}

	public Planters updatePlanters(Planters planters) {
		Planters plantersexist = viewPlanters(planters.getPlanterId());
		if (plantersexist != null) {
			planters = plantersRepository.save(planters);
		}
		return planters;
	}

	public Planters deletePlanters(int plantersId) {
		Planters planters = viewPlanters(plantersId);
		if (planters != null)
			plantersRepository.deleteById(plantersId);
		return planters;
	}

	public List<Planters> viewAllPlanters(double minCost, double maxCost) {
		List<Planters> plantersList = plantersRepository.findAll();
		return plantersList;
	}
}
