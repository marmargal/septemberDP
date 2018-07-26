package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
	private Decision decision;
	private Collection<Question> questions;
	private Collection<Immigrant> immigrants;
	
	@Valid
	@OneToMany(mappedBy = "officer")
	public Collection<Application> getApplications(){
		return applications;
	}
	
	public void setApplications(Collection<Application> applications){
		this.applications = applications;
	}
	
	@Valid
	@OneToOne
	public Decision getDecision(){
		return decision;
	}
	
	public void setDecision(Decision decision){
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
