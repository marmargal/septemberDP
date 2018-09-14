package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;

@Embeddable
@Access(AccessType.PROPERTY)
public class CreditCard {

	// Constructors
	
	public CreditCard(){
		super();
	}
	
	// Attributes

	private String holderName;
	private String brandName;
	private String number;
	private Integer expirationMonth;
	private Integer expirationYear;
	private Integer cvv;

	@NotBlank
	@SafeHtml
	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	@NotBlank
	@SafeHtml
	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	@CreditCardNumber
	@Pattern(regexp = "^\\d{4}\\s?\\d{4}\\s?\\d{4}\\s?\\d{4}$")
	@SafeHtml
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	@Range(min = 1, max = 12)
	@NotNull
	public Integer getExpirationMonth() {
		return expirationMonth;
	}

	public void setExpirationMonth(Integer expirationMonth) {
		this.expirationMonth = expirationMonth;
	}

	@Range(min = 18, max = 99)
	@NotNull
	public Integer getExpirationYear() {
		return expirationYear;
	}

	public void setExpirationYear(Integer expirationYear) {
		this.expirationYear = expirationYear;
	}

	

	@Range(min = 100, max = 999)
//	@NotNull
	public Integer getCvv() {
		return cvv;
	}

	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}
	

	

}
