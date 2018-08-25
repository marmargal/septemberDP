package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Compostela extends DomainEntity {

	// Constructor

	public Compostela() {
		super();
	}

	// Attributes

	private String header;
	private String body;
	private String footer;
	private String logo;
	private boolean decision;
	private boolean finallyDecision;
	private String justification;

	@NotBlank
	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	@NotBlank
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@NotBlank
	public String getFooter() {
		return footer;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}

	@URL
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

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


	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}
	
	// Relationships
	
	private Walk walk;

	@Valid
	@ManyToOne
	public Walk getWalk() {
		return walk;
	}

	public void setWalk(Walk walk) {
		this.walk = walk;
	}

}
