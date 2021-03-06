package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = {@Index(columnList = "finallyDecision,walk_id") })
public class Compostela extends DomainEntity {

	// Constructor

	public Compostela() {
		super();
	}

	// Attributes

	private boolean decision;
	private boolean finallyDecision;
	private String justification;
	private Date date;

	public boolean isDecision() {
		return decision;
	}

	public void setDecision(boolean decision) {
		this.decision = decision;
	}
	
	public boolean isfinallyDecision() {
		return finallyDecision;
	}

	public void setfinallyDecision(boolean finallyDecision) {
		this.finallyDecision = finallyDecision;
	}

	@SafeHtml
	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}
	
	@Past
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	// Relationships
	
	private Walk walk;
	private User user;

	@Valid
	@ManyToOne
	public Walk getWalk() {
		return walk;
	}

	public void setWalk(Walk walk) {
		this.walk = walk;
	}
	
	@Valid
	@ManyToOne
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
