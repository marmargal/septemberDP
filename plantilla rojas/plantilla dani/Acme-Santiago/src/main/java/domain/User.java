package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class User extends Actor {

	// Constructors

	public User() {
		super();
	}

	// Relationships
	private Collection<Comment> comments;
	private Collection<Chirp> chirps;
	private Collection<Route> routes;
	private Collection<User> following;
	private Collection<User> followers;
	private Collection<Compostela> compostelas;
	private Collection<Cambio> cambios;

	@Valid
	@OneToMany(mappedBy = "user")
	public Collection<Comment> getComments() {
		return comments;
	}

	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}

	@Valid
	@OneToMany(mappedBy = "user")
	public Collection<Chirp> getChirps() {
		return chirps;
	}

	public void setChirps(Collection<Chirp> chirps) {
		this.chirps = chirps;
	}

	@Valid
	@OneToMany(mappedBy = "user")
	public Collection<Route> getRoutes() {
		return routes;
	}

	public void setRoutes(Collection<Route> routes) {
		this.routes = routes;
	}
	
	@Valid
	@ManyToMany
	public Collection<User> getFollowing() {
		return following;
	}

	public void setFollowing(Collection<User> following) {
		this.following = following;
	}

	@Valid
	@ManyToMany(mappedBy = "following")
	public Collection<User> getFollowers() {
		return followers;
	}

	public void setFollowers(Collection<User> followers) {
		this.followers = followers;
	}
	
	@Valid
	@OneToMany(mappedBy="user")
	public Collection<Compostela> getCompostelas() {
		return compostelas;
	}

	public void setCompostelas(Collection<Compostela> compostelas) {
		this.compostelas = compostelas;
	}

	@Valid
	@OneToMany(mappedBy="user")
	public Collection<Cambio> getCambios() {
		return cambios;
	}

	public void setCambios(Collection<Cambio> cambios) {
		this.cambios = cambios;
	}
	

}