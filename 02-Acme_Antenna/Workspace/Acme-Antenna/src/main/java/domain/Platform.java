package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Platform extends DomainEntity{

	// Constructors
	public Platform(){
		super();
	}
	
	// Attributes
	private String name;
	private String description;
	
	@NotBlank
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@NotBlank
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	// Relationships
	private Satellite satellite;

	@Valid
	@ManyToOne(optional=false)
	public Satellite getSatellite() {
		return satellite;
	}

	public void setSatellite(Satellite satellite) {
		this.satellite = satellite;
	}
}
