package com.cg.onlinenursery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.onlinenursery.entity.Seed;

@Repository
public interface SeedRepository extends JpaRepository<Seed, Integer> {
	// @Query(value="select h from Seed h where h.commonName = :name",nativeQuery =
	// true)
	public Seed commonName(String name);

	// @Query(value="select h from Seed h where h.typeOfSeeds = :type",nativeQuery =
	// true)
	public List<Seed> typeOfSeeds(String type);

}
