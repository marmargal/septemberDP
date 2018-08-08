package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Request extends DomainEntity {

	public Request() {
		// TODO Auto-generated constructor stub
	}

	private CreditCard creditCard;
	private Date moment;
	private String description;
	private Date finishMoment;
	private String result;

	@NotNull
	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	@Past
	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}

	@NotNull
	@NotBlank
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@NotNull
	public Date getFinishMoment() {
		return finishMoment;
	}

	public void setFinishMoment(Date finishMoment) {
		this.finishMoment = finishMoment;
	}

	@NotNull
	@NotBlank
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	// relaciones
	private Handyworker handyworker;
	private User user;

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Handyworker getHandyworker() {
		return handyworker;
	}

	public void setHandyworker(Handyworker handyworker) {
		this.handyworker = handyworker;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
