package domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class Application extends DomainEntity{

	
	//Constructors
	
	public Application(){
		super();
	}
	
	// Attributes

	private String ticker;
	private Date openedMoment;
	private Date closedMoment;
	private CreditCard creditCard;
	private Double statistics;

	@Column(unique = true)
	@Pattern(regexp = "[0-9]{6}-[A-Z]{4}[0-9]{2}")
	@NotBlank
	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	@Past
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getOpenedMoment() {
		return openedMoment;
	}

	public void setOpenedMoment(Date openedMoment) {
		this.openedMoment = openedMoment;
	}

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
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
	
	// Relationships
	
	private Officer officer;
	private Immigrant immigrant;
	private PersonalSection personalSection;
	private List<ContactSection> contactSection;
	private List<WorkSection> workSection;
	private List<SocialSection> socialSection;
	private List<EducationSection> educationSection;
	private List<Question> question;
	private Visa visa;

	@Valid
	@ManyToOne(optional=true)
	public Officer getOfficer() {
		return officer;
	}

	public void setOfficer(Officer officer) {
		this.officer = officer;
	}
	
	
	@Valid
	@ManyToOne(optional=false)
	public Immigrant getImmigrant() {
		return immigrant;
	}

	public void setImmigrant(Immigrant immigrant) {
		this.immigrant = immigrant;
	}
	
	
	
	@Valid
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	public PersonalSection getPersonalSection() {
		return personalSection;
	}

	public void setPersonalSection(PersonalSection personalSection) {
		this.personalSection = personalSection;
	}

	@Valid
	@OneToMany(cascade = CascadeType.ALL)
	public List<ContactSection> getContactSection() {
		return contactSection;
	}

	public void setContactSection(List<ContactSection> contactSection) {
		this.contactSection = contactSection;
	}

	@Valid
	@OneToMany(cascade = CascadeType.ALL)
	public List<WorkSection> getWorkSection() {
		return workSection;
	}

	public void setWorkSection(List<WorkSection> workSection) {
		this.workSection = workSection;
	}

	@Valid
	@OneToMany(cascade = CascadeType.ALL)
	public List<SocialSection> getSocialSection() {
		return socialSection;
	}

	public void setSocialSection(List<SocialSection> socialSection) {
		this.socialSection = socialSection;
	}

	@Valid
	@OneToMany(cascade = CascadeType.ALL)
	public List<EducationSection> getEducationSection() {
		return educationSection;
	}

	public void setEducationSection(List<EducationSection> educationSection) {
		this.educationSection = educationSection;
	}

	@Valid
	@OneToMany(mappedBy="application")
	public List<Question> getQuestion() {
		return question;
	}

	public void setQuestion(List<Question> question) {
		this.question = question;
	}

	@Valid
	@ManyToOne(optional=true)
	public Visa getVisa() {
		return visa;
	}

	public void setVisa(Visa visa) {
		this.visa = visa;
	}

	

	
	
	
}
