package com.cg.onlinenursery.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = "Seed")
public class Seed implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "seedId")
	// @GeneratedValue
	private int seedId;
	@Column(name = "commonName")
	private String commonName;
	@Column(name = "bloomTime")
	private int bloomTime;
	@Column(name = "watering")
	private String watering;
	@Column(name = "difficultyLevel")
	private String difficultyLevel;
	@Column(name = "temparature")
	private int temparature;
	@Column(name = "typeOfSeeds")
	private String typeOfSeeds;
	@Column(name = "seedDescription")
	private String seedDescription;
	@Column(name = "seedStock")
	private Integer seedStock;
	@Column(name = "seedPerPacket")
	private Integer seedPerPacket;

	@JsonProperty(access = Access.READ_ONLY)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "plantersid")
	private Planters planters;

	public Seed() {
		// TODO Auto-generated constructor stub
	}

	public Seed(int seedId, String commonName, int bloomTime, String watering, String difficultyLevel, int temparature,
			String typeOfSeeds, String seedDescription, Integer seedStock, Integer seedPerPacket, Planters planters) {
		super();
		this.seedId = seedId;
		this.commonName = commonName;
		this.bloomTime = bloomTime;
		this.watering = watering;
		this.difficultyLevel = difficultyLevel;
		this.temparature = temparature;
		this.typeOfSeeds = typeOfSeeds;
		this.seedDescription = seedDescription;
		this.seedStock = seedStock;
		this.seedPerPacket = seedPerPacket;
		this.planters = planters;
	}

	public Integer getSeedId() {
		return seedId;
	}

	public void setSeedId(int seedId) {
		this.seedId = seedId;
	}

	public String getCommonName() {
		return commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	public int getBloomTime() {
		return bloomTime;
	}

	public void setBloomTime(int bloomTime) {
		this.bloomTime = bloomTime;
	}

	public String getWatering() {
		return watering;
	}

	public void setWatering(String watering) {
		this.watering = watering;
	}

	public String getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(String difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public int getTemparature() {
		return temparature;
	}

	public void setTemparature(int temparature) {
		this.temparature = temparature;
	}

	public String getTypeOfSeeds() {
		return typeOfSeeds;
	}

	public void setTypeOfSeeds(String typeOfSeeds) {
		this.typeOfSeeds = typeOfSeeds;
	}

	public String getSeedDescription() {
		return seedDescription;
	}

	public void setSeedDescription(String seedDescription) {
		this.seedDescription = seedDescription;
	}

	public Integer getSeedStock() {
		return seedStock;
	}

	public void setSeedStock(Integer seedStock) {
		this.seedStock = seedStock;
	}

	public Integer getSeedPerPacket() {
		return seedPerPacket;
	}

	public void setSeedPerPacket(Integer seedPerPacket) {
		this.seedPerPacket = seedPerPacket;
	}

	public Planters getPlanters() {
		return planters;
	}

	public void setPlanters(Planters planters) {
		this.planters = planters;
	}
}