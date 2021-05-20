package com.cg.onlinenursery.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.onlinenursery.entity.Seed;

// You don't forget the requirement
@Service
public interface ISeedService {

	Seed addSeed(Seed seed);

	Seed updateSeed(Seed seed);

	Seed deleteSeed(int seedId);

	Seed viewSeed(int seedId);

	Seed viewSeed(String commonName);

	List<Seed> viewAllSeeds();

	List<Seed> viewAllSeeds(String typeOfSeed);

}