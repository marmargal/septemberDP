package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Route extends DomainEntity {

	// Constructors
	public Route() {
		super();
	}

	// Attributes
	private String name;
	private Double lentgh;
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
	private User user;
	private Hike hike;
	private Collection<Comment> comments;

	@Valid
	@ManyToOne
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Valid
	@OneToOne(optional = false)
	public Hike getHike() {
		return hike;
	}

	public void setHike(Hike hike) {
		this.hike = hike;
	}

	@Valid
	@OneToMany(mappedBy = "route")
	public Collection<Comment> getComments() {
		return comments;
	}

	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}

}
