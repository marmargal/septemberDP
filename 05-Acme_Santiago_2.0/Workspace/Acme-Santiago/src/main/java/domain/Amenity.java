package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Amenity extends DomainEntity {

	// Constructor

	public Amenity() {
		super();
	}

	// Attributes

	private String name;
	private String description;
	private Collection<String> pictures;

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

	@ElementCollection
	public Collection<String> getPictures() {
		return pictures;
	}

	public void setPictures(Collection<String> pictures) {
		this.pictures = pictures;
	}

	// Relationships
	private Inn inn;

	@Valid
	@NotNull
	@OneToOne
	public Inn getInn() {
		return inn;
	}

	public void setInn(Inn inn) {
		this.inn = inn;
	}

}
