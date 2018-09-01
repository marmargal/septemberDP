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

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Pet extends DomainEntity{
	
	// Constructors
	
	public Pet(){
		super();
	}

	private String name;
	private String type;
	private Integer foodExpense;
	private Boolean status;
	private String identifier;
	private Date date;
	private Integer age;
	private Boolean chip;
	
	@NotBlank
	@Pattern(regexp = "^((DOG)|(CAT)|(BIRD))$")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotNull
	public Integer getFoodExpense() {
		return foodExpense;
	}

	public void setFoodExpense(Integer foodExpense) {
		this.foodExpense = foodExpense;
	}

	@NotNull
	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@NotBlank
	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	@Past
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@NotNull
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@NotNull
	public Boolean getChip() {
		return chip;
	}

	public void setChip(Boolean chip) {
		this.chip = chip;
	}
	
	// Relationships

	private Center center;
	private Application application;
	private MedicalReport medicalReport;
	
	@Valid
	@ManyToOne(optional=false)
	public Center getCenter() {
		return center;
	}
	public void setCenter(Center center) {
		this.center = center;
	}

	@Valid
	@OneToOne(optional=true)
	public Application getApplication() {
		return application;
	}
	public void setApplication(Application application) {
		this.application = application;
	}

	@Valid
	@OneToOne(optional=true)
	public MedicalReport getMedicalReport() {
		return medicalReport;
	}
	public void setMedicalReport(MedicalReport medicalReport) {
		this.medicalReport = medicalReport;
	}
	
}
