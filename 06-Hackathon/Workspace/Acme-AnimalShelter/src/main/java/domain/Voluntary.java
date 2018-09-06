package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Voluntary extends Actor{
	
	// Constructors
	
	public Voluntary(){
		super();
	}
	
	private boolean ban;
	private Collection<Notice> notice;

	public boolean isBan() {
		return ban;
	}

	public void setBan(boolean ban) {
		this.ban = ban;
	}
	@Valid
	@OneToMany(mappedBy = "voluntary")
	public Collection<Notice> getNotice() {
		return notice;
	}

	public void setNotice(Collection<Notice> notice) {
		this.notice = notice;
	}
	
	
	

}
