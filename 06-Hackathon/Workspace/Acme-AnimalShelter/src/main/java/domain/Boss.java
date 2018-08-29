package domain;

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

	private Center center;
	
	@Valid
	@NotNull
	@OneToMany(mappedBy = "boss")
	public Center getCenter() {
		return center;
	}

	public void setCenter(Center center) {
		this.center = center;
	}
}
