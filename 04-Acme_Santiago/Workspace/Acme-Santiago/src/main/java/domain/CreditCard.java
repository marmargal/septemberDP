package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

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
	private int expirationMonth;
	private int expirationYear;
	private int cvv;
	private boolean expired;

	@NotBlank
	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	@NotBlank
	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	@CreditCardNumber
	@Pattern(regexp = "^\\d{4}\\s?\\d{4}\\s?\\d{4}\\s?\\d{4}$")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	@Range(min = 1, max = 12)
	@NotNull
	public int getExpirationMonth() {
		return expirationMonth;
	}

	public void setExpirationMonth(int expirationMonth) {
		this.expirationMonth = expirationMonth;
	}

	@Range(min = 18, max = 99)
	@NotNull
	public int getExpirationYear() {
		return expirationYear;
	}

	public void setExpirationYear(int expirationYear) {
		this.expirationYear = expirationYear;
	}

	

	@Range(min = 100, max = 999)
	@NotNull
	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public boolean getExpired() {
		boolean res= false;
		Date moment=new Date(System.currentTimeMillis());
		int year = moment.getYear();
		int month = moment.getMonth();
		
		if(this.getExpirationYear()<= year){
			if(this.getExpirationMonth()>=month){
				res= true;
			}
		}
		return res;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}
	

	

}
