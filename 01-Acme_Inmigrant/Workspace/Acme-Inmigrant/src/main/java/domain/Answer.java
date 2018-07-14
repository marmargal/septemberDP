package domain;

import java.sql.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Answer extends DomainEntity {

	// Attributes

	private String reply;
	private Date moment;

	@Valid
	@NotBlank
	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	@Valid
	@Past
	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}
	
	// Relationships
	
//	private Inmigrant inmigrant;
//
//	@Valid
//	@ManyToOne
//	public Inmigrant getInmigrant() {
//		return inmigrant;
//	}
//
//	public void setInmigrant(Inmigrant inmigrant) {
//		this.inmigrant = inmigrant;
//	}
	
	

}
