package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Access(AccessType.PROPERTY)
public class Requirement extends DomainEntity {

	// Constructors
	
	public Requirement(){
		super();
	}
	
	// Attributes

	private String title;
	private String description;
	private Boolean abrogated;

	@NotBlank
	@SafeHtml
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@NotBlank
	@SafeHtml
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@NotNull
	public Boolean getAbrogated() {
		return abrogated;
	}

	public void setAbrogated(Boolean abrogated) {
		this.abrogated = abrogated;
	}


	// Relationships
	private Law law;

	@Valid
	@ManyToOne(optional=false)
	public Law getLaw() {
		return law;
	}

	public void setLaw(Law law) {
		this.law = law;
	}
	
}
