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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = {@Index(columnList = "taboo") })
public class Comment extends DomainEntity {

	// Constructors
	public Comment() {
		super();
	}

	// Attributes
	private Date moment;
	private String title;
	private String text;
	private Collection<String> pictures;
	private Integer rating;
	private boolean taboo;

	@Past
	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}

	@NotBlank
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@NotBlank
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@ElementCollection
	public Collection<String> getPictures() {
		return pictures;
	}

	public void setPictures(Collection<String> pictures) {
		this.pictures = pictures;
	}

	@NotNull
	@Range(min = 0, max = 3)
	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	// Relationships
	private User user;
	private Route route;
	private Hike hike;

	@Valid
	@ManyToOne
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Valid
	@ManyToOne
	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	@Valid
	@ManyToOne
	public Hike getHike() {
		return hike;
	}

	public void setHike(Hike hike) {
		this.hike = hike;
	}

	public boolean isTaboo() {
		return taboo;
	}

	public void setTaboo(boolean taboo) {
		this.taboo = taboo;
	}

}
