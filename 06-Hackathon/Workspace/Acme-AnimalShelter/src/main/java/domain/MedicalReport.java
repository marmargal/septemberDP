package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class MedicalReport extends DomainEntity{
	
	// Constructors
	
	public MedicalReport(){
		super();
	}

	private String diagnosis;
	private String initialState;
	private String treatment;
	private Collection<String> diseases;
	private Date date;
	
	@NotBlank
	@SafeHtml
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	@NotBlank
	@SafeHtml
	public String getInitialState() {
		return initialState;
	}

	public void setInitialState(String initialState) {
		this.initialState = initialState;
	}

	@NotBlank
	@SafeHtml
	public String getTreatment() {
		return treatment;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
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
	@ElementCollection
	public Collection<String> getDiseases() {
		return diseases;
	}
	public void setDiseases(Collection<String> diseases) {
		this.diseases = diseases;
	}

	
	// Relationships

	private Pet pet;
	private Veterinary veterinary;
	
	@Valid
	@OneToOne(optional=false)
	public Pet getPet() {
		return pet;
	}
	public void setPet(Pet pet) {
		this.pet = pet;
	}

	@Valid
	@ManyToOne(optional=false)
	public Veterinary getVeterinary() {
		return veterinary;
	}
	public void setVeterinary(Veterinary veterinary) {
		this.veterinary = veterinary;
	}
	
	
}
