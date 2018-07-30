package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;


@Entity
@Access(AccessType.PROPERTY)
public class Officer extends Actor{
	
	// Constructors

	public Officer() {
		super();
	}
	
	// Relationships
	private Collection<Application> applications;
	private Collection<Decision> decision;
	private Collection<Question> questions;
	
	@Valid
	@OneToMany(mappedBy = "officer")
	public Collection<Application> getApplications(){
		return applications;
	}
	
	public void setApplications(Collection<Application> applications){
		this.applications = applications;
	}
	
	@Valid
	@OneToMany(mappedBy = "officer")
	public Collection<Decision> getDecision(){
		return decision;
	}
	
	public void setDecision(Collection<Decision> decision){
		this.decision = decision;
	}
	
	@Valid
	@OneToMany(mappedBy = "officer")
	public Collection<Question> getQuestions(){
		return questions;
	}
	
	public void setQuestions(Collection<Question> questions){
		this.questions = questions;
	}
}
