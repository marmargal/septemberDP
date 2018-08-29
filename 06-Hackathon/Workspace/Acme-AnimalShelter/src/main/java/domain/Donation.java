package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Donation extends DomainEntity{
	
	// Constructors
	
	private Double cuantity;
	private Integer name;
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

	@NotNull
	public Integer getName() {
		return name;
	}

	public void setName(Integer name) {
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
	
	@Valid
	@ManyToOne(optional=false)
	public Voluntary getVoluntary() {
		return voluntary;
	}

	public void setVoluntary(Voluntary voluntary) {
		this.voluntary = voluntary;
	}

	@Valid
	@ManyToOne(optional=false)
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
}
