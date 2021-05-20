package com.cg.onlinenursery.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlinenursery.controller.SeedController;
import com.cg.onlinenursery.entity.Seed;
import com.cg.onlinenursery.repository.SeedRepository;

@Service
public class SeedServiceImpl {
	@Autowired
	private SeedRepository seedRepository;
	Logger logger = LoggerFactory.getLogger(SeedController.class);

	public SeedServiceImpl() {
		logger.trace("Trace Seed Constructor is created*****88");
		logger.error("Error Constructor is created*****88");
		logger.info(" Info Seed Constructor is created*****88");
		logger.debug("Debug Seed Constructor is created*****88");
		logger.warn("Warn Seed Constructor is created*****88");
	}

	public Seed viewSeed(String commonName) {
		logger.info("Inside viewSeed method");
		Seed seedList = seedRepository.commonName(commonName);
		return seedList;
	}

	public List<Seed> viewSeedBy(String typeofseed) {
		logger.info("Inside viewSeed method");
		List<Seed> seedList = seedRepository.typeOfSeeds(typeofseed);
		return seedList;
	}

	public List<Seed> viewAllSeeds() {
		logger.info("Inside ViewSeed method");
		List<Seed> seedList = seedRepository.findAll();
		return seedList;
	}

	public List<Seed> viewSeed() {
		logger.info("Inside ViewSeed method");
		List<Seed> seedList = seedRepository.findAll();
		return seedList;
	}

	public List<Seed> viewAllSeeds(String typeOfSeed) {
		logger.info("Inside ViewAllSeed method");
		List<Seed> seedList = seedRepository.findAll();
		return seedList;

	}

	public Seed addSeed(Seed seed) {
		logger.info("Inside addSeed method");
		Seed seedexist = viewSeed(seed.getSeedId());
		logger.info("New Seed" + seed);
		if (seedexist == null) {
			seed = (Seed) seedRepository.save(seed);
		}
		return seed;
	}

	public Seed updateSeed(Seed seed) {
		logger.info("Inside updateSeed method");
		Seed seedexist = viewSeed(seed.getSeedId());
		logger.info("Update Seed" + seed);
		if (seedexist != null) {
			seed = (Seed) seedRepository.save(seed);
		}
		return seed;
	}

	public Seed deleteSeed(int seedId) {
		logger.info("Inside deleteSeed method");
		Seed seed = viewSeed(seedId);
		logger.info("Delete Seed" + seed);
		if (seed != null)
			seedRepository.deleteById(seedId);
		return seed;
	}

	public Seed viewSeed(int seedId) {
		logger.info("Inside viewSeed method");
		Optional<Seed> optional = seedRepository.findById(seedId);
		logger.info("New Seed" + seedId);
		Seed seed = null;
		if (optional.isPresent()) {
			seed = optional.get();
		}
		return seed;

	}
}