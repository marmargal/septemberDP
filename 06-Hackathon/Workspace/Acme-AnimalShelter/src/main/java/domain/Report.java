package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
@Table(indexes = {@Index(columnList = "suitable") })
public class Report extends DomainEntity{
	
	// Constructors
	
	public Report(){
		super();
	}

	private String description;
	private Boolean suitable;
	private Date makeMoment;

	@NotBlank
	@SafeHtml
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@NotNull
	public Boolean getSuitable() {
		return suitable;
	}

	public void setSuitable(Boolean suitable) {
		this.suitable = suitable;
	}

	@Past
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getMakeMoment() {
		return makeMoment;
	}

	public void setMakeMoment(Date makeMoment) {
		this.makeMoment = makeMoment;
	}
	
	// Relationships
	
	private Employee employee;
	private Application application;
	
	@Valid
	@ManyToOne(optional=false)
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Valid
	@OneToOne(optional=false)
	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}
	
}
