package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Warehouse extends Actor{
	
	// Constructors
	
	public Warehouse(){
		super();
	}
	
	private Integer capacity;
	private Integer stock;
	private Integer dogFood;
	private Integer catFood;
	private Integer birdFood;
	
	@NotNull
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	@NotNull
	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	@NotNull
	public Integer getDogFood() {
		return dogFood;
	}

	public void setDogFood(Integer dogFood) {
		this.dogFood = dogFood;
	}

	@NotNull
	public Integer getCatFood() {
		return catFood;
	}

	public void setCatFood(Integer catFood) {
		this.catFood = catFood;
	}

	@NotNull
	public Integer getBirdFood() {
		return birdFood;
	}

	public void setBirdFood(Integer birdFood) {
		this.birdFood = birdFood;
	}
}
