package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class User extends Actor{

	// Constructors
	
	public User(){
		super();
	}
	
	// Relationships
	private Comment comment;
	private Subscription subscription;
	private Antenna antenna;
	
	@Valid
	@OneToMany(mappedBy="user")
	public Comment getComment() {
		return comment;
	}
	
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	
	@Valid
	@OneToMany(mappedBy="user")
	public Subscription getSubscription() {
		return subscription;
	}
	
	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}
	
	@Valid
	@OneToMany(mappedBy="user")
	public Antenna getAntenna() {
		return antenna;
	}
	public void setAntenna(Antenna antenna) {
		this.antenna = antenna;
	}
}
