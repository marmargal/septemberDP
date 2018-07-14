package domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
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
	
	// Relationships
	
	private PersonalSection personalSection;
	private List<ContactSection> contacSection;
	private List<WorkSection> workSection;
	private List<SocialSection> socialSection;
	private List<EducationSection> educationSection;
	private List<Question> question;

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
	public List<ContactSection> getContacSection() {
		return contacSection;
	}

	public void setContacSection(List<ContactSection> contacSection) {
		this.contacSection = contacSection;
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
	@OneToMany
	public List<Question> getQuestion() {
		return question;
	}

	public void setQuestion(List<Question> question) {
		this.question = question;
	}
	
	
	
}
