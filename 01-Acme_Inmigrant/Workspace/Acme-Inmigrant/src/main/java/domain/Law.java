package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Law extends DomainEntity {

	// Attributes

	private String title;
	private String text;
	private Date enactmentDate;
	private Date abrogationTime;

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

	@Past
	public Date getEnactmentDate() {
		return enactmentDate;
	}

	public void setEnactmentDate(Date enactmentDate) {
		this.enactmentDate = enactmentDate;
	}

	@Past
	public Date getAbrogationTime() {
		return abrogationTime;
	}

	public void setAbrogationTime(Date abrogationTime) {
		this.abrogationTime = abrogationTime;
	}
	
	// Relationships
	
	private Requirement requirement;

	@Valid
	@OneToMany
	public Requirement getRequirement() {
		return requirement;
	}

	public void setRequirement(Requirement requirement) {
		this.requirement = requirement;
	}
	
	
}
