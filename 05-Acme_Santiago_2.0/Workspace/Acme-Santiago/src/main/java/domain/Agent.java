package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Agent extends Actor{

	//Constructor
	
	public Agent(){
		super();
	}
	
	// Relationships
	
	private Collection<Advertisement> advertisements;
	
	@Valid
	@OneToMany
	public Collection<Advertisement> getAdvertisements() {
		return advertisements;
	}

	public void setAdvertisements(Collection<Advertisement> advertisements) {
		this.advertisements = advertisements;
	}

}
