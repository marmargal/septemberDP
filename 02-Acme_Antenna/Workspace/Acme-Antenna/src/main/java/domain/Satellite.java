package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = {@Index(columnList = "name,description") })
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
	private Collection<Antenna> antennas;
	private Collection<Platform> platforms;
	
	@Valid
	@OneToMany(mappedBy="satellite")
	public Collection<Antenna> getAntennas() {
		return antennas;
	}
	
	public void setAntennas(Collection<Antenna> antennas) {
		this.antennas = antennas;
	}
	
	@Valid
	@OneToMany(mappedBy="satellite")
	public Collection<Platform> getPlatforms() {
		return platforms;
	}
	public void setPlatforms(Collection<Platform> platforms) {
		this.platforms = platforms;
	}
	
	
}
