package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Size;

@Entity
@Access(AccessType.PROPERTY)
public class Decision extends DomainEntity {
	
	// Constructors
	public Decision() {
		super();
	}
	

	// Attributes
	private String comment;
	private Boolean approved;
	
	@Size(min = 1, max = 100)
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Boolean getApproved() {
		return approved;
	}
	public void setApproved(Boolean approved) {
		this.approved = approved;
	}
	
	// Relationships
	private Administrator administrator;
	private Prueba prueba;
	
	@Valid
	@ManyToOne(optional=false)
	public Administrator getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	@Valid
	@OneToOne(optional=false)
	public Prueba getPrueba() {
		return prueba;
	}

	public void setPrueba(Prueba prueba) {
		this.prueba = prueba;
	}
	
	
}
