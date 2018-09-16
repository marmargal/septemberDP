package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Administrator extends Actor{
	
	// Constructors
	
	public Administrator(){
		super();
	}
	
	private Collection<Cambio> cambios;
	
	@Valid
	@OneToMany(mappedBy="administrator")
	public Collection<Cambio> getCambios() {
		return cambios;
	}

	public void setCambios(Collection<Cambio> cambios) {
		this.cambios = cambios;
	}
}
