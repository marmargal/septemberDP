package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Stand extends DomainEntity{
	
	// Constructors
	
	public Stand(){
		super();
	}

	private Integer numMaxVoluntaries;
	private String fliers;
	private Boolean isOfCompany;
	
	@NotNull
	public Integer getNumMaxVoluntaries() {
		return numMaxVoluntaries;
	}
	public void setNumMaxVoluntaries(Integer numMaxVoluntaries) {
		this.numMaxVoluntaries = numMaxVoluntaries;
	}

	@URL
	@NotBlank
	public String getFliers() {
		return fliers;
	}

	public void setFliers(String fliers) {
		this.fliers = fliers;
	}

	@NotNull
	public Boolean getIsOfCompany() {
		return isOfCompany;
	}

	public void setIsOfCompany(Boolean isOfCompany) {
		this.isOfCompany = isOfCompany;
	}
	
	// Relationships
	
	private Collection<Voluntary> voluntaries;
	private Company company;
	private Employee employee;
	
	@Valid
	@ManyToMany
	public Collection<Voluntary> getVoluntaries() {
		return voluntaries;
	}
	
	public void setVoluntaries(Collection<Voluntary> voluntaries) {
		this.voluntaries = voluntaries;
	}
	
	@Valid
	@ManyToOne(optional=false)
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	
	@Valid
	@OneToOne
	public Employee getEmployee() {
		return employee;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
}
