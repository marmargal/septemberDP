package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = {@Index(columnList = "hike_id,taboo") })
public class Advertisement extends DomainEntity{

	//Constructor
	
	public Advertisement(){
		super();
	}
	
	//Attributes
	
	private String title;
	private String banner;
	private String targetPage;
	private CreditCard creditCard;
	private Integer activeDays;
	private boolean taboo;
	
	@NotBlank
	@SafeHtml
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@URL
	@SafeHtml
	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	@URL
	@SafeHtml
	public String getTargetPage() {
		return targetPage;
	}

	public void setTargetPage(String targetPage) {
		this.targetPage = targetPage;
	}

	@Valid
	@NotNull
	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	@NotNull
	public Integer getActiveDays() {
		return activeDays;
	}

	public void setActiveDays(Integer activeDays) {
		this.activeDays = activeDays;
	}
	
	public boolean isTaboo() {
		return taboo;
	}

	public void setTaboo(boolean taboo) {
		this.taboo = taboo;
	}
	
	//Relationships
	
	private Hike hike;

	@Valid
	@ManyToOne
	public Hike getHike() {
		return hike;
	}

	public void setHike(Hike hike) {
		this.hike = hike;
	}
}
