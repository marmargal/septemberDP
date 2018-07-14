package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Application {

	// Attributes

	private String ticker;
	private Date openedMoment;
	private Date closedMoment;
	private CreditCard creditCard;
	private Double statistics;

	// TODO: Mirar pattern Ticker
	@NotBlank
	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	@Past
	public Date getOpenedMoment() {
		return openedMoment;
	}

	public void setOpenedMoment(Date openedMoment) {
		this.openedMoment = openedMoment;
	}

	@Past
	public Date getClosedMoment() {
		return closedMoment;
	}

	public void setClosedMoment(Date closedMoment) {
		this.closedMoment = closedMoment;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public Double getStatistics() {
		return statistics;
	}

	public void setStatistics(Double statistics) {
		this.statistics = statistics;
	}
}
