//package domain;
//
//import java.util.Collection;
//
//import javax.persistence.Access;
//import javax.persistence.AccessType;
//import javax.persistence.Entity;
//import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
//import javax.validation.Valid;
//import javax.validation.constraints.NotNull;
//
//@Entity
//@Access(AccessType.PROPERTY)
//public class Officer extends Actor{
//	
//	// Constructors
//
//	public Officer() {
//		super();
//	}
//	
//	// Relationships
//	private Collection<Application> applications;
//	private Decision decision;
//	private Collection<Question> questions;
//	
//	@Valid
//	@OneToMany(mappedBy = officer)
//	public Collection<Application> getApplication(){
//		return applications;
//	}
//	
//	public void setApplications(Collection<Application> applications){
//		this.applications = applications;
//	}
//	
//	@Valid
//	@NotNull
//	@OneToOne
//	public Decision getDecision(){
//		return decision;
//	}
//	
//	public void setDecision(Decision decision){
//		this.decision = decision
//	}
//	
//	@Valid
//	@OneToMany(mappedBy = officer)
//	public Collection<Question> getQuestions(){
//		return questions;
//	}
//	
//	public void setQuestions(Collection<Question> questions){
//		this.questions = questions;
//	}
//}
