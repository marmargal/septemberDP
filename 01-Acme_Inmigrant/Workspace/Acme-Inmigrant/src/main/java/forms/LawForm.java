package forms;

import java.util.Date;
import java.util.List;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;

import domain.Law;
import domain.Requirement;

public class LawForm {
	// Constructors

	public LawForm() {
		super();
	}

	// Attributes

	private int id;
	private String title;
	private String text;
	private Date enactmentDate;
	private Date abrogationTime;

	private List<Requirement> requirement;
	private List<Law> laws;
	private Law lawParent;

	@NotBlank
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@NotBlank
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Past
	public Date getEnactmentDate() {
		return enactmentDate;
	}

	public void setEnactmentDate(Date enactmentDate) {
		this.enactmentDate = enactmentDate;
	}

	@Past
	public Date getAbrogationTime() {
		return abrogationTime;
	}

	public void setAbrogationTime(Date abrogationTime) {
		this.abrogationTime = abrogationTime;
	}

	@Valid
	@OneToMany
	public List<Requirement> getRequirement() {
		return requirement;
	}

	public void setRequirement(List<Requirement> requirement) {
		this.requirement = requirement;
	}

	@Valid
	@OneToMany
	public List<Law> getLaws() {
		return laws;
	}

	public void setLaws(List<Law> laws) {
		this.laws = laws;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Valid
	@ManyToOne(optional = true)
	public Law getLawParent() {
		return lawParent;
	}

	public void setLawParent(Law lawParent) {
		this.lawParent = lawParent;
	}
	
}
