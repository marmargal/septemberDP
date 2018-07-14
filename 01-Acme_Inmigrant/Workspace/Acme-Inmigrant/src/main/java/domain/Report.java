package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
	
	// Relationships
	private Immigrant immigrant;
	
	@OneToOne
	@Valid
	@NotNull
	public Immigrant getImmigrant() {
		return immigrant;
	}

	public void setImmigrant(Immigrant immigrant) {
		this.immigrant = immigrant;
	}
}
