package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Veterinary extends Actor{
	
	// Constructors
	
	public Veterinary(){
		super();
	}
	
	private boolean ban;
	private Collection<MedicalReport> medicalReport;

	public boolean isBan() {
		return ban;
	}

	public void setBan(boolean ban) {
		this.ban = ban;
	}
	@Valid
	@OneToMany(mappedBy = "veterinary")
	public Collection<MedicalReport> getMedicalReport() {
		return medicalReport;
	}

	public void setMedicalReport(Collection<MedicalReport> medicalReport) {
		this.medicalReport = medicalReport;
	}

}
