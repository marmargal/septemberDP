package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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
	
	private Voluntary voluntary;
	private Company company;
	
	@Valid
	@ManyToOne(optional=true)
	public Voluntary getVoluntary() {
		return voluntary;
	}
	public void setVoluntary(Voluntary voluntary) {
		this.voluntary = voluntary;
	}
	
	@Valid
	@ManyToOne(optional=false)
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
}
