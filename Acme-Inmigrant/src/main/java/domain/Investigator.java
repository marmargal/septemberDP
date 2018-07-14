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
//
//@Entity
//@Access(AccessType.PROPERTY)
//public class Investigator extends Actor{
//	
//	// Constructors
//
//	public Investigator() {
//		super();
//	}
//	
//	// Relationships
//	private Collection<Report> reports;
//	
//	@Valid
//	@OneToMany(mappedBy = writer)
//	public Collection<Report> getReports(){
//		return reports;
//	}
//	
//	public void setReports(Collection<Report> reports){
//		this.reports = reports;
//	}
//	
//	
//	
//	
//	
//}
