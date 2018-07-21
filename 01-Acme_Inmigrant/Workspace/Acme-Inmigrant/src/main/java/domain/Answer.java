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
public class Answer extends DomainEntity {

	// Constructors
	
	public Answer(){
		super();
	}
	
	// Attributes

	private String reply;
	private Date moment;

	@NotBlank
	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	@Past
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}
	
	// Relationships
	
	private Immigrant immigrant;
	private Question question;

	@Valid
	@ManyToOne
	public Immigrant getImmigrant() {
		return immigrant;
	}

	public void setImmigrant(Immigrant immigrant) {
		this.immigrant = immigrant;
	}

	@Valid
	@OneToOne(optional=false)
	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
	
	

}
