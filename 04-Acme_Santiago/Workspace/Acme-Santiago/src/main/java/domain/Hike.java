package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Hike extends DomainEntity {

	// Constructors
	public Hike() {
		super();
	}

	// Attributes
	private String name;
	private Double lentgh;
	private String description;
	private String originCity;
	private String destinationCity;
	private Collection<String> pictures;
	private DificultLevel dificultLevel;

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

	@NotBlank
	public String getOriginCity() {
		return originCity;
	}

	public void setOriginCity(String originCity) {
		this.originCity = originCity;
	}

	@NotBlank
	public String getDestinationCity() {
		return destinationCity;
	}

	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}

	@Valid
	@NotNull
	@Enumerated(EnumType.STRING)
	public DificultLevel getDificultLevel() {
		return dificultLevel;
	}

	public void setDificultLevel(DificultLevel dificultLevel) {
		this.dificultLevel = dificultLevel;
	}

	@NotNull
	public Double getLentgh() {
		return lentgh;
	}

	public void setLentgh(Double lentgh) {
		this.lentgh = lentgh;
	}

	@ElementCollection
	public Collection<String> getPictures() {
		return pictures;
	}

	public void setPictures(Collection<String> pictures) {
		this.pictures = pictures;
	}

	// Relationships
	private Route route;
	private Collection<Comment> comments;

	@Valid
	@OneToOne(optional = true)
	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	@Valid
	@OneToMany(mappedBy = "hike")
	public Collection<Comment> getComments() {
		return comments;
	}

	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}
}
