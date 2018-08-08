package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Agent extends Actor {

	public Agent() {
		// TODO Auto-generated constructor stub
	}

	// relaciones
	private Collection<Banner> banners;

	@OneToMany(mappedBy="agent")
	@Valid
	public Collection<Banner> getBanners() {
		return banners;
	}

	public void setBanners(Collection<Banner> banners) {
		this.banners = banners;
	}
}
