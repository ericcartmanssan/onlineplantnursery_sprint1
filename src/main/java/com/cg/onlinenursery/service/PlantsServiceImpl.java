package com.cg.onlinenursery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlinenursery.entity.Plants;
import com.cg.onlinenursery.repository.PlantsRepository;

@Service
public class PlantsServiceImpl {
	@Autowired
	private PlantsRepository plantsRepository;

	public List<Plants> viewPlants(String commonName) {
		List<Plants> plantsList = plantsRepository.findAll();
		return plantsList;
	}

	public List<Plants> viewAllPlants() {
		List<Plants> plantsList = plantsRepository.findAll();
		return plantsList;
	}

	public List<Plants> viewAllPlants(String typeOfPlants) {
		List<Plants> plantsList = plantsRepository.findAll();
		return plantsList;
	}

	public List<Plants> viewPlants() {
		List<Plants> plantsList = plantsRepository.findAll();
		return plantsList;
	}

	public Plants addPlants(Plants plants) {
		Plants plantsexist = viewPlants(plants.getPlantId());
		if (plantsexist == null) {
			plants = plantsRepository.save(plants);
		}
		return plants;
	}

	public Plants updatePlants(Plants plants) {
		Plants plantsexist = viewPlants(plants.getPlantId());
		if (plantsexist != null) {
			plants = plantsRepository.save(plants);
		}
		return plants;
	}

	public Plants deletePlants(int plantsId) {
		Plants plants = viewPlants(plantsId);
		if (plants != null)
			plantsRepository.deleteById(plantsId);
		return plants;
	}

	public Plants viewPlants(int plantsId) {
		Optional<Plants> optional = plantsRepository.findById(plantsId);
		Plants plants = null;
		if (optional.isPresent()) {
			plants = optional.get();
		}
		return plants;
	}

}