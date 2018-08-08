package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Handyworker extends Actor {

	// Constructors
	public Handyworker() {
		// TODO Auto-generated constructor stub
	}

	// relaciones
	Collection<Request> requests;

	@Valid
	@OneToMany(mappedBy = "handyworker")
	public Collection<Request> getRequests() {
		return requests;
	}

	public void setRequests(Collection<Request> requests) {
		this.requests = requests;
	}
}
