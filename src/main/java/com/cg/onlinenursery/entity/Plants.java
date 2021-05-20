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
@Table(name = "Plants")

public class Plants implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "plantId")
	// @GeneratedValue
	private Integer plantId;
	@Column(name = "plantHeight")
	private Integer plantHeight;
	@Column(name = "plantSpread")
	private String plantSpread;
	@Column(name = "commonName")
	private String commonName;
	@Column(name = "bloomTime")
	private String bloomTime;
	@Column(name = "medicinalOrCulinaryUse")
	private String medicinalOrCulinaryUse;
	@Column(name = "difficultLevel")
	private String difficultLevel;
	@Column(name = "temperature")
	private String temperature;
	@Column(name = "typeOfPlant")
	private String typeOfPlant;
	@Column(name = "plantDescription")
	private String plantDescription;
	@Column(name = "plantStock")
	private Integer plantStock;
	@Column(name = "plantCost")
	private Double plantCost;

	@JsonProperty(access = Access.READ_ONLY)
	@JoinColumn(name = "planterid")
	@ManyToOne(fetch = FetchType.LAZY)
	private Planters planter;

	public Integer getPlantId() {
		return plantId;
	}

	public void setPlantId(Integer plantId) {
		this.plantId = plantId;
	}

	public Integer getPlantHeight() {
		return plantHeight;
	}

	public void setPlantHeight(Integer plantHeight) {
		this.plantHeight = plantHeight;
	}

	public String getPlantSpread() {
		return plantSpread;
	}

	public void setPlantSpread(String plantSpread) {
		this.plantSpread = plantSpread;
	}

	public String getCommonName() {
		return commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	public String getBloomTime() {
		return bloomTime;
	}

	public void setBloomTime(String bloomTime) {
		this.bloomTime = bloomTime;
	}

	public String getMedicinalOrCulinaryUse() {
		return medicinalOrCulinaryUse;
	}

	public void setMedicinalOrCulinaryUse(String medicinalOrCulinaryUse) {
		this.medicinalOrCulinaryUse = medicinalOrCulinaryUse;
	}

	public String getDifficultLevel() {
		return difficultLevel;
	}

	public void setDifficultLevel(String difficultLevel) {
		this.difficultLevel = difficultLevel;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getTypeOfPlant() {
		return typeOfPlant;
	}

	public void setTypeOfPlant(String typeOfPlant) {
		this.typeOfPlant = typeOfPlant;
	}

	public String getPlantDescription() {
		return plantDescription;
	}

	public void setPlantDescription(String plantDescription) {
		this.plantDescription = plantDescription;
	}

	public Integer getPlantStock() {
		return plantStock;
	}

	public void setPlantStock(Integer plantStock) {
		this.plantStock = plantStock;
	}

	public Double getPlantCost() {
		return plantCost;
	}

	public void setPlantCost(Double plantCost) {
		this.plantCost = plantCost;
	}

	public Planters getPlanter() {
		return planter;
	}

	public void setPlanter(Planters planter) {
		this.planter = planter;
	}

	public Plants(Integer plantId, Integer plantHeight, String plantSpread, String commonName, String bloomTime,
			String medicinalOrCulinaryUse, String difficultLevel, String temperature, String typeOfPlant,
			String plantDescription, Integer plantStock, Double plantCost, Planters planter) {
		super();
		this.plantId = plantId;
		this.plantHeight = plantHeight;
		this.plantSpread = plantSpread;
		this.commonName = commonName;
		this.bloomTime = bloomTime;
		this.medicinalOrCulinaryUse = medicinalOrCulinaryUse;
		this.difficultLevel = difficultLevel;
		this.temperature = temperature;
		this.typeOfPlant = typeOfPlant;
		this.plantDescription = plantDescription;
		this.plantStock = plantStock;
		this.plantCost = plantCost;
		this.planter = planter;
	}

	public Plants() {
		// TODO Auto-generated constructor stub
	}
}