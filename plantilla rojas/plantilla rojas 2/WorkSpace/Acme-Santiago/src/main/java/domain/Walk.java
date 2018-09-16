package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = {@Index(columnList = "route_id") })
public class Walk extends DomainEntity {

	// Constructor

	public Walk() {
		super();
	}

	// Attributes

	private String title;
	private Collection<Date> daysOfEachHike;

	@NotBlank
	@SafeHtml
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

	private Route route;
	private Inn inn;

	

	@Valid
	@ManyToOne(optional = false)
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