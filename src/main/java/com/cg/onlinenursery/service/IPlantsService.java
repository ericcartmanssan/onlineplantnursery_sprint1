package com.cg.onlinenursery.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.onlinenursery.entity.Plants;

// You don't forget the requirement
@Service
public interface IPlantsService {

	Plants addPlants(Plants Plants);

	Plants updatePlants(Plants plants);

	Plants deletePlants(String plants);

	Plants viewPlants(int plantsId);

	Plants viewPlants(String commonName);

	List<Plants> viewAllPlants();

	List<Plants> viewAllPlants(String typeOfPlants);
}