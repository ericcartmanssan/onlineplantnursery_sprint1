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

import com.cg.onlinenursery.entity.Plants;
import com.cg.onlinenursery.exception.PlantsIdNotFoundException;
import com.cg.onlinenursery.service.PlantsServiceImpl;

@RestController
@RequestMapping("/plants")
public class PlantsController {

	@Autowired
	private PlantsServiceImpl PlantsService;
	Logger logger = LoggerFactory.getLogger(PlantsController.class);

	@GetMapping("/plants")
	public ResponseEntity<List<Plants>> viewPlants() {
		logger.info("Inside viewPlants method");
		List<Plants> plantsList = PlantsService.viewPlants();
		logger.info("ViewAll Plants" + plantsList);
		ResponseEntity<List<Plants>> response = new ResponseEntity<>(plantsList, HttpStatus.NOT_FOUND);
		if (!plantsList.isEmpty()) {
			response = new ResponseEntity<>(plantsList, HttpStatus.OK);
		}

		return response;
	}

	@GetMapping("/{plantsId}")
	public ResponseEntity<Plants> ViewPlants(@PathVariable("plantsId") int plantsId) throws PlantsIdNotFoundException {
		logger.info("Inside ViewPlants method");
		Plants plants = PlantsService.viewPlants(plantsId);
		logger.info("View Plants" + plants);
		ResponseEntity<String> response = ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body("Plants" + plantsId + "Not found");
		if (plants == null) {
			throw new PlantsIdNotFoundException(plantsId + " Not Found");
		}

		return new ResponseEntity<>(plants, HttpStatus.OK);

	}

	@GetMapping("/commanname/{commanname}")
	public ResponseEntity<List<Plants>> viewPlants(@PathVariable("commanname") String commanname) {
		logger.info("Inside viewPlants method");
		List<Plants> plants = PlantsService.viewPlants(commanname);
		logger.info("view Plants" + plants);
		ResponseEntity<String> response = ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body("Plants" + commanname + "Not found");
//    		if (plants == null) {
//    			throw new PlantsIdNotFoundException("commanname Not found ");
//    		}

		return new ResponseEntity<>(plants, HttpStatus.ACCEPTED);
	}

	@GetMapping("/typeofplants/{typeofplants}")
	public ResponseEntity<List<Plants>> viewAllPlants(@PathVariable("typeofplants") String typeofplants) {
		logger.info("Inside viewAllPlants method");
		List<Plants> plants = PlantsService.viewAllPlants(typeofplants);
		logger.info("viewAll Plants" + plants);
		ResponseEntity<String> response = ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body("Seed" + typeofplants + "Not found");
//    		if (plants == null) {
//    			throw new SeedsIdNotFoundException("typeofseed Not found ");
//    		}

		return new ResponseEntity<>(plants, HttpStatus.ACCEPTED);
	}

//    @GetMapping("/{plants}/planters")
// 	public ResponseEntity<Planters> getPlanter(@PathVariable("plantsId") int plantsId) {
// 		Planters planters = PlantsService.ViewPlanters(plantsId);
//
// 		if (planters == null) {
// 			throw new SPlantsIdNotFoundException();
// 		}
//
// 		return new ResponseEntity<>(planters, HttpStatus.OK);
// }

	@PostMapping
	public ResponseEntity<String> addplants(@RequestBody Plants plants) throws PlantsIdNotFoundException {
		// logger.debug("Planters no is coming here "+ planters.getPlanterId());
		// if(planters.getPlanterheight()<0|| planters.getPlanterCost()<0||
		// planters.getPlanterCapacity()<0|| planters.getPlanterColor().isEmpty()) {
		// logger.error("planters name is not here");
		// throw new PlantersIdNotFoundException(planters + "again enter planters name
		// ");
		if (plants.getPlantCost() < 0) {
			// logger.error("planters id is coming here");
			throw new PlantsIdNotFoundException("Plants id can not be negative");
		} else if (plants.getCommonName().isEmpty()) {
			throw new PlantsIdNotFoundException("Plants name can not be null");
			// Planters newPlanters = plantersService.addPlanters(planters);
		} else if (plants.getPlantId() < 0) {
			throw new PlantsIdNotFoundException("plants id not be negative");
		}
//    	else {URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newPlanters.getPlanterId()).toUri();
//    	return ResponseEntity.created(location).build();
		else
			PlantsService.addPlants(plants);
		return ResponseEntity.status(HttpStatus.OK).body("plants added successfully");
	}

//    @PostMapping
//	public ResponseEntity<String> addplants(@RequestBody Plants Plants) {
//    	logger.info("Inside addplants method");
//		// If message is inserted it returns inserted message object else null
//    	Plants newPlants = PlantsService.addPlants(Plants);
//    	logger.info("New Plants" + newPlants);
//		// response is set to error if message is null.
//		if (newPlants == null)
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Inernal server error");
//		// response is set to inserted message id in response header section.
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{plantsid}")
//				.buildAndExpand(newPlants.getPlantId()).toUri();
//		return ResponseEntity.created(location).build();
//}

	@DeleteMapping(value = "/{plantId}")
	public ResponseEntity<String> deletePlants(@PathVariable("plantsId") int plantsId)
			throws PlantsIdNotFoundException {
		logger.info("Inside deletePlants method");
		Plants plantsPresent = PlantsService.deletePlants(plantsId);
		logger.info("Delete Plants" + plantsPresent);

		// Creating an success response.
		// ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.OK)
		if (plantsPresent == null) {

			throw new PlantsIdNotFoundException();
		}
		// return response;
		return ResponseEntity.status(HttpStatus.OK).body("Plants " + plantsId + " deleted");
	}

	@PutMapping("/{plantsId}")
	public ResponseEntity<Object> updatePlants(@PathVariable("plantsId") int plantsId, @RequestBody Plants plants) {
		logger.info("Inside updatePlants method");

		plants.setPlantId(plantsId);

		Plants updateplants = PlantsService.updatePlants(plants);
		logger.info("Update Plants" + updateplants);
		// response is set to error if seed is null.
// 		if (updateplants == null) {
//
// 		throw new PlantsIdNotFoundException();
// } 
// 		else {
		// response is set to updated seed id in response header section.
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/plants}")
				.buildAndExpand(plants.getPlantId()).toUri();
		return ResponseEntity.created(location).build();
	}
}
