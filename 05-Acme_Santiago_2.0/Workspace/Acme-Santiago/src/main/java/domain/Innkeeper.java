package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Innkeeper extends Actor {

	// Constructors

	public Innkeeper() {
		super();
	}

	// Relationships
	private Collection<Inn> inns;
	private Collection<Amenity> amenities;

	@Valid
	@OneToMany(mappedBy = "innkeeper")
	public Collection<Inn> getInns() {
		return inns;
	}

	public void setInns(Collection<Inn> inns) {
		this.inns = inns;
	}
	
	@Valid
	@OneToMany
	public Collection<Amenity> getAmenities() {
		return amenities;
	}

	public void setAmenities(Collection<Amenity> amenities) {
		this.amenities = amenities;
	}
}
