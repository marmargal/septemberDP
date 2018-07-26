package domain;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Law extends DomainEntity {

	// Constructors

	public Law() {
		super();
	}

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
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getEnactmentDate() {
		return enactmentDate;
	}

	public void setEnactmentDate(Date enactmentDate) {
		this.enactmentDate = enactmentDate;
	}

	@Past
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getAbrogationTime() {
		return abrogationTime;
	}

	public void setAbrogationTime(Date abrogationTime) {
		this.abrogationTime = abrogationTime;
	}

	// Relationships

	private List<Requirement> requirement;
	private List<Law> laws;
	private Law lawParent;
	private Country country;

	@Valid
	@OneToMany(mappedBy = "law")
	public List<Requirement> getRequirement() {
		return requirement;
	}

	public void setRequirement(List<Requirement> requirement) {
		this.requirement = requirement;
	}

	@Valid
	@OneToMany(mappedBy = "lawParent")
	public List<Law> getLaws() {
		return laws;

	}

	public void setLaws(List<Law> laws) {
		this.laws = laws;
	}

	@Valid
	@ManyToOne(optional = true)
	public Law getLawParent() {
		return lawParent;
	}

	public void setLawParent(Law lawParent) {
		this.lawParent = lawParent;
	}

	@Valid
	@ManyToOne(optional = false)
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

}
