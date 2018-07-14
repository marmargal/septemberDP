package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class EducationSection extends DomainEntity {
	
	// Constructors

	public EducationSection() {
		super();
	}
	
	// Attributes
	private String nameDegree;
	private String institution;
	private Date dateAwarded;
	private String level;
	
	@NotBlank
	public String getNameDegree() {
		return nameDegree;
	}
	public void setNameDegree(String nameDegree) {
		this.nameDegree = nameDegree;
	}

	@NotBlank
	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	@Past
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getDateAwarded() {
		return dateAwarded;
	}

	public void setDateAwarded(Date dateAwarded) {
		this.dateAwarded = dateAwarded;
	}

	@Valid
	@NotNull
	@Pattern(regexp = "^((NONE)|(ELEMENTARY)|(PRIMARY)|(SECONDARY)|(HIGH)|(BACHELOR)|(UNIVERSITY_DEGREE)|(MASTER)|(DOCTORATE))$")
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	
}
