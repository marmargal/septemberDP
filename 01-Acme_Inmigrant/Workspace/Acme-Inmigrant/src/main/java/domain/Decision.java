package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
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
	private Date momment;
	private String comment;
	
	@NotBlank
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
	public Date getMomment() {
		return momment;
	}

	public void setMomment(Date momment) {
		this.momment = momment;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	// Relationships
	private Application application;
	
	@OneToOne
	public Application getApplication() {
		return application;
	}
	public void setApplication(Application application) {
		this.application = application;
	}
	
}
