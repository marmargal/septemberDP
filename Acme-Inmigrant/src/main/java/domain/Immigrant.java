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
//	private Collection<Visa> visas;
//	private Collection<Answer> answers;
//	private Collection<Report> reports;
//	
//	
//	@Valid
//	@OneToMany(mappedBy = immigrant)
//	public Collection<Visa> getVisas(){
//		return visas;
//	}
//	
//	public void setVisas(Collection<Visa> visas){
//		this.visas = visas;
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
