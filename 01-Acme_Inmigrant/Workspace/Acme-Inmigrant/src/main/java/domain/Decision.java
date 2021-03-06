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

import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Decision extends DomainEntity {
	// Constructors

	public Decision() {
		super();
	}
	
	// Attributes
	private Boolean accept;
	private Date moment;
	private String comment;
	
	@NotNull
	public Boolean getAccept() {
		return accept;
	}
	public void setAccept(Boolean accept) {
		this.accept = accept;
	}

	@Past
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date momment) {
		this.moment = momment;
	}

	@SafeHtml
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	// Relationships
	private Application application;
	private Officer officer;
	
	@Valid
	@OneToOne
	public Application getApplication() {
		return application;
	}
	public void setApplication(Application application) {
		this.application = application;
	}
	
	@Valid
	@ManyToOne(optional=true)
	public Officer getOfficer() {
		return officer;
	}
	public void setOfficer(Officer officer) {
		this.officer = officer;
	}
	
}
