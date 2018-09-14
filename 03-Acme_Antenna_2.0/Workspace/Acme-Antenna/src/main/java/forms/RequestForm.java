package forms;



import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import domain.Antenna;
import domain.CreditCard;
import domain.Handyworker;
import domain.Request;


public class RequestForm {

	// Constructor
	public RequestForm(){
		super();
	}
	
	// Atributes
	private int id;
	private CreditCard creditCard;
	private String description;
	private String result;
	private Antenna antenna;
	private Handyworker requestHandyworker;
	private Request request;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getResult() {
		return result;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
	
	@NotNull
	@Valid
	public Antenna getAntenna() {
		return antenna;
	}
	
	public void setAntenna(Antenna antenna) {
		this.antenna = antenna;
	}
	
	@NotNull
	@Valid
	public Handyworker getRequestHandyworker() {
		return requestHandyworker;
	}
	
	public void setRequestHandyworker(Handyworker requestHandyworker) {
		this.requestHandyworker = requestHandyworker;
	}
	
	public Request getRequest() {
		return request;
	}
	
	public void setRequest(Request request) {
		this.request = request;
	}
	
	
}
