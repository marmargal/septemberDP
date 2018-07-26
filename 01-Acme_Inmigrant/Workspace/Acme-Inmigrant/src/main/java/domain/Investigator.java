package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Investigator extends Actor{
	
	// Constructors

	public Investigator() {
		super();
	}
	
	// Relationships
	private Collection<Report> reports;
	private Collection<Immigrant> immigrants; 
	
	@Valid
	@OneToMany(mappedBy = "writer")
	public Collection<Report> getReports(){
		return reports;
	}
	
	public void setReports(Collection<Report> reports){
		this.reports = reports;
	}
	
	@Valid
	@OneToMany
	public Collection<Immigrant> getImmigrants() {
		return immigrants;
	}

	public void setImmigrants(Collection<Immigrant> immigrants) {
		this.immigrants = immigrants;
	}
}
