package com.cg.onlinenursery.controller;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cg.onlinenursery.entity.Seed;
import com.cg.onlinenursery.exception.SeedsCommonNameNotFoundException;
import com.cg.onlinenursery.exception.SeedsIdNotFoundException;
import com.cg.onlinenursery.service.SeedServiceImpl;

@RestController
@RequestMapping("/seed")
@Controller
public class SeedController {
	@Autowired
	private SeedServiceImpl SeedService;
	Logger logger = LoggerFactory.getLogger(SeedController.class);

	public SeedController() {
		logger.trace("Trace Seed Constructor is created*******88");
		logger.error("Error Constructor is created*******88");
		logger.info(" Info Seed Constructor is created*******88");
		logger.debug("Debug Seed Constructor is created*******88");
		logger.warn("Warn Seed Constructor is created*******88");

	}

	@GetMapping("/seed")
	public ResponseEntity<List<Seed>> viewSeed() {
		logger.debug("seed id excucation");
		logger.info("Inside viewSeed method");
		List<Seed> seedList = SeedService.viewSeed();
		logger.info("ViewAll Seed" + seedList);
		ResponseEntity<List<Seed>> response = new ResponseEntity<>(seedList, HttpStatus.NOT_FOUND);
		if (!seedList.isEmpty()) {
			response = new ResponseEntity<>(seedList, HttpStatus.OK);
		}
		return response;
	}

	@GetMapping("/{seedId}")
	public ResponseEntity<Seed> ViewSeed(@PathVariable("seedId") int seedId) {
		logger.info("Inside ViewSeed method");
		Seed seed = SeedService.viewSeed(seedId);
		logger.info("View Seed" + seed);
		if (seed == null) {
			throw new SeedsIdNotFoundException(seedId + " Not Found");
		}
		// return response;
		return new ResponseEntity<Seed>(seed, HttpStatus.ACCEPTED);

	}

	@GetMapping("/commanname/{commanname}")
	public ResponseEntity<Seed> viewSeed(@PathVariable("commanname") String commanname)
			throws SeedsCommonNameNotFoundException {
		logger.info("Inside viewSeed method");
		Seed seed = SeedService.viewSeed(commanname);
		logger.info("view Seed" + seed);
		if (seed == null) {
			throw new SeedsCommonNameNotFoundException("common name not found ");
		}

		return new ResponseEntity<>(seed, HttpStatus.ACCEPTED);
	}

//    @GetMapping("/typeofseed/{typeofseed}")
//    public ResponseEntity<Seed> viewAllSeed(@PathVariable("typeofseed") String typeofseed) throws TypeofSeedNotFoundException {
//    	logger.info("Inside viewAllSeed method");
//        Seed seed = SeedService.viewSeed(typeofseed);
//    	logger.info("view Seed" + seed);
//    		if (seed == null) {
//    			throw new TypeofSeedNotFoundException("typeofseed Not found ");
//    		}
//
//    		return new ResponseEntity<>(seed, HttpStatus.ACCEPTED);
//    }

//    @PostMapping
//	public ResponseEntity<String> addseed(@RequestBody Seed seed) {
//    	logger.info("Inside addseed method");
//		// If message is inserted it returns inserted message object else null
//    	Seed newSeed = SeedService.addSeed(seed);
//    	logger.info("New Seed" + newSeed);
//		// response is set to error if message is null.
//		if (newSeed == null)
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Inernal server error");
//		// response is set to inserted message id in response header section.
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{seedid}")
//				.buildAndExpand(newSeed.getSeedId()).toUri();
//		return ResponseEntity.created(location).build();
//}
	@PostMapping
	public ResponseEntity<String> addseed(@RequestBody Seed seed) throws SeedsIdNotFoundException {
		// logger.debug("Planters no is coming here "+ planters.getPlanterId());
		// if(planters.getPlanterheight()<0|| planters.getPlanterCost()<0||
		// planters.getPlanterCapacity()<0|| planters.getPlanterColor().isEmpty()) {
		// logger.error("planters name is not here");
		// throw new PlantersIdNotFoundException(planters + "again enter planters name
		// ");
		if (seed.getSeedId() < 0) {
			// logger.error("planters id is coming here");
			throw new SeedsIdNotFoundException("seed id can not be negative");
		} else if (seed.getCommonName().isEmpty()) {
			throw new SeedsIdNotFoundException("Common name can not be null");
			// Planters newPlanters = plantersService.addPlanters(planters);
		} else if (seed.getSeedPerPacket() < 0) {
			throw new SeedsIdNotFoundException("seedPerPacket not be null");
		}
//		else {URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newPlanters.getPlanterId()).toUri();
//		return ResponseEntity.created(location).build();
		else
			SeedService.addSeed(seed);
		return ResponseEntity.status(HttpStatus.OK).body("seed added successfully");
	}

	@DeleteMapping(value = "/{seedId}")
	public ResponseEntity<String> deleteSeed(@PathVariable("seedId") int seedId) {
		logger.info("Inside deleteSeed method");
		Seed seedPresent = SeedService.deleteSeed(seedId);
		logger.info("Delete Seed" + seedPresent);
		// Creating an success response.
		// ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.OK)
		if (seedPresent == null) {

			throw new SeedsIdNotFoundException();
		}
		// return response;
		return ResponseEntity.status(HttpStatus.OK).body("Seed " + seedId + " deleted");
	}

	@PutMapping("/{seedId}")
	public ResponseEntity<Object> updateSeed(@PathVariable("seedId") int seedId, @RequestBody Seed seed) {
		logger.info("Inside updatSeed method");
		seed.setSeedId(seedId);
		Seed updateseed = SeedService.updateSeed(seed);
		logger.info("Update Seed" + updateseed);
		// response is set to error if seed is null.
		if (updateseed == null) {

			throw new SeedsIdNotFoundException();
		} else {
			// response is set to updated seed id in response header section.
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{seed}")
					.buildAndExpand(seed.getSeedId()).toUri();
			return ResponseEntity.created(location).build();
		}
	}
}