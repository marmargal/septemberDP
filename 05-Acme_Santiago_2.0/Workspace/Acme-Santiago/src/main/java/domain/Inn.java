package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Inn extends DomainEntity {

	// Constructors
	public Inn() {
		super();
	}

	// Attributes
	private String name;
	private String badge;
	private Collection<String> address;
	private String phoneNumber;
	private String email;
	private String webSite;
	private CreditCard creditCard;

	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotBlank
	@URL
	public String getBadge() {
		return badge;
	}

	public void setBadge(String badge) {
		this.badge = badge;
	}

	@ElementCollection
	@NotNull
	public Collection<String> getAddress() {
		return address;
	}

	public void setAddress(Collection<String> address) {
		this.address = address;
	}

	// falta pattern
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Email
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@URL
	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	@NotNull
	@Valid
	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	// Relationships
	private Innkeeper innkeeper;
	private Collection<Registry> registries;

	@Valid
	@ManyToOne
	public Innkeeper getInnkeeper() {
		return innkeeper;
	}

	public void setInnkeeper(Innkeeper innkeeper) {
		this.innkeeper = innkeeper;
	}

	@Valid
	@OneToMany(mappedBy = "inn", cascade = CascadeType.ALL)
	public Collection<Registry> getRegistries() {
		return registries;
	}

	public void setRegistries(Collection<Registry> registries) {
		this.registries = registries;
	}

}