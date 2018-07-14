package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Report extends DomainEntity {

	// Attributes

	private String text;
	private String picture;

	@NotBlank
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@URL
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	//TODO: Descomentar las relationships de Report
//	// Relationships
//	
//	private Investigator investigator;
//
//	@Valid
//	@ManyToOne(optional = false)
//	public Investigator getInvestigator() {
//		return investigator;
//	}
//
//	public void setInvestigator(Investigator investigator) {
//		this.investigator = investigator;
//	}
	
	

}
