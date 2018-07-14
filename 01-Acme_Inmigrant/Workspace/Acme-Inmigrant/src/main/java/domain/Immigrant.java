//package domain;
//
//import java.util.Collection;
//
//import javax.persistence.Access;
//import javax.persistence.AccessType;
//import javax.persistence.Entity;
//import javax.persistence.OneToMany;
//import javax.validation.Valid;
//
//@Entity
//@Access(AccessType.PROPERTY)
//public class Immigrant extends Actor{
//	
//	// Constructors
//
//	public Immigrant() {
//		super();
//	}
//	
//	// Relationships
//	private Collection<Application> applications;
//	private Collection<Answer> answers;
//	private Collection<Report> reports;
//	
//	
//	@Valid
//	@OneToMany(mappedBy = immigrant)
//	public Collection<Application> getApplications(){
//		return applications;
//	}
//	
//	public void setApplications(Collection<Application> applications){
//		this.applications = applications;
//	}
//	
//	@Valid
//	@OneToMany(mappedBy = immigrant)
//	public Collection<Answer> getAnswers(){
//		return answers;
//	}
//	
//	public void setAnswers(Collection<Answer> answers){
//		this.answers = answers;
//	}
//	
//	@Valid
//	@OneToMany(mappedBy = immigrant)
//	public Collection<Report> getReports(){
//		return reports;
//	}
//	
//	public void setReports(Collection<Report> reports){
//		this.reports = reports;
//	}
//	
//	
//}
