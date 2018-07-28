package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Satellite extends DomainEntity{

	// Constructors
	public Satellite(){
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
	private Antenna antenna;
	private Platform platform;
	
	@Valid
	@OneToMany(mappedBy="satellite")
	public Antenna getAntenna() {
		return antenna;
	}
	
	public void setAntenna(Antenna antenna) {
		this.antenna = antenna;
	}
	
	@Valid
	@OneToMany(mappedBy="satellite")
	public Platform getPlatform() {
		return platform;
	}
	public void setPlatform(Platform platform) {
		this.platform = platform;
	}
	
	
}
