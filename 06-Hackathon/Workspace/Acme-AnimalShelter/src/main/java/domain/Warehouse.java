package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class Warehouse extends DomainEntity{
	
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
	@Range(min=1, max=1000000000)
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	@NotNull
	@Range(min=1, max=1000000000)
	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	@NotNull
	@Range(min=1, max=1000000000)
	public Integer getDogFood() {
		return dogFood;
	}

	public void setDogFood(Integer dogFood) {
		this.dogFood = dogFood;
	}

	@NotNull
	@Range(min=1, max=1000000000)
	public Integer getCatFood() {
		return catFood;
	}

	public void setCatFood(Integer catFood) {
		this.catFood = catFood;
	}

	@NotNull
	@Range(min=1, max=1000000000)
	public Integer getBirdFood() {
		return birdFood;
	}

	public void setBirdFood(Integer birdFood) {
		this.birdFood = birdFood;
	}
}
