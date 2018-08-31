package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Boss extends Actor{
	
	// Constructors
	
	public Boss(){
		super();
	}
	
	// Relationships

	private Collection<Center> centers;
	
	@Valid
	@NotNull
	@OneToMany(mappedBy = "boss")
	public Collection<Center> getCenters() {
		return centers;
	}

	public void setCenters(Collection<Center> centers) {
		this.centers = centers;
	}
}
