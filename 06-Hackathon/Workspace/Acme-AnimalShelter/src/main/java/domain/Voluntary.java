package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

@Entity
@Access(AccessType.PROPERTY)
public class Voluntary extends Actor{
	
	// Constructors
	
	public Voluntary(){
		super();
	}
	

}
