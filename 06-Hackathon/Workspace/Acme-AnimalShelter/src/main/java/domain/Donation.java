package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Donation extends DomainEntity{
	
	// Constructors
	
	private Double cuantity;
	private String name;
	private CreditCard creditCard;
	
	public Donation(){
		super();
	}

	@NotNull
	public Double getCuantity() {
		return cuantity;
	}

	public void setCuantity(Double cuantity) {
		this.cuantity = cuantity;
	}

	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	
	// Relationships
	
	private Voluntary voluntary;
	private Client client;
	private Event event;
	
	@Valid
	@ManyToOne(optional=true)
	public Voluntary getVoluntary() {
		return voluntary;
	}

	public void setVoluntary(Voluntary voluntary) {
		this.voluntary = voluntary;
	}

	@Valid
	@ManyToOne(optional=true)
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	@Valid
	@ManyToOne
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	
}
