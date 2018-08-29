package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Client extends Actor{
	
	// Constructors
	
	public Client(){
		super();
	}
	
	// Relationships

	private Collection<Application> applications;
	
	@Valid
	@OneToMany(mappedBy = "client")
	public Collection<Application> getApplications() {
		return applications;
	}

	public void setApplications(Collection<Application> applications) {
		this.applications = applications;
	}
}
