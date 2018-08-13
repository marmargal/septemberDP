package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Inkeeper extends Actor {

	// Constructors

	public Inkeeper() {
		super();
	}

	// Relationships
	private Collection<Inn> inns;

	@Valid
	@OneToMany(mappedBy = "inkeeper")
	public Collection<Inn> getInns() {
		return inns;
	}

	public void setInns(Collection<Inn> inns) {
		this.inns = inns;
	}
}
