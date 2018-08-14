package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Request extends DomainEntity {

	public Request() {
		super();
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
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}

	@NotBlank
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getFinishMoment() {
		return finishMoment;
	}

	public void setFinishMoment(Date finishMoment) {
		this.finishMoment = finishMoment;
	}

	@NotNull
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	// relaciones
	private Handyworker handyworker;
	private User user;
	private Antenna antenna;
	private Handyworker requestHandyworker;

	@Valid
	@ManyToOne(optional = true)
	public Handyworker getHandyworker() {
		return handyworker;
	}

	public void setHandyworker(Handyworker handyworker) {
		this.handyworker = handyworker;
	}

	@Valid
	@ManyToOne(optional = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Valid
	@OneToOne
	public Antenna getAntenna() {
		return antenna;
	}

	public void setAntenna(Antenna antenna) {
		this.antenna = antenna;
	}

	@Valid
	@ManyToOne(optional = true)
	public Handyworker getRequestHandyworker() {
		return requestHandyworker;
	}

	public void setRequestHandyworker(Handyworker requestHandyworker) {
		this.requestHandyworker = requestHandyworker;
	}
}
