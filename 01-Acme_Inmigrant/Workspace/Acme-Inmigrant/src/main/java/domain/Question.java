package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Past;

@Entity
@Access(AccessType.PROPERTY)
public class Question extends DomainEntity {

	// Constructors
	
	public Question(){
		super();
	}
	
	// Attributes

	private boolean statement;
	private Date moment;

	public boolean getStatement() {
		return statement;
	}

	public void setStatement(boolean statement) {
		this.statement = statement;
	}

	@Past
	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}
	
	// Relationships
	
	private Answer answer;
	private Officer officer;
	private Application application;

	@Valid
	@OneToOne(optional = false)
	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	@Valid
	@ManyToOne(optional=true)
	public Officer getOfficer() {
		return officer;
	}

	public void setOfficer(Officer officer) {
		this.officer = officer;
	}

	@Valid
	@ManyToOne(optional=true)
	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	
	

}
