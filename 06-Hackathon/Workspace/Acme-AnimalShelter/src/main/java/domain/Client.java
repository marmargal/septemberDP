package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = {@Index(columnList = "ban") })
public class Client extends Actor{
	
	// Constructors
	
	public Client(){
		super();
	}
	
	private boolean ban;

	public boolean isBan() {
		return ban;
	}

	public void setBan(boolean ban) {
		this.ban = ban;
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
