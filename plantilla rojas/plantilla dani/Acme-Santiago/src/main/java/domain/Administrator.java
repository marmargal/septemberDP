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
	
	private Collection<Brid> brids;
	
	@Valid
	@OneToMany(mappedBy="administrator")
	public Collection<Brid> getBrids() {
		return brids;
	}

	public void setBrids(Collection<Brid> brids) {
		this.brids = brids;
	}
}
/*select t.administrator from Brid t where (select count(w) from Brid w where w.administrator.id = t.administrator.id and w.approved = false)
>= all(select (select count(w2) from Brid w2 where w2.administrator.id = t2.administrator.id and w2.approved = false) from Brid t2)*/