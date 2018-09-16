package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Prueba extends DomainEntity {
	
	// Constructors
	public Prueba() {
		super();
	}
	

	// Attributes
	private String tracer;
	private Date moment;
	private String title;
	private String description;
	private int gauge;
	
	@NotBlank
	@SafeHtml
	@Pattern(regexp = "[0-9]{6}-\\w{5}")
	public String getTracer() {
		return tracer;
	}
	public void setTracer(String tracer) {
		this.tracer = tracer;
	}

	@Past
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}

	@NotBlank
	@SafeHtml
	@Size(min = 1, max = 100)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@NotBlank
	@SafeHtml
	@Size(min = 1, max = 250)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Range(min = 1, max = 3)
	public int getGauge() {
		return gauge;
	}

	public void setGauge(int gauge) {
		this.gauge = gauge;
	}
	
	// Relationships
	private User user;
	private Route route;
	private Decision decision;
	
	@Valid
	@ManyToOne(optional=false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Valid
	@ManyToOne(optional=true)
	public Route getRoute() {
		return route;
	}
	public void setRoute(Route route) {
		this.route = route;
	}
	
	@Valid
	@OneToOne(optional=true)
	public Decision getDecision() {
		return decision;
	}
	public void setDecision(Decision decision) {
		this.decision = decision;
	}
	
	
}
