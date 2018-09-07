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

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Application extends DomainEntity{
	
	// Constructors
	
	public Application(){
		super();
	}

	private String ticker;
	private Date createMoment;
	private Boolean closed;
	
	//TODO: Hacer pattern --> cambiarlo en el populate
	@NotBlank
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	@Past
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getCreateMoment() {
		return createMoment;
	}

	public void setCreateMoment(Date createMoment) {
		this.createMoment = createMoment;
	}

	@NotNull
	public Boolean getClosed() {
		return closed;
	}

	public void setClosed(Boolean closed) {
		this.closed = closed;
	}

	// Relationships
	
	private Pet pet;
	private Client client;
	private Report report;

	@Valid
	@ManyToOne(optional=false)
	public Pet getPet() {
		return pet;
	}
	public void setPet(Pet pet) {
		this.pet = pet;
	}

	@Valid
	@ManyToOne(optional=false)
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	
	@Valid
	@OneToOne(optional=true)
	public Report getReport() {
		return report;
	}
	public void setReport(Report report) {
		this.report = report;
	}
	
	
}
