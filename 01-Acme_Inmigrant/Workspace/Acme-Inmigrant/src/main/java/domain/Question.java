package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
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

	private Boolean statement;
	private Date moment;

	public Boolean getStatement() {
		return statement;
	}

	public void setStatement(Boolean statement) {
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

	@Valid
	@OneToOne(optional = false)
	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
	
	

}
