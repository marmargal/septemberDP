package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@Access(AccessType.PROPERTY)
public class Administrator extends Actor {

	// Constructors

	public Administrator() {
		super();
	}

	private Collection<Decision> decisions;

	@OneToMany(mappedBy="administrator")
	public Collection<Decision> getDecisions() {
		return decisions;
	}

	public void setDecisions(Collection<Decision> decisions) {
		this.decisions = decisions;
	}

}
