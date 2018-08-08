package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Subscription extends DomainEntity{

	// Constructors
	public Subscription(){
		super();
	}
	
	// Attributes
	private Date startDate;
	private Date endDate;
	private CreditCard creditCard;
	private String keyCode;
	
	@Past
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@NotNull
	public CreditCard getCreditCard() {
		return creditCard;
	}
	
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	
	// Relationships
	private User user;
	private Platform platform;
	
	@Valid
	@ManyToOne(optional=true)
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	@Valid
	@OneToOne
	public Platform getPlatform() {
		return platform;
	}
	
	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

	@Column(unique = true)
	@Pattern(regexp = "[0-9]{6}-[A-Z]{4}[0-9]{2}")
	@NotBlank
	public String getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(String keyCode) {
		this.keyCode = keyCode;
	}
	
	
}