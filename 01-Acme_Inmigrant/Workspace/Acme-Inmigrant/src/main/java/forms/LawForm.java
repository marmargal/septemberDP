package forms;

import java.util.Collection;
import java.util.Date;
import java.util.List;

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

	private String title;
	private String text;
	private Date enactmentDate;
	private Date abrogationTime;

	private Collection<Requirement> requirement;
	private List<Law> law;

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
	public Collection<Requirement> getRequirement() {
		return requirement;
	}

	public void setRequirement(Collection<Requirement> collection) {
		this.requirement = collection;
	}

	@Valid
	@OneToMany
	public List<Law> getLaw() {
		return law;
	}

	public void setLaw(List<Law> law) {
		this.law = law;
	}
}
