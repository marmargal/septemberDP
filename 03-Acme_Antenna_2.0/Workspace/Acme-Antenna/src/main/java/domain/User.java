package domain;

import java.util.Collection;

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
	private Collection<Comment> comments;
	private Collection<Subscription> subscriptions;
	private Collection<Antenna> antennas;
	
	@Valid
	@OneToMany(mappedBy="user")
	public Collection<Comment> getComments() {
		return comments;
	}
	
	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}
	
	@Valid
	@OneToMany(mappedBy="user")
	public Collection<Subscription> getSubscriptions() {
		return subscriptions;
	}
	
	public void setSubscriptions(Collection<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}
	
	@Valid
	@OneToMany(mappedBy="user")
	public Collection<Antenna> getAntennas() {
		return antennas;
	}
	public void setAntennas(Collection<Antenna> antennas) {
		this.antennas = antennas;
	}
}
