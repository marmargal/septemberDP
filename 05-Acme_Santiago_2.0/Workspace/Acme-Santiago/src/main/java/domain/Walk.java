<<<<<<< HEAD
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Walk extends DomainEntity {

	// Constructor

	public Walk() {
		super();
	}

	// Attributes

	private String title;
	private Collection<Date> daysOfEachHike;

	@NotBlank
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@ElementCollection
	public Collection<Date> getDaysOfEachHike() {
		return daysOfEachHike;
	}

	public void setDaysOfEachHike(Collection<Date> daysOfEachHike) {
		this.daysOfEachHike = daysOfEachHike;
	}

	// Relationships

	private Collection<Comment> comments;
	private Route route;
	private Inn inn;

	@Valid
	@OneToMany(mappedBy = "walk")
	public Collection<Comment> getComments() {
		return comments;
	}

	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}

	@Valid
	@ManyToOne(optional=false)
	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	@Valid
	@ManyToOne
	public Inn getInn() {
		return inn;
	}

	public void setInn(Inn inn) {
		this.inn = inn;
	}

}
=======
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Walk extends DomainEntity {

	// Constructor

	public Walk() {
		super();
	}

	// Attributes

	private String title;
	private Collection<Date> daysOfEachHike;

	@NotBlank
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@ElementCollection
	public Collection<Date> getDaysOfEachHike() {
		return daysOfEachHike;
	}

	public void setDaysOfEachHike(Collection<Date> daysOfEachHike) {
		this.daysOfEachHike = daysOfEachHike;
	}

	// Relationships

	private Collection<Comment> comments;
	private Route route;
	private Inn inn;

	@Valid
	@OneToMany
	public Collection<Comment> getComments() {
		return comments;
	}

	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}

	@Valid
	@ManyToOne(optional=false)
	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	@Valid
	@ManyToOne
	public Inn getInn() {
		return inn;
	}

	public void setInn(Inn inn) {
		this.inn = inn;
	}

}
>>>>>>> 254c2e47546558cea9c3c67d06e9dc79abfb610c
