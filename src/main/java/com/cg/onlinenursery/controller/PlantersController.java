package com.cg.onlinenursery.controller;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cg.onlinenursery.entity.Planters;
import com.cg.onlinenursery.entity.Plants;
import com.cg.onlinenursery.entity.Seed;
import com.cg.onlinenursery.exception.PlantersIdNotFoundException;
import com.cg.onlinenursery.exception.PlantersShapeNotFoundException;
import com.cg.onlinenursery.exception.PlantsIdNotFoundException;
import com.cg.onlinenursery.exception.SeedsIdNotFoundException;
import com.cg.onlinenursery.service.IPlantersService;

@RestController
@RequestMapping("/planters")
public class PlantersController {

	@Autowired
	private IPlantersService plantersService;

	Logger logger = LoggerFactory.getLogger(PlantersController.class);

	@GetMapping
	public ResponseEntity<List<Planters>> getPlanters() {
		logger.info("Inside getPlanters method");

		List<Planters> plantersList = plantersService.viewAllPlanters();
		logger.info("viewAll Planters" + plantersList);
		ResponseEntity<List<Planters>> response = new ResponseEntity<>(plantersList, HttpStatus.NOT_FOUND);

		if (!plantersList.isEmpty()) {
			response = new ResponseEntity<>(plantersList, HttpStatus.OK);
		}

		return response;
	}

	@GetMapping("/{plantersId}")
	public ResponseEntity<Planters> viewPlanters(@PathVariable("plantersId") int planterId)
			throws PlantersIdNotFoundException {
		logger.info("Inside viewPlanters method");

		Planters planters = plantersService.viewPlanters(planterId);
		logger.info("View Planters" + planters);
		ResponseEntity<String> response = ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body("Planters" + planterId + "Not found");
		if (planters == null) {
			throw new PlantersIdNotFoundException("PlantersId Not found ");
		}

		return new ResponseEntity<>(planters, HttpStatus.ACCEPTED);
	}

	@GetMapping("/plants/{plantersId}")
	public ResponseEntity<List<Plants>> viewPlants(@PathVariable("plantersId") int planterId)
			throws PlantsIdNotFoundException {
		logger.info("Inside viewPlanters method" + planterId);
		List<Plants> plants = plantersService.viewPlants(planterId);
		logger.info("View Plants" + plants);

		if (plants == null) {
			throw new PlantsIdNotFoundException("PlantsId Not found ");
		}

		return new ResponseEntity<>(plants, HttpStatus.ACCEPTED);
	}

	@GetMapping("/seeds/{plantersId}")
	public ResponseEntity<List<Seed>> viewSeed(@PathVariable("plantersId") int planterId)
			throws SeedsIdNotFoundException {
		logger.info("Inside viewPlanters method");
		List<Seed> seeds = plantersService.viewSeeds(planterId);
		logger.info("View Seeds" + seeds);

		if (seeds == null) {
			throw new SeedsIdNotFoundException("PlantsId Not found ");
		}

		return new ResponseEntity<>(seeds, HttpStatus.ACCEPTED);
	}

	@GetMapping("/planterShape/{planterShape}")
	public ResponseEntity<List<Planters>> viewPlanters(@PathVariable("planterShape") String planterShape)
			throws PlantersShapeNotFoundException {
		logger.info("Inside viewPlanters method");
		List<Planters> planters = plantersService.viewPlanters(planterShape);
		logger.info("view Planters" + planters);
		if (planters == null) {
			throw new PlantersShapeNotFoundException("planterShape Not found ");
		}

		return new ResponseEntity<>(planters, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/planters/{pid}")
	public ResponseEntity<String> deletePlanters(@PathVariable("pid") int plantersId)
			throws PlantersIdNotFoundException {
		logger.info("Inside deletePlanters method");
		Planters plantersPresent = plantersService.viewPlanters(plantersId);
		logger.info("Delete Planters" + plantersPresent);
		if (plantersPresent == null) {
			throw new PlantersIdNotFoundException("PlantersId " + plantersId + " not found");
		}
		plantersService.deletePlanters(plantersId);
		return ResponseEntity.status(HttpStatus.OK).body("Planters " + plantersId + " deleted");
	}

	@PutMapping("/{plantersId}")
	public ResponseEntity<String> updatePlanters(@PathVariable("plantersId") int plantersId,
			@RequestBody Planters planters) throws PlantersIdNotFoundException {
		// logger.info("Inside updatePlanters method");
		planters.setPlanterId(plantersId);
		Planters updatePlanters = plantersService.updatePlanters(planters);
		if (updatePlanters.getPlanterCost() < 0 || updatePlanters.getPlanterCapacity() < 0
				|| updatePlanters.getPlanterheight() < 0 || updatePlanters.getPlanterColor().isEmpty()) {
			logger.info("object is null");
			throw new PlantersIdNotFoundException(plantersId + "not found");
		}

		else {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(planters.getPlanterId()).toUri();
			return ResponseEntity.created(location).build();
		}
	}

	@PostMapping
	public ResponseEntity<String> addPlanters(@RequestBody Planters planters) throws PlantersIdNotFoundException {
		// logger.debug("Planters no is coming here "+ planters.getPlanterId());
		// if(planters.getPlanterheight()<0|| planters.getPlanterCost()<0||
		// planters.getPlanterCapacity()<0|| planters.getPlanterColor().isEmpty()) {
		// logger.error("planters name is not here");
		// throw new PlantersIdNotFoundException(planters + "again enter planters name
		// ");
		if (planters.getPlanterheight() < 0) {
			// logger.error("planters id is coming here");
			throw new PlantersIdNotFoundException("Planters id can not be negative");
		} else if (planters.getPlanterCost() < 0) {
			throw new PlantersIdNotFoundException("Planters cost can not be negative");
			// Planters newPlanters = plantersService.addPlanters(planters);
		} else if (planters.getPlanterCapacity() < 0) {
			throw new PlantersIdNotFoundException("planter capacity not be negative");
		}
//	else {URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newPlanters.getPlanterId()).toUri();
//	return ResponseEntity.created(location).build();
		else
			plantersService.addPlanters(planters);
		return ResponseEntity.status(HttpStatus.OK).body("planters added successfully");
	}
}

//@PostMapping
//public ResponseEntity<String> addPlanters(@RequestBody Planters planters){
//	logger.info("Inside addPlanters method");
//	Planters newPlanters = plantersService.addPlanters(planters);
//	logger.info("New Planters" + newPlanters);
//	
//	if(newPlanters == null)
//		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
//	URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newPlanters.getPlanterId()).toUri();
//	return ResponseEntity.created(location).build();
//  }

//if (addPlanters == null) {
//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Planters" + plantersId + "Not found");
//}else {
//URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(planters.getPlanterId()).toUri();
//return ResponseEntity.created(location).build();
//}
